package Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import swiftbot.ImageSize;

public class BotCamera2 {
	
	
	public static String image_path = "/data/home/pi/Detect_Object/";
	
	public BotCamera2()
	{
		
	}
	
	

	public void take_image_save(String img_name) {
	    try {
	        BufferedImage image = Detect_Object2.swiftBot.takeStill(ImageSize.SQUARE_240x240);

	        if (image == null) {
	            System.out.println("ERROR: Image is null");
	        } else {
	            // Format: ddMMyyyy_HHmmss (Day/Month/Year_HourMinuteSecond)
	            String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
	            String imgFilename = img_name + "_" + timestamp + ".jpg"; // Append timestamp

	            // Save the image
	            ImageIO.write(image, "jpg", new File(image_path + imgFilename));

	            System.out.println("SUCCESS: Image Saved as " + imgFilename);
	            Thread.sleep(1000);
	        }
	    } catch (Exception e) {
	        System.out.println("\nCamera not enabled!");
	        System.out.println("Try running the following command: ");
	        System.out.println("sudo raspi-config nonint do_camera 0\n");
	        System.out.println("Then reboot using the following command: ");
	        System.out.println("sudo reboot\n");
	        System.exit(5);
	    }
	}



	public void QRCodeDetection(String selected_mode) {

		System.out.println("Starting 15s timer to scan a QR code");

		try {
			while (true) {  // will loop back and start 15s timer each time no qr code has been scanned. 

				long startTime = System.currentTimeMillis();
				long endTime = startTime + 15000; // 15 seconds in milliseconds

				while (System.currentTimeMillis() < endTime) {

					BufferedImage img = Detect_Object2.swiftBot.getQRImage();
					selected_mode = Detect_Object2.swiftBot.decodeQRImage(img);

					if (selected_mode.isEmpty()) {
						System.out.println(
								"No QR Code was found. Try adjusting the distance of the SwiftBot's Camera from the QR code, or try another.");
					} else {
						System.out.println("SUCCESS: QR code found"); 
						System.out.println("Decoded message: " + selected_mode);
						return;
					}

					System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds");
					Thread.sleep(1000);
				}
				
				System.out.println("15 second is over...");
			}
		} catch (Exception e) {
			System.out.println("ERROR: Unable to scan for code.");
			e.printStackTrace();
			System.exit(5);
		}
	}
	
	

}
