package Animal_Classes;

public class Bird extends Animal
{
	public Bird()
	{
		super("Bird", true , 2, "Yellow", true, "chirp");
	}
	public void display()
	{
		System.out.println();
		System.out.println("***** Bird *****");
		super.display();
	}
}
