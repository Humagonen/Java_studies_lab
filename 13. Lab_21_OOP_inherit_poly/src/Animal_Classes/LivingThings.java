package Animal_Classes;

//Parent Class
class LivingThings {
	private String name;
	private boolean isAlive;

	// Constructor with parameters
	public LivingThings(String name, boolean isAlive) {
		this.name = name;
		this.isAlive = isAlive;
	}

	public void display() {
		System.out.println("Name: " + name);
		System.out.println("Alive: " + (isAlive ? "Yes" : "No"));
		System.out.println();
	}
}
