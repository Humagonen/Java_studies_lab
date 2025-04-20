package Test;

import java.util.Random;
import java.util.Scanner;

import swiftbot.Button;
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


public class Detect_Object2 {
	
	static SwiftBotAPI swiftBot;		//made it public
	
	private static Logs2 log= new Logs2();
	private static Run_modes2 mode= new Run_modes2();
	private static BotCamera2 camera= new BotCamera2();
	
	private static String valid_modes[] = {"Curious SwiftBot", "Scaredy SwiftBot", "Dubious SwiftBot"}; 
	private static String selected_mode = "";
	private static String mode_run = "";

	private static boolean press = false;  // variable to check termination
	private static boolean buttonpressX = false;
	private static boolean buttonpressY = false;

	private static long start_time = System.currentTimeMillis(); 
	private static long end_time;
	private static long total_time;

	private static String image_path = "/data/home/pi/Detect_Object/";
	

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
					mode.curious_swiftbot(selected_mode, press); // loop back to itself in the method

				} else if (selected_mode.equals("Scaredy SwiftBot")) {
					System.out.println("Executing Scaredy SwiftBot mode..."); 
					mode.scaredy_swiftbot(selected_mode, press); // loop back to itself in the method

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
				camera.QRCodeDetection(selected_mode);
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
			mode.curious_swiftbot(selected_mode, press);

		} else {
			System.out.println("Executing Scaredy SwiftBot mode...");
			mode.scaredy_swiftbot(selected_mode, press);
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
			    log.save_log(mode_run, mode.getObject_num(), total_time, image_path);  // not sure of this
			    
			} else if (buttonpressY == true) {

			    System.out.println("\nButton Y is pressed.");
			    log.save_log(mode_run, mode.getObject_num(), total_time, image_path);
			    System.out.println();
			    log.display_log(); // display log file content (execution log)
			} else {
				System.out.println("\nNo button is pressed in time.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\nProgram ended. Thank you for using SwiftBot!");		
		System.exit(0);
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
