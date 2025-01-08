
public class StringOrder {

	public static void main(String[] args) {

		String name1 = "Hailey";
		String name2 = "Huma";
		String name3 = "Samira"; 

		// Variables to store the sorted names
		String first, second, third;

		// Using if-else to sort the names in alphabetical order
		if (name1.compareTo(name2) <= 0 && name1.compareTo(name3) <= 0) {
			
			first = name1;
			
			if (name2.compareTo(name3) <= 0) {
				second = name2;
				third = name3;
			} else {
				second = name3;
				third = name2;
			}
			
		} else if (name2.compareTo(name1) <= 0 && name2.compareTo(name3) <= 0) {
			
			first = name2;
			
			if (name1.compareTo(name3) <= 0) {
				second = name1;
				third = name3;
			} else {
				second = name3;
				third = name1;
			}
			
		} else {
			
			first = name3;
			
			if (name1.compareTo(name2) <= 0) {
				second = name1;
				third = name2;
			} else {
				second = name2;
				third = name1;
			}
		}

		// Output the sorted names
		System.out.println("The names in alphabetical order are: " + first + ", " + second + ", " + third);
	}
}
