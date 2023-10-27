// Name:
// Date:
  
import java.util.*;
import java.lang.Math;
public class Permutations
{
   public static int count = 0;
    
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("\nHow many digits? ");
      int n = sc.nextInt();
      leftRight("", n);  
              
      oddDigits("", n);
      
      superprime(n);
      if(count==0)
         System.out.println("no superprimes");
      else
         System.out.println("Count is "+count);
   }
   
    /**
     * Builds all the permutations of a string of length n containing Ls and Rs
     * @param s A string 
     * @param n An postive int representing the length of the string
     */
   public static void leftRight(String s, int n)
   {
       
      String l = s+"L";
      if(l.length() == n)
      {
         System.out.println(l);
      }else{
         leftRight(l,n);
      }
      String r = s+"R";
      if(r.length() == n)
      {
         System.out.println(r);
      }else{
         leftRight(r,n);
      }
      
   }
   
    /**
     * Builds all the permutations of a string of length n containing odd digits
     * @param s A string 
     * @param n A postive int representing the length of the string
     */
   
   public static void oddDigits(String s, int n)
   {
      String[] array = {"1","3","5","7","9"};
      for(int x = 0; x < array.length; x++)
      {
         String string = s+array[x];
         if(string.length() == n)
         {
            System.out.println(string);
         }else{
            oddDigits(string,n);
         }
      }
      /*String one = s+"1";
      if(one.length() == n)
      {
         System.out.println(one);
      }else{
         oddDigits(one,n);
      }
   
      String three = s+"3";
      if(three.length() == n)
      {
         System.out.println(three);
      }else{
         oddDigits(three,n);
      }
      String five = s+"5";
      if(five.length() == n)
      {
         System.out.println(five);
      }else{
         oddDigits(five,n);
      }
   
      String seven = s+"7";
      if(seven.length() == n)
      {
         System.out.println(seven);
      }else{
         oddDigits(seven,n);
      }
   
      String nine = s+"9";
      if(nine.length() == n)
      {
         System.out.println(nine);
      }else{
         oddDigits(nine,n);
      }*/
   
   
      
   }
      
    /**
     * Builds all combinations of a n-digit number whose value is a superprime
     * @param n A positive int representing the desired length of superprimes  
     */
   public static void superprime(int n)
   {
      recur(2, n); //try leading 2, 3, 5, 7, i.e. all the single-digit primes
      recur(3, n); 
      recur(5, n);
      recur(7, n);
      
   }

    /**
     * Recursive helper method for superprime
     * @param k The possible superprime
     * @param n A positive int representing the desired length of superprimes
     */
   static String[] array = {"1","3","5","7","9"};
   private static void recur(int k, int n)
   {
      
      /*if(n == 1)
      {
         System.out.println(""+k);
         return;
      }*/
      String beginNum = ""+k;
      if(beginNum.length() == n)
      {
         System.out.println(beginNum);
         return;
      }
      for(int x = 0; x<array.length; x++)
      {
         String base = beginNum+array[x];
         if(isPrime(Integer.parseInt(base)))
         {
            if(base.length() == n)
            {
               count++;
               System.out.println(base);
            }else{
               recur(Integer.parseInt(base),n);
            }
         }
      } 
            /*if(string.length() < n)
         {
            string = string+array[x];
            recur(Integer.parseInt(string),n);
         }else{
            System.out.println(string);
         }*/
         /*else if(isPrime(Integer.parseInt(string)))
         {
            System.out.println(string);
            return;
         }else{
            string = string.substring(0,string.length()-1);
         }*/
      /*if(check == 0 && string.length()==n && isPrime(Integer.parseInt(string)))
         {
            System.out.println(string);
            check = 1;
         }*/
      
      //int check = 0;
      /*for(int x = 0; x < array.length; x++)
      {
         
      
         if(string.length() == n && isPrime(Integer.parseInt(string)) == true && check == 0)
         {
            System.out.println(string);
            check = 1;
         }else{
            string = string+array[x];
            recur(Integer.parseInt(string),n);
         }
      }*/
   
   }

    /**
     * Determines if the parameter is a prime number.
     * @param n An int.
     * @return true if prime, false otherwise.
     */
   public static boolean isPrime(int n)
   {
   //2-sqrt(n)
      for(int x = 2; x<= Math.sqrt(n); x++)
      {
         if(n%x == 0)
         {
            return false;
         }
      }
      return true;
   }
}
