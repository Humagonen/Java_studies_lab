package Animal_Classes;

public class Dog extends Animal
{
	public Dog()
	{
		super("Dog", true ,4, "Golden", false, "Woof");
	}
	public void display()
	{
		System.out.println();
		System.out.println("***** Dog *****");
		super.display();
	}
}
