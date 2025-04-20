import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

import swiftbot.Button;
import swiftbot.ImageSize;
import swiftbot.SwiftBotAPI;

/*  
 * saved images are overwriting. I want each image captured saved as different images. (e.g. curious.jpg(1), curious.jpg(2)...)
 * solved by adding timestamps: easier for debugging
 * 
 * in termination the program still runs while termination is also running
 * solved by putting if button presses after each movement code block
 * ###################################
 * 
 * 30 cm gap is not quite 30 cm in real life.. should I make it greater? yes the main thing is the bots movement
 * wandering goes weird (like a turtle). thats fine though
 * 
 * add additional func as termination color in the report - turquoise
 * 
 * test: if any other button is pressed, nothing should be displayed: SUCCESS
 * 
 * test qr code detection without check variable 
 * 
 */


public class Detect_Object {

	private static String valid_modes[] = {"Curious SwiftBot", "Scaredy SwiftBot", "Dubious SwiftBot"}; 
	public static String selected_mode = "";
	private static String mode_run = "";
	private static int speed = 40;
	private static int object_num = 0;
	
	private static boolean press = false;  // variable to check termination
	private static boolean buttonpressX = false;
	private static boolean buttonpressY = false;
	
	//	static DetectQR DQ = new DetectQR();

	private static long start_time = System.currentTimeMillis(); 
	private static long end_time;
	private static long total_time;

	private static String image_path = "/data/home/pi/Detect_Object/";
	private static String log_path = "/data/home/pi/Detect_Object/";

	static SwiftBotAPI swiftBot;

	public static void main(String[] args) throws InterruptedException {
		
		int retry_attempts = 0; // local variable
		
		try {
			swiftBot = new SwiftBotAPI();
		} catch (Exception e) {

			System.out.println("\nI2C disabled!");
			System.out.println("Run the following command:");
			System.out.println("sudo raspi-config nonint do_i2c 0\n");
			System.exit(5);
		}
		
		swiftBot.enableButton(Button.X, () -> { press = true;
												swiftBot.stopMove(); // already have this down too
												System.out.println("\nButton X Pressed.");
												terminate_program();
												});
		
		greeting();
		get_mode();  // get mode from the user either by scanning a qr or manual entry

		// check mode validity:
		System.out.println("Checking if input matches a mode...\n");
		Thread.sleep(1000);
		
		while (retry_attempts < 5) {

			boolean found = false;
			for (String mode : valid_modes) {  // check if selected mode is in the valid modes array by going through each array element
				if (mode.equals(selected_mode)) { // for manual entry, Case-insensitive match can be added as additional func. mode.equalsIgnoreCase(selected_mode)
					found = true;
					break;
				}
			}

			if (found == true) {   // if its a valid mode
				System.out.println(selected_mode + " is a valid mode.");  // testing

				mode_run = selected_mode;

				if (selected_mode.equals("Curious SwiftBot")) {

					System.out.println("Executing Curious SwiftBot mode...");
					curious_swiftbot(); // loop back to itself in the method

				} else if (selected_mode.equals("Scaredy SwiftBot")) {

					System.out.println("Executing Scaredy SwiftBot mode..."); 
					scaredy_swiftbot(); // loop back to itself in the method

				} else { // Dubious mode

					System.out.println("Executing Dubious SwiftBot mode...");
					System.out.println("\nRandomly selecting a mode...");
					Thread.sleep(1000);
					select_random_mode();
				}

				break;

			} else {  // if invalid mode

				retry_attempts ++;
				int n = 5 - retry_attempts;
				System.out.println("\nInvalid mode. You have " + n + " attempts left.\n");

				if (retry_attempts == 5) { 

					System.out.println("All 5 attempts are invalid. Terimating Program...");
					System.exit(0); // terminate program

				}

				get_mode(); // if retry_attempts != 5 get a mode again 
			}
		}  // end of retry_attempts loop
	}



