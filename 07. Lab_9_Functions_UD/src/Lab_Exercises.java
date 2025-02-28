import java.time.LocalDate; // to get current date for Q5


public class Lab_Exercises {

	public static void main(String args[])
	{
		int x = 55, z = 10;

		System.out.println("if odd false, if even true\n");
		System.out.println("for x = 55");
		OddEven(x);

		System.out.println("\nfor z = 10");
		OddEven(z);
		
		////////////// reversed string
		System.out.println("\n------------Reversed String------------\n");

		String d = "abc";
		System.out.println("\nReversed str of " + d + " is:");
		ReverseStr(d);


		////////////// leap year
		System.out.println("\n---------------Leap Year--------------\n");


		int w = 2000;
		boolean i = LeapYear(w);
		System.out.println("\nLeap Year for " + w + " : " + i);
		

		///////////// days in month
		System.out.println("\n------------Days in Month------------\n");

		System.out.println();
		int year = 2000;
		String month = "september";
		String numericMonth= convertMonth(month);
		int days = DaysinMonth(month, year); 
		
		System.out.println("Days in month " + month + " (" + numericMonth + "), year " + year + ": " + days);
	    
		// use month.equals() instead of ==, or it will not give the lowercase converted value

		
		
		////////// q5
		System.out.println("\n--------------Days Alive------------\n");

		int birthYear = 2000;
		String birthMonth = "september"; // Full month name
		int birthDay = 14;

		// Calculate total days alive
		daysAlive(birthYear, birthMonth, birthDay);

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
	public static String ReverseStr(String s) {
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
		return check;
	}




	// Q4
	// days in month
	public static int DaysinMonth(String month, int year) {
	    // Convert the month to a numeric format
	    String numericMonth = convertMonth(month);
	    int days;

	    switch (numericMonth) {
	        case "01": case "03": case "05": case "07": case "08": case "10": case "12":
	            days = 31; // Months with 31 days
	            break;
	        case "04": case "06": case "09": case "11":
	            days = 30; // Months with 30 days
	            break;
	        case "02":
	            days = LeapYear(year) ? 29 : 28; // February
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid month: " + month);
	    }

	    return days;
	}
	
	// Alternative Solution in another class, see the solution (only with int)


	
	
	// Q5
	// days alive
	public static int daysAlive(int birthYear, String birthMonth, int birthDay) {
	    // Get today's date
	    java.time.LocalDate today = java.time.LocalDate.now();
	    int currentYear = today.getYear();
	    String currentMonth = String.format("%02d", today.getMonthValue()); // Convert month to two digits
	    int currentDay = today.getDayOfMonth();

	    int totalDays = 0;

	    // Calculate days for years between birth year and current year
	    for (int year = birthYear; year <= currentYear; year++) {
	        if (year == birthYear) {
	            // For the birth year, count days from the birth date to the end of the year
	            for (int month = Integer.parseInt(convertMonth(birthMonth)); month <= 12; month++) {
	                int daysInMonth = DaysinMonth(String.format("%02d", month), year);

	                if (month == Integer.parseInt(convertMonth(birthMonth))) {
	                    totalDays += daysInMonth - birthDay + 1; // Include birth day
	                } else {
	                    totalDays += daysInMonth;
	                }
	            }
	        } else if (year == currentYear) {
	            // For the current year, count days from the start of the year to today's date
	            for (int month = 1; month <= Integer.parseInt(currentMonth); month++) {
	                int daysInMonth = DaysinMonth(String.format("%02d", month), year);

	                if (month == Integer.parseInt(currentMonth)) {
	                    totalDays += currentDay; // Add days up to the current day
	                } else {
	                    totalDays += daysInMonth;
	                }
	            }
	        } else {
	            // For all intermediate years, add 365 or 366 days
	            totalDays += LeapYear(year) ? 366 : 365;
	        }
	    }

	    System.out.println("Total days alive: " + totalDays);
	    return totalDays;
	}

	
	
	
	// Helper function to convert full month name to two-digit string
	public static String convertMonth(String month) {
	    month = month.toLowerCase();
	    switch (month) {
	        case "january": return "01";
	        case "february": return "02";
	        case "march": return "03";
	        case "april": return "04";
	        case "may": return "05";
	        case "june": return "06";
	        case "july": return "07";
	        case "august": return "08";
	        case "september": return "09";
	        case "october": return "10";
	        case "november": return "11";
	        case "december": return "12";
	        default: return month; // If already numeric, return as-is
	    }
	}

	
}
