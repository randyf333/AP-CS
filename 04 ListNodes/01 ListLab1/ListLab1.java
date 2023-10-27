// Name:     
// Date:
import java.util.*;
public class ListLab1
{
   public static void main(String[] args)
   {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer",
         	new ListNode("science",
         		new ListNode("java",
         			new ListNode("coffee", head)
         		)
         	)
         );
         
      print(head);
      print(head);
      
      /**** uncomment the code below for ListLab1 Assignment  ****/
      
      ListNode a = copyNode(head);
      System.out.println("The head has a value \"" + head.getValue() + "\" at "+ head);
      System.out.println("The copy of head has a value of \"" + a.getValue() + "\" at "+ a);
    
      System.out.print("Copy the list: ");
      ListNode copy = copyList(head);
      print(copy);
   
      System.out.print("The rest of the list: ");
      ListNode theRest = rest(copy);
      print(theRest);
   
      System.out.println("First of the rest = " + first(theRest));
      System.out.println("Second of the rest = " + second(theRest));
      ListNode p = pointerToLast(theRest);
      System.out.println("Pointer to Last = " + p.getValue()+ " at " + p);
      ListNode c = copyOfLast(theRest);
      System.out.println("Copy of Last =    " + c.getValue()+ " at " + c);
   
      Scanner in = new Scanner(System.in);
      System.out.print("Insert what? ");
      String x = in.next();
      theRest = insertFirst(theRest, x);
      theRest = insertLast(theRest, x);
      print(theRest);
   }
   public static void print(ListNode head)
   {
      System.out.print("[");
      while(head != null)
      {
         System.out.print(head.getValue());
         head = head.getNext();
         if(head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }
   /* enter your code here */
   public static ListNode copyNode(ListNode arg)
   {
      ListNode node = new ListNode(arg.getValue(),null);
      return node;
   }
   
   public static ListNode copyList(ListNode arg)
   {
      if(arg.getNext() == null)
      {
         ListNode list = new ListNode(arg.getValue(),null);
         return list;
      }else{
         Object data = arg.getValue();
         ListNode list = new ListNode(data,copyList(arg.getNext()));
         //copyList(list.getNext());
         return list;
      }
   }
   
   public static ListNode rest(ListNode h)
   {
      ListNode node = copyList(h.getNext());
      //ListNode list = new ListNode(h.getValue(),copyList(node));
      return node;
   }
   
   public static Object first(ListNode head)
   {
      if(head == null)
         return null;
      Object node = head.getValue();
      return node;
   }
   
   public static Object second(ListNode head)
   {
      if(head == null || head.getNext() == null)
         return null;
      ListNode list = head.getNext();
      Object node = list.getValue();
      return node;
   }
   
   public static ListNode pointerToLast(ListNode head)
   {
      if(head == null)
         return null;
      while(head.getNext() != null)
      {
         head = head.getNext();
      }
      ListNode node = new ListNode(head.getValue(),null);
      return node;
   }
   
   public static ListNode copyOfLast(ListNode head)
   {
      
      ListNode node = head;
      while(node.getNext() != null)
      {
         node = node.getNext();
      }
      node = copyNode(node);
      return node;
   }
   
   public static ListNode insertFirst(ListNode head, Object arg)
   {
      ListNode node = new ListNode(arg,head);
      return node;
   }
   public static ListNode insertLast(ListNode head, Object arg)
   {
      ListNode new_node = new ListNode(arg,null);
      ListNode last = head;
      while(last.getNext() != null)
      {
         last = last.getNext();
      }
      last.setNext(new_node);
      return head;
   }
   
      
     
}

/*****************************************
 
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 [computer, science, java, coffee, nonsense, boo, foo, hello]
 The head has a value "computer" at ListNode@15db9742
 The copy of head has a value of "computer" at ListNode@6d06d69c
 Copy the list: [computer, science, java, coffee, nonsense, boo, foo, hello]
 The rest of the list: [science, java, coffee, nonsense, boo, foo, hello]
 First of the rest = science
 Second of the rest = java
 Pointer to Last = hello at ListNode@7852e922
 Copy of Last =    hello at ListNode@4e25154f
 Insert what? p
 [p, science, java, coffee, nonsense, boo, foo, hello, p]
    
  **********************************************/
