package Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Logs2 
{
	private static String log_path = "/data/home/pi/Detect_Object/";
	
	private static Run_modes2 mode= new Run_modes2();
	private static BotCamera2 camera= new BotCamera2();
	
	public Logs2()
	{
		
	}
	
	
	public void save_log(String mode_run, int object_num, long total_time, String image_path) {

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
	        String content = new String(Files.readAllBytes(Paths.get(log_path + "execution_log.txt"))); // Read file content
	        System.out.println(content); // Print log content
	    } catch (IOException e) {
	        System.out.println("Error reading log file: " + e.getMessage());
	    }
	}

}
