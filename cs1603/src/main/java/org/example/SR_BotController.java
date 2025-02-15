package org.example;
import swiftbot.*;


public class SR_BotController {

	static SwiftBotAPI swiftBot;

	public static void main(String[] args) throws InterruptedException {
		try {
			swiftBot = new SwiftBotAPI();
		} catch (Exception e) {
			/*
			 * Outputs a warning if I2C is disabled. This only needs to be turned on once,
			 * so you won't need to worry about this problem again!
			 */
			System.out.println("\nI2C disabled!");
			System.out.println("Run the following command:");
			System.out.println("sudo raspi-config nonint do_i2c 0\n");
			System.exit(5);
		}
		
		
		


	}
	
}
