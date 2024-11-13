import java.util.ArrayList;

public class Exercise3 {


	public static void main(String args[])
	{
		Data x = new Data("Fred",41);
		Data a = new Data("Jo",43);
		Data b = new Data("Zoe",37);
		//		x.Print();
		//		a.Print();
		//		b.Print();
		
		ArrayList<Data> people = new ArrayList<Data>(10); // size 10 in memory is default
//		System.out.println(people.size());
		people.add(b);
		people.add(a);
		people.add(x);

		PrintDataArray(people);
		
	}
	
	// the following method is to print the data in the array
	private static void PrintDataArray(ArrayList<Data> array) 
	{
		for(Data e: array) 
		{
			e.Print();
		}
	}

}
