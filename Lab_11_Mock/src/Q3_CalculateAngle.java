
public class Q3_CalculateAngle {
	
	public static void main(String[] args) {

		double p = 20.5, r = 10.8, n = 7.2;

		CalculateAngle(p, r, n);

	}
	
	public static double CalculateAngle(double a, double b, double c) 
	{
		if ((a + b > c) && (a + c > b) && (b + c > a)){
			
			double x = (b*b + c*c - a*a)/(2*b*c);
			double y = Math.acos(x);
			double z = Math.toDegrees(y);
			return z;
			
		} else {
			return -1;
		}
	}

}
