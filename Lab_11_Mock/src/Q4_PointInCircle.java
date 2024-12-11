import java.util.ArrayList;

public class Q4_PointInCircle {
	
	public static void main(String[] args) {

		int[][] n = {	{1,1}, // 4+1= 5 < 25  --> 1
						{7,2},  // 16 < 25   --> 1          2
						{10,10}  };  // 113 > 25  --> 3
		
		// (x,y) in each row
		System.out.println(PointInCircle(n));

	}
	
	public static ArrayList<Integer> PointInCircle(int[][] points) {
		int h = 3;
		int k = 2;
		int r = 5;
		
		ArrayList<Integer> output = new ArrayList<Integer>();
		
		for (int i = 0; i < points.length ; i++) {
			
			int x = points[i][0];
			int y = points[i][1];
			
			int a = x - h;
			int b = y - k;
			
			double A = Math.pow(a, 2) + (Math.pow(b, 2)); // 16
			
			if (A < r*r) {
				
				output.add(1);
				
			}
			else if (A == (r*r)){
				
				output.add(2);
				
			}else if (A > r*r){
				
				output.add(3);
				
			}else {
				
				ArrayList<Integer> invalid = new ArrayList<Integer>();
				invalid.add(-1);
				return  invalid; // invalid case
			}
		}

		return output; 
	}

}
