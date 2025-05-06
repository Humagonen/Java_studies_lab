import java.util.ArrayList;
import java.util.Arrays;

public class Question5 {

	public static void main(String[] args) {

		ArrayList<String> C = new ArrayList<String>();

		C.add("13");
		C.add("-21");
		C.add(" ");
		C.add("");
		C.add("kk");
		C.add(null);

//		System.out.println(C.size());
		CToF(C);

	}



	// get string arraylist
	// convert each element to double
	// calculate each doubled element to fahrenheit
	// add to fahrenheit arraylist
	// string to double -> Double.parseDouble("3.14") → 3.14
	public static ArrayList<Double> CToF(ArrayList<String> C) {

		ArrayList<Double> fahrenheit = new ArrayList<Double>();


		 // test 1
		for (int i = 0; i < C.size(); i++) {

			if((C.get(i) == null) || (C.get(i).isEmpty())) {

				System.out.println("returned null" + fahrenheit);
				return null; 
			}
		}

		try {

			for (int i = 0; i < C.size(); i++) {

				fahrenheit.add((Double.parseDouble(C.get(i))) * 9.0/5.0 + 32);
			}

			System.out.println("no issues in the arraylist: " + fahrenheit);
			return fahrenheit;

		}catch(Exception e) {

			ArrayList<Double> fahrenheit2 = new ArrayList<Double>();
			System.out.println("returned empty array" + fahrenheit2);
			return fahrenheit2;
		}

	}

}
