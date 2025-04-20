import swiftbot.SwiftBotAPI;
import swiftbot.ImageSize;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

public class SwiftBotCamera {
    private SwiftBotAPI swiftBot;
    private static final String IMAGE_PATH = "/data/home/pi/Detect_Object/";

    public SwiftBotCamera(SwiftBotAPI swiftBot) { // Constructor
        this.swiftBot = swiftBot;
    }

    public void captureImage(String mode) {
        try {
            BufferedImage image = swiftBot.takeStill(ImageSize.SQUARE_240x240);
            if (image != null) {
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String filename = IMAGE_PATH + mode + "_" + timestamp + ".jpg";
                ImageIO.write(image, "jpg", new File(filename));
                System.out.println("Image saved: " + filename);
            } else {
                System.out.println("ERROR: Image is null");
            }
        } catch (IOException e) {
            System.out.println("Error saving image: " + e.getMessage());
        }
    }

    public String scanQRCode() {
        try {
            BufferedImage img = swiftBot.getQRImage();
            return swiftBot.decodeQRImage(img);
        } catch (Exception e) {
            System.out.println("Error scanning QR code: " + e.getMessage());
            return "";
        }
    }
}

