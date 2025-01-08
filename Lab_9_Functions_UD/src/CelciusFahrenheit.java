
public class CelciusFahrenheit {


	public static void main(String args[])
	{
		int[] temp = {13, 44, 16, 11};
		int[] temp1 = {-13, -4, 16, 10};
		String[] temp2 = {"abc", "", "16", "1"};

		System.out.println("Conversion for: {13, 44, 16, 11}");
		CelsiusToFahrenheit(temp);
		System.out.println("\nConversion for (negative values): {-13, -4, 16, 10}"); 
		CelsiusToFahrenheit(temp1); // return 0 for negative values
		System.out.println("\nConversion for (String): {\"abc\", \"\", \"16\", \"1\"}");
		CelsiusToFahrenheitStr(temp2);

	}

	// gets int as input, double as output
	static public double[] CelsiusToFahrenheit(int[] celsius)  
	{
		double[] fahrenheit = new double[celsius.length];

		for (int i = 0; i < fahrenheit.length; i++) {
			if (celsius[i] < 0) {
				fahrenheit[i] = 0.0;
			} else{
				fahrenheit[i] = celsius[i] * 9/5 + 32;
			}
			System.out.println(fahrenheit[i]);
		}
		return fahrenheit;
	}


	// Gets string as input, double as output (need to parse from str to double)
	static public double[] CelsiusToFahrenheitStr(String[] celsius)
	{
		double[] fahrenheit = new double[celsius.length];

		for (int i = 0; i < fahrenheit.length; i++) {

			if ((celsius[i] == "") || (celsius[i] == " ") || (celsius[i].matches("[a-zA-Z]+"))) {

				fahrenheit[i] = 0.0;

			} else {
				fahrenheit[i] = Integer.parseInt(celsius[i]);
			}

			System.out.println(fahrenheit[i]);

		} return fahrenheit;
	}
}
