
import java.util.Scanner;

public class DaysInMonth {

    // Function to check if a year is a leap year
    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true;
        }
        return false;
    }

    // Function to return the number of days in a month
    public static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31; // Months with 31 days
            case 4: case 6: case 9: case 11:
                return 30; // Months with 30 days
            case 2:
                return isLeapYear(year) ? 29 : 28; // February
            default:
                throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input month and year
        System.out.print("Enter month (1-12): ");
        int month = scanner.nextInt();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();

        try {
            int days = getDaysInMonth(month, year);
            System.out.println("Number of days in month " + month + " of year " + year + ": " + days);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
