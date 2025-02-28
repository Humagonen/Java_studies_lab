import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.spi.FileSystemProvider;
import java.sql.Time;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

import swiftbot.Button;
import swiftbot.ImageSize;
import swiftbot.SwiftBotAPI;

public class Detect_Object {

	private static String valid_modes[] = {"Curious SwiftBot", "Scaredy SwiftBot", "Dubious SwiftBot"}; 
	public static String selected_mode = "";
	private static String mode_run = "";
	private static int retry_attempts = 0;
	private static int speed = 40;
	private static int object_num = 0;
	private static boolean buttonpressX = false;
	private static boolean buttonpressY = false;

	private static boolean press = false;
//	static DetectQR DQ = new DetectQR();
	
	private static long start_time = System.currentTimeMillis(); 
	// get program init time for logging, not sure if this will get current time it might need to be in main. test it.

	private static String image_path = "/data/home/pi/";
	private static String log_path = "/data/home/pi/";



	static SwiftBotAPI swiftBot;

	public static void main(String[] args) throws InterruptedException {
						try {
							swiftBot = new SwiftBotAPI();
						} catch (Exception e) {
							
							System.out.println("\nI2C disabled!");
							System.out.println("Run the following command:");
							System.out.println("sudo raspi-config nonint do_i2c 0\n");
							System.exit(5);
						}

		
	
		// also for the logging, make a txt file and write on it in the end
		
		
		swiftBot.enableButton(Button.X, () -> { press = true;
												swiftBot.stopMove();
												System.out.println("Button X Pressed.");
												swiftBot.disableButton(Button.X);
												terminate_program(); });

		while (press == false) {
			
			greeting();

			get_mode();  // get mode from the user either by scanning a qr or manual entry

			System.out.println("Checking if input matches a mode...");
			// check validity:
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

//		swiftBot.stopMove();  // powers all motors off
//		swiftBot.disableAllButtons(); // disable all the buttons
//		terminate_program();
	
	}
								




	public static void get_mode() {

		Scanner reader = new Scanner(System.in);

		System.out.println("Please choose to scan QR code or enter the SwiftBot Mode manually. "
				+ "You have 5 attempts in total to specify a mode. ");
		System.out.println(
				"\nPress 1 to scan a QR code\r\n"
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



	public static void select_random_mode() { // random mode selection for dubious mode

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
		
		while (swiftBot.useUltrasound() > 100.0) {		
			speed = 40;
			// turn all leds to blue
			System.out.println("Wandering... (Speed:40)"
					+ "\nUnderlights: Blue");
	
			swiftBot.move(speed, speed, 5000);
	
			System.out.println("Waiting for 1 second after 5 seconds");
	
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			System.out.println("Changing direction randomly...");
	
			// 		randomly choose left or right and the degree to turn by adjusting time (random_degree)
			// 		20-90 degrees in speed 60 --> check with bot
			
			Random rand = new Random();
	
			int random_turn = rand.nextInt(0,2); // will generate either 0 or 1
			int random_degree = rand.nextInt(250, 2000);
	
			if (random_turn == 0) {  // turn right
	
				swiftBot.move(speed, 0, random_degree);
				
			} else {  // turn left
				
				swiftBot.move(0, speed, random_degree);
			}
		}
	}



	public static void curious_swiftbot() {

		while (swiftBot.useUltrasound() > 100.0) { // chose the detection distance as 100 cm - can be added as additional func
			wandering();
		}
		// while(true){
		try {

			// skip this while if its the first object that is detected, if not the first
			while (swiftBot.useUltrasound() > 100.0 && object_num != 0) {
				wandering();
			}
			
			swiftBot.stopMove();
			speed = 60;
			object_num ++;
			
			double dist_to_obj = swiftBot.useUltrasound();

			if (dist_to_obj < 30.0) {

				// go back until 30.0 cm is restored

				while (swiftBot.useUltrasound() != 30.0) {

					swiftBot.startMove(-speed, -speed);;

				}
				swiftBot.stopMove();


			} else if (dist_to_obj > 30.0) {

				// go forward until 30.0 cm is restored

				while (swiftBot.useUltrasound() != 30.0) {

					swiftBot.startMove(speed, speed);;

				}
				swiftBot.stopMove();

			} else { // dist_to_obj == 30.0

				// blink underlights in green

			}


			Thread.sleep(1000);

			System.out.println("Taking Picture...");
			Thread.sleep(1000);
			take_image_save("curious.jpg");
			
			// check again if there's an object
			double dist_to_obj2 = swiftBot.useUltrasound();
			// if the same object, and no new object is detected
			if (dist_to_obj2 == 30.0){
				// move in a slightly different direction (randomly choose between |20-90| degrees right or left)
				Random rand = new Random();
				int random_num = rand.nextInt(0,2);
				int random_time = rand.nextInt(250,2500);

				if (random_num == 0) { // if 0, turn right

					swiftBot.move(speed, 0, random_time);

				} else { // if 1, turn left
					swiftBot.move(0, speed, random_time);
				}
			}else if (dist_to_obj2 != 30.0 && dist_to_obj2 < 100.0){ // new object detected within that 30 cm
				// && dist_to_obj2 < 100.0 -> not sure if I need to add this
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// } end of while


	}



	public static void scaredy_swiftbot() {

		while (true) {

			while (swiftBot.useUltrasound() > 50.0) {   // while (distance_to_object > 50) 

				wandering();   // this will do all the steps at least once regardless of the distance to object at any time. FIX IT
				// do distance check constantly
			}

			try {
				swiftBot.stopMove();
				System.out.println("Object Detected. SwiftBot Stopped!");
				Thread.sleep(1000);

				speed = 60;
				object_num ++;

				System.out.println("Taking Picture...");
				Thread.sleep(1000);
				take_image_save("scaredy.jpg");  // displays image saved

				// turn and blink underlights in red

				// move back 15 cm
				swiftBot.move(-speed,-speed,500); // adjust time accordingly
				Thread.sleep(1000);

				// turn in the opposite direction
				swiftBot.move(speed,0,1500); 
				Thread.sleep(1000);

				// moving away from object for 3s and stops
				swiftBot.move(speed,speed,3000);

				// swiftbot wait for 5 seconds

				Thread.sleep(5000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	

	 // continuosly checks distance with ultrasound within 30 cm or 50 cm

	
	
	public static void get_distance() {
		try {
			double distance = swiftBot.useUltrasound();
//			System.out.println("Distance from front-facing obstacle: " + distance + " cm"); // might move this
//			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println("ERROR: Ultrasound Unsuccessful");
			System.exit(5);
		}
	}
	
	
	

	public static void take_image_save(String img_name) {  // in curious the parameter str will be "curious.jpg"

		try {
			
			BufferedImage image = swiftBot.takeStill(ImageSize.SQUARE_240x240);

			if (image == null) {
				System.out.println("ERROR: Image is null");
			} else {
				// Save the bwImage to a directory.
				ImageIO.write(image, "jpg", new File("/data/home/pi/" + img_name));  // each time its overwriting to the same image! 
				
				System.out.println("SUCCESS: Image Saved");
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



	public static void terminate_program() {

		// have to save each info to execution log in log_path

		try {
			
			swiftBot.stopMove();
			
			long current_time = System.currentTimeMillis();
			long total_time = current_time - start_time; // calculate total time of execution

			System.out.println("Program Terminated. Would you like to view the execution log?"
					+ "\nPress button Y for 'Yes', and X for 'No' on the SwiftBot.");

			swiftBot.disableAllButtons();

			swiftBot.enableButton(Button.X, () -> {buttonpressX = true;
												   swiftBot.disableAllButtons();});
			swiftBot.enableButton(Button.Y, () -> {buttonpressY = true;
												   swiftBot.disableAllButtons();});


			Thread.sleep(1000); // give 30 seconds for user to press a button


			if (buttonpressX == true) {

				System.out.println("Log file saved at: " + log_path);

			} else if (buttonpressY == true) {

				System.out.println("Execution log:");

				System.out.println("- Mode executed: " + mode_run);  // if dubious, we wont know which mode it actually ran. may have to change that or add selected_mode as an if else

				System.out.println("- Duration: " + total_time/ 1000.0 + " seconds");
				System.out.println("- Number of objects encountered: " + object_num);
				System.out.println("- Images saved at: " + image_path);
				System.out.println("- Log file saved at: " + log_path);


			} else {
				System.out.println("Invalid button press. Please try again");
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		System.out.println("Program ended. Thank you for using SwiftBot!");
		swiftBot.disableAllButtons();
		System.exit(0); // terminate program
	}



	public static void QRCodeDetection() {

		System.out.println("Starting 15s timer to scan a QR code");
		boolean check = true;

		try {

			while (check) {   // will loop back and start 15s timer each time no code has been scanned. 

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

	
	
	
/*
	
	public class UltraSound 
	{
		public static void main(String[] args) throws Exception 
		{
			SwiftBotAPI api = new SwiftBotAPI();
			
			for(int i=0;i<25;++i)
			{
				long before = System.currentTimeMillis();
				double d = api.useUltrasound();
				long after = System.currentTimeMillis();
				
				System.out.println("Distance = " + d + " Time = " + (after-before));
				Thread.sleep(1000);
			}
		}
	}
 
 * takes 25 ultra sound readings and displays the distance in centimetres along 
 * with how long the method call takes. Note that there may be some variability 
 * in the results, perhaps taking a few readings and taking the average might be 
 * useful, but consider the time each call takes
 */

	
	
	
	
/* 
 * public class SBButton 
{
	private static boolean apressed = false;
	public static void main(String[] args) throws Exception 
	{
		SwiftBotAPI api = new SwiftBotAPI();
		
		api.disableAllButtons();
		
		api.enableButton(Button.A, () -> {apressed = true;});
		
		for(int i=0;i<100;++i)
		{
			System.out.println("Checking button (" + i + ")...");
			if (apressed == true)
			{
				System.out.println("+++A Pressed");  // CALL TERMINATION
				apressed = false;
			}
			Thread.sleep(100);
		}
		api.disableAllButtons();
		System.exit(1);
	}
}

 */

	
	
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
