// Name:  Randy Fu
// Date:  9/8/2020

public class Modes
{
   public static void main(String[] args)
   {
      int[] tally = {0,0,10,5,10,0,7,1,0,6,0,10,3,0,0,1};
      display(tally);
      int[] modes = calculateModes(tally);
      display(modes);
      int sum = 0;
      for(int k = 0; k < tally.length; k++)
         sum += tally[k];
      System.out.println("kth \tindex"); 
      for(int k = 0; k < sum+1; k++)
         System.out.println(k + "\t\t" + kthDataValue(tally, k));
     System.out.println(kthDataValue(tally,1));
     System.out.println(kthDataValue(tally,14));
     System.out.println(kthDataValue(tally,15));
     System.out.println(kthDataValue(tally,16));
   }
     
  /**
   * precondition: tally.length > 0
   * postcondition: returns an int array that contains the modes(s);
   *                the array's length equals the number of modes.
   */
   public static int[] calculateModes(int[] tally)
   {
      // Your code goes here.
      int x = findMax(tally);
      int num = 0;
      int z = 0;
      int check = 0;
      int[] empty = {};
      for(int y = 0; y < tally.length; y++)
      {
      
         if(tally[y] == x)
         {
            num++ ;          
                             //return 
         }
         
      }
      int[] value = new int[num];
      for(int y = 0; y < tally.length; y++)
      {
         
         if(tally[y] == x)
         {
            value[z] = y; 
            z++;
         }
      }
      for(int k = 0; k < tally.length; k++)
      {
         if(tally[0] != tally[k])
         {
            check = 1;
         }
      }
      if(check == 0)
      {
         for(int k = 0; k < tally.length; k++)
         {
            value = empty;
         }
      }
      return value;
   }
     
  /**  
   * precondition:  tally.length > 0
   * 	             0 < k <= total number of values in data collection
   * postcondition: returns the kth value in the data collection
   *                represented by tally
   */
   public static int kthDataValue(int[] tally, int k)
   {
      // Your code goes here.
      int x = 0;
      int in = 1;
      for(int y = 0; y < tally.length; y++)
      {
         x = x+tally[y];
      }
      int[] number = new int[x+1];
      for(int z = 0; z < tally.length; z++)
      {
         if(tally[z] > 0)
         {
            for(int pos = 0; pos < tally[z]; pos++)
            {
               number[in] = z;
               in++;
            }
            
         }
      }
      return number[k];
   }
   
     
  /**
   * precondition:  nums.length > 0
   * postcondition: returns the maximal value in nums
   */
   public static int findMax(int[] nums)
   {
      int pos = 0;
      for(int k = 1; k < nums.length; k++)
         if(nums[k] > nums[pos])
            pos = k;
      return nums[pos];
   }
   
   public static void display(int[] args)
   {
      for(int k = 0; k < args.length; k++)
         System.out.print(args[k] + " ");
      System.out.println();
      System.out.println();
   }
}