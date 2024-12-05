import java.io.File;

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
			}else {
				System.out.println(dir_list[i].getName() + "    is NOT a directory ");
			}
		}

		//	2)	In addition to the above, modify the program 
		//		so that you can specify a filter on the type 
		//		of file, e.g. “*.txt”.
//		for(int i=0;i<dir_list.length;++i){
//			// Find the file extension
//			int dotIndex = dir_list.lastIndexOf('.');
//			String fileType = (dotIndex > 0) ? fileName.substring(dotIndex + 1) : "Unknown";
//
//			// Print the result
//			System.out.println("File Name: " + fileName);
//		}

	}

}
