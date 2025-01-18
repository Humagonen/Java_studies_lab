
public class Question3 {
	
	public static void main(String[] args) {
		
		int a = 5;
		int b = 6;
		int c = 7;
		
		IsRightAngled(a,b,c);
	}
	
	public static int IsRightAngled(int a, int b, int c) {
		
		if ((a<5  || a>30) || (b<5  || b>30)  || (c<5  || c>30)) {
			
			return -2;
		}
		
		if (a*a + b*b == c*c) {
			
			return 1;
			
		}else {
			
			return 0;
		}		
	}
	

}
