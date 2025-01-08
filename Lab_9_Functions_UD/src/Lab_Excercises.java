import java.time.LocalDate; // to get current date for Q5


public class Lab_Excercises {

	public static void main(String args[])
	{
		int x = 55, z = 10;

		System.out.println("if odd false, if even true\n");
		System.out.println("for x = 55");
		OddEven(x);
		System.out.println();
		System.out.println("for z = 10");
		OddEven(z);
		//////////////

		String d = "abc";
		System.out.println("\nReversed str of " + d + " is:");
		ReverseStr(d);

		//////////////

		int w = 2023;
		System.out.println("\nChecking Year " + w + " :");
		LeapYear(w);

		/////////////
		System.out.println();
		DaysinMonth("March", 2014); 
	// use month.equals() instead of ==, or it will not give the lowercase converted value

	}


	// Q1
	public static boolean OddEven(int a)
	{
		boolean bool = false;
		if (a % 2 == 0) {  // check even
			bool = true;
		}
		System.out.println(bool);
		return bool;
	}


	// Q2
	public static String ReverseStr(String s)
	{
		String reversedstr = "";
		for (int i = 0; i < s.length(); i ++) {  // abc -> cba

			char b = s.charAt(s.length() - 1 - i); 
			// gets the last char from the str then moves to the one before in each i value
			reversedstr = reversedstr + b;

		}
		System.out.println(reversedstr);
		return reversedstr;
	}

	// Alternative solution for Q2 with built in functions
	public static String reverseString(String s) {
		return new StringBuilder(s).reverse().toString(); // Reverse string directly
	}



	// Q3

	//	Conditions for a Leap Year
	//A year is a leap year if it satisfies the following rules:

	//Divisible by 4: The year must be divisible by 4.
	//Not divisible by 100: If the year is divisible by 100, 
	//	it is not a leap year unless it also satisfies the third condition.

	//Divisible by 400: If the year is divisible by 400, 
	//	it is a leap year regardless of the first two conditions.
	public static boolean LeapYear(int year)
	{
		boolean check = false;

		if (year % 4 == 0) {
			if (year % 100 != 0) {
				check = true;
			}
		}
		if (year % 400 ==0) {
			check = true;
		}

		System.out.println("Leap Year: " + check);
		return check;
	}




	// Q4
	public static int DaysinMonth(String x, int year) // 03, 2014
	{
		int days = 0;
		String month = x.toLowerCase();

		if (month.equals("02") || month.equals("february")) {
			if (LeapYear(year) == true) {
				days = 29;
			} else {
				days = 28;
			}
		}else {

			if (month.equals("01") || month.equals("january")) {
				days = 31;
			} else if (month.equals("03") || month.equals("march")) {
				days = 31;
			} else if (month.equals("04") || month.equals("april")) {
				days = 30;
			} else if (month.equals("05") || month.equals("may")) {
				days = 31;
			} else if (month.equals("06") || month.equals("june")) {
				days = 30;
			} else if (month.equals("07") || month.equals("july")) {
				days = 31;
			} else if (month.equals("08") || month.equals("august")) {
				days = 31;
			} else if (month.equals("09") || month.equals("september")) {
				days = 30;
			} else if (month.equals("10") || month.equals("october")) {
				days = 31;
			} else if (month.equals("11") || month.equals("november")) {
				days = 30;
			} else if (month.equals("12") || month.equals("december")) {
				days = 31;
			} else {
				System.out.println("Invalid Date");
			}
		}

			System.out.println("Days in Month for " + month + "," + year + " is " + days + " days.");
			return days; // can be improved by converting to lowercase if its uppercase
		}

	
		// Alternative Solution in another class, see the solution



		// Q5
		public static int DaysAlive(int day, int month, int year)
		{
			int daysalive = 0;

			// get current date, get the date of birth, 
			// loop the years in between and check if any of them is a leap year. 
			// if leap year add 1 day. if not just subtract


			LocalDate currentDate = LocalDate.now();
			System.out.println("Current Date: " + currentDate);


			//		if (month == 2 || month == 02) {
			//			if (LeapYear(year) == true) {
			//				daysalive = ;
			//			} else {
			//				daysalive = ;
			//			}
			//		}

			System.out.println("Days alive is: " + daysalive);
			return daysalive;
		}


	}
