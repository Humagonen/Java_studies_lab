package org.example;

import java.util.Arrays;
import java.util.Scanner;
import swiftbot.*;

public class Main {
	private static int score = 0;
	private static int lives = 3;
	public String[] player_guesses = {};

	static String[] colors_to_display =  random_colors();


	private static SwiftBotAPI swiftBot = new SwiftBotAPI();

	public static void main(String[] args) throws InterruptedException {
		try {

			System.out.println("Some other code");
		}catch(Exception e) {
			System.out.println(e);
			System.exit(5);
		}
		//ASCII art
		greeting();
		

		Scanner reader = new Scanner(System.in);

		while(true) {
			System.out.println("Are you ready to start?\n");
			System.out.println(
					"1 = Yes         \t|\t 2 = No\n"
					);
			System.out.print("Enter a number: ");
			// read the users input
			String ans = reader.next();

			switch(ans) {
			case "1":
				startGame();
				break;
			case "2":
				reader.close();
				System.exit(0);
				break;
			default:
				System.out.println("ERROR: Please enter a valid number");
			}
		}

	}
	
	
	
	
	// added the option for asking to continue or quit after 5 rounds
	public static void startGame() {
	    System.out.println("Displaying colors in progress");
	    int rounds = 0; // Track number of rounds played

	    while (lives > 0) {
	        display_lights(); // Display the lights for the player to memorize
	        rounds++; // Increment round counter

	        Scanner reader = new Scanner(System.in);
			//next we ask the user to input the correct sequence
	        System.out.println("Enter the colours in the correct order (comma-separated):");
	        String input = reader.nextLine();
	        String[] userGuesses = input.split(",");
	        // Check if the colors match the generated ones
	        
	        if (Arrays.equals(userGuesses, colors_to_display)) {
	            score++;
	            System.out.println("Correct! Your score: " + score);
	        } else {
	            lives--;
	            System.out.println("Incorrect! Remaining lives: " + lives);
	        }

	        // After every 5 rounds, prompt the player to continue or quit
	        if (rounds % 5 == 0) {
	            System.out.println("You've completed " + rounds + " rounds.");
	            System.out.println("Would you like to continue or quit?");
	            System.out.println("1 = Continue         \t|\t 2 = Quit");

	            String choice = reader.nextLine();
	            if (choice.equals("2")) {
	                System.out.println("Thank you for playing! Your final score: " + score);
	                break; // Exit the game loop if the user chooses to quit
	            }
	        }
	    }

	    // End the game if lives are exhausted
	    if (lives == 0) {
	    	//display_finalMsg();
	        System.out.println("Game over! Your final score: " + score);
	    }
	}

	
	

	public static void display_lights(){
		//loop through the array of colors, once the process is done return true or error
		try {
			System.out.println("Turning on the lights one by one");
			//some code to display colors
			for(String rgb : colors_to_display) {
				int[] current_color = new int[3];
				switch (rgb) {
				case "red":
					current_color = new int[]{255,0,0};
				case "green":
					current_color = new int[]{0,255,0};
					break;
				case "blue":
					current_color = new int[]{0,0,255};
					break;
				case "white":
					current_color = new int[]{255,255,255};
					break;
				default:
					current_color = new int[]{0,0,0};

				}
				swiftBot.fillUnderlights(current_color);
				Thread.sleep(300);
			}


		} catch (Exception error){
			System.out.println("An error occured while running the lights");
			System.out.println(error);
		}

	}


	//method to generate the random colors
	public static String[] random_colors() {
		String[] colorOptions = {"red", "green", "blue", "white"}; // Define available colors
		String[] result = new String[4]; // Array to hold 4 random colors

		// Generate 4 random colors
		for (int i = 0; i < result.length; i++) {
			int randomIndex = (int) (Math.random() * colorOptions.length); // Random index
			result[i] = colorOptions[randomIndex]; // Assign random color
		}
		return result;
	}




	
	

	//method to celebrate
	public void celebrate(int score){
		//do something

		System.out.println(score);
	}

	
	
	
	
	
	public static void greeting(){
		System.out.println("  __  __                                      _____                      ");
		System.out.println(" |  \\/  |                                    / ____|                     ");
		System.out.println(" | \\  / | ___  _ __ ___   ___  _ __  _   _  | |  __  __ _ _ __ ___   ___ ");
		System.out.println(" | |\\/| |/ _ \\| '_ ` _ \\ / _ \\| '_ \\| | | | | | |_ |/ _` | '_ ` _ \\ / _ \\");
		System.out.println(" | |  | | (_) | | | | | | (_) | | | | |_| | | |__| | (_| | | | | | |  __/");
		System.out.println(" |_|  |_|\\___/|_| |_| |_|\\___/|_| |_|\\__, |  \\_____|\\__,_|_| |_| |_|\\___|");
		System.out.println("                                      __/ |                              ");
		System.out.println("                                     |___/                               ");
		System.out.println("");
		System.out.println("\t\t\tThe rules are as follows");
        System.out.println("1. Memorize the sequence of colors displayed.");
        System.out.println("2. Enter the colors in the correct order.");
        System.out.println("3. Each correct answer increases your score.");
        System.out.println("4. You lose a life for each incorrect answer. You have 3 lives.");
        System.out.println("5. After every 5 rounds, you will be asked if you want to continue or quit.");
        System.out.println("");
		System.out.println("");
	}

}