import java.util.Random;
import swiftbot.SwiftBotAPI;

public class RunModes {
	private static String lightred ="\033[38;5;9m";
	private static int[] green = {0, 255, 0};
	private SwiftBotAPI swiftBot; 
	private BotCamera camera;
	
    private int speed = 40;
    private int object_num = 0; 
    private boolean press = false;  // variable for termination control

    public RunModes(SwiftBotAPI swiftBot, BotCamera camera) { // constructor
        this.swiftBot = swiftBot;
        this.camera = camera;
    }
    
    public void setPress(boolean value) {  // called in Detect_Object class for termination checks
        press = value;
    }

    public boolean getPress() {
        return press;
    }

	public void wandering(String selected_mode) { 
		
		System.out.println(Detect_Object.blue); // make displayed information blue for wandering mode
		
		double n = 50.0;  // Threshold for scaredy mode

		int[] blue = {0, 0, 255}; // blue color
		swiftBot.fillUnderlights(blue);

		if (selected_mode.equals("Curious SwiftBot")) {
			n = 80.0;
		}

		while (!getPress()) { // Exit when press is true
			if (swiftBot.useUltrasound() <= n || getPress()) {
				System.out.println("Stopping.");
				swiftBot.stopMove();
				return;
			}
			speed = 40;
			System.out.println("Wandering... (Speed: 40)\nUnderlights: Blue");

			// Move in smaller steps to check distance and press frequently
			// if distance is smaller or equal to the required gap (Object detected) OR 
			// if press is true, stop and return function(exit).
			for (int i = 0; i < 50; i++) { 
				if (swiftBot.useUltrasound() <= n || getPress()) { 
					System.out.println("\nObject Detected while wandering! Stopping." + Detect_Object.reset);
					swiftBot.stopMove();
					return;
				}
				swiftBot.move(speed, speed, 100);  // loop 50s * 100 = 5 seconds
			}

			if (getPress()) return;  // Check X button press

			System.out.println(Detect_Object.yellow + "\nWaiting for 1 second after 5 seconds" + Detect_Object.blue);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (getPress()) return;  // Check X button press again before turning
			
			System.out.println(Detect_Object.yellow + "\nChanging direction randomly...\n" + Detect_Object.blue);
			random_turn(250, 2000);
		}
		System.out.println(Detect_Object.reset); // reset display color
	}
	
