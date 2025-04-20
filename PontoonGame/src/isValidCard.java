import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class isValidCard {
	
	public static void main(String[] args) {
		
		//////////////////// Q1 /////////////////////
		
	    System.out.println(isValidCard(null));      // -1 (Null input)
	    System.out.println(isValidCard(""));        // -2 (Empty string)
	    System.out.println(isValidCard("AH"));      // 1  (Valid card)
	    System.out.println(isValidCard("10D"));     // -2 (Too long)
	    System.out.println(isValidCard("1H"));      // -3 (Invalid rank)
	    System.out.println(isValidCard("TC"));      // 1  (Valid)
	    System.out.println(isValidCard("ZZ"));      // -3 (Invalid rank)
	    System.out.println(isValidCard("9X"));      // -4 (Invalid suit)
	    System.out.println();
	    
	    //////////////////// Q2 /////////////////////
	    
        // Test case 1: Null hand (should return -1)
        System.out.println(validHand(null)); // Expected: -1
        // Test case 2: Empty hand (should return 0)
        System.out.println(validHand(Arrays.asList())); // Expected: 0
        // Test case 3: Hand with all valid cards (should return 2)
        System.out.println(validHand(Arrays.asList("AS", "TH", "KC"))); // Expected: 2
        // Test case 4: Hand with one invalid card (should return 1)
        System.out.println(validHand(Arrays.asList("AS", "10H", "XYZ"))); // Expected: 1
        // Test case 5: Hand with multiple invalid cards (should return 1)
        System.out.println(validHand(Arrays.asList("ZZ", "XY", "10H"))); // Expected: 1
        System.out.println();
        
	    //////////////////// Q3 /////////////////////

        System.out.println(scoreHand(new ArrayList<> (Arrays.asList("AS", "KH", "3D")))); // Expected: 11 + 10 + 3 = 24
        System.out.println(scoreHand(new ArrayList<> (Arrays.asList("2H", "10D", "9S")))); // Expected: 2 + 5 + 9 = 16
        System.out.println(scoreHand(new ArrayList<> (Arrays.asList("AC", "AD", "AH")))); // Expected: 11 + 11 + 11 = 33

	    
	}
	
	
	public static int isValidCard(String card) {
	    if (card == null) {
	        return -1; // Null input
	    }
	    if (card.length() != 2) {
	        return -2; // Must be exactly 2 characters
	    }
	    
	    char rank = card.charAt(0);
	    char suit = card.charAt(1);

	    // Valid ranks: 2-9, T (10), J, Q, K, A
	    String validRanks = "23456789TJQKA";
	    // Valid suits: Hearts (H), Diamonds (D), Clubs (C), Spades (S)
	    String validSuits = "HDCS";

	    if (validRanks.indexOf(rank) == -1) {
	        return -3; // Invalid rank
	    }
	    if (validSuits.indexOf(suit) == -1) {
	        return -4; // Invalid suit
	    }
	    
	    return 1; // Valid card
	}
	
	
	public static int validHand(List<String> hand) {
	    // Check if hand is null
	    if (hand == null) {
	        return -1;
	    }
	    
	    // Check if hand is empty
	    if (hand.isEmpty()) {
	        return 0;
	    }
	    
	    // Check if all cards are valid
	    for (String card : hand) {
	        if (isValidCard(card) != 1) { // adjust this according to the valid return value of the validcard method
	            return 1; // Found an invalid card
	        }
	    }
	    
	    return 2; // All cards are valid
	}
	
	
	public static int scoreHand(ArrayList<String> hand) {
	    int totalScore = 0;
	    
	    for (String card : hand) {
	        char rank = card.charAt(0); // First character represents the rank
	        
	        // Assign scores based on rank
	        switch (rank) {
	            case '2': totalScore += 2; break;
	            case '3': totalScore += 3; break;
	            case '4': totalScore += 4; break;
	            case '5': totalScore += 5; break;
	            case '6': totalScore += 6; break;
	            case '7': totalScore += 7; break;
	            case '8': totalScore += 8; break;
	            case '9': totalScore += 9; break;
	            case 'T': case 'J': case 'Q': case 'K': totalScore += 10; break;
	            case 'A': totalScore += 11; break;
	        }
	    }
	    
	    return totalScore;
	}



}
