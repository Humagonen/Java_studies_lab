
public class Calculate_Angle {
	
	public static void main(String[] args) {
		
		CalculateAngle(3.0, 4.0, 5.0);

	}
	
	
	public static double CalculateAngle (double a, double b, double c) {	
	 
	    if ((a+b > c) && (a+c> b) && (b+c> a)) {
	    	
	    	double cosA = (b*b + c*c - a*a) / (2*b*c);
	    	double degree = Math.toDegrees(Math.acos(cosA));
	    	
	    	System.out.println("The values (" + a + ", " + b + ", " + c  + ") can form a triangle");
	    	
	    	return degree;
	    	
	    } else {
	    	
	    	System.out.println("The values (" + a + ", " + b + ", " + c  + ") do not form a triangle");
	    	return -1.0;
	    }
	    
	}

}