	public static void get_mode() {

		Scanner reader = new Scanner(System.in);

		System.out.println("Please choose to scan QR code or enter the SwiftBot Mode manually. "
				+ "You have 5 attempts in total to specify a mode. ");

		System.out.println("\nPress 1 to scan a QR code\r\n"
				+ "Press 2 to enter the mode manually\n");

		boolean choice = true;
		while (choice) {

			String ans = reader.next();

			switch (ans) {
			case "1":
				QRCodeDetection();
				choice = false;
				break;

			case "2":
				System.out.println("Enter one of the following modes (must be exactly the same):\r\n"
						+ "Curious SwiftBot, Scaredy SwiftBot, Dubious SwiftBot.\n");
				reader.nextLine();
				selected_mode = reader.nextLine();

				choice = false;
				break;

			default:
				System.out.println("Error: Invalid option\r\n"
						+ "Press 1 to scan a QR code, Press 2 to enter manually:");  
				break;
			}
		}
	}



	public static void select_random_mode() { // random mode selection for Dubious SwiftBot mode

		Random rand = new Random();
		int random_num = rand.nextInt(0,2); // will generate either 0 or 1

		if (random_num == 0) {

			System.out.println("Executing Curious SwiftBot mode...");
			curious_swiftbot();

		} else {

			System.out.println("Executing Scaredy SwiftBot mode...");
			scaredy_swiftbot();
		}	
	}



	public static void wandering() { 
		double n = 50.0;  // Threshold for scaredy mode

		int[] blue = {0, 0, 255}; // blue color
		swiftBot.fillUnderlights(blue);

		if (selected_mode.equals("Curious SwiftBot")) {
			n = 80.0;
		}

		Random rand = new Random();

		while (!press) { // Exit when press is true
			if (swiftBot.useUltrasound() <= n || press) {
				System.out.println("Obstacle detected! Stopping.");
				swiftBot.stopMove();
				return;
			}

			speed = 40;
			System.out.println("\nWandering... (Speed: 40)\nUnderlights: Blue\n");

			// Move in smaller steps to check distance and press frequently
			// if distance is smaller or equal to the required gap (Object detected) OR 
			// if press is true, stop and return function(exit).
			for (int i = 0; i < 50; i++) { 
				if (swiftBot.useUltrasound() <= n || press) { 
					System.out.println("\nObject Detected while wandering! Stopping."); // delete this and put in curious and scaredy
					swiftBot.stopMove();
					swiftBot.disableUnderlights();
					return;
				}
				swiftBot.move(speed, speed, 100);  // loop 50s * 100 = 5 seconds
			}

			if (press) return;  // Check X button press again before sleeping

			System.out.println("\nWaiting for 1 second after 5 seconds");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (press) return;  // Check X button press again before turning

			System.out.println("\nChanging direction randomly...");
			int random_turn = rand.nextInt(2); // 0 or 1
			int random_degree = rand.nextInt(250, 2000);

			if (random_turn == 0) {  
				swiftBot.move(speed, 0, random_degree); // Turn right
			} else {  
				swiftBot.move(0, speed, random_degree); // Turn left
			}
		}
	}


