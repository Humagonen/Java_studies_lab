
public class Q1_CalculateExpression {
	
	public static void main(String[] args) {
		
		double n = 20.5, x = 10.8, y = 7.2, z = 1.1;
		
		CalculateExpression(n, x, y, z);
		
	}
	
	public static double CalculateExpression(double n, double x, double y, double z) 
	{
		double ans = 7.2 * n * (x*x + y*y + z * z);
		return ans;
	}

}
