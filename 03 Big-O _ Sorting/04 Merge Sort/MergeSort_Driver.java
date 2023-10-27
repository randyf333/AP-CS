// Name:
// Date:

import java.util.*;
import java.io.*;

public class MergeSort_Driver
{
   public static void main(String[] args) throws Exception
   {
      //Part 1, for doubles
      //double[] array = {3,1,4,1,5,9,2,6};    // small example array from the MergeSort handout
      int n = (int)(Math.random()*50+10);
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random();
         	
      MergeSort.sort(array);
   
      print(array);
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("oops!");
         
      //Part 2, for Comparables
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      MergeSort.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }

   
   public static void print(double[] a)   
   {                             
      for(double number : a)    
         System.out.print(number+" ");
      System.out.println();
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
  
   public static void print(Object[] peach)
   {
      for(Object abc : peach)    
         System.out.print(abc+" ");
   
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
/////////////////////////////////////////////
// 15 Oct 07
// MergeSort Code Handout
// from Lambert & Osborne, p. 482 - 485

class MergeSort
{
   private static final int CUTOFF = 10; // for small lists, recursion isn't worth it
  
   public static void sort(double[] array)
   { 
      double[] copyBuffer = new double[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);
   }
   
   /*  array			array that is being sorted
       copyBuffer		temp space needed during the merge process
       low, high		bounds of subarray
       middle			midpoint of subarray   
   */
   private static void mergeSortHelper(double[] array, double[] copyBuffer,
                                                      int low, int high)
   {  
      if ( high - low  < CUTOFF )                  //switch to selection sort  when
         SelectionLowHigh.sort(array, low, high);        //the list gets small enough 
      else
         if (low < high)
         {
            int middle = (low + high) / 2;
            mergeSortHelper(array, copyBuffer, low, middle);
            mergeSortHelper(array, copyBuffer, middle + 1, high);
            merge(array, copyBuffer, low, middle, high);
         }
   }
   
   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low				beginning of first sorted subarray
      middle			end of first sorted subarray
      middle + 1		beginning of second sorted subarray
      high				end of second sorted subarray   
   */
   public static void merge(double[] array, double[] copyBuffer,
                                   int low, int middle, int high)
   {
      int midplus = middle+1;
      double[] sub1 = new double[middle-low+1];
      double[] sub2 = new double[high - middle];
      for(int x = 0; x < array.length; x++)
      {
         copyBuffer[x] = array[x];
      }
         // Initialize i1 and i2 to the first items in each subarray  
      // Interleave items from the subarrays into the copyBuffer in such 
      // a way that order is maintained.
      for(int x = 0; x<sub1.length; x++)
      {
         sub1[x] = copyBuffer[low+x];
      }
      for(int x = 0; x< sub2.length; x++)
      {
         sub2[x] = copyBuffer[midplus+x];
      }
      int i1 = 0;//first itemsin each subarray
      int i2 = 0;//
      int k = low;
      while(i1 < sub1.length && i2 < sub2.length)
      {
         if(sub1[i1]<=sub2[i2])
         {
            copyBuffer[k] = sub1[i1];
            i1++;
         }else{
            copyBuffer[k] = sub2[i2];
            i2++;
         }
         k++;
      }
      while(i1<sub1.length)
      {
         copyBuffer[k] = sub1[i1];
         i1++;
         k++;
      }
      while(i2<sub2.length)
      {
         copyBuffer[k] = sub2[i2];
         i2++;
         k++;
      }
      /*for(int x = 0; x < array.length; x+=2)
      {
         double i1 = array[low];
         double i2 = array[midplus];
         if(i1<i2)
         {
            copyBuffer[x] = i1;
            copyBuffer[x+1] = i2;
         }else{
            copyBuffer[x] = i2;
            copyBuffer[x+1] = i1;
         }
         low+=2;
         midplus+=2;
      }*/
          
      
      //then copy the just-sorted values (from low to high)
      // from the copyBuffer back to the array.
      for(int x = 0; x < array.length; x++)
      {
         array[x] = copyBuffer[x];
      }
   
      
   }	
    
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static void sort(Comparable[] array)
   { 
      Comparable[] copyBuffer = new Comparable[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);
   
   }

   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low, high		bounds of subarray
      middle			midpoint of subarray  */
      
   @SuppressWarnings("unchecked")
   private static void mergeSortHelper(Comparable[] array, Comparable[] copyBuffer, int low, int high)
   {
     // if ( high - low  < CUTOFF )                  //switch to selection sort  when
         // SelectionLowHigh.sort(array, low, high);        //the list gets small enough 
      // else
      if (low < high)
      {
         int middle = (low + high) / 2;
         mergeSortHelper(array, copyBuffer, low, middle);
         mergeSortHelper(array, copyBuffer, middle + 1, high);
         merge(array, copyBuffer, low, middle, high);
      }
   
   }
   
   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low				beginning of first sorted subarray
      middle			end of first sorted subarray
      middle + 1		beginning of second sorted subarray
      high				end of second sorted subarray   */
      
   @SuppressWarnings("unchecked")
   public static void merge(Comparable[] array, Comparable[] copyBuffer,
                                   int low, int middle, int high)
   {
      int midplus = middle+1;
      Comparable[] sub1 = new Comparable[middle-low+1];
      Comparable[] sub2 = new Comparable[high - middle];
      for(int x = 0; x < array.length; x++)
      {
         copyBuffer[x] = array[x];
      }
         // Initialize i1 and i2 to the first items in each subarray  
      // Interleave items from the subarrays into the copyBuffer in such 
      // a way that order is maintained.
      for(int x = 0; x<sub1.length; x++)
      {
         sub1[x] = copyBuffer[low+x];
      }
      for(int x = 0; x< sub2.length; x++)
      {
         sub2[x] = copyBuffer[midplus+x];
      }
      int i1 = 0;//first itemsin each subarray
      int i2 = 0;//
      int k = low;
      while(i1 < sub1.length && i2 < sub2.length)
      {
         if(sub1[i1].compareTo(sub2[i2]) <= 0)
         {
            copyBuffer[k] = sub1[i1];
            i1++;
         }else{
            copyBuffer[k] = sub2[i2];
            i2++;
         }
         k++;
      }
      while(i1<sub1.length)
      {
         copyBuffer[k] = sub1[i1];
         i1++;
         k++;
      }
      while(i2<sub2.length)
      {
         copyBuffer[k] = sub2[i2];
         i2++;
         k++;
      }
      for(int x = 0; x < array.length; x++)
      {
         array[x] = copyBuffer[x];
      }
   
   }    	
}

/***************************************************
This is the extension.  You will have to uncomment Lines 89-90 above.
***************************************************/

class SelectionLowHigh
{
   public static void sort(double[] array, int low, int high)
   {  
      for(int x = array.length-1; x > 0; x--)
         swap(array,findMax(array,0,x),x);
   }
   private static int findMax(double[] array, int low, int upper)
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
}
