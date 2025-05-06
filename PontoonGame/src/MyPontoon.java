import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyPontoon {

	public static void main(String[] args) {

		System.out.println("--------------------Q1---------------------");
		
		ValidCardCheck("hh");  // invalid chars
		ValidCardCheck(null);  // is null
		ValidCardCheck("45H");  // not of 2 length
		ValidCardCheck("2C");  // Is Valid  - has to be uppercase
		ValidCardCheck("");  // is empty

		System.out.println();
		
		/////////////////////////// Q2 ///////////////////////////////
		System.out.println("--------------------Q2---------------------");
		
		ArrayList<String> hand = new ArrayList<String>();
		hand.add("3H");
		hand.add("5D");
		hand.add("2C");
		hand.add("AD");
//		hand.add("AD"); // duplicate
		
		ValidHandCheck(hand);

		/////////////////////////// Q3 //////////////////////////////
		System.out.println("--------------------Q3---------------------");
		
		ScoreHand(hand);  // gives error when there's invalid card/hand
		
		/////////////////////////// Q4 //////////////////////////////
		System.out.println("--------------------Q4---------------------");
		
		HandBust(hand);
		
		/////////////////////////// Q5 //////////////////////////////
		System.out.println("--------------------Q5---------------------");
		ArrayList<ArrayList<String>> allHands = new ArrayList<>();

//		ArrayList<String> hand1 = new ArrayList<>(Arrays.asList("KH", "9S"));  // 7
//		ArrayList<String> hand1 = new ArrayList<>(Arrays.asList("2C", "5H"));  // 7
		ArrayList<String> hand2 = new ArrayList<>(Arrays.asList("KH", "9S"));  // 19
		ArrayList<String> hand3 = new ArrayList<>(Arrays.asList("AD", "8H", "2C"));  // 25 - bust
		ArrayList<String> hand4 = new ArrayList<>(Arrays.asList("9D", "TS"));  // 19, but same score as hand2, same length => first wins

		ArrayList<String> hand5 = new ArrayList<>(Arrays.asList("AH", "8D", "7C"));  // 27 - bust

//		allHands.add(hand1);
		allHands.add(hand2);
		allHands.add(hand3);
		allHands.add(hand4);
		allHands.add(hand5);
		
		System.out.println("WHO WON? Best hand is at index: " + WhoWon1(allHands));


		/////////////////////////// Q6 //////////////////////////////
		System.out.println("---------------------Q6--------------------");
		
        List<List<String>> validHands = new ArrayList<>(allHands);
//        validHands.add(Arrays.asList("AS", "KH", "3D")); 
//        validHands.add(Arrays.asList("TC", "7S", "5H"));

        List<String> remaining = CardRemains2(validHands);
        System.out.println("Remaining cards in the deck:");
        System.out.println(remaining);
		
	}

	// Q1 - Is Card Valid? 
	public static int ValidCardCheck(String x) {

		String firstchars = "23456789TJQKA";
//		String secondchars[] = {"D","H","C","S"};  // array - wanted to try with different data type
		// this did not work btw
//		String secondchars = "DHCS";  // string alternative
	    List<String> secondchars = Arrays.asList("D", "H", "C", "S");	

		if (x == null) {
			System.out.println("Card is null");
			return 0;
		}else if (x.isEmpty()) { // .length covers this too, unless they want different test case for this u can delete it
			System.out.println("Card is empty");
			return 1;
		}else if (x.length() != 2) {
			System.out.println("Card is not of 2 length");
			return 2;
		}

		char xfirst = x.charAt(0);
		char xsecond = x.charAt(1);

		// OR 
     // if (firstchars.indexOf(xfirst) == -1 || !secondchars.contains(String.valueOf(xsecond)))
		// same as    (secondchars.contains(String.valueOf(xsecond)) == false)
		if ((firstchars.indexOf(xfirst) == -1) || secondchars.toString().indexOf(xsecond) == -1) {

			System.out.println("Card has invalid characters");
			return 3; // invalid characters
		}

		System.out.println("Card is Valid: " + x);
		return 4;
	}

///////////////////////////////////////////////////////////////////////////////////////////////

	// Q2 - Is Hand Valid?
	public static int ValidHandCheck(ArrayList<String> hand) {
		// a hand should not have the same card twice - ask this

		if (hand == null) {
			System.out.println("hand is null");
			return 0;
		}else if (hand.isEmpty() == true) {
			System.out.println("hand is empty");  
			return 1; 
		}else if (hand.size() < 1) { // or = 0
			System.out.println("hand has less than 1 card");  // this might be the same condition as the one before
			return 1; 
		}

		for(int i = 0; i < hand.size() ; i++) {   // Invalid cards in the hand

			if (ValidCardCheck(hand.get(i)) != 4) {  // check the return number (4) later
				System.out.println("Invalid cards found in the hand: " + hand.get(i));
				return -1;  // found invalid card(s) in the hand
			}
		}

		Set<String> uniqueCards = new HashSet<>(hand);  // sets can only have one unique element
		// uniqueCards will only have the unique elemnts in the hand, then compare sizes:
		if (uniqueCards.size() < hand.size()) { // if set size is less than list size, return -2
			System.out.println("Duplicate cards found in the hand");
			return -2;  // or any specific error code you want
		}
		
		System.out.println("Valid hand and cards!");
		return 5;  // VALID HAND AND CARDS IN IT, NO DUPLICATES
	}


///////////////////////////////////////////////////////////////////////////////////////////////

	// Q3 - input arraylist, scores stored as hashmaps
	// ASSUMING CARDS AND HANDS ARE VALID - no need to check again
	public static int ScoreHand(ArrayList<String> hand) {  // List<String> hand

		int score = 0;
		HashMap<String, Integer> cardValues = new HashMap<>();
		cardValues.put("2", 2);
		cardValues.put("3", 3);
		cardValues.put("4", 4);
		cardValues.put("5", 5);
		cardValues.put("6", 6);
		cardValues.put("7", 7);
		cardValues.put("8", 8);
		cardValues.put("9", 9);
		cardValues.put("T", 10);
		cardValues.put("J", 10);
		cardValues.put("Q", 10);
		cardValues.put("K", 10);
		cardValues.put("A", 11);
//		cardValues.put("k", 10);  // to test error - and yes when invalid card theres error

		for(int i = 0; i < hand.size() ; i++) {  
			// 6C  -> only first index is important here!
			String key = String.valueOf(hand.get(i).charAt(0));
			score += cardValues.get(key);
		}
		
		
		//		// optional solution
		//        for (String card : hand) { // loop each card in hand 
		//            Integer value = cardValues.get(card.charAt(0));
		//            if (value != null) {
		//                score += value;
		//            } else {
		//                // Optional: throw an error if invalid card is encountered
		//                throw new IllegalArgumentException("Invalid card: " + card);
		//            }
		//        }

		System.out.println("\nScore is: " + score);
		return score;
	}
	
	
	// AS ARRAYS
//	public static int ScoreHand2(String[] hand) {
//	    int total = 0;
//	    int aces = 0;
//
//	    for (String card : hand) {
//	        switch (card) {
//	            case "A":
//	                aces++;
//	                total += 11;
//	                break;
//	            case "K":
//	            case "Q":
//	            case "J":
//	            case "10":
//	                total += 10;
//	                break;
//	            default:
//	                total += Integer.parseInt(card); // assuming it's 2–9
//	        }
//	    }
//
//	    // adjust aces if needed
//	    while (total > 21 && aces > 0) {
//	        total -= 10;
//	        aces--;
//	    }
//
//	    return total;
//	}



///////////////////////////////////////////////////////////////////////////////////////////////

	// Q4 - Is the hand bust?
	public static boolean HandBust(List<String> hand) { 

		boolean bust = false;
		// tried converting list to arraylist bc ScoreHand got arraylist input
		ArrayList<String> hand1 = new ArrayList<>(hand);
		// Arraylist to list:
		// List<String> list = arrayList;

		if (ScoreHand(hand1) > 21) {
			System.out.println("\nHand is bust.");
			bust = true;			
		}else {
			System.out.println("\nHand is NOT bust.");
		}
		
		return bust;	
	}


///////////////////////////////////////////////////////////////////////////////////////////////

	// Q5 - Who won? - returns the index of the hand, gets multiple hands as input (2d array/lists)
	
	// as arraylists
	public static int WhoWon(ArrayList<ArrayList<String>> hands) {
		
		if (hands == null) {  // if 2d hands arrays are null
			System.out.println("Input list of hands is null");
			return -1; // invalid input 
		}  

		if (hands.size() == 0) { // or hands.isEmpty()
			System.out.println("No hands provided");
			return -2; // no hands
		}
		
		// Check for duplicate cards across all hands
		HashSet<String> allCards = new HashSet<>();

		for (int i = 0; i < hands.size(); i++) {
			ArrayList<String> hand = hands.get(i);
			for (String card : hand) {
				if (allCards.add(card) == false) { // if a card cannot be added to the set
					System.out.println("Duplicate card found: " + card + " in multiple hands/same hand");
					return -4; // Duplicate card across hands
				}
			}
		}
	
		// ONLY TO CHECK WITHIN DIFFERENT HANDS
//		Set<String> seenCardsAcrossHands = new HashSet<>();
//
//		for (int i = 0; i < hands.size(); i++) {
//		    ArrayList<String> hand = hands.get(i);
//		    Set<String> localSet = new HashSet<>(hand); // make a set of this hand's cards
//
//		    if (localSet.size() < hand.size()) {
//		        continue; // skip same-hand duplicates, not our business here (already checked before)
//		    }
//
//		    for (String card : hand) {
//		        if (seenCardsAcrossHands.contains(card)) {
//		            System.out.println("Duplicate card found across different hands: " + card);
//		            return -4; // Or whatever error code
//		        } else {
//		            seenCardsAcrossHands.add(card);
//		        }
//		    }
//		}


		int indexwon = -1; // the index number if no one won
		int scoremax = -1;
		int fewestCards = -1; 

		for (int i = 0; i < hands.size(); i++) {
			ArrayList<String> hand = hands.get(i);  // get each hand in every loop

			// Validate hand  - assuming valid, skip
//			if (ValidHandCheck(hand) != 5) {
//				System.out.println("Hand at index " + i + " is invalid");
//				continue;  // Skip invalid hand
//			}

			int score = ScoreHand(hand); // get score

			if (score > 21) continue; // Check for bust, do nothing, they lost. go to the next hand

			// Select winner if not bust
	        if (score > scoremax || (score == scoremax && (fewestCards == -1 || hand.size() < fewestCards))) {
	        	scoremax = score;
	            indexwon = i;
	            fewestCards = hand.size(); // update the fewest card count
	        }
		}

		if (indexwon == -1) {
			System.out.println("All hands were bust or invalid");
			return -3; // All hands bust or invalid
		}

		return indexwon;
	}

	public static int WhoWon1(ArrayList<ArrayList<String>> hands) {

	    // Step 1: Check for null input
	    if (hands == null) {
	        System.out.println("Input list of hands is null");
	        return -1; // invalid input
	    }

	    // Step 2: Check if no hands are given
	    if (hands.size() == 0) {
	        System.out.println("No hands provided");
	        return -2; // no hands
	    }

	    // Step 3: Check for duplicate cards across all hands
	    HashSet<String> allCards = new HashSet<String>();

	    for (int i = 0; i < hands.size(); i++) {
	        ArrayList<String> hand = hands.get(i);

	        for (int j = 0; j < hand.size(); j++) {
	            String card = hand.get(j);

	            if (allCards.contains(card)) {
	                System.out.println("Duplicate card found: " + card + " in multiple hands or same hand");
	                return -4; // duplicate card found
	            }

	            allCards.add(card);
	        }
	    }

	    // Step 4: Initialize tracking variables
	    int indexWon = -1;
	    int highestScore = -1;
	    int fewestCards = -1;

	    // Step 5: Loop through each hand and calculate scores
	    for (int i = 0; i < hands.size(); i++) {
	        ArrayList<String> hand = hands.get(i);

	        int score = ScoreHand(hand); // assumes ScoreHand already works

	        // Skip bust hands
	        if (score > 21) {
	            continue;
	        }

	        // Step 6: Decide winner
	        if (score > highestScore) {
	            highestScore = score;
	            indexWon = i;
	            fewestCards = hand.size();
	        } else if (score == highestScore) {
	            if (fewestCards == -1 || hand.size() < fewestCards) {
	                indexWon = i;
	                fewestCards = hand.size();
	            }
	        }
	    }

	    // Step 7: If no winner found
	    if (indexWon == -1) {
	        System.out.println("All hands were bust or invalid");
	        return -3;
	    }

	    // Step 8: Return winning hand index
	    return indexWon;
	}


	
	// as 2D arrays
	public static int WhoWon2(String[][] hands) {

	    if (hands == null) {  // check if input is null
	        System.out.println("Input list of hands is null");
	        return -1; // invalid input
	    }

	    if (hands.length == 0) {
	        System.out.println("No hands provided");
	        return -2; // no hands
	    }

	    // Check for duplicate cards across all hands
	    HashSet<String> allCards = new HashSet<>();

	    for (int i = 0; i < hands.length; i++) {
	        String[] hand = hands[i];
	        for (String card : hand) {
	            if (!allCards.add(card)) { // if already in set = duplicate
	                System.out.println("Duplicate card found: " + card + " in multiple hands/same hand");
	                return -4; // duplicate card found
	            }
	        }
	    }

	    int indexwon = -1;
	    int scoremax = -1;
	    int fewestCards = -1;

	    for (int i = 0; i < hands.length; i++) {
	        String[] hand = hands[i];

	        // You can add optional hand validation here if needed
	        // if (ValidHandCheck(hand) != 5) continue;

//	        int score = ScoreHand(hand); // THERES AN ERROR bc ScoreHand method uses Arraylists
	        int score = ScoreHand(new ArrayList<>(Arrays.asList(hand)));
	        
	        if (score > 21) continue; // bust? skip

	        // Winner logic
	        if (score > scoremax || (score == scoremax && (fewestCards == -1 || hand.length < fewestCards))) {
	            scoremax = score;
	            indexwon = i;
	            fewestCards = hand.length;
	        }
	    }

	    if (indexwon == -1) {
	        System.out.println("All hands were bust or invalid");
	        return -3;
	    }

	    return indexwon;
	}


///////////////////////////////////////////////////////////////////////////////////////////////

	// Q6 - Remaining Cards, return lists
	public static List<String> CardRemains(List<List<String>> hands) { 

		// Step 1: Build full deck
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
        String[] suits = {"H", "D", "C", "S"};

        Set<String> fullDeck = new HashSet<>();
        for (String rank : ranks) {
            for (String suit : suits) {
                fullDeck.add(rank + suit);
            }
        }

        // Step 2: Remove cards used in hands
        for (List<String> hand : hands) {
            fullDeck.removeAll(hand);
        }

        // Step 3: Return remaining cards as a sorted list
        List<String> remaining_hands = new ArrayList<>(fullDeck);
        Collections.sort(remaining_hands); // Optional: sorts alphabetically

		return remaining_hands; 
	}
	
	
	// Another method
	public static List<String> CardRemains2(List<List<String>> hands) {
	    
	    // Step 1: Build the full deck (52 cards)
	    String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};
	    String[] suits = {"H", "D", "C", "S"};
	    
	    List<String> fullDeck = new ArrayList<>(); // use a list so we can remove easily
	    
	    for (int i = 0; i < ranks.length; i++) {
	        for (int j = 0; j < suits.length; j++) {
	            String card = ranks[i] + suits[j]; // example: "AH", "9D"
	            fullDeck.add(card);
	        }
	    }

	    // Step 2: Loop through each card in each hand and remove it from the deck
	    for (int i = 0; i < hands.size(); i++) {
	        List<String> hand = hands.get(i);
	        for (int j = 0; j < hand.size(); j++) {
	            String card = hand.get(j);
	            fullDeck.remove(card); // remove if present
	        }
	    }

	    // Step 3: Sort the remaining cards alphabetically
	    Collections.sort(fullDeck); // optional

	    return fullDeck;
	}


}
