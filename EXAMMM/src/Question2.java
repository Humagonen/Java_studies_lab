
public class Question2 {
	
	public static void main(String[] args) {
		
		double P = 12.6;
		double r = 10.6;
		double t = 6.6;
		double y = 5.7;

		CCI(P,r,t,y);
	}
	
	public static double CCI(double P, double r, double t, double y) {
		
		
		double amount = P * Math.pow((1 + r/t), t*y);
		return amount;

	}
	

}
