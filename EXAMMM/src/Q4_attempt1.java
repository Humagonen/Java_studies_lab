
public class Q4_attempt1 {
	
	public static void main(String[] args) {

		String x = " ";
		String y = "25";

		Quadrant(x, y);

	}
	
    public static int Quadrant(String x, String y) {

		int area = 0;
		if ((x == null) || ( y== null) || (x.trim().isEmpty()) || (y.trim().isEmpty())) {
			return -1;
		}else {

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


			}catch (Exception e) {
				return -2;
			}
			
			return area;

		}// else ends

	}

}
