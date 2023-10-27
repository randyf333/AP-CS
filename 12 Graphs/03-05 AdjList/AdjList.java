// Name:   
// Date:
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/* Graphs 3: EdgeList 
 */
interface VertexInterface
{
   String toString(); // Don't use commas in the list.  Example: "C [C D]"
   String getName();
   ArrayList<Vertex> getAdjacencies();
   void addAdjacent(Vertex v);
} 

class Vertex implements VertexInterface 
{
   private final String name;
   private ArrayList<Vertex> adjacencies;
   public Vertex(String text)
   {
      name = text;
      adjacencies = new ArrayList<Vertex>();
   }
  /* enter your code here  */
   public String toString()
   {
      String text = "[";
      for(int x = 0; x < adjacencies.size(); x++)
      {
         text += adjacencies.get(x).getName() + " ";
      }
      text = text.trim()+"]";
      return name + " " + text;
   }
   public String getName()
   {
      return name;
   }
   public ArrayList<Vertex> getAdjacencies()
   {
      ArrayList<Vertex> copy = new ArrayList<Vertex>(adjacencies);
      return copy;
   }
   public void addAdjacent(Vertex v)
   {
      adjacencies.add(v);    
   }
  
}   

interface AdjListInterface 
{ 
   List<Vertex> getVertices();
   Vertex getVertex(int i) ;
   Vertex getVertex(String vertexName);
   Map<String, Integer> getVertexMap();
   void addVertex(String v);
   void addEdge(String source, String target);
   String toString();  //returns all vertices with their edges (omit commas)
}

  
/* Graphs 4: DFS and BFS 
 */
interface DFS_BFS
{
   List<Vertex> depthFirstSearch(String name);
   List<Vertex> depthFirstSearch(Vertex v);
   List<Vertex> breadthFirstSearch(String name);
   List<Vertex> breadthFirstSearch(Vertex v);
   /*  three extra credit methods */
   List<Vertex> depthFirstRecur(String name);
   List<Vertex> depthFirstRecur(Vertex v);
   void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/* Graphs 5: Edgelist with Cities 
 */
interface EdgeListWithCities
{
   void graphFromEdgeListData(String fileName) throws FileNotFoundException;
   int edgeCount();
   int vertexCount(); //count the vertices in the object
   boolean isReachable(String source, String target);
   boolean isConnected();
}


public class AdjList implements AdjListInterface// , DFS_BFS , EdgeListWithCities
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();
       /* enter your code here  */
   public List<Vertex> getVertices()
   {
      return vertices;
   }
   public Vertex getVertex(int i) 
   {
      return vertices.get(i);
   }
   public Vertex getVertex(String vertexName)
   {
      for(int x = 0; x < vertices.size(); x++)
      {
         if(vertices.get(x).getName().equals(vertexName))
         {
            return vertices.get(x);
         }
      }
      return null;
   }
   public Map<String, Integer> getVertexMap(){
      return nameToIndex;
   }
   public void addVertex(String v){
      
      if(getVertex(v) == null){
         Vertex cur = new Vertex(v);
         vertices.add(cur);
         if(!nameToIndex.containsKey(cur.getName()))
            nameToIndex.put(v, vertices.indexOf(cur));
      }
   
   }
   public void addEdge(String source, String target){
      Vertex sec;
      Vertex fir = getVertex(source);
      if(fir != null)
      { 
         if(!nameToIndex.containsKey(target))
         {
            addVertex(target);
            sec = getVertex(target);
         }else{
            sec = getVertex(target);
         }
         fir.addAdjacent(sec);
      }
   }
   public String toString(){
      String graph = "";
      for(int x = 0; x < vertices.size(); x++)
      {
         graph += vertices.get(x) + "\n";
      }
      return graph.trim();
   }
   public List<Vertex> depthFirstSearch(String name)//preorder + list to not visit again, use stack. Do not add to visited unless its popped from stack
   {
      List<Vertex> search = depthFirstSearch(getVertex(name));
      return search;
   }
   public List<Vertex> depthFirstSearch(Vertex v)
   {
      List<Vertex> reachable = new ArrayList<Vertex>();
      Stack<Vertex> temp = new Stack<Vertex>();
      temp.push(v);
      while(!temp.isEmpty())
      {
         Vertex edge = temp.pop();
         if(!reachable.contains(edge))
         {
            reachable.add(edge);
         }
         for(Vertex x : edge.getAdjacencies())
         {
            if(!reachable.contains(x))
               temp.push(x);
         }                  
      }
      return reachable;
   }
   public List<Vertex> breadthFirstSearch(String name)//level order + list to not visit again, Queue has all neighbors of source node. 
   {
      List<Vertex> bsearch = breadthFirstSearch(getVertex(name));
      return bsearch;
   }
   public List<Vertex> breadthFirstSearch(Vertex v)
   {
      List<Vertex> breachable = new ArrayList<Vertex>();
      Queue<Vertex> temp = new LinkedList<Vertex>();
      temp.add(v);
      while(!temp.isEmpty())
      {
         Vertex edge = temp.remove();
         if(!breachable.contains(edge))
         {
            breachable.add(edge);
         }
         for(Vertex x : edge.getAdjacencies())
         {
            if(!breachable.contains(x))
               temp.add(x);
         }
      }
      return breachable;
   }
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException
   {
      Scanner data = new Scanner(new File(fileName));
      while(data.hasNextLine())
      {
         String city = data.next();
         addVertex(city);
         addEdge(city,data.next());
      }
   }
   public int edgeCount()
   {
      int count = 0;
      for(Vertex v : vertices)
      {
         for(Vertex c : v.getAdjacencies())
         {
            count++;
         }
      }
      return count;   
   }
   public int vertexCount()//count the vertices in the object
   {
      int count = 0;
      for(Vertex v : vertices)
      {
         count++;
      }
      return count;
   
   } 
   public boolean isReachable(String source, String target)
   {
      List<Vertex> v = depthFirstSearch(source);
      if(v.contains(getVertex(target)))
      {
         return true;
      }else{
         return false;
      }
   }
   public boolean isConnected()
   {
      for(Vertex v : vertices)
      {
         for(String s : nameToIndex.keySet())
         {
            if(!v.getName().equals(s))
            {
               if(!isReachable(v.getName(),s))
               {
                  return false;
               }
            }
         }
      }
      return true;
   }

 
 /*  three extra credit methods, recursive version  */
   List<Vertex> depthFirstRecur(String name)
   {
      return null;
   }
   
   List<Vertex> depthFirstRecur(Vertex v)
   {
      return null;
   }
   
   void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable)
   {
      
   }   
}


