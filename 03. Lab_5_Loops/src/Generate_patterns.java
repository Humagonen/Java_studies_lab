
public class Generate_patterns {

	public static void main(String[] args) {

		System.out.println("Generating Patterns:\n");
		patterns();

	}

	public static void patterns() {

		int n = 10;

		// 1.	"++++++++++"
		for (int i = 1; i <= n; i++) {
			System.out.print("+");
		}


		// 2.	"---------" 
		System.out.print("\n");

		for (int i = 1; i <= n; i++) {
			System.out.print("-");
		}


		// 3.	"+-+-+-+-+-" 
		System.out.print("\n");

		for (int i = 1; i <= n; i++) {

			if (i % 2 == 0) {
				System.out.print("-");
			} else {
				System.out.print("+");
			}
		}


		// 4.	"*+-*+-*+-" 
		System.out.println();

		for (int i = 1; i < n; i++) {
			switch (i % 3) {
			case 1:
				System.out.print("*"); // when the remainder = 1 (1, 4, 7)
				break;
			case 2:
				System.out.print("+"); // whent the remainder = 2 (2, 5, 8)
				break;
			case 0:
				System.out.print("-"); // when the remainder = 0 (3, 6, 9)
				break;
			}
		}


		// 5.	"*+-*+-*+-* 
		System.out.print("\n");

		for (int i = 1; i <= n; i++) {
			switch (i % 3) {
			case 1:
				System.out.print("*"); // when the remainder = 1 (1, 4, 7)
				break;
			case 2:
				System.out.print("+"); // whent the remainder = 2 (2, 5, 8)
				break;
			case 0:
				System.out.print("-"); // when the remainder = 0 (3, 6, 9)
				break;
			}
		}


		// 6.	"**++**++**++"
		System.out.print("\n");

		for (int i = 1; i <= 6; i++) {

			if (i % 2 == 0) {
				System.out.print("++");
			} else {
				System.out.print("**");
			}
		}


		// 7.	"***+++---***+++---"
		System.out.print("\n");

		for (int i = 1; i <= 6; i++) {
			switch (i % 3) {
			case 1:
				System.out.print("***");
				break;
			case 2:
				System.out.print("+++"); 
				break;
			case 0:
				System.out.print("---"); 
				break;
			}
		}


		// 8.	"***+++------+++***"
		System.out.print("\n");

		for (int i = 1; i <= 2; i++) {

			if (i % 2 == 0) {
				System.out.print("---+++***");
			} else {
				System.out.print("***+++---");
			}
		}
	}
}
