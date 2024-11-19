
public class Mock_test2 {

	public static void main(String args[]){

		int N = 5;

		System.out.print("First solution:\n");
		String sequence = MakeSequence(N);
		System.out.println(sequence); 

		System.out.print("Second solution:\n");
		MakeSequence2(N);


	}

	public static String MakeSequence(int N) {
		// Handle invalid input
		if (N < 1) {
			return ""; 
		}

		StringBuilder result = new StringBuilder();

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N - i + 1; j++) {
				result.append(i); // Append 'i' to the sequence
			}
		}

		// Return the constructed sequence as a String
		return result.toString();
	}

	// second way
	public static String MakeSequence2(int N) 
	{
		String result = "";

		if (N < 1) {

			return ""; 
		}
		else{

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N -i + 1 ; j++) {
					System.out.print(i);
					result = result + i; 
				}
			}
			return result;
		}

	}


}
