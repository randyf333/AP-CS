 //Name:   
 //Date:
 
import java.util.*;


/* implement the API for java.util.PriorityQueue
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public boolean add(E obj)
   {
      myHeap.add(myHeap.size(),obj);
      heapUp(myHeap.size()-1);
      return true;
   }
   
   public E remove()
   {
      E num = myHeap.remove(1);
      heapDown(1,myHeap.size());
      return num;
   }
   
   public E peek()
   {
      return myHeap.get(myHeap.size()-1);
   }
   
   public boolean isEmpty()
   {
      if(myHeap.isEmpty())
      {
         return true;
      }
      if(myHeap.size() < 2 && myHeap.get(0) == null)
      {
         return true;
      }
      return false;
   }
   
   private void heapUp(int k)
   {
      int smallest = k;
      int parent = k/2;
      if(parent > 0 && myHeap.get(smallest).compareTo(myHeap.get(parent)) < 0)
      {
         smallest = parent;
      } 
      if(smallest != k)
      {
         swap(smallest,k);
         heapUp(smallest);
      }
   }
   
   private void swap(int a, int b)
   {
      E temp = myHeap.get(a);
      myHeap.set(a,myHeap.get(b));
      myHeap.set(b,temp);
   }
   
   private void heapDown(int k, int size)
   {
      int smallest = k;
      int l = k*2;
      int r = 2*k+1;
      if(l < size && myHeap.get(l).compareTo(myHeap.get(smallest)) < 0)
      {
         smallest = l;
      }
      if(r < size && myHeap.get(r).compareTo(myHeap.get(smallest)) < 0)
      {
         smallest = r;
      }
      if(smallest != k)
      {
         swap(k,smallest);
         heapDown(smallest,size);
      }
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
