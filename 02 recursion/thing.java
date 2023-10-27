public class thing
{
   public static void main(String[] args)
   {
     // int x = digitMatch(380,0);
      //System.out.println(x);
      //System.out.println(digitMatchtwo(380,0));
      System.out.print(fooA(2));
   }
   public static String fooA (int x){
      String s = ""+x;
      if(x == 1)
      {
      
         return "1";
      }else{
         return fooA(x-1)+" "+s+" "+fooA(x-1);
       
      }
   
   }      /*static int count = 0;
   public static int digitMatch(int x, int y) {
      if(x < 0 || y < 0)
      {
         throw new IllegalArgumentException();
      }
      String xs = ""+x;
      String ys = ""+y;
      if(x == y)
      {
         count = xs.length();
         return count;
      }
      if(xs == ""|| ys=="")
      {
         return count;
      }
      if(xs.length()==1||ys.length()==1)
      {
         if(xs.charAt(xs.length()-1)==ys.charAt(ys.length()-1))
         {
            count++;
            return count;   
         }else{
            return 0;   
         }
      }
      if(xs.charAt(xs.length()-1)==ys.charAt(ys.length()-1))
      {
         count++;
         digitMatch(Integer.parseInt(xs.substring(0,xs.length()-1)),Integer.parseInt(ys.substring(0,ys.length()-1)));
         
      }else{
         digitMatch(Integer.parseInt(xs.substring(0,xs.length()-1)),Integer.parseInt(ys.substring(0,ys.length()-1)));
      }
   
      return count;
   }
   public static int digitMatch(int x, int y) {
    
      if(x < 0 || y < 0)
       {
        throw new IllegalArgumentException();
       }
    String xs = String.valueOf(x);
    String ys = String.valueOf(y);
    int result;
    
      if(xs.charAt(xs.length()-1)==ys.charAt(ys.length()-1))
       {
        result = 1;
       
      }else{
        result = 0;
       }
    
      if(xs.length() == 1 || ys.length() == 1)
       {
        
         return result;
       
      }else{
        
         return result+digitMatch(Integer.parseInt(xs.substring(0,xs.length()-1)),Integer.parseInt(ys.substring(0,ys.length()-1)));
       }
   }*/
   /*public static void recursive_display(int[][]array,int n)
   {
      if(n == array.length)
      {
      System.out.println();
      }
   }*/
}