import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Scanner;

import swiftbot.SwiftBotAPI;

public class Detect_Object {

	private static String valid_modes[] = {"Curious SwiftBot", "Scaredy SwiftBot", "Dubious SwiftBot"}; 
	private static String selected_mode = "";
	private static String mode_run = "";
	private static int retry_attempts = 0;
	private static int speed = 40;
	private static int object_num = 0;
	private static long start_time = System.currentTimeMillis(); 
	// get program init time for logging, not sure if this will get current time it might need to be in main. test it.

	// define image_path
	// define log_path
	


	static SwiftBotAPI swiftBot;

	public static void main(String[] args) throws InterruptedException {
//				try {
//					swiftBot = new SwiftBotAPI();
//				} catch (Exception e) {
//					
//					System.out.println("\nI2C disabled!");
//					System.out.println("Run the following command:");
//					System.out.println("sudo raspi-config nonint do_i2c 0\n");
//					System.exit(5);
//				}

		
		//		while (!ButtonX) {   //  while button X is not pressed

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
					curious_swiftbot();

				} else if (selected_mode.equals("Scaredy SwiftBot")) {

					System.out.println("Executing Scaredy SwiftBot mode...");
					scaredy_swiftbot();

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
		
		//		}
		
		terminate_program(); // if button press is X, terminate program
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

	

	public static void select_random_mode() {

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
		
// 		randomly choose left or right and the degree to turn by adjusting time 
//		swiftBot.move(0, speed, random_turn);
//		swiftBot.move(speed, 0, random_turn);
		
	}



	public static void curious_swiftbot() {

		speed = 60;
		object_num ++;
		
//		while (!object_detected) {
			
			wandering();
			
//		}
		
		// do curious steps



	}



	public static void scaredy_swiftbot() {

		speed = 60;
		object_num ++;

//		while (!object_detected && distance_to_object <= 50) {
//		
//			wandering();
//		
//	    }
	
	    // do scaredy steps


	}


	public static void terminate_program() {


		long current_time = System.currentTimeMillis();
		long total_time = current_time - start_time; // calculate total time of execution
		
		boolean check = true;

//		while (check) {
//			System.out.println("Program Terminated. Would you like to view the execution log?"
//					+ "\nPress button Y for 'Yes', and X for 'No' on the SwiftBot.");
//
//
//			if (buttonpress == X) {
//
//				System.out.println("Log file saved at: " + log_path);
//				check = false;
//				
//			} else if (buttonpress == Y) {
//				
//				System.out.println("Execution log:");
//				System.out.println("- Mode executed: " + mode_run);  // if dubious, we wont know which mode it actually ran. may have to change that or add selected_mode as well
//				System.out.println("- Duration: " + total_time/ 1000.0 + " seconds");
//				System.out.println("- Number of objects encountered: " + object_num);
//				System.out.println("- Images saved at: " + image_path);
//				System.out.println("- Log file saved at: " + log_path);
//				
//				check = false;
//				
//			} else {
//				System.out.println("Invalid button press. Please try again");
//			}
//		}
		
		System.out.println("Program ended. Thank you for using SwiftBot!");
		System.exit(0); // terminate program

	}



	public static void QRCodeDetection() {

		System.out.println("Starting 15s timer to scan a QR code");

		try {

			// while () {   // will loop back each time no code has been scanned. 
			// could use a variable x and assign true if qr code is found: while(x == false)
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
					break;
				}

				System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds");
				Thread.sleep(1000);
			}

//			System.out.println("15s time finished, starting timer to scan a QR code again...");

			//			}
		} catch (Exception e) {
			System.out.println("ERROR: Unable to scan for code.");
			e.printStackTrace();
			System.exit(5);
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
