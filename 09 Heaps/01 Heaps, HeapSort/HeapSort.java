// Name:
// Date:
import java.text.DecimalFormat;
public class HeapSort
{
   public static int SIZE;  //9 or 100
	
   public static void main(String[] args)
   {
      //Part 1: Given a heap, sort it. Do this part first. 
      SIZE = 9;  
      double heap[] = {-1,30,1,2,16,80,84,85,17,99};
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
      
      //Part 2:  Generate 100 random numbers, make a heap, sort it.
      /*SIZE = 100;
      double[] heap = new double[SIZE + 1];
      //double heap[] = {-1,30,1,2,16,80,84,85,17,99};
      heap = createRandom(heap);
      display(heap);
      makeHeap(heap, SIZE);
      display(heap); 
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));*/
   }
   
   //******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array)
   {
      /* enter your code here */
      /*int half = array.length/2;
      for(int x = half; x > 0; x--)
      {
         heapDown(array,x,array.length);
      }*/
      makeHeap(array,array.length);  
      for(int i = array.length-1; i > 0; i--){
         swap(array,1,i);
         heapDown(array,1,i);
      }
   }
  
   public static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
   
   public static void heapDown(double[] array, int k, int size)
   {
      int largest = k;
      int l = k*2;
      int r = 2*k+1;
      if(l < size && array[l] > array[largest])
      {
         largest = l;
      }
      if(r < size && array[r] > array[largest])
      {
         largest = r;
      }
      if(largest != k)
      {
         swap(array,k,largest);
         heapDown(array,largest,size);
      }
   }
   
   public static boolean isSorted(double[] arr)
   {
      for(int k = 1; k < arr.length-1; k++)
      {
         if(arr[k] > arr[k+1])
         {
            return false;
         }
      }
      return true;
   }
   
   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {  
      DecimalFormat df = new DecimalFormat("#.00");
      array[0] = -1;   //because it will become a heap
      for(int x = 1; x < array.length; x++)
      {
         array[x] = Double.parseDouble(df.format((Math.random()*100)+1));
      }
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int size)
   {
      /*sort(array);  
      for(int i = size; i > 0; i--){
         swap(array,1,i);
         heapDown(array,1,i);
      }*/
      int half = size/2;
      for(int x = half; x > 0; x--)
      {
         heapDown(array,x,size);
      }
   
   }
}

