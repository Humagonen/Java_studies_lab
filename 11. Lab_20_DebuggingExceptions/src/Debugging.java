import java.util.Random;

public class Debugging 
{
	
   static public Random rand = new Random();
   static public void main(String args[])
   {
      double x = 10;
		
      x = F1(x);
      x = F2(x);
      x = F3(x);
      System.out.println(x);
   }
   static public double F1(double x)
   {
         double local = rand.nextDouble();
         return(x + local);
   }
   static public double F2(double x)
   {
      double local = rand.nextDouble();
      return(x + local*10.0);
   }
   static public double F3(double x)
   {
      double local = rand.nextDouble();
      return(x + local*100.0);
   }
}

