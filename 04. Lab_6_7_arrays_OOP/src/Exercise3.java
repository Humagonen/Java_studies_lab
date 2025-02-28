import java.util.ArrayList;

public class Exercise3 {

	public static void main(String args[])
	{
		Data x = new Data("Fred",41);
		Data a = new Data("Jo",43);
		Data b = new Data("Zoe",37);
		
		System.out.println(x); // to see the difference 
		x.Print();
		//		a.Print();
		//		b.Print();
		
		System.out.println();
		ArrayList<Data> people = new ArrayList<Data>(); // size 10 in memory is default
		
		System.out.println(people.size()); // check size
		
		people.add(x);
		people.add(a);
		people.add(b);
		// people.add(2, new Data("Harry", 71)); 
		// array.add(int index, the data to be added to the array)
		
		System.out.println(people.size()); // check size after adding 
		
		System.out.println();
		PrintDataArray(people);
		
	}
	
	// the following method is to print the data in the array
	private static void PrintDataArray(ArrayList<Data> array) 
	{
		for(Data e: array) // for e in array. loop for all elements in it
		{
			e.Print(); // print each value
		}
	}

}
