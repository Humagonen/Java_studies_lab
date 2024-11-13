
public class Check_divisable {

	//	•	Displays if a whole number (>0) is divisible by 2 and 3
	//	•	Displays if a whole number (>0) is divisible by 7 or 9
	//	•	Displays if a whole number (>0) is divisible by 2 and 3 but not 5

	public static void main(String[] args) {
		int x = 14; 

		if (x > 0 && (x % 2 == 0 && x % 3 == 0)) {

			System.out.println("Whole number is divisable by 2 and 3");
			System.out.println("The number is:" + x);
		}
		else if (x > 0 && (x % 7 == 0 || x % 9 == 0)) {

			System.out.println("Whole number is divisable by 7 or 9");
			System.out.println("The number is:" + x);
		}
		else if (x > 0 && (x % 2 == 0 && x % 3 == 0 && x % 5 != 0)) {

			System.out.println("Whole number is divisible by 2 and 3 but not 5");
			System.out.println("The number is:" + x);
		}
		else {
			System.out.println("Does not apply");
			System.out.println("The number is:" + x);
		}

	}

}
