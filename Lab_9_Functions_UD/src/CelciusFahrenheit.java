
public class CelciusFahrenheit {
	
	
	public static void main(String args[])
	{
		int[] temp = {13, 44, 16, 11};
		int[] temp1 = {-13, -4, 16, 10};
		String[] temp2 = {"abc", "", "16", "1"};
		
		CelsiusToFahrenheit(temp);
		System.out.println();
		CelsiusToFahrenheit(temp1);
		System.out.println();
		CelsiusToFahrenheitStr(temp2);
		
	}
	
	
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
	
	
	
	static public double[] CelsiusToFahrenheitStr(String[] celsius)
	{
		double[] fahrenheit = new double[celsius.length];
		
		for (int i = 0; i < fahrenheit.length; i++) {
			
			if ((celsius[i] == "") || (celsius[i] == " ") || (celsius[i].matches("[a-zA-Z]+"))) {
				
				fahrenheit[i] = 0.0;

			} else{
				fahrenheit[i] = Integer.parseInt(celsius[i]);
			}
			
			System.out.println(fahrenheit[i]);

		}
		
		return fahrenheit;
	}
	
	
	
	


}
