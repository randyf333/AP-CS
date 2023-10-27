// Name:
// Date:
 
import java.text.DecimalFormat;
public class SmartCard_Driver
{
   public static void main(String[] args) 
   {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
   
      SmartCard jimmy = new SmartCard(20.00); 
      jimmy.board(center);                    //Boarded at Center City.  SmartCard has $20.00
      jimmy.exit(suburbia);              //From Center City to Suburb costs $2.75.  SmartCard has $17.25
      jimmy.exit(uptown);				//Error:  did not board?!
      System.out.println();
   	/*	
      SmartCard susie = new SmartCard(1.00); 
      susie.board(uptown);            		//Boarded at Uptown.  SmartCard has $1.00
      susie.exit(suburbia);				//Insuffient funds to exit. Please add more money.
      System.out.println();
   
      SmartCard kim = new SmartCard(.25);    
      kim.board(uptown);            		    //Insuffient funds to board. Please add more money.
      System.out.println();
   
      SmartCard b = new SmartCard(10.00);   
      b.board(center);            		    //Boarded at Center City.  SmartCard has $10.00
      b.exit(downtown);					//From Center City to Downtown costs $0.50.  SmartCard has $9.50
      System.out.println();
        
      SmartCard mc = new SmartCard(10.00);  
      mc.board(suburbia);            		    //Boarded at Suburbia.  SmartCard has $10.00
      mc.exit(downtown);					//From Suburbia to Downtown costs $2.75.  SmartCard has $7.25
      System.out.println();
    //testcase
      SmartCard test = new SmartCard(80.00);
      test.board(center);
      test.exit(center);
      */
      SmartCard test2 = new SmartCard(100.00);
      SmartCard test3 = new SmartCard(100.00);
      test2.board(center);
      test2.exit(center);
     
      //test3.board(uptown);
      //test3.exit(uptown);
               //Make more test cases.  What else needs to be tested?
   }
} 	

//Note SmartCard is not denoted as public.  Why?
class SmartCard 
{//replaced money with this.m
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   public double money,m;
   public int n,y,z;
   public Object o,f;
   public String s,p,c;
   public boolean b;
   public int check;
   Station sta = new Station(s,n);
   /* enter your code below */
   public SmartCard(double m)
   {
      //money = m;
      //money = this.m;
      this.m = m;
      o = null;
      s = null;
      b = false;
   }

   public void addMoney(double d)
   {
      m = m + d;
   }
   public String getBalance()
   {
      return df.format(m);
   }
   public boolean isBoarded()
   {
      if(check == 1)
      {
         return true;
      }else{
         return false;
      }
   }
   public void board(Station s)
   {
      //String p;
      z = s.getZone();
      if(isBoarded() == true)
      {
         System.out.println("Error: already boarded?!");
         return;
      }
   
      if(this.m < .5)
      {
         System.out.println("Insufficient funds to board. Please add more money.");
         check = 1;
         return;
      }
      p = s.getName();
      
      check = 1;
      System.out.println("Boarding station: "+s.getName()+", Balence: "+df.format(m));
      sta = s;
     
      return;
   }
   
   public double cost(Station s)
   {
      money = 0; 
      if(s.getZone() == y)
      {
         money = MIN_FARE;
      }
      if(s.getZone() != y)
      {
         int diff = Math.abs(z-s.getZone());////////////
         int num = 0;
         for(int x = 0; x < diff; x++)
         {
            num++;
         }
         money = MIN_FARE+(0.75*num);
         y = s.getZone();
         return money;
      }
      
      return money;
   }

   public void exit(Station s)
   {
      double price = cost(s);
      c = s.getName();
      if(isBoarded() == false)
      {
         System.out.println("Error: Did not board?!");
         check = 2;
         return;
      }
      if(price > m)
      {
         System.out.println("Insufficient funds to exit. Please add more money.");
         check = 2;
         return;
      }
      
      m = m - price;
         
      System.out.println("Start: " + p + ", End: " + c + ", Cost: "+df.format(price)+", Remaining balence "+df.format(m));
      check = 2;
      sta = null;
      
   }
   
   //the next 3 methods are for use ONLY by Grade-It
   //these accessor methods only return your private data
   //they do not make any changes to your data
   double getMoneyRemaining()
   {
    //enter your code here and replace the return with yours
      return m;
   }
   
   Station getBoardedAt()
   {
    //enter your code here and replace the return with yours
      return sta;
   }
  
   boolean getIsOnBoard()
   {
   //enter your code here and replace the return with yours
      boolean a = isBoarded();
      return a;
   }
}
   
//Note Station is not a public class.  Why?
class Station
{
   private String s,x;
   private int i,y;
   /*public Station()
   {
      this();
   }*/
   public Station(String x,int y)
   {
      s = x;
      i = y;
      this.x = s;
      this.y = i;
   }
   public int getZone()
   {
      return i;
   }
   
   public String getName()
   {
      return s;
   }
}

 /*******************  Sample Run ************

 Boarded at Center City.  SmartCard has $20.00
 From Center City to Suburb costs $2.75.  SmartCard has $17.25
 Error: did not board?!
 
 Boarded at Uptown.  SmartCard has $1.00
 Insufficient funds to exit. Please add more money.
 
 Insufficient funds to board. Please add more money.
 
 Boarded at Center City.  SmartCard has $10.00
 From Center City to Downtown costs $0.50.  SmartCard has $9.50
 
 Boarded at Suburb.  SmartCard has $10.00
 From Suburb to Downtown costs $2.75.  SmartCard has $7.25
 
 ************************************************/
