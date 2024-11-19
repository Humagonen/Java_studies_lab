
public class Exercise1_2 {
	public static void main(String[] args) {

		int[] T = {1, 3, 5, 10};  // T -> timeinminutes

		System.out.println(T[3]);

		for(int i= 0; i <= T.length -1; i++) {

			System.out.println(T[i] + " multiplied by 60 (from minutes to seconds) = " + T[i] * 60);
			//			System.out.println(T[i] * 60);

		}

		System.out.println("\n");
		Matrix();
//		FindIndexSimple(); 
	}

	public static void Matrix() {

		int[][] M = {  // M for movement
				{100, 100, 1000}, // 100, 100, 1000 represent [(left wheel), (right wheel), (time in milliseconds)]. 
				{70, 100, 2000},
				{100, 70, 3000},
		};

		System.out.println(M[1][2] + "\n"); // 2000
		
		// nested for loop
		for(int i= 0; i <= M.length -1; i++) {
			for(int j= 0; j <= M.length -1; j++) {

				System.out.print(M[i][j] + " ");  // how to display just as it as a list

			}
			System.out.print("\n");

		}
		

	}

//	public static void FindIndexSimple() {
//			int[] numbers = {10, 20, 30, 40, 50};
//			int target = 30;
//
//			int index = Arrays.asList(numbers).indexOf(target);
//			System.out.println("The index of " + target + " is: " + index);
//		}

}