// Name:
// Date:
//uses PostfixEval

import java.util.*;
public class InfixPostfixEval
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /*build your list of Infix expressions here  */
      ArrayList<String> infixExp = new ArrayList<String>();
      /*infixExp.add("3 + 4 * 5");  
      infixExp.add("3 * ( 4 + 5 )");
      infixExp.add("1.3 + 2.7 + -6 * 6");  
      infixExp.add("( 33 + -43 ) * ( -55 + 65 )");
      infixExp.add("3 * 4 + 5 / 2 - 5");
      infixExp.add("8 + 1 * 2 - 9 / 3");
      infixExp.add("3 * ( 4 * 5 + 6 )");
      infixExp.add("3 + ( 4 - 5 - 6 * 2 )");
      infixExp.add("8 + 1 * 2 - 9 / 3");
     // System.out.println(
      infixExp.add("2 + 7 % 3");
      infixExp.add("( 2 + 7 ) % 3");
      //infixExp.add("2 + 3 - 4");
      //System.out.println(LEFT.indexOf("5"));*/
      infixExp.add("1 - ( 2 / 3 ) + 4"); 
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get the conversion to work first
         //System.out.println(infix + "\t\t\t" + pf );  
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
                  /* enter your code here  */
      Stack<String> list = new Stack<String>();
      String result = "";
      for(int x = 0; x < nums.size(); x++)
      {
         String character = nums.get(x);
         /*if(LEFT.indexOf(character) == -1 && operators.indexOf(character) == -1 && RIGHT.indexOf(character) == -1)
         {
            result = result + character + " ";
         }else{
            if(RIGHT.indexOf(character) != -1){
               while(!list.isEmpty() && list.peek() != "(" && check != 0)
               {
                  result = result + list.pop() + " ";
                  list.pop();
                  check = 0;
               }
            }else if(LEFT.indexOf(character) != -1)
            {
               list.push(character);
            }else if(!list.isEmpty())
            {
               if(!isLower(list.peek().charAt(0), character.charAt(0)))
               {
                  list.push(character);
               }else{
                  result = result + list.pop() + " ";   
                  list.push(character);
               }
            }else{                 
               list.push(character);
            }
         }*/
         if(LEFT.indexOf(character) == -1 && operators.indexOf(character) == -1 && RIGHT.indexOf(character) == -1)
         {
            result = result + character + " ";
         }else if(LEFT.indexOf(character) != -1)
         {
            list.push(character);
         }else if(RIGHT.indexOf(character) != -1){
            while(!list.isEmpty() && LEFT.indexOf(list.peek()) < 0)
            {
               String string = list.peek();
               result = result + list.pop() + " ";
               
            }
            list.pop();
         }else{
            if(!list.isEmpty() && isLower(character.charAt(0),list.peek().charAt(0)))
            {
               while(!list.isEmpty() && isLower(character.charAt(0),list.peek().charAt(0)))
               {
                  result = result + list.pop() + " ";
               }
               list.push(character);
            }else{
               list.push(character);
            }
            
         }
      }
      while(!list.isEmpty())
      {
         if(LEFT.indexOf(list.peek()) >=0)
         {
            list.pop();
         }else{
            result = result + list.pop() + " ";
         }
      }
      return result.trim();
   }
   
   //returns true if c1 has strictly lower precedence than c2
   public static boolean isLower(char c1, char c2)
   {
      /*if(operators.indexOf(c1) >= 0 && operators.indexOf(c2)>= 0)
      {
         if(c1 > c2 || c1 == c2)
         {
            return true;//switch if codepost gives error
         }else{
            return false;
         }
      }
      return false;*/
      if(c1 == '+' && c2 == '*')
      {
         return true;
      }else if(c1 == '+' && c2 == '/')
      {
         return true;
      }else if(c1 == '-' && c2 == '*')
      {
         return true;
      }else if(c1 == '-' && c2 == '/')
      {
         return true;
      }else if(c1 == c2)
      {
         return false;
      }else if(c1 == '+' && c2 == '%'){
         return true;
      }else if(c1 == '-' && c2 == '%'){
         return true;
      }else if(c1 == '-' && c2 == '+'){
         return true;
      }else if(c1 == '+' && c2 == '-'){
         return true;
      }else if(c1 == '*' && c2 == '/'){
         return true;
      }else if(c1 == '/' && c2 == '*'){
         return true;
      }else{
         return false;
      }
      /*else if(c1 == '*' && c2 == '+')
      {
         return false;
      }else if(c1 == '*' && c2 == '-')
      {
         return false;
      }else if(c1 == '/' && c2 == '+')
      {
         return false;
      }else if(c1 == '/' && c2 == '-')
      {
         return false;
      }else if(c1 == '%' && c2 == '+'){
         return false;
      }else if(c1 == '%' && c2 == '-'){
         return false;
      }*/
   }
}

/********************************************

 Infix  	-->	Postfix		-->	Evaluate
 3 + 4 * 5			3 4 5 * + 			23.0
 3 * (4 + 5)			3 4 5 + * 			27.0
 1.3 + 2.7 + -6 * 6			1.3 2.7 + -6 6 * + 			-32.0
 ( 33 + -43 ) * ( -55 + 65 )			33 -43 + -55 65 + * 			-100.0
 3 * 4 + 5 / 2 - 5			3 4 5 2 5 - / + * 			6.999999999999999
 8 + 1 * 2 - 9 / 3			8 1 2 9 3 / - * + 			7.0
 3 * ( 4 * 5 + 6 )			3 4 5 6 + * * 			132.0
 3 + ( 4 - 5 - 6 * 2 )			3 4 5 - 6 2 * - + 			-10.0
 2 + 7 % 3			2 7 3 % + 			3.0
 ( 2 + 7 ) % 3			2 7 + 3 % 			0.0
     
***********************************************/
