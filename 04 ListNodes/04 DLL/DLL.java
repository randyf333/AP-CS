// Name:
// Date:

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   public int size()
   {
      return size;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode node;
      if(index == 0)
      {
         node = head;
      }else{
         node = head.getNext();
      }
      for(int x = 0; x < index-1; x++)             
      {
         node = node.getNext();             
      }
      DLNode temp = new DLNode(obj,node,node.getNext());
      node.setNext(temp);
      (node.getNext().getNext()).setPrev(temp);
      size++;
   }
   
   /* return obj at position index. 	*/
   public Object get(int index)
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode node = head.getNext();
      for(int x = 0; x<index; x++)
      {
         node = node.getNext();
      }
      return node.getValue();
   }
   
   /* replaces obj at position index. 
        returns the obj that was replaced*/
   public Object set(int index, Object obj)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      Object data = get(index);
      DLNode node = head;
      for(int x = 0; x < index+1; x++)
      {
         node = node.getNext();
      }
      node.setValue(obj);
      return data;
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object of the node that was removed.    */
   public Object remove(int index)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      Object data = get(index);
      DLNode node = head;
      
      for(int x = 0; x < index; x++)
      {
         node = node.getNext();
      }
      node.setNext(node.getNext().getNext());
      (node.getNext()).setPrev(node);
      size--;
      
      return data; 
   }
   
   /* inserts obj at front of list, increases size   */
   public void addFirst(Object obj)
   {
      add(0,obj);
      
   }
   
   /* appends obj to end of list, increases size    */
   public void addLast(Object obj)
   {
      DLNode temp = head;
      DLNode node = new DLNode(obj,temp,null);
      if(temp.getNext() == temp)
      {
         temp.setNext(node);
         size++;
      }else{
         while(temp.getNext() != null)
         {
            temp = temp.getNext();
         }
         temp.setNext(node);
         node.setPrev(temp);
         size++;
      }
   }
   
   /* returns the first element in this list  */
   public Object getFirst()
   {
      DLNode node = head.getNext();
      return node.getValue();
   }
   
   /* returns the last element in this list  */
   public Object getLast()
   {
      Object value = get(size-1);
      return value;
   }
   
   /* returns and removes the first element in this list, or
       returns null if the list is empty  */
   public Object removeFirst()
   {
   /* enter your code below  */
   
      if(head == null)
      {
         return null;
      }else{
         Object word = remove(0);
         return word;
      }
   } 
   /* returns and removes the last element in this list, or
       returns null if the list is empty  */
   public Object removeLast()
   {
      if(head == null)
      {
         return null;
      }else{
         /* enter your code below  */
         Object data = get(size-1);
         DLNode node = head;
         for(int x = 0; x < size-1; x++)
         {
            node = node.getNext();
         }
         node.setNext(node.getNext().getNext());
         size--;
         return data;
      }
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
      String list = "[";
      DLNode node = head.getNext();
      while(node.getNext() != null)
      {
         list = list+node.getValue()+", ";
         node = node.getNext();
      }
      list = list+node.getValue()+"]";
      return list;
   }
}