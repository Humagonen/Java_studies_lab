import java.text.DecimalFormat;

public class Lab_excercises {

	public static void main(String args[])
	{
		double number = 1.0/3.0;

		System.out.println(number);

		DecimalFormat number_format = new DecimalFormat("#.##");
		String formatted_string = number_format.format(number);
		System.out.println("Formatted number: " + formatted_string);

		double x = 123.45, y = 17.0/3.0, z = Math.sqrt(2);
		String a = "Hello", b = "World"; 

		System.out.println("y: " + y);
		System.out.println("z: " + z);
		System.out.println("x: " + x);
		System.out.println("a: " + a);
		System.out.println("b: " + b);

		Q1(y);

		System.out.println("Number of digits before and after the decimal point:");
		analyzeDigits("x", x);
		analyzeDigits("y", y);
		analyzeDigits("z", z);

	}

	// Q7.2.1 Strings
	public static void Q1(double y) {

		// 1
		DecimalFormat number_format = new DecimalFormat("#.###");
		String formatted_num = number_format.format(y);
		System.out.println("Formatted number (y): " + formatted_num);

	}
	// 2
	public static void analyzeDigits(String name, double num) {
		// Convert the number to a string
		String numStr = String.valueOf(num);

		// Find the index of the decimal point
		int decimalIndex = numStr.indexOf('.');

		// Count digits before the decimal point
		int digitsBefore = (decimalIndex == -1) ? numStr.length() : decimalIndex;
//		(decimalIndex == -1)   if 
		// Count digits after the decimal point
		int digitsAfter = (decimalIndex == -1) ? 0 : numStr.length() - decimalIndex - 1;

		System.out.println(name + ": " + digitsBefore + " digits before and " + digitsAfter + " digits after the decimal point.");
	}

}
