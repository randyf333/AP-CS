// Name:
// Date:

import java.util.*;

public class TreeLab
{
   public static TreeNode root = null;
   public static String s = "XCOMPUTERSCIENCE";
   //public static String s = "XThomasJeffersonHighSchool"; 
   //public static String s = "XAComputerScienceTreeHasItsRootAtTheTop";
   //public static String s = "XA";   //comment out lines 44-46 below
   //public static String s = "XAF";  //comment out lines 44-46 below
   //public static String s = "XAFP";  //comment out lines 44-46 below
   //public static String s = "XDFZM";  //comment out lines 44-46 below 
   
   public static void main(String[] args)
   {
      root = buildTree( root, s );
      System.out.print( display( root, 0) );
   
      System.out.print("\nPreorder: " + preorderTraverse(root));
      System.out.print("\nInorder: " + inorderTraverse(root));
      System.out.print("\nPostorder: " + postorderTraverse(root));
   
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Only children = " + countOnlys(root));
      System.out.println("Grandparents = " + countGrandParents(root));
   
      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Longest path = " + longestPath(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
   
      System.out.println("\nBy Level: ");
      System.out.println(displayLevelOrder(root));
   }
 
   public static TreeNode buildTree(TreeNode root, String s)
   {
      root = new TreeNode("" + s.charAt(1), null, null);
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, 
            (int)(1 + Math.log(pos) / Math.log(2)));
      insert(root, "A", 17, 5); 
      insert(root, "B", 18, 5); 
      insert(root, "C", 37, 6); //B's right child
      return root;
   }

   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
      {
         if((pos & (1 << k)) == 0)
            p = p.getLeft();
         else
            p = p.getRight();
      }
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   }
   
   private static String display(TreeNode t, int level)
   {
      // turn your head towards left shoulder visualize tree
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
   
   public static String preorderTraverse(TreeNode t)
   { 
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public static String inorderTraverse(TreeNode t)
   {
      String toReturn = ""; 
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());	//recurse left
      toReturn += t.getValue() + " ";      		//inorder visit
      toReturn += inorderTraverse(t.getRight());//recurse right
      return toReturn;
   }
   
   public static String postorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += postorderTraverse(t.getLeft());
      toReturn += postorderTraverse(t.getRight());
      toReturn += t.getValue() + " ";
      return toReturn;
   }
   
   public static int countNodes(TreeNode t)
   {
      /*int count = 0;
      Queue<TreeNode> q = new LinkedList<TreeNode>();
      String toReturn = "";
      if(t == null)
      {
         return 0;
      }
      q.add(t);
      while(!q.isEmpty())
      {
         TreeNode node = q.remove();
         count++;
         if(node.getLeft() != null)
         {
            q.add(node.getLeft());
         }
         if(node.getRight() != null)
         {
            q.add(node.getRight());
         }
      }
      return count;*/
      int count = 1;
      if(t == null)
         return 0;
      return count+countNodes(t.getLeft())+countNodes(t.getRight());       
   }
   
   public static int countLeaves(TreeNode t)
   {
      if(t == null)
      {
         return 0;
      }
      if(t.getLeft() == null && t.getRight() == null)
      {
         return 1;
      }
      return countLeaves(t.getLeft())+countLeaves(t.getRight());
   }
   
   /*  there are clever ways and hard ways to count grandparents  */ 
   public static int countGrandParents(TreeNode t)//See if height of each tree is greater then 2-smart way
   {
      if(t == null)
      {
         return 0;
      }
      int count = 0;
      if(t.getLeft() != null && t.getRight() != null)
      {
         TreeNode lNode = t.getLeft();
         TreeNode rNode = t.getRight();
         if(lNode.getLeft() != null || lNode.getRight() != null || lNode.getRight() != null || rNode.getLeft() != null)
         {
            count++;
         }
      }else if(t.getLeft() != null)
      {
         TreeNode node = t.getLeft();
         if(node.getLeft() != null || node.getRight() != null)
         {
            count++;
         }
      }else if(t.getRight() != null)
      {
         TreeNode node = t.getRight();
         if(node.getLeft() != null || node.getRight() != null)
         {
            count++;
         }
      }
      count += countGrandParents(t.getLeft()) + countGrandParents(t.getRight());
      return count;
         
   }
   
   public static int countOnlys(TreeNode t)
   {
      if(t == null)
      {
         return 0;
      }
      int count = 0;
      if(t.getLeft() != null && t.getRight() == null)
      {
         countOnlys(t.getLeft());
         count++;
      }
      if(t.getLeft() == null && t.getRight() != null)
      {
         countOnlys(t.getRight());
         count++;
      }
      count += countOnlys(t.getLeft())+countOnlys(t.getRight());
      return count;
   }
   
  /* returns the max of the heights to the left and the heights to the right  
     returns -1 in case the tree is null
    */
   public static int height(TreeNode t)
   {
      if(t == null){
         return -1;
      }
      int lHeight = height(t.getLeft());
      int rHeight = height(t.getRight());
      if(lHeight > rHeight)
      {
         return lHeight+1;
      }else{
         return rHeight+1;
      }   
   }
   
 /* return the max of the sum of the heights to the left and the heights to the right  
 */
   public static int longestPath(TreeNode t)
   {
      if(t == null)
      {
         return 0;
      }
      int b = (height(t.getLeft())+1)+(height(t.getRight())+1);
      int rLength = longestPath(t.getRight());
      int lLength = longestPath(t.getLeft());
      return Math.max(b, Math.max(rLength,lLength));
      
   }
      /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object min(TreeNode t)
   {
      if(t == null)
      {
         return null;
      }
      String cur = (String)t.getValue();
      String left = (String)min(t.getLeft());
      String right = (String)min(t.getRight());
      if(left == null &&  right == null)
         return cur;
      if(left != null && left.compareTo(cur) < 0)
      {
         cur = left;
      }
      if(right != null && right.compareTo(cur) < 0)
      {
         cur = right;
      }
      return cur;
   }
   
   /*  Object must be cast to Comparable in order to call .compareTo  
    */
   @SuppressWarnings("unchecked")
   public static Object max(TreeNode t)
   {
      if(t == null)
      {
         return null;
      }
      String cur = (String)t.getValue();
      String left = (String)max(t.getLeft());
      String right = (String)max(t.getRight());
      if(left == null && right == null)
         return cur;
      if(left != null && left.compareTo(cur) > 0)
      {
         cur = left;
      }
      if(right != null && right.compareTo(cur) > 0)
      {
         cur = right;
      }
      return cur;
   
   }
      
   /* This method is not recursive.  Use a local queue
    * to store the children of the current TreeNode.
    */
   public static String displayLevelOrder(TreeNode t)
   {
      Queue<TreeNode> q = new LinkedList<TreeNode>();
      String toReturn = "";
      if(t == null)
      {
         return "";
      }
      q.add(t);
      while(!q.isEmpty())
      {
         TreeNode node = q.remove();
         toReturn+=node.getValue();
         if(node.getLeft() != null)
         {
            q.add(node.getLeft());
         }
         if(node.getRight() != null)
         {
            q.add(node.getRight());
         }
      }
      return toReturn;
   }
}

/***************************************************
    ----jGRASP exec: java TreeLab
 		  	E
 		E
 			C
 	M
 			N
 		T
 			E
 C
 			I
 		U
 			C
 	O
 			S
 					C
 				B
 		P
 				A
 			R
 
 Preorder: C O P R A S B C U C I M T E N E C E 
 Inorder: R A P B C S O C U I C E T N M C E E 
 Postorder: A R C B S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Only children = 3
 Grandparents = 5
 
 Height of tree = 5
 Longest path = 8
 Min = A
 Max = U
 
 By Level: 
 COMPUTERSCIENCEABC    
 *******************************************************/
 //Code to find path of a value from root
/*String toReturn = "";

   if(root == null)

     return "";

   if(root.getValue().toString().equals(n.getValue().toString()))

     return root.getValue().toString(); 

   else{  

     if(!path(root.getLeft(), n).equals(""))

     

      return root.getValue().toString()+ " "+path(root.getLeft(), n);

    

     if(!path(root.getRight(), n).equals(""))

     

      return root.getValue().toString()+ " "+path( root.getRight(), n);

   }  

   return toReturn;*/