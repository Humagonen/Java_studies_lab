public class Generate_sequence {

	public static void main(String[] args) {

		System.out.println("Generating Sequences:\n");
		sequences();

	}



	public static void sequences() {


		//1. 4,5,6,7,8,9,10,11
		for (int i = 4; i <= 11; i++) {

			if (i == 11) {
				System.out.print(i);
			}else {
				System.out.print(i + ",");
			}
		}

		System.out.println();


		// 2. 10, 13, 16, 19
		for (int i = 10; i <= 19; i = i+3) {

			if (i == 19) {
				System.out.print(i);
			}else {
				System.out.print(i + ",");
			}
		}

		System.out.println();


		// 3. 1, 4, 7, 10, 13, 16
		for (int i = 1; i <= 16; i = i+3) {

			if (i == 16) {
				System.out.print(i);
			}else {
				System.out.print(i + ",");
			}
		}
		
		System.out.println();


		// 4. 2, 4, 6, 8, 10, 12
		for (int i = 2; i <= 12; i = i+2) {

			if (i == 12) {
				System.out.print(i);
			}else {
				System.out.print(i + ",");
			}
		}
		
		System.out.println();


		// 5. 1, 4, 9, 16, 25, 36, 49, 64, 81, 100
		for (int i = 1; i <= 10; i++) {

			if (i == 10) {
				System.out.print(i*i);
			}else {
				System.out.print(i*i+ ",");
			}
		}


		System.out.println();


		// 6. -10, -8, -6, -4, -2, 0, 2, 4, 6, 8, 10
		for (int i = -10; i <= 10; i = i+2) {

			if (i == 10) {
				System.out.print(i);
			}else {
				System.out.print(i+ ",");
			}
		}


		System.out.println();


		// 7. -20, -15, -10, -5, 5, 10, 15, 20
		for (int i = -20; i <= 20; i = i+5) {

			if (i == 20) {
				System.out.print(i);
			}else if(i == 0) {
				continue;
			}else {
				System.out.print(i+ ",");
			}
		}

	}

}
