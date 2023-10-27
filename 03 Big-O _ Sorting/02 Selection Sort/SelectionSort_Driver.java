// Name:
// Date:
//Selection sort
//selection sort has same forloop structure as #4 on warmup.
//bigO of n^2
import java.util.*;
import java.io.*;

public class SelectionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
     //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      //int n = 6;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      //for(int x = 0; x < array.length; x++)
         //array[x] = Math.random()*10;
      Selection.sort(array);
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
   
      Selection.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
  
   public static void print(double[] a)
   {
      for(double d: a)         //for-each
         System.out.print(d+" ");
      System.out.println();
   }
  
   public static void print(Object[] papaya)
   {
      for(Object abc : papaya)     //for-each
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
   
   @SuppressWarnings("unchecked")
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
/////////////////////////////////////////////////////

class Selection
{
   public static void sort(double[] array)
   {
      for(int x = array.length-1; x > 0; x--)
         swap(array,findMax(array,x),x);
      
   }        
   
   
   // upper controls where the inner loop of the Selection Sort ends
   private static int findMax(double[] array, int upper)
   {
      int index = 0;
      for(int x = 1; x < upper+1; x++)
      {
         if(array[x]>array[index])
         {
            index = x;
         }
      }
      return index;
   }
   private static void swap(double[] array, int a, int b)
   {
      double temp = 0;
      temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }   	
   
	/*******  for Comparables ********************/
   @SuppressWarnings("unchecked")
   public static void sort(Comparable[] array)
   {
      for(int x = array.length-1; x > 0; x--)
         swap(array,findMax(array,x),x);
   
   }
   
   @SuppressWarnings("unchecked")
   public static int findMax(Comparable[] array, int upper)
   {
      int index = 0;
      for(int x = 1; x < upper+1; x++)
      {
         if(array[x].compareTo(array[index])>0)
         {
            index = x;
         }
      }
      return index;
   }
   public static void swap(Object[] array, int a, int b)
   {
      Object temp = 0;
      temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   
   }
}

