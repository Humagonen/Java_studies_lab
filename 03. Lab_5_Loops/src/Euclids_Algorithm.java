
public class Euclids_Algorithm {

	public static void main(String[] args) {
		
		int a = 26;
		int b = 88; 

		euclid(a, b);
		
	}
	
	public static void euclid(int a, int b) {
		
		while (b != 0) {
			
			int temp = b;
			b = a % b;
			a = temp;	
		}
		
		System.out.println("HCF, GCF: " + a);
	}

}

