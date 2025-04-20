package Test;
public class SwiftBotController {
    private SwiftBotAPI swiftBot;
    private SwiftBotLogger logger;  // Logger instance
    private boolean press = false;
    private boolean buttonpressX = false;
    private boolean buttonpressY = false;
    private String selected_mode = "";
    private String mode_run = "";
    private SwiftBotMovement movement;
    private SwiftBotCamera camera;

    public SwiftBotController() {
        try {
            swiftBot = new SwiftBotAPI();
        } catch (Exception e) {
            System.out.println("I2C disabled! Run: sudo raspi-config nonint do_i2c 0");
            System.exit(5);
        }

        // Initialize Logger (start time is recorded automatically)
        logger = new SwiftBotLogger();

        movement = new SwiftBotMovement(swiftBot);
        camera = new SwiftBotCamera(swiftBot);

        // Enable termination button
        swiftBot.enableButton(Button.X, this::terminate_program);
    }

    public void start() {
        greeting();
        get_mode();
        execute_mode();
    }

    private void terminate_program() {
        press = true;
        System.out.println("Terminating...");

        // Save execution time and log details
        logger.save_log(mode_run, movement.getObjectCount());

        System.out.println("Would you like to view the execution log?");
        System.out.println("Press button Y for 'Yes', and X for 'No' on the SwiftBot.");

        swiftBot.disableAllButtons();
        swiftBot.enableButton(Button.X, () -> {
            buttonpressX = true;
            swiftBot.disableAllButtons();
        });

        swiftBot.enableButton(Button.Y, () -> {
            buttonpressY = true;
            swiftBot.disableAllButtons();
        });

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (buttonpressX) {
            System.out.println("\nButton X is pressed. Exiting...");
        } else if (buttonpressY) {
            System.out.println("\nButton Y is pressed.");
            logger.display_log();  // Show logs before exiting
        } else {
            System.out.println("\nNo button pressed in time.");
        }

        System.out.println("\nProgram ended. Thank you for using SwiftBot!");
        System.exit(0);
    }

    public static void main(String[] args) {
        SwiftBotController controller = new SwiftBotController();
        controller.start();
    }
}



