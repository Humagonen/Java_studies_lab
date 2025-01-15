import java.util.Arrays;

public class Revision {

	public static void main(String[] args) {

		int[] min = {4, 1, 30, 12};
		int[] joules = {50, 120, 75, 220};
		double[] pounds = {10.5, 11.1, 523.2, 75.3};
		int[] temp = {13, 44, 16, 11};

		min_to_sec(min);  // input int, return int
		System.out.println();
		
		joule_to_kilojoule(joules); // input int, return double
		System.out.println();
		
		pounds_to_dollars(pounds);  // input double, return double
		System.out.println();
		
		celcius_to_fahrenheit(temp); // input int, return double
	}



	public static int[] min_to_sec(int[] min) {
		int[] seconds = new int[min.length];

		for (int i = 0; i < seconds.length; i++) {

			seconds[i] = min[i] * 60;
			System.out.print(seconds[i] + ",");
		}

		System.out.println("\n" + Arrays.toString(seconds));
		return seconds;
	}




	public static double[] joule_to_kilojoule(int[] joules) {
		
		double[] kiloJoule = new double[joules.length];

		for (int i = 0; i < kiloJoule.length; i++) {

			kiloJoule[i] = joules[i] * 0.001;
			System.out.print(kiloJoule[i] + ",");
		}

		System.out.println("\n" + Arrays.toString(kiloJoule));
		return kiloJoule;
		
	}


	public static double[] pounds_to_dollars(double [] pounds) {

		double[] dollars = new double[pounds.length];

		for (int i = 0; i < dollars.length; i++) {

			dollars[i] = pounds[i] * 1.22;
			System.out.print(dollars[i] + ",");
		}

		System.out.println("\n" + Arrays.toString(dollars));
		return dollars;

	}


	public static double[] celcius_to_fahrenheit(int [] temp) {

		double[] fahrenheit = new double[temp.length];

		for (int i = 0; i < fahrenheit.length; i++) {

			fahrenheit[i] = temp[i] * 9.0/5.0 + 32;  // IMPORTANT
			// if we want to return double, do the calculations with double
			System.out.print(fahrenheit[i] + ",");
		}

		System.out.println("\n" + Arrays.toString(fahrenheit));
		return fahrenheit;
	}
}
