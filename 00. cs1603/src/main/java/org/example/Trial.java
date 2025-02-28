package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Trial {
    private static int score = 0; // To track the player's score
    private static int lives = 3; // Player starts with 3 lives

    // Array to store the sequence of colors to display
    static String[] colorsToDisplay;

    public static void main(String[] args) {
        greeting(); // Display ASCII art and rules

        Scanner reader = new Scanner(System.in);
        while (true) {
            System.out.println("Are you ready to start?\n");
            System.out.println("1 = Yes         \t|\t 2 = No\n");
            System.out.print("Enter a number: ");
            String ans = reader.next();

            switch (ans) {
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

    // Game initialization
    public static void startGame() {
        System.out.println("Game started! Try to match the sequence.");
        colorsToDisplay = randomColors(); // Generate the initial random colors

        Scanner reader = new Scanner(System.in);
        int rounds = 0; // Track the number of rounds played

        while (lives > 0) {
            displayLights(); // Simulate displaying the lights

            // Ask the player to input their guess
            System.out.println("Enter the colors in the correct order (comma-separated):");
            String input = reader.nextLine();
            String[] userGuesses = input.split(",");

            // Check if the guesses match the displayed colors
            if (Arrays.equals(userGuesses, colorsToDisplay)) {
                score++;
                rounds++;
                System.out.println("Correct! Your score: " + score);
                colorsToDisplay = randomColors(); // Generate a new sequence for the next round
            } else {
                lives--;
                System.out.println("Incorrect! Remaining lives: " + lives);
            }

            // Check if 5 rounds are completed
            if (rounds % 5 == 0) {
                System.out.println("You've completed " + rounds + " rounds.");
                System.out.println("Do you want to continue or quit?");
                System.out.println("1 = Continue         \t|\t 2 = Quit");

                String choice = reader.nextLine();
                if (choice.equals("2")) {
                    System.out.println("Thank you for playing! Your final score: " + score);
                    break; // Exit the game loop if the user chooses to quit
                }
            }
        }

        // Game over
        if (lives == 0) {
            System.out.println("Game over! Your final score: " + score);
        }
    }

    // Simulate displaying lights by printing colors to the console
    public static void displayLights() {
        System.out.println("Memorize this sequence of colors:");
        for (String color : colorsToDisplay) {
            System.out.println(color); // Print each color
            try {
                Thread.sleep(1000); // Wait 1 second between colors for realism
            } catch (InterruptedException e) {
                System.out.println("An error occurred during the delay: " + e.getMessage());
            }
        }
    }

    // Generate a random sequence of colors
    public static String[] randomColors() {
        String[] colorOptions = {"red", "green", "blue", "yellow"}; // Available colors
        String[] result = new String[4]; // Array to hold 4 random colors

        // Generate the random sequence
        Random random = new Random();
        for (int i = 0; i < result.length; i++) {
            int randomIndex = random.nextInt(colorOptions.length); // Random index
            result[i] = colorOptions[randomIndex];
        }
        return result;
    }

    // Greeting and rules display
    public static void greeting() {
        System.out.println("  __  __                                      _____                      ");
        System.out.println(" |  \\/  |                                    / ____|                     ");
        System.out.println(" | \\  / | ___  _ __ ___   ___  _ __  _   _  | |  __  __ _ _ __ ___   ___ ");
        System.out.println(" | |\\/| |/ _ \\| '_ ` _ \\ / _ \\| '_ \\| | | | | | |_ |/ _` | '_ ` _ \\ / _ \\");
        System.out.println(" | |  | | (_) | | | | | | (_) | | | | |_| | | |__| | (_| | | | | | |  __/");
        System.out.println(" |_|  |_|\\___/|_| |_| |_|\\___/|_| |_|\\__, |  \\_____|\\__,_|_| |_| |_|\\___|");
        System.out.println("                                      __/ |                              ");
        System.out.println("                                     |___/                               ");
        System.out.println("");
        System.out.println("\t\t\tThe rules are as follows:");
        System.out.println("1. Memorize the sequence of colors displayed.");
        System.out.println("2. Enter the colors in the correct order.");
        System.out.println("3. Each correct answer increases your score.");
        System.out.println("4. You lose a life for each incorrect answer. You have 3 lives.");
        System.out.println("5. After every 5 rounds, you will be asked if you want to continue or quit.");
        System.out.println("");
    }
}