	public static void curious_swiftbot() {  // after finishing 1 round (object detection and image taken) should I wait for 5s like in scaredy mode? check
		int[] green = {0, 255, 0}; // Green
		Random rand = new Random();

		while (!press) {  
			while (swiftBot.useUltrasound() > 80.0 && !press) { // Moves until an object is within 80 cm
				wandering();
				if (press) return;  // Exit immediately
			}

			if (press) return;

			try {
				swiftBot.stopMove();
				speed = 60;
				object_num++;

				double dist_to_obj = swiftBot.useUltrasound();

				// if object is within 30 cm
				if (dist_to_obj < 45.0) {  // adjusted the 30 cm to 45.0 to make the bot go for 30 cm in real life
					swiftBot.fillUnderlights(green);
					while (swiftBot.useUltrasound() < 44.5 && !press) {
						swiftBot.startMove(-speed, -speed);
					}
					swiftBot.disableUnderlights();
					Thread.sleep(1000);

				// if object is outside 30 cm 
				} else if (dist_to_obj > 45.0) { 
					swiftBot.fillUnderlights(green);
					while (swiftBot.useUltrasound() > 45.5 && !press) {
						swiftBot.startMove(speed, speed);
					}
					swiftBot.disableUnderlights();
					Thread.sleep(1000);

				} else { // if the object is exactly at 30 cm distance
					for (int i = 0; i < 3; i++) {
						if (press) return;  // Stop blinking if pressed
						swiftBot.fillUnderlights(green); 
						Thread.sleep(1000);

						if (press) return;
						swiftBot.disableUnderlights();
						Thread.sleep(1000);
					}
				}

				swiftBot.stopMove();
				Thread.sleep(1000);

				if (press) return;

				System.out.println("Taking Picture...");
				Thread.sleep(1000);
				take_image_save("curious");

				if (press) return;

				// randomly change direction after taking picture
				int random_num = rand.nextInt(2);  // 0 or 1
				int random_time = rand.nextInt(500, 2000);

				if (random_num == 0) { 
					swiftBot.move(speed, 0, random_time);
				} else { 
					swiftBot.move(0, speed, random_time);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public static void scaredy_swiftbot() { 
		while (!press) {  // Continuously check if press == false

			// Constantly check distance while wandering
			while (!press && swiftBot.useUltrasound() > 50.0) {   
				wandering();  
			}

			if (press) return;

			try {
				swiftBot.stopMove();
				System.out.println("SwiftBot Stopped!");
				Thread.sleep(1000);
				
				// Blink underlights in red
				int[] red = {255, 0, 0};
				for (int i = 0; i < 3; i++) { 
					swiftBot.fillUnderlights(red);
					Thread.sleep(300);
					swiftBot.disableUnderlights();
					Thread.sleep(300);
					if (press) return;
				} // moved blinking before saving image bc makes more sense. change in requirements

				speed = 60;
				object_num++;

				System.out.println("Taking Picture...");
				Thread.sleep(1000);
				take_image_save("scaredy");  // takes image and displays image saved

				if (press) return;

				// Move back 15 cm
				swiftBot.move(-speed, -speed, 500); 
				Thread.sleep(1000);
				if (press) return;

				// Turn around (approx 180 degrees)
				swiftBot.move(speed, 0, 1870); // tested for 180 degrees
				Thread.sleep(1000);
				if (press) return;

				// Move away for 3 seconds
				swiftBot.move(speed, speed, 3000);
				if (press) return;

				// Wait for 5 seconds ??
				Thread.sleep(5000);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	public static void take_image_save(String img_name) {
	    try {
	        BufferedImage image = swiftBot.takeStill(ImageSize.SQUARE_240x240);

	        if (image == null) {
	            System.out.println("ERROR: Image is null");
	        } else {
	            // Format: ddMMyyyy_HHmmss (Day/Month/Year_HourMinuteSecond)
	            String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
	            String imgFilename = img_name + "_" + timestamp + ".jpg"; // Append timestamp

	            // Save the image
	            ImageIO.write(image, "jpg", new File(image_path + imgFilename));

	            System.out.println("SUCCESS: Image Saved as " + imgFilename);
	            Thread.sleep(1000);
	        }
	    } catch (Exception e) {
	        System.out.println("\nCamera not enabled!");
	        System.out.println("Try running the following command: ");
	        System.out.println("sudo raspi-config nonint do_camera 0\n");
	        System.out.println("Then reboot using the following command: ");
	        System.out.println("sudo reboot\n");
	        System.exit(5);
	    }
	}



	public static void QRCodeDetection() {

		System.out.println("Starting 15s timer to scan a QR code");
		boolean check = true;  // maybe no need for this. make while true and delete check? ask gpt
		// try without this
		
		try {
			while (check) {  // will loop back and start 15s timer each time no qr code has been scanned. 

				long startTime = System.currentTimeMillis();
				long endTime = startTime + 15000; // 15 seconds in milliseconds

				while (System.currentTimeMillis() < endTime) {

					BufferedImage img = swiftBot.getQRImage();
					selected_mode = swiftBot.decodeQRImage(img);

					if (selected_mode.isEmpty()) {
						System.out.println(
								"No QR Code was found. Try adjusting the distance of the SwiftBot's Camera from the QR code, or try another.");
					} else {
						System.out.println("SUCCESS: QR code found"); 
						System.out.println("Decoded message: " + selected_mode);
						check = false;
						break;
					}

					System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds");
					Thread.sleep(1000);
				}
				
				System.out.println("15 second is over...");
			}
		} catch (Exception e) {
			System.out.println("ERROR: Unable to scan for code.");
			e.printStackTrace();
			System.exit(5);
		}
	}

	
	
////////////////////////////// program termination and saving logs /////////////////////////////

	public static void terminate_program() {

		int[] turquoise = {48, 213, 200}; // termination color
		swiftBot.fillUnderlights(turquoise);
		
		try {
			
			// calculate total time of execution, start_time was defined in the very beginning
			end_time = System.currentTimeMillis();
			total_time = end_time - start_time; 

			System.out.println("Program Terminated. Would you like to view the execution log?"
					+ "\nPress button Y for 'Yes', and X for 'No' on the SwiftBot.");
			
			swiftBot.disableAllButtons();
            
			swiftBot.enableButton(Button.X, () -> {buttonpressX = true;
												   swiftBot.disableAllButtons();});
			
			swiftBot.enableButton(Button.Y, () -> {buttonpressY = true;
												   swiftBot.disableAllButtons();});


			// put a timer here -> once a button is pressed display messages will appear immediately
			Thread.sleep(4000); // give time for the user to press a button

			if (buttonpressX == true) {
				System.out.println("\nButton X is pressed.");
			    save_log();
			    
			} else if (buttonpressY == true) {

			    System.out.println("\nButton Y is pressed.");
			    save_log();
			    System.out.println();
			    display_log(); // display log file content (execution log)
			} else {
				System.out.println("\nNo button is pressed in time.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nProgram ended. Thank you for using SwiftBot!");		
		System.exit(0);
	}
	
	
	public static void save_log() {

	    try (FileWriter writer = new FileWriter(log_path + "execution_log.txt")) {
	        writer.write("Execution Log:\n");
	        writer.write("- Mode executed: " + mode_run + "\n");
	        writer.write("- Duration: " + (total_time / 1000.0) + " seconds\n");
	        writer.write("- Number of objects encountered: " + object_num + "\n");
	        writer.write("- Images saved at: " + image_path + "\n");
	        writer.write("- Log file saved at: " + log_path + "\n");

	        System.out.println("Log file saved at: " + log_path);

	    } catch (IOException e) {
	        System.out.println("Error writing log file: " + e.getMessage());
	    }
	}
	
	
	public static void display_log() {

	    try {
	        String content = new String(Files.readAllBytes(Paths.get(log_path + "execution_log.txt"))); // Read file content
	        System.out.println(content); // Print log content
	    } catch (IOException e) {
	        System.out.println("Error reading log file: " + e.getMessage());
	    }
	}

	

	public static void greeting() {

		System.out.println("________          __                 __        _____  __       __               __   ");
		System.out.println("\\______ \\   _____/  |_  ____   _____/  |_     /  _  \\|  |_    |__| ____   _____/  |_ ");
		System.out.println(" |  | |  \\_/ __ \\   __\\/ __ \\_/ ___\\   __\\   |  | |  |  _ \\   |  |/ __ \\_/ ___\\   __\\");
		System.out.println(" |  |_/   \\  ___/|  | \\  ___/\\  \\___|  |     |  |_|  | |_/ \\  |  \\  ___/\\  \\___|  |  ");
		System.out.println("/_______ / \\____ >__|  \\____ >\\____ >__|      \\_____/\\____/\\__|  |\\____ >\\____ >__|  ");
		System.out.println("                                                          \\______|                   ");
		System.out.println("------------------------------------");
		System.out.println(" Welcome to SwiftBot Control System");
		System.out.println("------------------------------------");
		System.out.println("SwiftBot will continuously scan its surroundings.  ");
		System.out.println("If an object is detected, it will react based on the selected mode.  ");
		System.out.println();
		System.out.println("Press the 'X' button on SwiftBot at any time to stop the program.");
		System.out.println("-------------------------------------------------------------------");
		System.out.println("\n");
	}

}
