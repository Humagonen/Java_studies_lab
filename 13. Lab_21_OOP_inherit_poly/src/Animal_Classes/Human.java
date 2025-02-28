package Animal_Classes;

public class Human extends LivingThings{

	private String lang;
	
	public Human(String name, boolean isAlive, String language)  // If I give parameters to construct, call in main with them
	{
		super(name, isAlive);
		lang = language;
		
	}

	public void spokenLang() {

		System.out.println("Language spoken = " + lang);

	}

	public void display()
	{
		System.out.println();
		System.out.println("***** Human *****");
		super.display();

		spokenLang();
	}

}


