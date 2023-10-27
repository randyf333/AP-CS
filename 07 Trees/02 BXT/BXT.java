// Name: 
// Date:  
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT
{
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
    
   public void buildTree(String str)
   {
      Stack<TreeNode> s = new Stack<TreeNode>();
      String[] expression = str.split(" ");
      if(expression.length == 1)
      {
         root = new TreeNode(expression[0]);
      }else{
         for(String item : expression)
         {
            if(!isOperator(item))
            {
               s.push(new TreeNode(item));
            }else{
               root = new TreeNode(item);
               root.setRight(s.pop());
               root.setLeft(s.pop());
               s.push(root);
            }
         }
      }
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      if(t.getLeft() == null && t.getRight() == null)
      {
         return Double.parseDouble((String)t.getValue());
      }
      return computeTerm((String)t.getValue(),evaluateNode(t.getLeft()),evaluateNode(t.getRight()));
   }
   
   private double computeTerm(String s, double a, double b)
   {
      double num = a;
      if(s.compareTo("+") == 0)
      {
         num = num + b;
      }else if(s.compareTo("-")==0){
         num = num-b;
      }else if(s.compareTo("*")==0){
         num = num*b;
      }else if(s.compareTo("/")==0){
         num = num/b;
      }else if(s.compareTo("%")==0){
         num = num%b;
      }else if(s.compareTo("^")==0){
         num = Math.pow(num,b);
      }          
      return num;
   }
   
   private boolean isOperator(String s)
   {
      String operators = "+ - * / % ^ !";
      if(operators.indexOf(s) != -1)
      {
         return true;
      }else{
         return false;
      }
   
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      String toReturn = ""; 
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());	//recurse left
      toReturn += t.getValue() + " ";      		//inorder visit
      toReturn += inorderTraverse(t.getRight());//recurse right
      return toReturn;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      if(root == null)
         return "";
      toReturn += root.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(root.getLeft());   //recurse left
      toReturn += preorderTraverse(root.getRight());  
      return toReturn;    
   }
   
  /* extension */
   public String inorderTraverseWithParentheses()
   {
      return inorderTraverseWithParentheses(root);
   }
//    
   private String inorderTraverseWithParentheses(TreeNode t)
   {
      String toReturn = ""; 
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());	//recurse left
      toReturn += t.getValue() + " ";      		//inorder visit
      toReturn += inorderTraverse(t.getRight());//recurse right
      return toReturn;
   
   }
}