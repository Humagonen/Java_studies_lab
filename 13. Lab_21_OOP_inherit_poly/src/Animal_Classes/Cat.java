package Animal_Classes;

public class Cat extends Animal {
	
	public Cat()
	{
		super("Cat", true ,4, "tortoise shell", false, "meow");
	}
	public void display()
	{
		System.out.println();
		System.out.println("***** Cat *****");
		super.display();
	}

}
