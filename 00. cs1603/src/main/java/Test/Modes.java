package Test;
import swiftbot.SwiftBotAPI;
import java.util.Random;

public class Modes {
    private SwiftBotAPI swiftBot;
    private int speed = 40;
    private int object_num = 0;

    public Modes(SwiftBotAPI swiftBot) { // Constructor
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

    public void curious_swiftbot() {
        System.out.println("Executing Curious SwiftBot Mode...");
        while (true) {
            wandering();
            if (swiftBot.useUltrasound() <= 80.0) {
                take_action("curious");
            }
        }
    }

    public void scaredy_swiftbot() {
        System.out.println("Executing Scaredy SwiftBot Mode...");
        while (true) {
            wandering();
            if (swiftBot.useUltrasound() <= 50.0) {
                take_action("scaredy");
            }
        }
    }

    public void select_random_mode() {
        Random rand = new Random();
        if (rand.nextBoolean()) {
            curious_swiftbot();
        } else {
            scaredy_swiftbot();
        }
    }

    private void take_action(String mode) {
        object_num++;
        swiftBot.stopMove();
        System.out.println("Object detected! Taking action for " + mode + " mode.");
    }

    public int getObjectCount() {
        return object_num;
    }
}
