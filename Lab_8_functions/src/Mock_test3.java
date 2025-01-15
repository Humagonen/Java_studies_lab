public class Mock_test3 {

    public static void main(String args[]) {
        String strGrade = "45", strBonus = "15";
        String gradeLetter = CalculateGradeLetter(strGrade, strBonus); 
        System.out.println("Grade Letter: " + gradeLetter);
    }

    public static String CalculateGradeLetter(String strGrade, String strBonus) {
        // Check for null or empty inputs
        if (strGrade == null || strBonus == null) {
            return "-1";
        }
        if ((strGrade.trim().isEmpty()) || (strBonus.trim().isEmpty())) {
            return "-2";
            
            // strGrade.isEmpty() || strBonus.isEmpty() || strGrade.equals(" ") || strBonus.equals(" ")
        }

        try {
            // Parse input strings to integers
            int g = Integer.valueOf(strGrade);
            int b = Integer.valueOf(strBonus);

            double result = g + b;

            // Determine grade letter
            if (result >= 70) {
                return "A";
            } else if (result >= 60) {
                return "B";
            } else if (result >= 50) {
                return "C";
            } else if (result >= 40) {
                return "D";
            } else {
                return "F";
            }
        } catch (NumberFormatException e) {
            // Handle invalid number input
            return "-3"; // Indicate invalid number format
        }
    }
}
