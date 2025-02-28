
public class Q2_PMT {

	public static void main(String[] args) {

		double p = 20.5, r = 10.8, n = 7.2;

		PMT(p, r, n);

	}
	
	public static double PMT(double p, double r, double n) 
	{
		
		r = (r * 1/100)/12;  // convert to monthly
		
		n = n * 12;
		
		double monthlyAnnuity = 0.0;
		
		double x = 1 + r;
		
		double power = Math.pow(x,n); // (1+r)^n
		
		monthlyAnnuity = (p * r * power) / (power - 1);

		return monthlyAnnuity;

	}


}
