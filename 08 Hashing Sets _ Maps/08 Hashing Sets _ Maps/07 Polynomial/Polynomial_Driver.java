 // Name:    
 // Date: 

import java.util.*;

public class Polynomial_Driver
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();    // 2x^3 + -4x + 2
      poly.makeTerm(1, -4);
      poly.makeTerm(3, 2);
      poly.makeTerm(0, 2);
      System.out.println("Map:  " + poly.getMap());
      System.out.println("String:  " + poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
      
      System.out.println("-----------");
      Polynomial poly2 = new Polynomial();  // 2x^4 + x^2 + -5x + -3
      poly2.makeTerm(1, -5);
      poly2.makeTerm(4, 2);
      poly2.makeTerm(0, -3);
      poly2.makeTerm(2, 1);
      System.out.println("Map:  " + poly2.getMap()); 
      System.out.println("String:  " +poly2.toString());
      evaluateAt = -10.5;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly2.evaluateAt(evaluateAt));
      
      
      System.out.println("-----------");
      System.out.println("Sum: " + poly.add(poly2));
      System.out.println("Product:  " + poly.multiply(poly2));
      
      /*  Another case:   (x+1)(x-1) -->  x^2 + -1    */
      // System.out.println("===========================");
      // Polynomial poly3 = new Polynomial();   // (x+1)
      // poly3.makeTerm(1, 1);
      // poly3.makeTerm(0, 1);
      // System.out.println("Map:  " + poly3.getMap());
      // System.out.println("String:  " + poly3.toString());
   //       
      // Polynomial poly4 = new Polynomial();    // (x-1)
      // poly4.makeTerm(1, 1);
      // poly4.makeTerm(0, -1);
      // System.out.println("Map:  " + poly4.getMap());
      // System.out.println("String:  " + poly4.toString());
      // System.out.println("Product:  " + poly4.multiply(poly3));   // x^2 + -1 
   //    
   //    /*  testing the one-arg constructor  */
      // System.out.println("==========================="); 
      // Polynomial poly5 = new Polynomial("2x^3 + 4x^2 + 6x^1 + -3");
      // System.out.println("Map:  " + poly5.getMap());  
      // System.out.println(poly5);
   
   }
}
interface PolynomialInterface
{
   public void makeTerm(Integer exp, Integer coef);
   public Map<Integer, Integer> getMap();
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface
{
   private Map<Integer, Integer> poly;
   public Polynomial()
   {
      poly = new TreeMap<Integer, Integer>();
   }
   public void makeTerm(Integer exp, Integer coef){
      poly.put(exp,coef);
   }
   public Map<Integer, Integer> getMap(){
      return poly;
   }
   public double evaluateAt(double x){
      double sum = 0;
      for(int i : poly.keySet())
      {
         sum += Math.pow(x,i)*poly.get(i);
      }
      return sum;
   }
   public Polynomial add(Polynomial other){
      Polynomial sum = new Polynomial();
      Map<Integer, Integer> add= other.getMap();
      for(int a : poly.keySet())          
      {
         for(int b : add.keySet())
         {
            if(a == b)
            {
               sum.makeTerm(a, poly.get(a)+add.get(b));
            }
         }
      }
      for(int a : poly.keySet())
      {
         if(!sum.getMap().keySet().contains(a))
         {
            sum.makeTerm(a,poly.get(a));
         }
      }
      for(int b : add.keySet())
      {
         if(!sum.getMap().keySet().contains(b))
         {
            sum.makeTerm(b,add.get(b));
         }
      }
      return sum;
   }
   public Polynomial multiply(Polynomial other){
      Polynomial product = new Polynomial();
      Map<Integer, Integer> mult = other.getMap();
      for(int x : poly.keySet())
      {
         for(int y : mult.keySet())
         {
            if(product.getMap().keySet().contains(x+y))
            {
               product.getMap().put(x+y,product.getMap().get(x+y)+poly.get(x)*mult.get(y));
            }else{
               product.makeTerm(x+y, poly.get(x)*mult.get(y));
            }
         }
      }
      return product;
   }
   public String toString(){
      String s = "";
      Stack<String> expression = new Stack<String>();
      for(int i : poly.keySet())
      {
         if(i == 0)
         {
            expression.push(""+poly.get(i));
         }else{
            expression.push(""+((poly.get(i) == 1) ? "" : poly.get(i)) + "x" + ((i > 1) ? "^" + i : ""));
         }
      }
      int size = expression.size()-1;
      for(int x = 0; x < size; x++)
         s += expression.pop() + " + ";
      s += expression.pop();
      return s;
   }
}


/***************************************  
  ----jGRASP exec: java Polynomial_teacher
 Map:  {0=2, 1=-4, 3=2}
 String:  2x^3 + -4x + 2
 Evaluated at 2.0: 10.0
 -----------
 Map:  {0=-3, 1=-5, 2=1, 4=2}
 String:  2x^4 + x^2 + -5x + -3
 Evaluated at -10.5: 24469.875
 -----------
 Sum: 2x^4 + 2x^3 + x^2 + -9x + -1
 Product:  4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 
  ----jGRASP: operation complete.
 ********************************************/