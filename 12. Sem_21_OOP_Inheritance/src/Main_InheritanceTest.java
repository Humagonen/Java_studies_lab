
public class Main_InheritanceTest {

	public static void main(String args[])
	{
		Car c = new Car();
		c.display();

		Bicycle bike = new Bicycle();
		bike.display();

		Scooter sc = new Scooter();
		sc.display();

		if (sc instanceof Scooter) { 
			System.out.println("yes that is true");
		}

		if (sc instanceof Vehicle) {
			System.out.println("yes that is true too");
		}

		// sc is an instance of both Scooter and Vehicle classes since one inherits from the other

	}
}

