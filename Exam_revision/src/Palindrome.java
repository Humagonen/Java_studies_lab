/*
Palindrome Checker
Question:

Write a program to check if a word is a palindrome (reads the same forwards and backward).
The program should:
1. Use a for loop to reverse the input string.
2. Use an if-else condition to check if the reversed string matches the original.
3. Use a try-catch block to handle cases where the input is empty or null.
4. Store all checked words in an ArrayList and display the list at the end of the program.
5. Allow the user to enter multiple words using a while loop and type "exit" to stop.

 */
public class Palindrome {
	
	public static void main(String[] args) {
		
		check_palindrome("atata");
		check_palindrome("heyo");
		
	}

	
	public static boolean check_palindrome(String s) {
		
		boolean check = true;
		String a = "";
		
		for (int i = 0; i < s.length(); i++) {
			
			a = a + s.charAt(s.length() - i -1);  // length gives 1 more than the index
		}
		
		System.out.println("Original: " + s);
        System.out.println("Reversed: " + a);
		
		if (s.equals(a)) { // is a palindrome  EQUALS returns bool, compareto returns int
			
			System.out.println("The string " + s + " is a palindrome");
			check = true;
		}else {  // is not a palindrome
			
			System.out.println("The string " + s + " is NOT a palindrome");
			check = false;
		}
		
		System.out.println();
		return check;
	}
}
