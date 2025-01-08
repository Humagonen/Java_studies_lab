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

		RotateTriangles(triangles);
	}

	
	public static double[][] RotateTriangles (int[][] triangles) 
	{
		double[][] results = new double[triangles.length][1]; // [rows][columns] we could have given empty[] to the second part too

		for (int i = 0; i < triangles.length ; i++) {

//			System.out.println(Arrays.toString(triangles[i]));  // it gives all the triangles

			if (triangles[i] == null){

				results[i][0] = -1;
				continue;

			} else if (triangles[i].length != 7){

				results[i][0] = -2;
				continue;

			} else if (triangles[i][6] > 360 || triangles[i][6] < 0){ // triangles[i][6] = angle

				results[i][0] = -3;
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
			System.out.println("Rotated Triangle " + i + ": " + Arrays.toString(results[i]));
			// it only gave output for 0 and 1, bc the rest is invalid

		}

		System.out.println("\nNew Triangles");
		System.out.println(Arrays.deepToString(results));  // deepToString for 2d arrays
		return results;

	}

}
