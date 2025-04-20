import swiftbot.Button;
import swiftbot.SwiftBotAPI;
import java.util.Scanner;

public class SwiftBotController {
    private SwiftBotAPI swiftBot;
    private boolean press = false;
    private String selectedMode = "";
    private SwiftBotMovement movement;
    private SwiftBotCamera camera;
    private SwiftBotLogger logger;

    public SwiftBotController() {  // controller
        try {
            swiftBot = new SwiftBotAPI();
        } catch (Exception e) {
            System.out.println("I2C disabled! Run: sudo raspi-config nonint do_i2c 0");
            System.exit(5);
        }
        movement = new SwiftBotMovement(swiftBot);
        camera = new SwiftBotCamera(swiftBot);
        logger = new SwiftBotLogger();
        
        swiftBot.enableButton(Button.X, this::terminateProgram);
    }

    public void start() {
        greeting();
        getMode();
        executeMode();
    }

	public static void greeting() {
		System.out.println("________          __                 __        _____  __       __               __   ");
		System.out.println("\\______ \\   _____/  |_  ____   _____/  |_     /  _  \\|  |_    |__| ____   _____/  |_ ");
		System.out.println(" |  | |  \\_/ __ \\   __\\/ __ \\_/ ___\\   __\\   |  | |  |  _ \\   |  |/ __ \\_/ ___\\   __\\");
		System.out.println(" |  |_/   \\  ___/|  | \\  ___/\\  \\___|  |     |  |_|  | |_/ \\  |  \\  ___/\\  \\___|  |  ");
		System.out.println("/_______ / \\____ >__|  \\____ >\\____ >__|      \\_____/\\____/\\__|  |\\____ >\\____ >__|  ");
		System.out.println("                                                          \\______|                   ");
		System.out.println("------------------------------------");
		System.out.println(" Welcome to SwiftBot Control System");
		System.out.println("------------------------------------");
		System.out.println("SwiftBot will continuously scan its surroundings.  ");
		System.out.println("If an object is detected, it will react based on the selected mode.  ");
		System.out.println();
		System.out.println("Press the 'X' button on SwiftBot at any time to stop the program.");
		System.out.println("-------------------------------------------------------------------");
		System.out.println("\n");
	}

    private void getMode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose mode: (1) QR Scan, (2) Manual Entry");
        while (true) {
            String choice = scanner.next();
            if (choice.equals("1")) {
                selectedMode = camera.scanQRCode();
                break;
            } else if (choice.equals("2")) {
                System.out.println("Enter mode (Curious, Scaredy, Dubious):");
                scanner.nextLine();
                selectedMode = scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid option, try again.");
            }
        }
    }

    private void executeMode() {
        if (selectedMode.equals("Curious SwiftBot")) {
            movement.curiousMode();
        } else if (selectedMode.equals("Scaredy SwiftBot")) {
            movement.scaredyMode();
        } else if (selectedMode.equals("Dubious SwiftBot")) {
            movement.randomMode();
        } else {
            System.out.println("Invalid mode.");
            System.exit(0);
        }
    }

    private void terminateProgram() {
        press = true;
        System.out.println("Terminating...");
        logger.saveLog(selectedMode, movement.getObjectCount());
        System.exit(0);
    }

    public static void main(String[] args) {
        SwiftBotController controller = new SwiftBotController();
        controller.start();
    }
}

