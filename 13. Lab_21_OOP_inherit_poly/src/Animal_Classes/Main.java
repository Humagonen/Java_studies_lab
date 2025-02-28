package Animal_Classes;

public class Main {
	
	public static void main(String[] args) {
		
		Dog d = new Dog();
		Animal b = new Bird();
		Animal c = new Cat();
		
		LivingThings h = new Human("Huma", true, "Turkish");
		Human x = new Human("Sevde", true, "English");
		
		// both defining with Animal or with the specific animal class works the same
		// same thing with Human and LivingThings
		
		
		d.display();
		b.display();
		c.display();
		
		h.display();
		x.display();
	}

}
