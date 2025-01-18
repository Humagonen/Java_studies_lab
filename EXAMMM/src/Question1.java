
public class Question1 {
	
	public static void main(String[] args) {
		
		double w = 12.6;
		double h = 10.6;
		BMI(w,h);
	}

	public static double BMI(double w, double h) {
		
		double b = w/Math.pow(h, 2);
		return b;

	}
	
	
}