	public void maintain_bufferzone() {
		
		double dist_to_obj = swiftBot.useUltrasound();
		try {
			// if object is within 30 cm
			if (dist_to_obj < 30.0) {  
				swiftBot.fillUnderlights(green);
				System.out.println("Object is within 30 cm (" + dist_to_obj + " cm). Moving Back...");
				Thread.sleep(500);

				while (swiftBot.useUltrasound() < 25.0 && !getPress()) { // adjusted the 30 cm to make the bot go for 30 cm in real life
					swiftBot.startMove(-speed, -speed);
				}
				swiftBot.disableUnderlights();
				Thread.sleep(1000);

				// if object is outside 30 cm 
			} else if (dist_to_obj > 30.0) { 
				swiftBot.fillUnderlights(green);
				System.out.println("Object is outside 30 cm (" + dist_to_obj + " cm). Moving closer...");
				Thread.sleep(500);
				while (swiftBot.useUltrasound() > 55.0 && !getPress()) {
					swiftBot.startMove(speed, speed);
				}
				swiftBot.disableUnderlights();
				Thread.sleep(1000);

			} else { // if the object is exactly at 30 cm distance
				System.out.println("Object is exactly 30 cm away! blinking underlights in green.");
				Thread.sleep(500);
				for (int i = 0; i < 3; i++) {
					if (getPress()) return;  // Stop blinking if pressed
					swiftBot.fillUnderlights(green);
					Thread.sleep(1000);

					if (getPress()) return;
					swiftBot.disableUnderlights();
					Thread.sleep(1000);
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void curious_swiftbot(String selected_mode) {  

		while (!getPress()) {  // while press is false (!press is a true condition when press is false)
			while (swiftBot.useUltrasound() > 80.0 && !getPress()) { // Moves until an object is within 80 cm
				wandering(selected_mode);
				if (getPress()) return;  // Exit immediately
			}

			if (getPress()) return;

			try {
				System.out.println(Detect_Object.green);
				takeAction(60);
				System.out.println("Object detected!\nSwiftBot Stopped.");

				Thread.sleep(500);
				maintain_bufferzone();

				swiftBot.stopMove();
				Thread.sleep(1000);

				if (getPress()) return;

				System.out.println("\nTaking Picture...");
				Thread.sleep(1000);
				camera.take_image_save("curious");
				
				double dist_to_obj = swiftBot.useUltrasound();  // get distance again after saving image

				if (getPress()) return;
				
				// Wait for 5 seconds
				System.out.println(Detect_Object.yellow + "\nWaiting for 5 seconds...\n" + Detect_Object.green);
				Thread.sleep(5000); 
				
				if (getPress()) return;
				double distance = swiftBot.useUltrasound(); // check if object moved again. if yes maintain buffer zone. if not carry on
//				System.out.println("Current distance to object is: " + distance);
				
				// check again if object has been moved
				if (dist_to_obj != distance) { // if object is moved
					System.out.println(Detect_Object.red + "Object Moved!" + Detect_Object.green);
					maintain_bufferzone();
					swiftBot.stopMove();
					Thread.sleep(1000);
					System.out.println("\nTaking Picture...");
					Thread.sleep(1000);
					camera.take_image_save("curious");
				}
				
				if (getPress()) return;
				// randomly change direction after taking picture
				System.out.println(Detect_Object.yellow + "\nRandomly change direction after taking picture..." + Detect_Object.green);

				Thread.sleep(1000); // wait for a second
				random_turn(500, 2000); // slightly move in a different direction

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Detect_Object.reset);
	}


	public void scaredy_swiftbot(String selected_mode) { 
		
		while (!getPress()) {  // Continuously check if press == false

			try {
				Thread.sleep(1000); // 1 second wait
				// Constantly check distance while wandering
				while (!getPress() && swiftBot.useUltrasound() > 50.0) {   
					wandering(selected_mode);  
				}

				if (getPress()) return;

				System.out.println(lightred);
				takeAction(60);
				System.out.println("\nObject detected!\nSwiftBot Stopped.");
				Thread.sleep(1000);

				if (getPress()) return;

				System.out.println("\nTaking Picture...");
				Thread.sleep(1000);
				camera.take_image_save("scaredy");  // takes image and displays image saved

				if (getPress()) return;

				// Blink underlights in red if an object is detected
				System.out.println(lightred + "Blinking Underlights in Red...");
				int[] red = {255, 0, 0};
				for (int i = 0; i < 3; i++) { 
					swiftBot.fillUnderlights(red);
					Thread.sleep(300);
					swiftBot.disableUnderlights();
					Thread.sleep(300);
					if (getPress()) return;
				} 

				if (getPress()) return;

				// Move back
				System.out.println(lightred + "Moving back...");
				swiftBot.move(-speed, -speed, 500); 
				Thread.sleep(1000);
				if (getPress()) return;

				// Turn around (180 degrees)
				swiftBot.move(speed, 0, 1870); // tested for 180 degrees
				Thread.sleep(1000);
				if (getPress()) return;

				// Move away for 3 seconds
				System.out.println("Move away for 3 seconds...");
				swiftBot.move(speed, speed, 3000);
				if (getPress()) return;

				// Wait for 5 seconds
				System.out.println("Waiting for 5 seconds...");
				Thread.sleep(5000);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(Detect_Object.reset);
	}
	
	public void random_turn(int a, int b) { // random turn function for wandering and curious mode
		
		Random rand = new Random();
		int random_turn = rand.nextInt(2); // 0 or 1
		int random_degree = rand.nextInt(a, b); 

		if (random_turn == 0) {  
			swiftBot.move(speed, 0, random_degree); // Turn right
		} else {  
			swiftBot.move(0, speed, random_degree); // Turn left
		}		
	}
	
    private void takeAction(int spd) {
        object_num++;
        speed = spd;
        swiftBot.stopMove();
    }

    public int getObject_num() {
        return object_num;
    }
}
