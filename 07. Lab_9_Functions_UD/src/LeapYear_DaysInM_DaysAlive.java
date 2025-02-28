import java.time.LocalDate;

public class LeapYear_DaysInM_DaysAlive {
	
    public static void main(String[] args) {

    	// days in month
        int month = 02;  // doesnt matter if we give 2 or 02
        int year = 2000;

        try {
            int days = getDaysInMonth(month, year);
            System.out.println("Number of days in month " + month + " of year " + year + ": " + days);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
        
        // days alive
        int birthYear = 2000;
        int birthMonth = 9;  // do not work with 09
        int birthDay = 14;
        
//        int currentYear = 2025;
//        int currentMonth = 1;
//        int currentDay = 15;
        
        daysAlive(birthYear, birthMonth, birthDay);
    }
	
    

    // Function to check if a year is a leap year
    public static boolean LeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0; // if divisible by 400, true if not false
            }
            return true; // divisible by 4 but not 100, so true
        }
        return false; // if not divisible by 4, false
    }
    
    
    /*  Alternative solution:
     A year is a leap year if it is divisible by 4 and (not divisible by 100 or divisible by 400)
     
     
    public static boolean LeapYear(int year) {
    
     	boolean check = (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);

    	System.out.println("Leap Year: " + check);
    	return check;
	}
	

     */

    
    // Function to return the number of days in a month
    public static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31; // Months with 31 days
                
            case 4: case 6: case 9: case 11:
                return 30; // Months with 30 days
            case 2:
                return LeapYear(year) ? 29 : 28; // February
            default:
                throw new IllegalArgumentException("Invalid month: " + month);
        }
    }
    
    
    
 // days alive
    public static int daysAlive(int birthYear, int birthMonth, int birthDay) {
        // Get the current date
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();
        int currentDay = today.getDayOfMonth();

        int totalDays = 0;

        // Add days for the full years in between. 2001-2024
        for (int year = birthYear + 1; year < currentYear; year++) {
            totalDays += LeapYear(year) ? 366 : 365;
        } // add 365 or 366 depending on each year if its a leap year or not

        
        // Add days from the birth year
        for (int month = birthMonth; month <= 12; month++) {
            if (month == birthMonth) {
                totalDays += getDaysInMonth(month, birthYear) - birthDay + 1; // Remaining days of the birth month
            } else {
                totalDays += getDaysInMonth(month, birthYear);
            }
        }

        
        // Add days for the current year
        for (int month = 1; month <= currentMonth; month++) {
            if (month == currentMonth) {
                totalDays += currentDay; // Days of the current month
            } else {
                totalDays += getDaysInMonth(month, currentYear);
            }
        }
        
        System.out.println("Total days alive: " + totalDays);
        return totalDays;
    }
}
