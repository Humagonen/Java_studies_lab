
public class Switch_statement {

	public static void main(String[] args) {

		String month = "September";
		int daysinmonth = 0;

		switch(month)
		{
		case "January": 
			daysinmonth = 31;
			break;
		case "February":
			daysinmonth = 28;
			break;
		case "March":
			daysinmonth = 31;
			break; 
		case "April":
			daysinmonth = 30;
			break;
		case "May":
			daysinmonth = 31; 
			break;
		case "June":
			daysinmonth = 30;
			break;
		case "July":
			daysinmonth = 31;
			break;
		case "August":
			daysinmonth = 31;
			break;
		case "September":
			daysinmonth = 30;
			break;
		case "October":
			daysinmonth = 31;
			break;
		case "November":
			daysinmonth = 30;
			break;
		case "December":
			daysinmonth = 31;
			break;
		default: 
			daysinmonth = -1;
			break;
		}

		System.out.println("Days in month " + month + " = " + daysinmonth);

		
		System.out.println("\n");
		easierans();
		System.out.println("\n");
		animallegs();
	}
	
	
	


	public static void easierans() {

		String month = "October";
		int daysinmonth = 0;
		
		switch (month)
		{
		case "January": case "March": case "May": case "July": case "August": case "October": case "December":
			daysinmonth = 31;
			break;
		case "February":
			daysinmonth = 28;
			break;     	
		case "April": case "June": case "September": case "November":
			daysinmonth = 30;
			break;    
		default: 
			daysinmonth = -1;
			break;
		}
		System.out.println("Days in month " + month + " = " + daysinmonth);

	}
	
	
	// Try new example:
//	4 legs: Alsatian Dog, Sphynx Cat, Baboon, Ocelot, Potto
//	0 legs: Cobra, Cod, Minke Whale
//	8 legs: Tarantula, Black Scorpion, Paul Allen the German Octopus
//	2 legs: Human, Klingon
//	Varies: Centipede (30 to 354 legs)
	
	public static void animallegs() {  

		String animal = "Centipede";
		String legs = " ";
		
		switch (animal)
		{
		case "Alsatian Dog": case "Sphynx Cat": case "Baboon": case "Ocelot": case "Potto":
			legs = "4";
			break;
		case "Tarantula": case "Black Scorpion": case "Paul Allen the German Octopus":
			legs = "8";
			break;     	
		case "Cobra": case "Cod": case "Minke Whale":
			legs = "0";
			break;    
		case "Human": case "Klingon":
			legs = "2";
			break;  
		case "Centipede":   // solved by making the legs variable as string
			legs = "30 to 354 legs";
			break; 
		default: 
			legs = "unknown";
			break;
		}
		System.out.println("Number of legs of " + animal + " = " + legs);

	}
	
}
