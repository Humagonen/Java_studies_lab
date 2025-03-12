import java.awt.image.BufferedImage;

public class DetectQR {
	static Detect_Object DO = new Detect_Object();
	
	public static void QRCodeDetection() {

		System.out.println("Starting 15s timer to scan a QR code");
		boolean check = true;

		try {

			while (check) {   // will loop back and start 15s timer each time no code has been scanned. 

				long startTime = System.currentTimeMillis();
				long endTime = startTime + 15000; // 15 seconds in milliseconds

				while (System.currentTimeMillis() < endTime) {

					BufferedImage img = DO.swiftBot.getQRImage();
					DO.selected_mode = DO.swiftBot.decodeQRImage(img);

					if (DO.selected_mode.isEmpty()) {
						System.out.println(
								"No QR Code was found. Try adjusting the distance of the SwiftBot's Camera from the QR code, or try another.");
					} else {
						System.out.println("SUCCESS: QR code found"); 
						System.out.println("Decoded message: " + DO.selected_mode);
						check = false;
						break;
					}

					System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds");
					Thread.sleep(1000);
				}

			}
		} catch (Exception e) {
			System.out.println("ERROR: Unable to scan for code.");
			e.printStackTrace();
			System.exit(5);
		}
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




//public static void wandering() { 
//
//// does not check continuously while wandering. 
////has to check at anytime if theres an object. instead it waits for the method to finish then check
//
//double n = 50.0;  // for scaredy mode 
//
//if(selected_mode == "Curious SwiftBot") {
//	n = 100.0;
//}
//
//while (swiftBot.useUltrasound() > n) {		// check at any time 
//	speed = 40;
//	// turn all leds to blue
//	System.out.println("Wandering... (Speed:40)"
//			+ "\nUnderlights: Blue\n");
//
//	swiftBot.move(speed, speed, 5000);
//
//	System.out.println("\nWaiting for 1 second after 5 seconds");
//
//	try {
//		Thread.sleep(1000);
//	} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//
//	System.out.println("\nChanging direction randomly...");
//
//	// 		randomly choose left or right and the degree to turn by adjusting time (random_degree)
//	// 		20-90 degrees in speed 60 --> check with bot
//	Random rand = new Random();
//
//	int random_turn = rand.nextInt(0,2); // will generate either 0 or 1
//	int random_degree = rand.nextInt(250, 2000);
//
//	if (random_turn == 0) {  // turn right
//
//		swiftBot.move(speed, 0, random_degree);
//
//	} else {  // turn left
//
//		swiftBot.move(0, speed, random_degree);
//	}
//}
//}
	





//
//public static void curious_swiftbot() {  // this is in a loop in main
//
//	while (swiftBot.useUltrasound() > 100.0) { // chose the detection distance as 100 cm - can be added as additional func
//		wandering();
//	}
//	// while(true){
//	try {
//
////		while (swiftBot.useUltrasound() > 100.0 && object_num != 0) { // if its not the first object being detected
////			wandering();
////		}
//		
//		swiftBot.stopMove();
//		speed = 60;
//		object_num ++;
//		
//		double dist_to_obj = swiftBot.useUltrasound();
//
//		if (dist_to_obj < 30.0) {
//
//			// go back until 30.0 cm is restored
//
//			while (swiftBot.useUltrasound() != 30.0) {  // goes forever
//
//				swiftBot.startMove(-speed, -speed);
//
//			}
//			swiftBot.stopMove();
//
//
//		} else if (dist_to_obj > 30.0) {
//
//			// go forward until 30.0 cm is restored
//
//			while (swiftBot.useUltrasound() != 30.0) {
//
//				swiftBot.startMove(speed, speed);
//
//			}
//			swiftBot.stopMove();
//
//		} else { // dist_to_obj == 30.0
//
//			// blink underlights in green
//
//		}
//
//		Thread.sleep(1000);
//
//		System.out.println("Taking Picture...");
//		Thread.sleep(1000);
//		take_image_save("curious.jpg");
//		
//		// check again if there's an object
//		double dist_to_obj2 = swiftBot.useUltrasound();
//		// if the same object, and no new object is detected
//		if (dist_to_obj2 == 30.0){
//			// move in a slightly different direction (randomly choose between |20-90| degrees right or left)
//			Random rand = new Random();
//			int random_num = rand.nextInt(0,2);
//			int random_time = rand.nextInt(250,2500);
//
//			if (random_num == 0) { // if 0, turn right
//
//				swiftBot.move(speed, 0, random_time);
//
//			} else { // if 1, turn left
//				swiftBot.move(0, speed, random_time);
//			}
//		}else if (dist_to_obj2 != 30.0 && dist_to_obj2 < 100.0){ // new object detected within that 30 cm
//			// && dist_to_obj2 < 100.0 -> not sure if I need to add this
//		}
//
//	} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	// } end of while
//}







//
//	public static void curious_swiftbot() {  // This method runs in a loop in main
//
//		int[] green = {0, 255, 0}; // Green
//		while(press == false) {  // try this instead of while true. unless X is pressed 
//
//			while (swiftBot.useUltrasound() > 100.0) { // Moves until an object is within 100 cm
//				wandering();
//			}
//
//			try {
//				swiftBot.stopMove();
//				speed = 60;
//				object_num++;
//
//				double dist_to_obj = swiftBot.useUltrasound();
//
//				if (dist_to_obj < 30.0) { // if within 30 cm 
//					// Move backward slightly until distance is at least 30.0 cm
//					swiftBot.fillUnderlights(green);
//					while (swiftBot.useUltrasound() < 29.5) {
//						swiftBot.startMove(-speed, -speed);
//					}
//					swiftBot.disableUnderlights();
//					Thread.sleep(300);
//					
//				} else if (dist_to_obj > 30.0) { // if outside 30 cm
//					// Move forward slightly until distance is at most 30.0 cm
//					swiftBot.fillUnderlights(green);
//					while (swiftBot.useUltrasound() > 30.5) {
//						swiftBot.startMove(speed, speed);
//					}
//					swiftBot.disableUnderlights();
//					Thread.sleep(300);
//					
//				} else { // if already in 30 cm, just blink underlights
//
//					// Blink underlights green 3 times
//					for (int i = 0; i < 3; i++) {
//						swiftBot.fillUnderlights(green); 
//						Thread.sleep(300);
//						
//						swiftBot.disableUnderlights();
//						Thread.sleep(300);
//					}
//				}
//				
//				swiftBot.stopMove();
//
//				Thread.sleep(1000);
//
//				System.out.println("Taking Picture...");
//				Thread.sleep(1000);
//				take_image_save("curious.jpg");
//
//				// Move in a slightly different direction (random left/right turn)
//				Random rand = new Random();
//				int random_num = rand.nextInt(2);  // 0 or 1
//				int random_time = rand.nextInt(500, 2000);
//
//				if (random_num == 0) { // If 0, turn right
//					
//					swiftBot.move(speed, 0, random_time);
//					
//				} else { // If 1, turn left
//					
//					swiftBot.move(0, speed, random_time);
//				}
//
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}




//
//	public static void wandering() { 
//	    double n = 50.0;  // Threshold for scaredy mode
//
//	    int[] blue = {0, 0, 255}; // blue color
//	    swiftBot.fillUnderlights(blue);
//	    
//	    if(selected_mode.equals("Curious SwiftBot")) {
//	        n = 100.0;
//	    }
//
//	    Random rand = new Random();
//
//	    while (press == false) { // Infinite loop, exit when an object is detected. unless x is pressed
//	        
//	    	if (swiftBot.useUltrasound() <= n) {
//	            System.out.println("Obstacle detected! Stopping1.");
//	            swiftBot.stopMove();
//	            break;
//	        }
//	    	// test if this if else statement is needed?
//
//	        speed = 40;
//
//	        System.out.println("Wandering... (Speed:40)"
//	                         + "\nUnderlights: Blue\n");
//
//	        // Move in smaller steps (100ms at a time) instead of blocking for 5 seconds
//	        for (int i = 0; i < 50; i++) { // 50 * 100ms = 5000ms
//	            if (swiftBot.useUltrasound() <= n) { // Check frequently
//	                System.out.println("Obstacle detected! Stopping2.");
//	                swiftBot.stopMove();
//	                swiftBot.disableUnderlights();
//	                return;
//	            }
//	            swiftBot.move(speed, speed, 100);
//	        }
//
//	        System.out.println("\nWaiting for 1 second after 5 seconds");
//
//	        try {
//	            Thread.sleep(1000);
//	        } catch (InterruptedException e) {
//	            e.printStackTrace();
//	        }
//
//	        System.out.println("\nChanging direction randomly...");
//
//	        int random_turn = rand.nextInt(2); // 0 or 1
//	        int random_degree = rand.nextInt(250, 2000);
//
//	        if (random_turn == 0) {  
//	            swiftBot.move(speed, 0, random_degree); // Turn right
//	        } else {  
//	            swiftBot.move(0, speed, random_degree); // Turn left
//	        }
//	    }
//	}
