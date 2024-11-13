import java.util.ArrayList;

public class Lab_exercises {
	public static void main(String args[]){
		
		// this is not possible:
//		double x = 10;
//		int y = x;
//		System.out.println(y);
		
		// correct way:
		double x = 10.5;
		int y = (int) x;
		System.out.println(x);
		System.out.println(y);
		
		char letter = 'A';
		int ascii = (int) letter;

		System.out.println(ascii);
		
		
	// Q6.7.2.1
		
		ArrayList<Data> people = new ArrayList<Data>();
		
		people.add(new Data("Fred", 21));
		people.add(new Data("Jo", 43));
		people.add(new Data("Zoe", 37));
		people.add(2, new Data("Harry", 71)); // inserting to specific index
		
		PrintDataArray(people);
		
	// Q6.7.2.2
		
		
	}

	
	private static void PrintDataArray(ArrayList<Data> array) 
	{
		for(Data e: array) 
		{
			e.Print();
		}
	}
}
