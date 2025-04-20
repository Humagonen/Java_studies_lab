import java.util.Random;
import java.util.Scanner;

import swiftbot.Button;
import swiftbot.SwiftBotAPI;

public class Detect_Object {
	
	private static String trqscolor = "\033[38;5;44m"; // turquoise color
	private static String lightblue ="\033[38;5;75m";
	
	public static String reset = "\u001B[0m";  // reset color (back to white)
	public static String red = "\033[38;5;160m";
	public static String green = "\033[38;5;77m";
	public static String blue = "\033[38;5;69m"; // color code for blue
	public static String yellow = "\033[38;5;3m";
	
	private static SwiftBotAPI swiftBot;
	private static Logging log;
	private static RunModes mode;
	private static BotCamera camera;

	private static String valid_modes[] = {"Curious SwiftBot", "Scaredy SwiftBot", "Dubious SwiftBot"}; 
	private static String selected_mode = "";
	private static String mode_run = ""; // to save the actual mode run in log file

	private static boolean press = false;  // variable to check termination
	private static boolean buttonpressX = false;
	private static boolean buttonpressY = false;

	private static long start_time = System.currentTimeMillis(); 
	private static long end_time;
	private static long total_time;

	private static String image_path = "/data/home/pi/Detect_Object/";
	
	public static void main(String[] args) throws InterruptedException {
		
		int retry_attempts = 0; // local variable to check valid mode selection
		
		try {
			 swiftBot = new SwiftBotAPI();
		} catch (Exception e) {

			System.out.println("\nI2C disabled!");
			System.out.println("Run the following command:");
			System.out.println("sudo raspi-config nonint do_i2c 0\n");
			System.exit(5);
		}
		
	    log = new Logging();
	    camera = new BotCamera(swiftBot);
	    mode = new RunModes(swiftBot, camera);
	    
		
		swiftBot.enableButton(Button.X, () -> { swiftBot.stopMove();
												press = true;
												mode.setPress(press); // update press for other classes
												System.out.println(red + "\nButton X Pressed." + reset);
												terminate_program();
												});
		
		greeting();
		get_mode();  // get mode from the user either by scanning a qr code or manual entry

		// check mode validity (run modes if valid):
		System.out.println("Checking if " + selected_mode + " matches a mode...\n");
		Thread.sleep(1000);
		
		while (retry_attempts < 5) {

			boolean found = false;
			for (String mode : valid_modes) {  // check if selected mode is in the valid modes array by going through each array element
				if (mode.equals(selected_mode)) {
					
					found = true;
					break;
				}
			}

			if (found == true) {   // if its a valid mode
				System.out.println(green + selected_mode + " is a valid mode!" + reset);

				mode_run = selected_mode;

				if (selected_mode.equals("Curious SwiftBot")) {
					System.out.println("Executing " + green + "Curious SwiftBot" + reset + " mode...");
					System.out.println("-------------------------------------------------------------");
					mode.curious_swiftbot(selected_mode); // loops back to itself in the method

				} else if (selected_mode.equals("Scaredy SwiftBot")) {
					System.out.println("Executing " + red + "Scaredy SwiftBot" + reset + " mode..."); 
					System.out.println("-------------------------------------------------------------");
					mode.scaredy_swiftbot(selected_mode); // loops back to itself in the method

				} else { // Dubious mode
					System.out.println("Executing Dubious SwiftBot mode...");
					System.out.println(yellow + "\nRandomly selecting a mode..." + reset);
					Thread.sleep(1000);
					select_random_mode();
				}
				break;

			} else {  // if invalid mode

				retry_attempts ++;
				int n = 5 - retry_attempts;
				System.out.println(red + "\nInvalid mode. You have " + n + " attempts left.\n" + reset);

				if (retry_attempts == 5) { 

					System.out.println(red + "All 5 attempts are invalid. Terimating Program..." + reset);
					System.exit(0); // terminate program directly
				}
				get_mode(); // if retry_attempts != 5 get a mode again 
			}
		}  // end of retry_attempts loop
	}



	public static void get_mode() {
		Scanner reader = new Scanner(System.in);

		System.out.println("Please choose to scan QR code or enter the SwiftBot Mode manually. "
				+ "You have 5 attempts in total to specify a mode. ");

		System.out.println("\nPress " + lightblue + "1" + reset + " to scan a QR code\r\n"
				+ "Press " + lightblue + "2" + reset + " to enter the mode manually\n");
		System.out.println("-------------------------------------------------------------\n");

		boolean choice = true;
		while (choice) {

			String ans = reader.next();

			switch (ans) {
			case "1":
				selected_mode = camera.QRCodeDetection(selected_mode);
				choice = false;
				break;

			case "2":
				System.out.println("\nEnter one of the following modes (must be exactly the same):\r\n"
						+ "- Curious SwiftBot\n- Scaredy SwiftBot\n- Dubious SwiftBot\n");
				System.out.println("-------------------------------------------------------------\n");
				reader.nextLine();
				selected_mode = reader.nextLine();
				choice = false;
				break;
				
			default:
				System.out.println(red + "Error: Invalid option\r\n" + reset 
						+ "Press 1 to scan a QR code, Press 2 to enter manually:");  
				System.out.println("-------------------------------------------------------------\n");
				break;
			}
		}
	}


