
public class Vehicle
{
	private int nwheels;
	private int nseats; 
	private int max_speed;
	private int age;

	public Vehicle(int nw, int num_seats, int ms, int vehicle_age)
	{
		nwheels = nw;
		nseats = num_seats; 
		max_speed = ms; 
		age = vehicle_age;
	}
	public void display()
	{
		System.out.println("Number of wheels = "+nwheels);
		System.out.println("Number of seats = "+nseats);
		System.out.println("Maximum speed is = "+max_speed);
		System.out.println("Age of vehicle is = "+age);
	}
}

