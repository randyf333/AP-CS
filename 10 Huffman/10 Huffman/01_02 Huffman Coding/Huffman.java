// name:        date: 
import java.util.*;
import java.io.*;
public class Huffman
{
   public static Scanner keyboard = new Scanner(System.in);
   public static void main(String[] args) throws IOException
   {
      //Prompt for two strings 
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat message? ");
      String message = keyboard.nextLine();
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.next();
   
      huffmanize( message, middlePart );
   }
   
   public static void huffmanize(String message, String middlePart) throws IOException
   {
      String binary = "";
      String scheme = "";
      if(message == null || message.length() == 0)
      {
         return;
      }
      if(message.length() == 1)
      {
         binary = "0";
         scheme = message+"0";
      }else{
         PriorityQueue<HuffmanTreeNode> pair = new PriorityQueue<HuffmanTreeNode>(); 
         char[] list = message.toCharArray();
         Map<Character, Integer> table = new HashMap<Character, Integer>(); 
         for(char c : list)
         {
            Integer i = table.get(c);
            table.put(c, (i == null) ? 1 : i+1);
         }
         for(Character x : table.keySet())
         {
            HuffmanTreeNode node = new HuffmanTreeNode(x, table.get(x));
            pair.add(node);
         }
         HuffmanTreeNode root = null;
      //tree building
         while(pair.size() > 1)
         {
            HuffmanTreeNode first = pair.remove();
            HuffmanTreeNode sec = pair.remove();
            HuffmanTreeNode sum = new HuffmanTreeNode("*",(first.getFreq()+sec.getFreq()));
            sum.setLeft(first);
            sum.setRight(sec);
            root = sum;
            pair.add(sum);
         }
         //binary = "";
         for(int x = 0; x < list.length; x++)
         {
            binary += findLetter(list[x],root, "");
         }
         scheme = schemeCode(root, "");
      }
      FileWriter binaryMessage = new FileWriter(new File("message."+middlePart+".txt"));
      binaryMessage.write(binary);
      FileWriter schemeWriter = new FileWriter(new File("scheme."+middlePart+".txt"));
      schemeWriter.write(scheme);
      binaryMessage.close();
      schemeWriter.close();
      System.out.println(binary);
      System.out.println(scheme);
      
               /*FileWriter binaryWrite = new FileWriter(binaryFile);
      FileWriter schemeWrite = new FileWriter(schemeFile);
      binaryWrite.write(binary);
      schemeWrite.write(scheme);*/
                                                 //Make a frequency table of the letters
      	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap).     
      	//Use the priority queue of nodes to build the Huffman tree
      	//Process the string letter-by-letter and search the tree for the 
   		//       letter. It's recursive. As you recur, build the path through the tree, 
   		//       where going left is 0 and going right is 1.
         //System.out.println the binary message 
      	//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
         //System.out.println the scheme from the tree--needs a recursive helper method
      	//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")          
   }
   private static String findLetter(Character input, HuffmanTreeNode root, String s)
   {
      String path = s;
      String temp = path;
      if(root.getLeft() != null || root.getRight() != null)
      {
         if((path = findLetter(input, root.getLeft(), path+="0"))== null)
         {
            path = findLetter(input, root.getRight(), temp+="1");
         }       
      }else{
         path = (input == root.getValue()) ? s : null;
      }
                 
      return path;
   }
   
   private static String schemeCode(HuffmanTreeNode root, String s)
   {
   
      String scheme = "";
      if(root.getLeft() == null && root.getRight() == null)
      {
         scheme = ""+root.getValue() + s;
         return scheme;
      }
      scheme += schemeCode(root.getLeft(), s + "0") + "\n";
      scheme += schemeCode(root.getRight(), s + "1");
      return scheme;
   }
}
	/*
	  * This tree node stores two values.  Look at TreeNode's API for some help.
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private Object value;
   private HuffmanTreeNode left, right;
   private int freq;
   
   public HuffmanTreeNode(Object initValue, int num)
   { 
      value = initValue; 
      left = null; 
      right = null; 
      freq = num;
   }
   public HuffmanTreeNode(Object initValue, int num, HuffmanTreeNode initLeft, HuffmanTreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
      freq = num;
   }
   public Object getValue()
   { 
      return value; 
   }
   
   public HuffmanTreeNode getLeft() 
   { 
      return left; 
   }
   
   public HuffmanTreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(HuffmanTreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(HuffmanTreeNode theNewRight)
   { 
      right = theNewRight;
   }
   public int getFreq()
   {
      return freq;
   }
   public int compareTo(HuffmanTreeNode node)
   {
      if(freq < node.getFreq())
      {
         return -1;
      }else if(freq > node.getFreq())
      {
         return 1;
      }else{
         return 0;
      }
   }
}
