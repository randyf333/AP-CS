import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
//here any additional imports that you may need
 
public class Cemetery
{
   public static void main (String [] args)
   {
      File file = new File("cemetery_short.txt");
      //File file = new File("cemetery.txt");
      int numEntries = countEntries(file);
      Person[] cemetery = readIntoArray(file, numEntries); 
      //see what you have
      for (int i = 0; i < cemetery.length; i++) 
         System.out.println(cemetery[i]);
         
      
      int min = locateMinAgePerson(cemetery);
      int max = locateMaxAgePerson(cemetery); 
      System.out.println("\nIn the St. Mary Magdelene Old Fish Cemetery --> ");
      System.out.println("Name of youngest person: " + cemetery[min].getName());
      System.out.println("Age of youngest person: " + cemetery[min].getAge());    
      System.out.println("Name of oldest person: " + cemetery[max].getName());
      System.out.println("Age of oldest person: " + cemetery[max].getAge());
     // Person person = makeObjects("Allen Jones Jimmy Jon Sr. 24 Jan 1850 80 a;djfa;djf");
      
      //you may create other testing cases here
      //comment them out when you submit your file to Grade-It
      
          
   }
   
   /* Counts and returns the number of entries in File f. 
      Returns 0 if the File f is not valid.
      Uses a try-catch block.   
      @param f -- the file object
   */
   public static int countEntries(File f)
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(f);  
      }
      catch(IOException e)
      {
         
         return 0;   
      }
   
      int count = 0;
      while(infile.hasNextLine())
      {
         count++;
         infile.nextLine();
      }
      return count;
   }
 
   /* Reads the data from file f (you may assume each line has same allignment).
      Fills the array with Person objects. If File f is not valid return null.
      @param f -- the file object 
      @param num -- the number of lines in the File f  
   */
   public static Person[] readIntoArray (File f, int num)
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(f);  
      }
      catch(IOException e)
      {
         
         return null;   
      }
   
      int size = 0;
      for(int x = 0; x < num; x++)
      {
         size++;
      }
      Person[] array = new Person[size];
      while(infile.hasNextLine())
      {
         for(int x = 0; x < array.length; x++)//Name
         {
            String n = "";
            String d = "";
            String a = "";
            String add = "";
            StringTokenizer st = new StringTokenizer(infile.nextLine());
            while(add.contains("0") == false && add.contains("1") == false && add.contains("2") == false && add.contains("3") == false && add.contains("xx") == false)
            {
               n = n+add+" ";
               //add = infile.next();//Name
               add = st.nextToken();
            } 
            for(int y = 0; y < 3; y++)
            {
               d = d+add+" ";
               //add = infile.next();
               add = st.nextToken();
            }  
            a = add;
            Person person = new Person(n,d,a);
            array[x] = person;
            //infile.nextLine();
         }
          
      }
      return array;
   }      
   /* A helper method that instantiates one Person object.
      @param entry -- one line of the input file.
      This method is made public for gradeit testing purposes.
      This method should not be used in any other class!!!
   */
   public static Person makeObjects(String entry)
   {
      String n = "";
      String d = "";
      String a = "";
      String add = "";
      StringTokenizer st = new StringTokenizer(entry);
      while(add.contains("0") == false && add.contains("1") == false && add.contains("2") == false && add.contains("3") == false && add.contains("xx") == false)
      {
         n = n+add+" ";
         add = st.nextToken();//Name
      } 
      for(int y = 0; y < 3; y++)
      {
         d = d+add+" ";
         add = st.nextToken();
      }  
      a = add;
      
      Person person = new Person(n,d,a);
      return person;
   }  
   
   /* Finds and returns the location (the index) of the Person
      who is the youngest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
   */
   public static int locateMinAgePerson(Person[] arr)
   {
      if(arr.length ==0)
      {
         return -1;
      }
      int index = 0;
      for(int x = 0; x < arr.length; x++)
      {
         if(arr[x].getAge()<arr[index].getAge())
         {
            index = x;
         }
      }
      return index;
   }   
   
   /* Finds and returns the location (the index) of the Person
      who is the oldest. (if the array is empty it returns -1)
      If there is a tie the lowest index is returned.
      @param arr -- an array of Person objects.
   */
   public static int locateMaxAgePerson(Person[] arr)
   {
      if(arr.length ==0)
      {
         return -1;
      }
      int index = 0;
      for(int x = 0; x < arr.length; x++)
      {
         if(arr[x].getAge()>arr[index].getAge())
         {
            index = x;
         }
      }
   
      return index;
   }        
} 
 
class Person
{
   //constant that can be used for formatting purposes
   private static final DecimalFormat df = new DecimalFormat("0.0000");
   /* private fields */
   
      
   /* a three-arg constructor  
    @param name, burialDate may have leading or trailing spaces
    It creates a valid Person object in which each field has the leading and trailing
    spaces eliminated*/
   String n, d, y;
   public Person(String name, String burialDate, String age)
   {
      n = name;
      d = burialDate;
      y = age;
   }
   /* any necessary accessor methods (at least "double getAge()" and "String getName()" )
   make sure your get and/or set methods use the same data type as the field  */
   public String getName()
   {
      return n;
   }
   public double getAge()
   {
      return calculateAge(y);
   }
   
   /*handles the inconsistencies regarding age
     @param a = a string containing an age from file. Ex: "12", "12w", "12d"
     returns the age transformed into year with 4 decimals rounding
   */
   public double calculateAge(String a)
   {
      if(a.contains("d"))
      {
         String num = df.format(Double.parseDouble(a.substring(0,a.length()-1))/365);
         return Double.parseDouble(num);
      }
      if(a.contains("w"))
      {
         String num2 =df.format(Double.parseDouble(a.substring(0,a.length()-1))/52);
         return Double.parseDouble(num2);
      }
      Double num3 = Double.parseDouble(a);
      return  num3;
   }
}
 
/******************************************
 
 John William ALLARDYCE, 17 Mar 1844, 2.9
 Frederic Alex. ALLARDYCE, 21 Apr 1844, 0.17
 Philip AMIS, 03 Aug 1848, 1.0
 Thomas ANDERSON, 06 Jul 1845, 27.0
 Edward ANGEL, 20 Nov 1842, 22.0
 Lucy Ann COLEBACK, 23 Jul 1843, 0.2685
 Thomas William COLLEY, 08 Aug 1833, 0.011
 Joseph COLLIER, 03 Apr 1831, 58.0
 
 In the St. Mary Magdelene Old Fish Cemetery --> 
 Name of youngest person: Thomas William COLLEY
 Age of youngest person: 0.011
 Name of oldest person: Joseph COLLIER
 Age of oldest person: 58.0
 
 **************************************/