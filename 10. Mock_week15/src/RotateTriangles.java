import java.util.Arrays;

public class RotateTriangles{

	public static void main(String[] args) {

		// The following input can be copied and pasted into Eclipse for testing
		int [][] triangles = 
			{
					{1, 2, 2, 4, 6, 2, 90}, //angle is 90, (fx,fy) = (6,2)    triangle[0]
					{-126, -30, -126, -131, -147, -137, 93}, //angle is 93, (fx,fy) = (-147,-137)
					null, //invalid null row
					{}, //invalid empty row
					{37, -125, -94}, //invalid row length
					{-136, -114, -18, 105, -133, -35, 893} //invalid degree >360
			};

		// to test by displaying
		System.out.println("Tests...");
		System.out.println("triangles[4]:        " + Arrays.toString(triangles[4]));  // [37, -125, -94]
		System.out.println("triangles[4][2]:     "+ triangles[4][2]);  // -94
		System.out.println("triangles.length:    " + triangles.length); // 6
		System.out.println("triangles[1].length: " + triangles[1].length); // 7
		
		System.out.println("\n-------------------------------------");
		
		RotateTriangles(triangles);
	}

	
	public static double[][] RotateTriangles (int[][] triangles) {
		
		double[][] results = new double[triangles.length][1]; 
		// second part must be [1] : each row will have an array with one value in them (0.0)
		
		/*  results array before reassigning values in them:
		[
		    [0.0],  // Row 0
		    [0.0],  // Row 1
		    [0.0],  // Row 2  , -1.0 will be assigned later
		    [0.0],  // Row 3  , -2.0 will be assigned later
		    [0.0],  // Row 4  , -2.0 will be assigned later
		    [0.0]   // Row 5  , -3.0 will be assigned later
		]
		*/

		for (int i = 0; i < triangles.length ; i++) {

			// System.out.println(Arrays.toString(triangles[i]));  // it gives all the triangles

			// Handle Invalids first:
			if (triangles[i] == null){

				results[i][0] = -1;
				
				System.out.println(Arrays.toString(triangles[i]));
				System.out.println("Triangle " + i + " rotated value: " + results[i][0]);
				System.out.println();
				continue;

			}else if (triangles[i].length != 7){

				results[i][0] = -2;
				
				System.out.println(Arrays.toString(triangles[i]));
				System.out.println("Triangle " + i + " rotated value: " + results[i][0]);
				System.out.println();
				continue;

			}else if (triangles[i][6] > 360 || triangles[i][6] < 0){ // triangles[i][6] => angle

				results[i][0] = -3;
				
				System.out.println(Arrays.toString(triangles[i]));
				System.out.println("Triangle " + i + " rotated value: " + results[i][0]);
				System.out.println();
				continue;

			}
			
			
			int x1 = triangles[i][0];
			int y1 = triangles[i][1];
			int x2 = triangles[i][2];
			int y2 = triangles[i][3];
			int x3 = triangles[i][4];
			int y3 = triangles[i][5];

			double angle = triangles[i][6]; 
			int fx = x3; 
			int fy = y3; 
			
			double theta = angle * (Math.PI / 180);

			double x1new = ((x1 - fx) * Math.cos(theta) - (y1 - fy) * Math.sin(theta)) + fx;
			double y1new = ((x1 - fx) * Math.sin(theta) + (y1 - fy) * Math.cos(theta)) + fy;

			double x2new = ((x2 - fx) * Math.cos(theta) - (y2 - fy) * Math.sin(theta)) + fx;
			double y2new = ((x2 - fx) * Math.sin(theta) + (y2 - fy) * Math.cos(theta)) + fy;

			// THIS IS IMPORTANT
			double[] row= {x1new, y1new, x2new, y2new, fx, fy};
			results[i] = row;
			
			
			System.out.println(Arrays.toString(triangles[i]));
			System.out.println("Triangle " + i + " rotated value: " + Arrays.toString(results[i]));
			System.out.println();
			// it only gave output for 0 and 1, bc the rest is invalid

		} // for loop ends

		System.out.println("\n-------------------------------------");
		System.out.println("New Triangles:");
		System.out.println(Arrays.deepToString(results));  // deepToString for 2d arrays, no need to print for coderunner tho
		return results;

	}

}
