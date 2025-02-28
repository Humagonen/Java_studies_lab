// seminar activity

public class Excercise_1 {

	public static void main(String[] args) {

		int n = 10;

		// Q1
		for (int i = 1; i <= n; i++) {
			// 5 % 2 == 0

			if (i % 2 == 0) {

				System.out.println(i + "!");  // 2, 4, 6, 8, 10
			}

		}

		System.out.println("\nQuestion 2:\n ");
		Q2();

		System.out.println("\nQuestion 3:\n ");
		Q3();
	}


	public static void Q2() {

		int x = 4;
		int y = 6;

		while (x < y) 
		{
			// What happens?
			System.out.println("Hi there...");

			x++;
		}
	}


	// nested loops
	public static void Q3() {

		int n = 5;
		int m = 5;

		for(int i= 1; i <= n ; ++i){  // second part must be a condition
			System.out.println(i);

			for(int j= 1; j <=m ;++j)
			{
				System.out.println(" ----> " + j);
			}
		}

	}


}


