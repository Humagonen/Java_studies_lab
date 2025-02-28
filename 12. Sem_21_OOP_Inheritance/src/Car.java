
public class Car extends Vehicle
{
	private double engine_cc; // only for car class	
	private int num_doors;
	
	public Car()
	{ 
		super(4, 4, 100, 40);
		engine_cc = 2000;  // only for car class
		num_doors = 4;
	}
	public void display()
	{
		System.out.println();
		System.out.println("***** Car *****");
		super.display();
		System.out.println("Engine_cc: "+engine_cc);  // only for car class	
		System.out.println("Number of doors: "+num_doors);
	}
}



