import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import swiftbot.ImageSize;
import swiftbot.SwiftBotAPI;

public class BotCamera {
	
    private SwiftBotAPI swiftBot;
    private static final String image_path = "/data/home/pi/Detect_Object/";

    public BotCamera(SwiftBotAPI swiftBot) {  
        this.swiftBot = swiftBot; 
    }

	public void take_image_save(String img_name) {
	    try {
	        BufferedImage image = swiftBot.takeStill(ImageSize.SQUARE_240x240);

	        if (image == null) {
	            System.out.println(Detect_Object.red + "ERROR: Image is null" + Detect_Object.reset);
	        } else {
	            // Format: ddMMyyyy_HHmmss (Day/Month/Year_HourMinuteSecond)
	            String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
	            String imgFilename = img_name + "_" + timestamp + ".jpg"; // Append timestamp to make image name unique

	            // Save the image
	            ImageIO.write(image, "jpg", new File(image_path + imgFilename));

	            System.out.println(Detect_Object.green + "SUCCESS" + Detect_Object.reset + ": Image Saved as " + imgFilename + "\n");
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


	public String QRCodeDetection(String selected_mode) {

		System.out.println(Detect_Object.blue + "Starting 15s timer to scan a QR code\n" + Detect_Object.reset);
		int tries = 0;
		
		try {
			while (tries < 5) {  // will loop back and start 15s timer each time no qr code has been scanned. 

				long startTime = System.currentTimeMillis();
				long endTime = startTime + 15000; // 15 seconds in milliseconds

				while (System.currentTimeMillis() < endTime) {

					BufferedImage img = swiftBot.getQRImage();
					selected_mode = swiftBot.decodeQRImage(img);

					if (selected_mode.isEmpty()) {
						System.out.println("No QR Code was found. Try adjusting the distance of the SwiftBot's Camera from the QR code, or try another.");
					} else {
						System.out.println(Detect_Object.green + "SUCCESS: QR code found" + Detect_Object.reset); 
						System.out.println("Decoded message: " + selected_mode);
						return selected_mode;
					}

					System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds");
					Thread.sleep(1000);
				}
				
				System.out.println(Detect_Object.blue + "\n15 second is over...\n" + Detect_Object.reset);
				System.out.println("Tries left: " + Detect_Object.blue + (4 - tries) + Detect_Object.reset);
				System.out.println("\n--------------------------------------------------\n");
				tries ++;
			}
			
			System.out.println(Detect_Object.blue + "\nNo QR code has been scanned in time.\n" + Detect_Object.reset);
		} catch (Exception e) {
			System.out.println(Detect_Object.red + "ERROR: Unable to scan for code." + Detect_Object.reset);
			e.printStackTrace();
			System.exit(5);
		}
		return selected_mode;
	}
}
