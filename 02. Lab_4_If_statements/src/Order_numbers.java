
public class Order_numbers {

	public static void main(String[] args) {  
		int a = 10, b = -10, c = 3;

		if (a > b) {

			if (a > c) {

				if (b > c) {

					System.out.println("Descending order is:" + a + "," + b + "," + c);

				}
				else {

					System.out.println("Descending order is:" + a + "," + c + "," + b);

				}

			}
			else {
				if (b > c) {

					System.out.println("Descending order is:" + b + "," + c + "," + a);

				}
				else {
					if (a > b) {

						System.out.println("Descending order is:" + c + "," + a + "," + b);

					}
					else {
						System.out.println("Descending order is:" + c + "," + b + "," + a);
					}
				}
			}

		}
		else {

			if (c > b) {
				System.out.println("Descending order is:" + c + "," + b + "," + a);
			}
			else {
				if (a > c) {
					System.out.println("Descending order is:" + b + "," + a + "," + c);
				}
				else {
					System.out.println("Descending order is:" + b + "," + c + "," + a);
				}

			}

		}

		System.out.println("\nElse-if solution:");
		shorterans();
		System.out.println("\nTemp assignment solution:");
		easiestans();

	}


	/////////////////////////////////////////////


	public static void shorterans() {
		int a = 10, b = -10, c = 3;

		if ((a > b) && (a > c) && (b > c)) {  // a, b, c

			System.out.println("Descending order is: " + a + "," + b + "," + c);

		}
		else if ((a > b) && (a > c) && (c > b)){  // a, c, b
			
			System.out.println("Descending order is: " + a + "," + c + "," + b);
			
		}
		
		else if ((b > a) && (a > c) && (b > c)){  // b, a, c
			
			System.out.println("Descending order is: " + b + "," + a + "," + c);
			
		}
		
		else if ((b > a) && (c > a) && (b > c)){  // b, c, a
			
			System.out.println("Descending order is: " + b + "," + c + "," + a);
			
		}
		
		else if ((c > a) && (c > b) && (a > b)){  // c, a, b
			
			System.out.println("Descending order is: " + c + "," + a + "," + b);
			
		}
		
		else{  // c, b, a
			
			System.out.println("Descending order is: " + c + "," + b + "," + a);
			
		}

	}
	
	///////////////////////
	
    public static void easiestans() {
        // Given three numbers
        int a = 10;
        int b = -10;
        int c = 3;

        // Sorting the numbers in ascending order, smallest to largest
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }
        if (a > c) {
            int temp = a;
            a = c;
            c = temp;
        }
        if (b > c) {
            int temp = b;
            b = c;
            c = temp;
        }

        // Display the numbers in ascending order
        System.out.println("Numbers in ascending order: " + a + ", " + b + ", " + c);

        // Display the numbers in descending order
        System.out.println("Numbers in descending order: " + c + ", " + b + ", " + a);
    }

}






