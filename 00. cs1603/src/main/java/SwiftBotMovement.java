import swiftbot.SwiftBotAPI;
import java.util.Random;

public class SwiftBotMovement {
    private SwiftBotAPI swiftBot;
    private int speed = 40;
    private int objectCount = 0;

    public SwiftBotMovement(SwiftBotAPI swiftBot) { // constructor
        this.swiftBot = swiftBot;
    }

    public void wandering() {
        double threshold = 50.0;
        swiftBot.fillUnderlights(new int[]{0, 0, 255}); // Blue light

        while (swiftBot.useUltrasound() > threshold) {
            swiftBot.move(speed, speed, 500);
        }
        swiftBot.stopMove();
    }

    public void curiousMode() {
        System.out.println("Curious SwiftBot Mode");
        while (true) {
            wandering();
            if (swiftBot.useUltrasound() <= 80.0) {
                takeAction("curious");
            }
        }
    }

    public void scaredyMode() {
        System.out.println("Scaredy SwiftBot Mode");
        while (true) {
            wandering();
            if (swiftBot.useUltrasound() <= 50.0) {
                takeAction("scaredy");
            }
        }
    }

    public void randomMode() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            curiousMode();
        } else {
            scaredyMode();
        }
    }

    private void takeAction(String mode) {
        objectCount++;
        swiftBot.stopMove();
        System.out.println("Object detected! Taking action for " + mode + " mode.");
    }

    public int getObjectCount() {
        return objectCount;
    }
}
