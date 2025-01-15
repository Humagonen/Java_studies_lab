import java.util.ArrayList;
import java.util.Arrays;

public class Fibonacci {

	public static void main(String[] args) {

		int n = 5;

		System.out.println("\n----------Simple Fibonacci----------");
		fibonaccisimple(n);
		
		System.out.println("\n-------------With Arrays------------");
		fibonacci(n);

		System.out.println("\n-----------With ArrayLists----------");
		fibonacci2(n); // most logical one

		System.out.println("\n-----------With Recursion-----------");
		//fibonacciRecursive(n);
		fibonacciRecursiveWithSequence(n);  // uses recursive function. displaying function

	}

	
	//	Write a program that when given a positive (say n) integer displays the first n Fibonacci numbers. 
	//	If n is not a positive integer the program should generate an error.

	public static int fibonaccisimple(int n) {
	    if (n < 0) {  // Handle invalid input
	        System.out.println("Give a valid number");
	        return -1;
	    } else if (n == 0) {  // Special case for n = 0
	        System.out.print("0");
	        return 0;
	    } else if (n == 1) {  // Special case for n = 1
	        System.out.print("0, 1");
	        return 1;
	    }

	    System.out.print("0, 1");  // Print the first two numbers
	    int a = 0, b = 1, c = 0;

	    for (int i = 2; i < n; i++) {  // Loop to calculate up to the nth Fibonacci number
	        c = a + b;
	        a = b;  // Update for the next iteration
	        b = c;

	        System.out.print(", " + c);
	    }

	    System.out.println(); // Add a newline for clean output
	    return b;  // Return the nth Fibonacci number
	}


	
	
	

	// Arrays
	public static int[] fibonacci(int n) {

		int[] sequence = new int[n+1];  // array with size n and filled with zeros

		int a = 0;
		int b = 1;
		int c = 0;


		if (n < 0) {  // handle invalid
			System.out.println("Give a valid number");
		}else if (n == 0) {

			// do nothing

		}else if (n == 1) {

			sequence[1] = 1;

		}else {

			sequence = new int[n];
			sequence[1] = 1;

			for (int i = 2; i < n; i++) {

				c = a + b;
				a = b;
				b = c;

				sequence[i] = c;
				//				System.out.println(c);

			}
		}

		System.out.println(Arrays.toString(sequence));
		return sequence;
	}


	


	// ArrayLists
	public static ArrayList<Integer> fibonacci2(int n) {

		ArrayList<Integer> sequence = new ArrayList<Integer>();

		int a = 0;
		int b = 1;
		int c = 0;

		if (n < 0) {  // handle invalid
			System.out.println("Give a valid number");
		}else if (n == 0) {

			sequence.add(0);

		}else if (n == 1) {

			sequence.add(0);
			sequence.add(1);

		}else {

			sequence.add(0);
			sequence.add(1);

			for (int i = 2; i < n; i++) {

				c = a + b;
				a = b;
				b = c;

				sequence.add(c);
			}
		}

		System.out.println(sequence);
		return sequence;
	}


	
	

	// Recursive
	public static int fibonacciRecursive(int n) {
		if (n < 0) {  // handle invalid
			System.out.println("Give a valid number");
			return -1;
		}else if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
		}
	}
	
	
	
	
	
	///////////////// recursion but with display ///////////////////
	public static int fibonacciRecursiveWithSequence(int n) {
	    if (n <= 0) { // Handle invalid input
	        System.out.println("Please provide a positive integer.");
	        return -1;
	    }

	    System.out.print("Fibonacci Sequence: ");
	    for (int i = 0; i < n; i++) { // Print exactly n Fibonacci numbers
	        System.out.print(fibonacciRecursive(i));
	        if (i < n - 1) { // Ensure that commas are printed between numbers but not at the end
	            System.out.print(", ");
	        }
	    }
	    System.out.println(); // Add a newline for clean output

	    return fibonacciRecursive(n - 1); // Return the nth Fibonacci number (last printed number)
	}
	
}
