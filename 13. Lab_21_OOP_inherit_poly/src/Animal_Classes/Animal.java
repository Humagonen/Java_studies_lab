package Animal_Classes;

public class Animal extends LivingThings{
	
	private int num_legs;
	private String colour;
	private boolean animal_is_wild;
	private String noise_made;
	
	public Animal(String name, boolean isAlive, int nl, String clr, boolean wild, String sound)
	{
		super(name, isAlive);
		num_legs = nl;
		colour = clr; 
		animal_is_wild = wild;
		noise_made = sound;

	}
	
	public void display()
	{
		super.display();
		
		if (num_legs < 1 || num_legs > 4) {
			System.out.println("Error: Number of legs cannot be less than 1 or greater than 4");
		
		}else {
			
			System.out.println("Number of legs = "+num_legs);
			System.out.println("Colour = "+colour);
			System.out.println("Does the animal live in the wild?  "+animal_is_wild);
			System.out.println("The noise it makes = "+noise_made);

		}
	}
}
