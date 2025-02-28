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
