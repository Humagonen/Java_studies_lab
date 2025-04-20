package Test;
import swiftbot.SwiftBotAPI;
import swiftbot.ImageSize;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;

public class Camera {
    private SwiftBotAPI swiftBot;
    private static final String image_path = "/data/home/pi/Detect_Object/";

    public Camera(SwiftBotAPI swiftBot) { // Constructor
        this.swiftBot = swiftBot;
    }

    public void take_image_save(String img_name) {
        try {
            BufferedImage image = swiftBot.takeStill(ImageSize.SQUARE_240x240);
            if (image == null) {
                System.out.println("ERROR: Image is null");
            } else {
                String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
                String imgFilename = img_name + "_" + timestamp + ".jpg";
                ImageIO.write(image, "jpg", new File(image_path + imgFilename));
                System.out.println("SUCCESS: Image Saved as " + imgFilename);
            }
        } catch (IOException e) {
            System.out.println("Error saving image: " + e.getMessage());
        }
    }

    public String QRCodeDetection() {
        try {
            BufferedImage img = swiftBot.getQRImage();
            return swiftBot.decodeQRImage(img);
        } catch (Exception e) {
            System.out.println("Error scanning QR code: " + e.getMessage());
            return "";
        }
    }
}
