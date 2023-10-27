// Name: 
// Date:

/**
 * Implements the cheat sheet's List interface.  Implements generics.
 * The backing array is an array of (E[]) new Object[10];
 * @override toString()
 */ 
public class TJArrayList<E>
{
   private int size;							//stores the number of objects
   private E[] myArray;
   public TJArrayList()                //default constructor makes 10 cells
   {
      myArray = (E[]) new Object[10];
      size = 0;
   }
   public int size()
   {
      return size;
   }
 	/* appends obj to end of list; increases size;
      must be an O(1) operation when size < array.length, 
         and O(n) when it doubles the length of the array.
	  @return true  */
   public boolean add(E obj)
   {
      size++;
      if(size < myArray.length)
      {
         int x = 0;
         E[] array = myArray;
         while(array[x] != null)
         {
            x++;
         }
         array[x] = obj;
      }else{
         int y = 0;
         E[] array = (E[]) new Object[myArray.length*2];
         for(int x = 0; x < myArray.length; x++)
         {
            array[x] = myArray[x];
         }
         while(array[y] != null)
         {
            y++;
         }
         array[y] = obj;
         myArray = array;
      }
      return true;
   }
   /* inserts obj at position index.  increments size. 
		*/
   public void add(int index, E obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      size++;
      E[] array = myArray;
      E temp = array[index];
      int x = index;
      while(array[x] != null)
      {
         x++;
      }
      for(int y = index; y < x; y++)
      {
         E temp2 = array[y+1];
         array[y+1] = temp;
         temp = temp2;
      }
      array[index] = obj;
   }

   /* return obj at position index.  
		*/
   public E get(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      E[] array = myArray;
      return array[index];
   
   }
   /**
    * Replaces obj at position index. 
    * @return the object is being replaced.
    */  
   public E set(int index, E obj) throws IndexOutOfBoundsException  
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      E[] array = myArray;
      E data = array[index];
      array[index] = obj;
      return data;
   }
 /*  removes the node from position index. shifts elements 
     to the left.   Decrements size.
	  @return the object at position index.
	 */
   public E remove(int index) throws IndexOutOfBoundsException  
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      size--;
      E[] array = myArray;
      E temp = array[index];
      E data = array[index];
      int x = index;
      while(array[x] != null)
      {
         x++;
      }
      for(int y = index; y < x; y++)
      {
         E temp2 = array[y+1];
         array[y] = temp2;
         temp = temp2;
      }
      return data;
   }
	   /*
		   This method compares objects.
         Must use .equals(), not ==
     	*/
      
   public boolean contains(E obj)
   {
      E[] array = myArray;
      int x = 0;
      while(array[x] != null)
      {
         if(array[x].equals(obj))
            return true;
         x++;
      }
      return false;
   }
	 /*returns a String of E objects in the array with 
       square brackets and commas.
     	*/
   public String toString()
   {
      String list = "[";
      int x = 0;
      while(myArray[x] != null)
      {
         x++;
      }
      int z = 0;
      for(int y = 0; y < x-1; y++)
      {
         list = list+myArray[y]+", ";
         z++;
      }
      list = list+myArray[z]+"]";
      return list;
   }
}