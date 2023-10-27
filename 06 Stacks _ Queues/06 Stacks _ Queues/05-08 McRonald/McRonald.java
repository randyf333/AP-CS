//Updated on 12.14.2020 v2

//Name:   Date:
import java.util.*;
import java.io.*;
public class McRonald
{
   public static final int TIME = 1080;     //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;      // to serve the front of the queue
   public static int thisCustomersTime;
   public static PrintWriter outfile = null; // file to display the queue information
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
   public static void displayTimeAndQueue(Queue<Customer> q, int min)
   { 
      //Billington's
      outfile.println(min + ": " + q);	
      //Jurj's
      //outfile.println("Customer#" + intServiceAreas[i] + 
      //                            " leaves and his total wait time is " + (intMinute - intServiceAreas[i]));                     	
   }
   public static int getCustomers()
   {
      return customers;
   }
   public static double calculateAverage()
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   public static int getLongestWaitTime()
   {
      return longestWaitTime;
   }
   public static int getLongestQueue()
   {
      return longestQueue;
   }    
   public static void main(String[] args)
   {     
    //set up file      
      try
      {
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      mcRonald(TIME, outfile);   //run the simulation
      outfile.close();	
   }
   
   public static void mcRonald(int TIME, PrintWriter of)
   {
      Queue<Customer> line = new LinkedList<Customer>();
      /***************************************
           Write your code for the simulation   
      **********************************/
        //int start = 0;
      int size = 0;
      while(totalMinutes < TIME || !line.isEmpty())
      {
         if(totalMinutes < TIME){
            double chance = Math.random();
            if(chance < CHANCE_OF_CUSTOMER)
            {
               Customer dude = new Customer();
               line.add(dude);
               customers++;
                                /*else{
               while(line.hasNext())
               thisCustomersTime = thisCustomersTime + 
               }*/
               if(line.size() > longestQueue)
               {
                  longestQueue = line.size();
               }
            //int currentTime = totalMinutes;
               displayTimeAndQueue(line,totalMinutes);
            }else{
               displayTimeAndQueue(line,totalMinutes);
            }
         }else{
            displayTimeAndQueue(line,totalMinutes);
         }
         if(!line.isEmpty())
         {
            Customer prev = line.peek();
            of.println("\t" + prev.statement());
            if(prev.getOrderTime() != 0)
            {
               prev.servingTime();
            }
            if(prev.getOrderTime() == 0)
            {
               thisCustomersTime = totalMinutes-prev.getArrivalTime()+1;
               line.remove();
            }
         }
         if(thisCustomersTime > longestWaitTime)
         {
            longestWaitTime = thisCustomersTime;
         }
         totalMinutes++;//goes at end
      }
              
      /*   report the data to the screen    */  
      System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + longestQueue);
   }
   static class Customer      
   {
      private int arrivedAt;
      private int orderAndBeServed;
          /**********************************
       Complete the Customer class with  
       constructor, accessor methods, toString.
    ***********************************/
      public Customer()
      {
         arrivedAt = totalMinutes;
         orderAndBeServed = timeToOrderAndBeServed();
      }
      public int getArrivalTime()
      {
         return arrivedAt;
      }   
      public int getOrderTime()
      {
         return orderAndBeServed;
      }
      public void servingTime()
      {
         orderAndBeServed--;
      }
      public String toString()
      {
         return ""+getArrivalTime();
      }
      public String statement()
      {
         String info = ""+arrivedAt+" is now being served for "+orderAndBeServed+" minutes.";
         return info;
      } 
   }
}