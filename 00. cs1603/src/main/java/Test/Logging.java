package Test;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Logging {
    private static final String log_path = "/data/home/pi/Detect_Object/";
    private static final String image_path = "/data/home/pi/Detect_Object/";
    private long start_time;
    private long end_time;
    private long total_time;

    public Logging(long start_time) {
        this.start_time = start_time;
    }

    public void save_log(String mode_run, int object_num) {
        try (FileWriter writer = new FileWriter(log_path + "execution_log.txt")) {
            writer.write("Execution Log:\n");
            writer.write("- Mode executed: " + mode_run + "\n");
            writer.write("- Duration: " + (total_time / 1000.0) + " seconds\n");
            writer.write("- Number of objects encountered: " + object_num + "\n");
            writer.write("- Images saved at: " + image_path + "\n");
            writer.write("- Log file saved at: " + log_path + "\n");

            System.out.println("Log file saved at: " + log_path);
        } catch (IOException e) {
            System.out.println("Error writing log file: " + e.getMessage());
        }
    }

    public void display_log() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(log_path + "execution_log.txt")));
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("Error reading log file: " + e.getMessage());
        }
    }

    public void calculateExecutionTime() {
        this.end_time = System.currentTimeMillis();
        this.total_time = end_time - start_time;
    }

    public long getTotalTime() {
        return total_time / 1000;
    }
}
