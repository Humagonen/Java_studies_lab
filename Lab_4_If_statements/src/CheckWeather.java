 public class CheckWeather {

    public static void main(String[] args) {
        int x = 1; // Raining?
        int y = 1; // Snowing?

        if (x == 6 || y == 5) {
            question1();
        } else {
            System.out.println("No sleet, perhaps just rain?");
        }
    }
    
    
    public static void question1() {
            System.out.println("There will be sleet");
    }
    
    
}
