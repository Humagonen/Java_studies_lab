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
		System.out.println();


		// Q.6.7.2

		ArrayList<Data> ArrayA = new ArrayList<Data>();

		ArrayA.add(new Data("Fred", 41));
		ArrayA.add(new Data("Jo", 43));
		ArrayA.add(new Data("Zoe", 37));
//		ArrayA.add(2, new Data("Harry", 71)); // inserting to specific index

		ArrayList<Data> ArrayB = new ArrayList<Data>();
		
		System.out.print("ArrayA:\n");
		PrintDataArray(ArrayA);
		System.out.println();

		ArrayB = ArrayA;  // copied ArrayA to ArrayB
		System.out.print("ArrayB:\n");
		PrintDataArray(ArrayB);
		System.out.println();

		ArrayA.remove(1);  // Removed the the specific indexed value: index 1 which is Jo
		System.out.print("ArrayB:\n");
		PrintDataArray(ArrayB);
		System.out.println();
		
		
		ArrayList<Data> ArrayC = new ArrayList<Data>();
		ArrayList<Data> ArrayD = new ArrayList<Data>();
		
		System.out.print("ArrayA:\n");
		PrintDataArray(ArrayA);
		System.out.println();
		
		ArrayC = ArrayA;  // copied ArrayA to ArrayC
		System.out.print("ArrayC:\n");
		PrintDataArray(ArrayC);
		System.out.println();
		
		ArrayD = (ArrayList<Data>)ArrayC.clone(); // if we use clone instead of assigning each 
// arrays to each other, then differences made in one array will not effect the other
		ArrayC.remove(1);
		System.out.print("ArrayC:\n");
		PrintDataArray(ArrayC);
		System.out.println();
		
		System.out.print("ArrayD:\n");
		PrintDataArray(ArrayD);
		System.out.println(); 
		
		
		// Q.6.7.3 Swiftbot
		

	}


	private static void PrintDataArray(ArrayList<Data> array) 
	{
		for(Data e: array) 
		{
			e.Print();
		}
	}
}
