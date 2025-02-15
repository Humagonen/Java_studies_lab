
public class Question4 {

	public static void main(String[] args) {

		String x = "  2  "; // tested with "", " ", null, "abc", "25" 
		// here "  2  "  must be is empty or null too, so .contains() must be used
 		String y = "3";

		Quadrant(x, y);

	}
	
	
	
	// tested with "", " ", "    ", null, "abc", "25" works perfectly on eclipse...
	public static int Quadrant(String x, String y) {

		int area = 0;

		// test 1
		if ((x == null) || (y == null) || (x.trim().isEmpty()) || (y.trim().isEmpty())) {

			System.out.println("its empty or null");
			return -1;

		}

		try {

			int x_int = Integer.parseInt(x);
			int y_int = Integer.parseInt(y);



			if ((x_int > 0) && (y_int > 0)){
				area = 1;

			}else if((x_int < 0) && (y_int > 0)){
				area = 2;

			}else if((x_int < 0) && (y_int < 0)){
				area = 3;

			}else if((x_int > 0) && (y_int < 0)){
				area = 4;
			}else if((x_int == 0) || (y_int == 0)){
				area = -3;
			}
		
			System.out.println("works good, area :" + area);
			return area;

		}catch (Exception e) {
			System.out.println("invalid");
			return -2;
		}
		
	}

}
