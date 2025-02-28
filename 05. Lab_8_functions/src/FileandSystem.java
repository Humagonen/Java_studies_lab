import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class FileandSystem {

	public static void main(String args[])
	{
		String dir_name = "C:\\Users\\humag\\Masaüstü"; //Or another directory
		File dir = new File(dir_name);


		//		1)	List whether each file is a directory or not.
		File[] dir_list = dir.listFiles();
		for(int i=0;i<dir_list.length;++i){
			if(dir_list[i].isDirectory()) {
				System.out.println(dir_list[i].getName() + "    is a directory ");
			} else {
				System.out.println(dir_list[i].getName() + "    is NOT a directory ");
			}
		}

		System.out.println("\n###################### Bring docx files only ################################");

		//	2)	In addition to the above, modify the program 
		//		so that you can specify a filter on the type 
		//		of file, e.g. “*.txt”. lets only see txt files:
		//		.endsWith(".txt")
		for(int i=0;i<dir_list.length;++i){
			if (dir_list[i].getName().endsWith(".docx")) {
				System.out.println(dir_list[i].getName());
			}
		}

		System.out.println();

		// 7.2.5 File Handling

		CreateWritefileBillyGoats();

		int words = WordCountFile("C:\\Users\\humag\\Masaüstü\\BRUNEL\\01, Introductory Programming\\Java_studies_lab\\Lab_8_functions\\Billy Goats.txt"); // count this file's words
		System.out.println("\nNumber of words in this file: " + words);

		WriteSqrtToFile(5, "C:\\Users\\humag\\Masaüstü\\BRUNEL\\01, Introductory Programming\\Java_studies_lab\\Lab_8_functions\\\\Billy Goats.txt");
		
		System.out.println("\nDisplay file content: ");
		displayFileContent("C:\\Users\\humag\\Masaüstü\\BRUNEL\\01, Introductory Programming\\Java_studies_lab\\Lab_8_functions\\\\Billy Goats.txt");
		
	}

	
	
	public static void CreateWritefileBillyGoats() {
		// Specify the directory and file name
		//		String directoryPath = "C:\\Users\\humag\\Masaüstü";
		//		String fileName = "Billy Goats.txt";

		//		File directory = new File(directoryPath); // Create the directory object

		// Ensure the directory exists
		//		if (!directory.exists()) {
		//			if (directory.mkdirs()) { // Create the directory if it doesn't exist
		//				System.out.println("Directory created: " + directoryPath);
		//			} else {
		//				System.out.println("Failed to create directory: " + directoryPath);
		//				return;
		//			}
		//		}

		// Create the file object
		//		File file = new File(directoryPath + File.separator + fileName);
		File file = new File("C:\\Users\\humag\\Masaüstü\\BRUNEL\\01, Introductory Programming\\Java_studies_lab\\Lab_8_functions\\\\Billy Goats.txt");

		// Attempt to create the file
		try {
			if (file.createNewFile()) {
				System.out.println("File created successfully: " + file.getAbsolutePath());
			} else {
				System.out.println("File already exists: " + file.getAbsolutePath());
			}

			// Write content to the file
			FileWriter writer = new FileWriter(file);
			writer.write("Once upon a time there were three billy goats, who were to go up to the hillside to make themselves fat, and the name of all three was “Gruff”. On the way up was a bridge over a cascading stream they had to cross; and under the bridge lived a great ugly troll, with eyes as big as saucers, and a nose as long as a poker. So first of all came the youngest Billy Goat Gruff to cross the bridge. “Trip, trap, trip, trap!” went the bridge. “Who's that tripping over my bridge?” roared the troll. “Oh, it is only I, the tiniest Billy Goat Gruff , and I'm going up to the hillside to make myself fat,” said the billy goat, with such a small voice. “Now, I'm coming to gobble you up,” said the troll. “Oh, no! pray don't take me. I'm too little, that I am,” said the billy goat. “Wait a bit till the second Billy Goat Gruff comes. He's much bigger”. “Well, be off with you,” said the troll. A little while after came the second Billy Goat Gruff to cross the bridge. Trip, trap, trip, trap, trip, trap, went the bridge. “Who's that tripping over my bridge?” roared the troll. “Oh, it's the second Billy Goat Gruff, and I'm going up to the hillside to make myself fat,” said the billy goat, who hadn't such a small voice. “Now I'm coming to gobble you up,” said the troll. “Oh, no! Don't take me. Wait a little till the big Billy Goat Gruff comes. He's much bigger”. “Very well! Be off with you,” said the troll. But just then up came the big Billy Goat Gruff. Trip, trap, trip, trap, trip, trap! went the bridge, for the billy goat was so heavy that the bridge creaked and groaned under him. “Who's that tramping over my bridge?” roared the troll. “It's I! The big Billy Goat Gruff,” said the billy goat, who had an ugly hoarse voice of his own. “Now I'm coming to gobble you up,” roared the troll. What happened next?"); // Add your content here
			//			writer.write("\nThey wanted to cross a bridge to reach green grass."); // Append a new line
			writer.close(); // Close the writer
			System.out.println("Content written to the file successfully.");

		} catch (IOException e) {
			System.out.println("An error occurred while creating the file: " + e.getMessage());
		}

	}



	public static int WordCountFile(String filename) {
		int wordCount = 0;

		// Read the file
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;

			// Process each line
			while ((line = reader.readLine()) != null) { // while reader line is not in the end
				// Split the line into words using whitespace
				String[] words = line.split("\\s+"); // split words 
				wordCount += words.length; // and append to array
			}
		} catch (IOException e) {
			System.out.println("An error occurred while reading the file: " + e.getMessage());
		}

		return wordCount;
	}


	public static void WriteSqrtToFile(int n, String filename) {

		try (FileWriter writer = new FileWriter(filename, true)) { // 'true' enables appending to the file!!
			System.out.println(); // to go to next line first

			for (int i = 1; i <= n; i++) {  
				double sqrt = Math.sqrt(i);

				// Format the square roots
				String sqrt1Decimal = new DecimalFormat("0.0").format(sqrt);
				String sqrt2Decimals = new DecimalFormat("0.00").format(sqrt);
				String sqrt3Decimals = new DecimalFormat("0.000").format(sqrt);

				// Write to the file
				writer.write("\n" + i + ": " + sqrt1Decimal + " " + sqrt2Decimals + " " + sqrt3Decimals);

				// expected output when n= 4:
				//		1: 1.0 1.00 1.000
				//		2: 1.4 1.41 1.414
				//		3: 1.7 1.73 1.732
				//		4: 2.0 2.00 2.000

			}
			System.out.println("File written successfully to: " + filename);
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file: " + e.getMessage());
		}

	}



	// Simple function to display file contents
	public static void displayFileContent(String filename) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}

}
