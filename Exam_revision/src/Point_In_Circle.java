import java.util.ArrayList;
import java.util.Iterator;

public class Point_In_Circle {

	public static void main(String[] args) {

		int[][] values = {  null,    // invalid 
				{1,1},   // inside
				{8,2},   // on
				{10,10}  // outside  
		};

		PointInCircle(values);

	}


	public static ArrayList<Integer> PointInCircle(int[][] points) {
		int h = 3;
		int k = 2;
		int r = 5;

	    ArrayList<Integer> output = new ArrayList<Integer>();

		for (int i = 0; i < points.length; i++) {  // points.length = 4, points to test in this case

			try {
	                
				int x = points[i][0];
				int y = points[i][1];

				double E = Math.pow(x - h,2) + Math.pow(y - k,2);

				if (E < r*r) {

					output.add(1);
					System.out.println("Point (" + x + ", " + y + ") is inside the circle, output: " + output);

	            }else if(E == r*r) {

					output.add(2);
					System.out.println("Point (" + x + ", " + y + ") is on the circle, output: " + output);

	            }else if(E > r*r) {

					output.add(3);
					System.out.println("Point (" + x + ", " + y + ") is outside the circle, output: " + output);
				}

			} catch (Exception e) {  // invalid cases
				output.add(-1);
				System.out.println("Invalid point, output: " + output);
			}
		}
			
		System.out.println("\nOutput for all points in total: " + output);
		return output;

	}

}
