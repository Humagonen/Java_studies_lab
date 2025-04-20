import java.io.FileWriter;
import java.io.IOException;

public class SwiftBotLogger { // no need for constructors
    private static final String LOG_PATH = "/data/home/pi/Detect_Object/execution_log.txt";

    public void saveLog(String mode, int objectCount) {
        try (FileWriter writer = new FileWriter(LOG_PATH)) {
            writer.write("Execution Log:\n");
            writer.write("- Mode executed: " + mode + "\n");
            writer.write("- Objects encountered: " + objectCount + "\n");
            writer.write("- Log saved at: " + LOG_PATH + "\n");
            System.out.println("Log saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing log file: " + e.getMessage());
        }
    }
}