	public static void select_random_mode() { // random mode selection for Dubious SwiftBot mode

		Random rand = new Random();
		int random_num = rand.nextInt(0,2); // will generate either 0 or 1

		if (random_num == 0) { // run curious mode
			System.out.println("Executing " + green + "Curious SwiftBot" + reset + " mode...");
			System.out.println("-------------------------------------------------------------");
			mode.curious_swiftbot(selected_mode);

		} else { // run scaredy mode
			System.out.println("Executing " + red + "Scaredy SwiftBot" + reset + " mode..."); 
			System.out.println("-------------------------------------------------------------");
			mode.scaredy_swiftbot(selected_mode);
		}	
	}


	
////////////////////////////// program termination, saving logs and greeting ///////////////////////////////

	public static void terminate_program() {

		swiftBot.stopMove();
		swiftBot.disableUnderlights();
		int[] turquoise = {48, 213, 200}; // termination underlights color
		swiftBot.fillUnderlights(turquoise);

		try {
			// calculate total time of execution, start_time was defined in the very beginning
			end_time = System.currentTimeMillis();
			total_time = end_time - start_time; 

			System.out.println("-------------------------------------------------------------\n");
			System.out.println(trqscolor + "Program Terminated. Would you like to view the execution log?"
					+ "\nPress button Y for 'Yes', and X for 'No' on the SwiftBot.");
			boolean check = true;
			while(check) {
				swiftBot.disableAllButtons();

				swiftBot.enableButton(Button.X, () -> {buttonpressX = true;
				swiftBot.disableAllButtons();});

				swiftBot.enableButton(Button.Y, () -> {buttonpressY = true;
				swiftBot.disableAllButtons();});

				Thread.sleep(4000); // give time for the user to press a button

				System.out.println(reset + "-------------------------------------------------------------\n" + red);
				if (buttonpressX == true) {
					System.out.println("Button X Pressed." + reset);
					log.save_log(mode_run, mode.getObject_num(), total_time, image_path); 
					check = false;
					break;
					
				} else if (buttonpressY == true) {
					System.out.println("Button Y Pressed." + reset);
					log.save_log(mode_run, mode.getObject_num(), total_time, image_path);
					System.out.println();
					log.display_log(); // display log file content (execution log)
					check = false;
					break;
				} else {
					System.out.println(blue + "Please either press button X or Y." + reset);
				}
			} // end of while loop

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(blue + "\nProgram ended. Thank you for using SwiftBot!" + reset);		
		System.exit(0);
	}
	
	
	public static void greeting() {

		System.out.println(blue);
		System.out.println("___________________________________________________________________________________________");
		System.out.println("________          __                 __        _____  __       __               __   ");
		System.out.println("\\______ \\   _____/  |_  ____   _____/  |_     /  _  \\|  |_    |__| ____   _____/  |_ ");
		System.out.println(" |  | |  \\_/ __ \\   __\\/ __ \\_/ ___\\   __\\   |  | |  |  _ \\   |  |/ __ \\_/ ___\\   __\\");
		System.out.println(" |  |_/   \\  ___/|  | \\  ___/\\  \\___|  |     |  |_|  | |_/ \\  |  \\  ___/\\  \\___|  |  ");
		System.out.println("/_______ / \\____ >__|  \\____ >\\____ >__|      \\_____/\\____/\\__|  |\\____ >\\____ >__|  ");
		System.out.println("                                                          \\______|                   ");
		System.out.println("___________________________________________________________________________________________");
		System.out.println("  ");
		
		System.out.println(reset);
		bot_image();
		System.out.println("------------------------------------");
		System.out.println(" Welcome to SwiftBot Control System");
		System.out.println("------------------------------------");
		System.out.println("SwiftBot will continuously scan its surroundings.");
		System.out.println("If an object is detected, it will react based on the selected mode.\n");
		System.out.println("Press the 'X' button on SwiftBot at any time to stop the program.");
		System.out.println("-------------------------------------------------------------------");
		System.out.println("\n");
	}

	
	public static void bot_image() {
		
		System.out.println(lightblue);
		
        System.out.println("  [BOT] MOVING FORWARD      |   [BOT] OBJECT DETECTED! STOPPING");
        System.out.println("                            | ");
        System.out.println("        _______             |          _______   ");
        System.out.println("      [o]     [o]           |        [X]     [X] ");
        System.out.println("       \\-------/            |         \\-------/   ");
        System.out.println("         |   |              |           |   |    ");
        System.out.println("         '---'              |           '---'    ");
        
		System.out.println(reset);
	}
}
