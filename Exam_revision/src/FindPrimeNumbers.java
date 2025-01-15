import java.util.ArrayList;

public class FindPrimeNumbers {

    public static void main(String[] args) {
        int x = 1;   // Starting number
        int y = 100; // Ending number

        ArrayList<Integer> primeNumbers = findPrimeNumbers(x, y);
        System.out.println("Prime numbers between " + x + " and " + y + ": " + primeNumbers);
    }

    // Method to find prime numbers between x and y
    public static ArrayList<Integer> findPrimeNumbers(int x, int y) {
        ArrayList<Integer> primes = new ArrayList<>();

        for (int i = x; i <= y; i++) {
            if (isPrime(i)) {
                primes.add(i); // Add prime number to ArrayList
            }
        }

        return primes;
    }

    // Helper method to check if a number is prime
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false; // 0 and 1 are not prime
        }
        
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false; // If divisible by any number, it's not prime
            }
        }
        
        return true; // Prime number
    }
}
