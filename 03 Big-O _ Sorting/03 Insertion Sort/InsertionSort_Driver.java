 //Name:     
 //Date:
 //big-O of n^2
import java.util.*;
import java.io.*;

public class InsertionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
      //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      Insertion.sort(array);
      print(array);
      
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
      System.out.println();
      
      //Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      Insertion.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
   
   public static void print(double[] a)
   {
      for(double d: a)         // for-each loop
         System.out.print(d+" ");
      System.out.println();
   }
   
   public static void print(Object[] papaya)
   {
      for(Object abc : papaya)    
         System.out.print(abc+" ");
   }
   
   public static boolean isAscending(double[] a)
   {
      if(a.length == 0 || a.length == 1)
         return true;
      for(int x = 1; x<a.length; x++)
      {
         if(a[x-1]>a[x])
            return false;
      }
      return true;
   
   }
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static boolean isAscending(Comparable[] a)
   {
      if(a.length == 0 || a.length == 1)
         return true;
      for(int x = 1; x<a.length; x++)
      {
         if(a[x-1].compareTo(a[x])>0)
            return false;
      }
      return true;
   
   }
}

//**********************************************************

class Insertion
{
   public static void sort(double[] array)
   {        
      for(int x = 1; x < array.length; x++)
      {
         int index = shift(array, x, array[x]);
         double temp = array[index];
         for(int y = index; y < x; y++)
         {
            double temp2 = array[y+1];
            array[y+1] = temp;
            temp = temp2;
         }
       //every number before the x moves right 1.
         
         array[index] = temp;
      }
   }
 
   private static int shift(double[] array, int index, double value)
   {
      int position = 0;
      if(array[index-1] > value)
      {
         while(index-1>=0 && array[index-1] > value)
         {
            index--;
            position = index;
         }
         return position;
      }else{
         return index;
      }
   }
 
   @SuppressWarnings("unchecked")
   public static void sort(Comparable[] array)
   { 
      for(int x = 1; x < array.length; x++)
      {
         int index = shift(array, x, array[x]);
         Comparable temp = array[index];
         for(int y = index; y < x; y++)
         {
            Comparable temp2 = array[y+1];
            array[y+1] = temp;
            temp = temp2;
         }
       //every number before the x moves right 1.
         
         array[index] = temp;
      }
   
   }
   
   @SuppressWarnings("unchecked")
   private static int shift(Comparable[] array, int index, Comparable value)
   {
      int position = 0;
      if(array[index-1].compareTo(value) > 0)
      {
         while(index-1>=0 && array[index-1].compareTo(value) > 0)
         {
            index--;
            position = index;
         }
         return position;
      }else{
         return index;
      }
   
   }
}
