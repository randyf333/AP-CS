// Name:
// Date:

import java.util.*;
import java.io.*;

public class Josephus
{
   private static String WINNER = "Josephus";
   
   public static void main(String[] args) throws FileNotFoundException
   {
      ListNode p = new ListNode("A", null);
      p.setNext(p);
      p = insert(p, "B");
      p = insert(p, "C");
      p = insert(p, "D");
      print(p);
        
      /* run numberCircle first with J_numbers.txt  */
      Scanner sc = new Scanner(System.in);
      System.out.print("How many names (2-20)? ");
      int n = Integer.parseInt(sc.next());
      System.out.print("How many names to count off each time? ");
      int countOff = Integer.parseInt(sc.next());
      ListNode winningPos = numberCircle(n, countOff, "J_numbers.txt");
      System.out.println(winningPos.getValue() + " wins!");  
     
      /* run josephusCircle next with J_names.txt  */
      System.out.println("\n ****  Now start all over. **** \n");
      System.out.print("Where should "+WINNER+" stand?  ");
      int winPos = Integer.parseInt(sc.next());        
      ListNode theWinner = josephusCircle(n, countOff, "J_names.txt", winPos);
      System.out.println(theWinner.getValue() + " wins!");
   }
   
   public static ListNode numberCircle(int n, int countOff, String filename) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      p = countingOff(p, countOff, n);
      return p;
   }
   
   public static ListNode josephusCircle(int n, int countOff, String filename, int winPos) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      replaceAt(p, WINNER, winPos);
      p = countingOff(p, countOff, n);
      return p;
   }

   /* reads the names, calls insert(), builds the linked list.
	 */
   public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
   {
      Scanner infile = new Scanner(f);
      ListNode node = new ListNode(infile.next(),null);
      node.setNext(node);
      for(int x = 1; x < n; x++)
      {
         node = insert(node,infile.next());
      }
      return node;
   }
   
   /* helper method to build the list.  Creates the node, then
    * inserts it in the circular linked list.
	 */
   public static ListNode insert(ListNode p, Object obj)
   {
      ListNode node = new ListNode(obj, p.getNext());
      p.setNext(node);
      p = node;
      return p;     
   }
   
   /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
      Ends with one remaining name, who is the winner. 
	 */
   public static ListNode countingOff(ListNode p, int count, int n)
   {
      for(int x = 0; x < n; x++)
      {
         print(p);
         p = remove(p,count);
         
      }
      return p;
   }
   
   /* removes the node after counting off count-1 nodes.
	 */
   public static ListNode remove(ListNode p, int count)
   {
      ListNode node = p;
      for(int x = 0; x < count-1; x++)
      {
         node = node.getNext(); 
      }
      node.setNext((node.getNext()).getNext());
      p = node;
      return p;
   }
   
   /* prints the circular linked list.
	 */
   public static void print(ListNode p)
   {
      ListNode node = p;
      node = p.getNext();
      do{
         System.out.print(node.getValue() + " ");
         node = node.getNext();
      }while(node != p.getNext());
      System.out.println();
   }
	
   /* replaces the value (the string) at the winning node.
	 */
   public static void replaceAt(ListNode p, Object obj, int pos)
   {
      for(int x = 0; x < pos; x++)
      {
         p = p.getNext();
      }
      p.setValue(obj);
   }
}
/**********************************************************
     ----jGRASP exec: java Josephus_Teacher
 A B C D
 How many names (2-20)? 5
 How many names to count off each time? 2
 1 2 3 4 5
 3 4 5 1
 5 1 3
 3 5
 3
 3 wins!
 
  ****  Now start all over. **** 
 
 Where should Josephus stand?  3
 Michael Hannah Josephus Ruth Matthew
 Josephus Ruth Matthew Michael
 Matthew Michael Josephus
 Josephus Matthew
 Josephus
 Josephus wins!
 
  ----jGRASP: operation complete.
  
  **************************************************/

