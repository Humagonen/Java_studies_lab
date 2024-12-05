import java.text.DecimalFormat;
import java.util.Random;

public class StringandMathFunctions {

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

		//Q1

		System.out.println("\nQ1:");
		Q1_Display3Decimal(y);

		//Q2
		System.out.println("\nQ2:");
		System.out.println("Number of digits before and after the decimal point:");
		Q2_analyzeDigits("x", x);
		Q2_analyzeDigits("y", y);
		Q2_analyzeDigits("z", z);

		// Q3
		System.out.println("\nQ3:");
		String c = Q3_ConcatReverse(a,b);  
		// return type added to assign to c, still gives output
		
		// Q4
		System.out.println("\nQ4:");
		Q4_ReplaceStr(c, "ll", "ppp");
		
		// 7.2.2. Math functions
		
		System.out.println("\nMath Functions:");
		RoundingTest(10.20);
		
		// 7.2.4	Random Number Generation
		
		Random rand = new Random();
		for(int i=0;i<10;++i)
		{
			System.out.println(rand.nextInt(-100,100));
		}
		
	}

	// Q7.2.1 Strings
	// 1)	Display ‘y’ to three decimal places
	public static void Q1_Display3Decimal(double y) {

		DecimalFormat number_format = new DecimalFormat("#.###");
		String formatted_num = number_format.format(y);
		System.out.println("Formatted number (y): " + formatted_num);

	}
	// 2)	Display how many digits come before the decimal point 
	// and how many comes after a number. Test this program on ‘x’, ‘y’ and ‘z’
	public static void Q2_analyzeDigits(String name, double num) {
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

	// 3)	Create a string ‘c’ that consists of ‘a’ in reverse 
	// concatenated with ‘b’, including a space between them

	public static String Q3_ConcatReverse(String d, String e) {

		// going to take c in reverse and e normal and concat them 
        String reversed = new StringBuilder(d).reverse().toString();
        String original = e;
        String c = reversed + " " + original; // olleh World
        System.out.println("\n" + c); // Output: olleh World

        return c;
	}

	// 4)	Search for the substring 'll' in ‘c’ and replace it for 'ppp'
	public static void Q4_ReplaceStr(String a, String b, String c) {

		// going to take c in reverse and e normal and concat them 
		String changedstr = a.replace(b, c);
        System.out.println("\n" + changedstr); // Output: olleh

	}
	
	
	// 7.2.2. Mathematical Functions

	private static void RoundingTest(double x) 
	{
		double ceil = Math.ceil(x);
		double floor = Math.floor(x);
		double round = Math.round(x);
		//System.out.println("\nFor: " + x + ", ceil: "+ ceil + ", floor: " + floor + ", and round: " + round );
	    // the output of this doesnt give specific formats
		
		// Use printf or format for formatted output
		// %1 -> first parameter (x)
		// $.5f -> format double to 5 decimal places
		
		// printf is formatted string printing method
	    System.out.printf("\nFor: %1$.5f, ceil: %2$.5f, floor: %3$.6f, and round: %4$.0f%n", x, ceil, floor, round);
	    // x here is the first parameter!
	    // On Windows, \n might not render correctly in certain contexts. %n avoids such issues. u can use %n instead.
	    
	    // OR this method can be used too:
	    String s = String.format("For: %1$.5f, ceil: %2$.5f, floor: %3$.6f, and round: %4$.0f%n", x, ceil, floor, round);
	    System.out.print("Second method:\n" + s);
	}
	
	
	
	
	
	
}
