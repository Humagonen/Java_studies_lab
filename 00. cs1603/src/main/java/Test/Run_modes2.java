package Test;

import java.util.Random;

public class Run_modes2 {
	
	private static BotCamera2 camera= new BotCamera2();
	
    private int speed = 40;
    private int object_num = 0;
	
	
	public Run_modes2()
	{
		
	}
	

	public void wandering(String selected_mode, boolean press) { 
		double n = 50.0;  // Threshold for scaredy mode

		int[] blue = {0, 0, 255}; // blue color
		Detect_Object2.swiftBot.fillUnderlights(blue);

		if (selected_mode.equals("Curious SwiftBot")) {
			n = 80.0;
		}

		Random rand = new Random();

		while (!press) { // Exit when press is true
			if (Detect_Object2.swiftBot.useUltrasound() <= n || press) {
				System.out.println("Obstacle detected! Stopping.");
				Detect_Object2.swiftBot.stopMove();
				return;
			}

			speed = 40;
			System.out.println("\nWandering... (Speed: 40)\nUnderlights: Blue\n");

			// Move in smaller steps to check distance and press frequently
			// if distance is smaller or equal to the required gap (Object detected) OR 
			// if press is true, stop and return function(exit).
			for (int i = 0; i < 50; i++) { 
				if (Detect_Object2.swiftBot.useUltrasound() <= n || press) { 
					System.out.println("\nObject Detected while wandering! Stopping."); // delete this and put in curious and scaredy
					Detect_Object2.swiftBot.stopMove();
					Detect_Object2.swiftBot.disableUnderlights();
					return;
				}
				Detect_Object2.swiftBot.move(speed, speed, 100);  // loop 50s * 100 = 5 seconds
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
				Detect_Object2.swiftBot.move(speed, 0, random_degree); // Turn right
			} else {  
				Detect_Object2.swiftBot.move(0, speed, random_degree); // Turn left
			}
		}
	}


	public void curious_swiftbot(String selected_mode, boolean press) {  // after finishing 1 round (object detection and image taken) should I wait for 5s like in scaredy mode? check
		int[] green = {0, 255, 0}; // Green
		Random rand = new Random();

		while (!press) {  
			while (Detect_Object2.swiftBot.useUltrasound() > 80.0 && !press) { // Moves until an object is within 80 cm
				wandering(selected_mode, press);
				if (press) return;  // Exit immediately
			}

			if (press) return;

			try {
				takeAction(60);
//				swiftBot.stopMove();
//				speed = 60;
//				object_num++;

				double dist_to_obj = Detect_Object2.swiftBot.useUltrasound();

				// if object is within 30 cm
				if (dist_to_obj < 45.0) {  // adjusted the 30 cm to 45.0 to make the bot go for 30 cm in real life
					Detect_Object2.swiftBot.fillUnderlights(green);
					System.out.println("Object is within 30 cm. Moving closer...");
					Thread.sleep(500);
					while (Detect_Object2.swiftBot.useUltrasound() < 44.5 && !press) {
						Detect_Object2.swiftBot.startMove(-speed, -speed);
					}
					Detect_Object2.swiftBot.disableUnderlights();
					Thread.sleep(1000);

				// if object is outside 30 cm 
				} else if (dist_to_obj > 45.0) { 
					Detect_Object2.swiftBot.fillUnderlights(green);
					System.out.println("Object is within 30 cm. Moving back...");
					Thread.sleep(500);
					while (Detect_Object2.swiftBot.useUltrasound() > 45.5 && !press) {
						Detect_Object2.swiftBot.startMove(speed, speed);
					}
					Detect_Object2.swiftBot.disableUnderlights();
					Thread.sleep(1000);

				} else { // if the object is exactly at 30 cm distance
					System.out.println("Object is exactly 30 cm away! blinking underlights in green.");
					Thread.sleep(500);
					for (int i = 0; i < 3; i++) {
						if (press) return;  // Stop blinking if pressed
						Detect_Object2.swiftBot.fillUnderlights(green); 
						Thread.sleep(1000);

						if (press) return;
						Detect_Object2.swiftBot.disableUnderlights();
						Thread.sleep(1000);
					}
				}

				Detect_Object2.swiftBot.stopMove();
				Thread.sleep(1000);

				if (press) return;

				System.out.println("Taking Picture...");
				Thread.sleep(1000);
				camera.take_image_save("curious");

				if (press) return;

				// randomly change direction after taking picture
				int random_num = rand.nextInt(2);  // 0 or 1
				int random_time = rand.nextInt(500, 2000);

				if (random_num == 0) { 
					Detect_Object2.swiftBot.move(speed, 0, random_time);
				} else { 
					Detect_Object2.swiftBot.move(0, speed, random_time);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public void scaredy_swiftbot(String selected_mode, boolean press) { 
		while (!press) {  // Continuously check if press == false

			// Constantly check distance while wandering
			while (!press && Detect_Object2.swiftBot.useUltrasound() > 50.0) {   
				wandering(selected_mode, press);  
			}

			if (press) return;

			try {
				takeAction(60);
//				speed = 60;
//				object_num++;
//				swiftBot.stopMove();
				
				System.out.println("\nSwiftBot Stopped!");
				Thread.sleep(1000);
				
				// Blink underlights in red if an object is detected
				int[] red = {255, 0, 0};
				for (int i = 0; i < 3; i++) { 
					Detect_Object2.swiftBot.fillUnderlights(red);
					Thread.sleep(300);
					Detect_Object2.swiftBot.disableUnderlights();
					Thread.sleep(300);
					if (press) return;
				} // moved blinking before saving image bc makes more sense. change in requirements

				System.out.println("Taking Picture...");
				Thread.sleep(1000);
				camera.take_image_save("scaredy");  // takes image and displays image saved

				if (press) return;

				// Move back 15 cm
				Detect_Object2.swiftBot.move(-speed, -speed, 500); 
				Thread.sleep(1000);
				if (press) return;

				// Turn around (approx 180 degrees)
				Detect_Object2.swiftBot.move(speed, 0, 1870); // tested for 180 degrees
				Thread.sleep(1000);
				if (press) return;

				// Move away for 3 seconds
				Detect_Object2.swiftBot.move(speed, speed, 3000);
				if (press) return;

				// Wait for 5 seconds ??
				Thread.sleep(5000);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
    private void takeAction(int spd) {
        object_num++;
        speed = spd;
        Detect_Object2.swiftBot.stopMove();
        System.out.println("\nObject detected!");
    }

    public int getObject_num() {
        return object_num;
    }

}
