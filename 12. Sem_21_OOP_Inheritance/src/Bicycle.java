
public class Bicycle extends Vehicle{
	
	public Bicycle()
	{
		super(2, 1, 35, 2);
	}

	public void display() 
	{
		
		System.out.println();
		System.out.println("***** Bicycle *****");
		super.display();
	}
}
