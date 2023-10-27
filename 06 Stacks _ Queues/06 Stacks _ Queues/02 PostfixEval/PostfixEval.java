// Name:
// Date:

import java.util.*;

public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      /*postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 +");
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *");
      postfixExp.add("3 4 5 + * 2 - 5 /");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("2 3 ^");
      postfixExp.add("20 3 %");
      postfixExp.add("21 3 %");
      postfixExp.add("22 3 %");
      postfixExp.add("23 3 %");
      postfixExp.add("5 !");
      postfixExp.add("1 1 1 1 1 + + + + !");*/
      //postfixExp.add("1.3 2.7 -6 6 * + +");
      postfixExp.add("2 5 - 4 3 + *");
      //postfixExp.add("8 1 2 9 3 / - * +");
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static double eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      Stack<Double> sum = new Stack<Double>();
      /*  enter your code here  */
      for(int x = 0; x < postfixParts.size(); x++)
      {
         String data = postfixParts.get(x);
         if(!isOperator(data))
         {
            sum.push(Double.parseDouble(data));
         }else if(data.compareTo("!") == 0){
            double num = 1;
            double value = sum.pop();
            for(int i = 1; i<=value; i++)
            {
               num = num*i;
            }
            sum.push(num);
         }else{
            sum.push(eval(sum.pop(),sum.pop(),data));
         }
      
      }
      return sum.pop();
   }
   
   public static double eval(double a, double b, String ch)
   {
      double num = b;
      if(ch.compareTo("+") == 0)
      {
         num = num + a;
      }else if(ch.compareTo("-")==0){
         num = num-a;
      }else if(ch.compareTo("*")==0){
         num = num*a;
      }else if(ch.compareTo("/")==0){
         num = num/a;
      }else if(ch.compareTo("%")==0){
         num = num%a;
      }else if(ch.compareTo("^")==0){
         num = Math.pow(num,a);
      }
                  
      return num;
   }
   
   public static boolean isOperator(String op)
   {
      if(operators.indexOf(op) != -1)
      {
         return true;
      }else{
         return false;
      }
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/