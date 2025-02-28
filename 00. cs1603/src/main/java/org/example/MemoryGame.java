package org.example;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import swiftbot.*;

public class MemoryGame {
    static SwiftBotAPI swiftBot;
    private ArrayList<Integer> sequence;
    private Scanner scanner;
    private int score;

    // Main method
    public static void main(String[] args) {
        MemoryGame game = new MemoryGame();
        game.startGame();
    }

    // RGB color definitions for buttons
    private static final int[][] COLORS = {
        {255, 0, 0},  // Red (Button A)
        {0, 255, 0},  // Green (Button B)
        {0, 0, 255},  // Blue (Button X)
        {255, 255, 0} // Yellow (Button Y)
    };

    // Constructor
    public MemoryGame() {
        try {
            swiftBot = new SwiftBotAPI();
            sequence = new ArrayList<>();
            scanner = new Scanner(System.in);
            score = 0;
        } catch (Exception e) {
            System.err.println("Failed to initialize SwiftBotAPI: " + e.getMessage());
            System.exit(1);
        }
    }

    // Enum for buttons
    private enum Button {
        A, B, X, Y
    }

    // Flash the LED corresponding to a button
    private void flashLED(Button button) {
        int[] rgb = COLORS[button.ordinal()];
        Underlight underlight;

        switch (button) {
            case A:
                underlight = Underlight.FRONT_LEFT;
                break;
            case B:
                underlight = Underlight.FRONT_RIGHT;
                break;
            case X:
                underlight = Underlight.MIDDLE_LEFT;
                break;
            case Y:
                underlight = Underlight.MIDDLE_RIGHT;
                break;
            default:
                throw new IllegalArgumentException("Invalid button: " + button);
        }

        try {
            swiftBot.setUnderlight(underlight, rgb);
            Thread.sleep(500); // Blink duration
            swiftBot.disableUnderlight(underlight);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Add a random color to the sequence
    private void addRandomColor() {
        Random random = new Random();
        sequence.add(random.nextInt(4)); // Random number between 0 and 3
    }

    // Display the sequence using LEDs
    private void displaySequence() {
        System.out.println("\nWatch the sequence carefully...");
        for (int color : sequence) {
            flashLED(Button.values()[color]);
            try {
                Thread.sleep(500); // Pause between flashes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Validate the user's input
    private boolean validateUserInput() {
        System.out.println("\nNow repeat the sequence using buttons (A, B, X, Y):");
        for (int i = 0; i < sequence.size(); i++) {
            System.out.print("Enter button " + (i + 1) + ": ");
            String input = scanner.next().toUpperCase();

            if (!input.matches("[ABXY]")) {
                System.out.println("Invalid input. Please enter A, B, X, or Y.");
                return false;
            }

            int userChoice;
            switch (input) {
                case "A":
                    userChoice = 0;
                    break;
                case "B":
                    userChoice = 1;
                    break;
                case "X":
                    userChoice = 2;
                    break;
                case "Y":
                    userChoice = 3;
                    break;
                default:
                    userChoice = -1; // This case should never be reached
            }

            if (userChoice != sequence.get(i)) {
                System.out.println("\nWrong sequence! Game Over.");
                return false;
            }
        }
        return true;
    }

    // Celebration Dive for high scores
    private void performCelebration() {
        System.out.println("\nCongratulations! You've reached a high score! Performing 'V' shape dive...");
        try {
            swiftBot.move(50, -50, 1000); // Turn left
            swiftBot.move(-50, 50, 1000); // Turn right
            swiftBot.fillUnderlights(new int[]{255, 255, 255}); // White LEDs
            Thread.sleep(2000);
            swiftBot.disableUnderlights();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Main game loop
    public void startGame() {
        System.out.println("*******************************************");
        System.out.println("*       Welcome to the Memory Game!       *");
        System.out.println("* Follow the sequence shown on the LEDs.  *");
        System.out.println("* Repeat it correctly to score points.    *");
        System.out.println("*******************************************");

        boolean keepPlaying = true;

        while (keepPlaying) {
            addRandomColor();
            System.out.println("\nRound: " + sequence.size() + " | Score: " + score);
            displaySequence();

            if (validateUserInput()) {
                score++;
                System.out.println("\nCorrect! Your score is now: " + score);
            } else {
                if (score >= 5) {
                    performCelebration();
                }
                System.out.println("\nGame Over! Your final score is: " + score);
                break;
            }

            // Ask to continue every 5 rounds
            if (sequence.size() % 5 == 0) {
                System.out.println("\nWould you like to continue playing? (yes/no)");
                String choice = scanner.next().toLowerCase();
                if (choice.equals("no")) {
                    keepPlaying = false;
                    if (score >= 5) {
                        performCelebration();
                    }
                    System.out.println("\nThank you for playing! Your final score is: " + score);
                }
            }
        }
    }
}
