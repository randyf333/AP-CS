// Name:              Date:
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode tree = new TreeNode("");
      while(huffLines.hasNextLine())
      {
         TreeNode way = tree;
         String line = huffLines.nextLine();
         String letter = line.substring(0,1);
         char[] path = line.substring(1).toCharArray();
         TreeNode node = new TreeNode("");
         for(int x = 0; x < path.length; x++)
         {
            int check = -1;
            if(path[x] == '0')
            {
               node = way.getLeft();
               check = 0;
            }else if(path[x] == '1'){
               node = way.getRight();
               check = 1;
            }
            if(check == 0)
            {
               if(node == null)
                  way.setLeft(new TreeNode(""));
               way = way.getLeft();
            }else{
               if(node == null)
                  way.setRight(new TreeNode(""));
               way = way.getRight();
            }
         }
         way.setValue(letter);
      }
      return tree;
   }
            
   public static String dehuff(String text, TreeNode root)
   {
      String message = "";
      //int count = 0;
      TreeNode current = root;
      char[] list = text.toCharArray();
      //while(count < text.length())
      for(int x = 0; x < list.length; x++)
      {
         //String line = text.substring(count,count+1);
         if(list[x]/*line*/ == '1')//line.compareTo("1") == 0)
         {
            current = current.getRight();
            //count++;
         }else{
            current = current.getLeft();
            //count++;
         }
         if(current.getLeft() == null && current.getRight() == null)
         {
            message += (String)current.getValue();
            current = root;
         }
      
      }
      return message;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
