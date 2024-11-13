// the ones that are commented does not work, also InfinityHuma does not make sense so can be considered as error


public class Lab3Excercise {
	
    public static void main(String[] args) {
        // Declare and assign variables for length and width
    	int a = 100, g = 0;
    	double b = 2.3, c = -52.2, pi = 3.142 ;
    	boolean d = true;
    	String e = "I am ", f = "a student", name = "Huma" ;
    	char h = '!';
    	long x;
    	double y;
    	String z;
    	
    	
    	//1
    	y = a + b;
    	System.out.println(y);
    	
    	//2
    	// y = a + d;
    	
    	//3
    	z = e + f;
    	System.out.println(z);
    	
    	//4
    	y = b * c;
    	System.out.println(y);
    	
    	//5
    	pi = 22/7;
    	System.out.println(pi);
    	
    	//6
    	// z = name / g;
    	
    	//7
    	// z = c / g;
    	
    	//8
//    	x = 10;
//    	x = Math.pow(x, 3); 
//    	System.out.println(x);
    	
    	//9
    	z = name + "is" + f + g;
    	System.out.println(z);
    	
    	//10
    	// y = (name + 10) / (name + h);
    	
    	//11
    	// x = (a +b)/(d+c);
    	
    	//12
    	y = 100.3;
    	y = (y / (a + b)) - c;
    	System.out.println(y);
    	
    	//13
    	// x * x = y * y + z * z;
    	
    	//14
//    	name = "Is " + f - h; 
    	
    	//15
    	y = ((pi + 1) / (pi + 2)) / (pi + 3);
    	System.out.println(y);
    	
    	//16
    	y = -2;
    	y = Math.pow(Math.pow(y, 2) / b, 1/3);
    	System.out.println(y);
    	
    	//17
    	y = b/g;
    	z = y + name; // InfinityHuma, 
    	System.out.println(z);
    	
    	//18
    	z = name;
    	z = b/g + z;  // b/ 0 + z (name)  therefore InfinityHuma
    	System.out.println(z);  
    	
    	// 19
    	y = -2.3;
    	y = a*y*y+b*y+c;
    	System.out.println(y);
    	
    	
    	///////////////////////////////////////
    	double num = Math.pow(a, c);
    	System.out.println(num);

    	double num2  = 1.2355555555555555555555555555555555555553;
    	float num3 = (float) num2; // 
    	System.out.println(num3);
    }


}
