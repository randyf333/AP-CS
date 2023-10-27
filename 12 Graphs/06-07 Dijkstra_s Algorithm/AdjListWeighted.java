import java.util.*;
import java.io.*;
 
/* Resource classes and interfaces
 * for use with Graphs6: Dijkstra
 *              Graphs7: Dijkstra with Cities
 */
 
class Edge 
{
   public final wVertex target;  //if it's public, you don't need accessor methods
   public final double weight;   //if it's public, you don't need accessor methods
  
   public Edge(wVertex argTarget, double argWeight) 
   {
      target = argTarget;
      weight = argWeight;
   }
}
 
interface wVertexInterface 
{
   String getName();
   double getMinDistance();
   void setMinDistance(double m);
   wVertex getPrevious();   //for Dijkstra 7
   void setPrevious(wVertex v);  //for Dijkstra 7
   ArrayList<Edge> getAdjacencies();
   void addEdge(wVertex v, double weight);   
   int compareTo(wVertex other);
}
 
class wVertex implements Comparable<wVertex>, wVertexInterface
{       
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   private wVertex previous;  //for building the actual path in Dijkstra 7
   
   /*  enter your code for this class here   */ 
   public wVertex(String n)
   {
      name = n;
      adjacencies = new ArrayList<Edge>();
   }
   public String getName()
   {
      return name;
   } 
   public double getMinDistance()
   {
      return minDistance;
   }
   public void setMinDistance(double m)
   {
      minDistance = m;
   }
   public ArrayList<Edge> getAdjacencies()
   {
      return adjacencies;
   }
   public void addEdge(wVertex v, double weight)
   {
      adjacencies.add(new Edge(v,weight));
   }
   public int compareTo(wVertex other)
   {
      return (int) (minDistance - other.getMinDistance());
   }
   public wVertex getPrevious()
   {
      return previous;
   }
   public void setPrevious(wVertex v)
   {
      previous = v;
   }
   public String toString()
   {
      return name;
   }
}
 
interface AdjListWeightedInterface 
{
   List<wVertex> getVertices();
   Map<String, Integer> getNameToIndex();
   wVertex getVertex(int i);   
   wVertex getVertex(String vertexName);
   void addVertex(String v);
   void addEdge(String source, String target, double weight);
   void minimumWeightPath(String vertexName);   //Dijkstra's
}
 
/* Interface for Graphs 7:  Dijkstra with Cities 
 */
 
interface AdjListWeightedInterfaceWithCities 
{       
   List<String> getShortestPathTo(wVertex v);
   AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException ;
}
 
 
public class AdjListWeighted implements AdjListWeightedInterface,AdjListWeightedInterfaceWithCities
{
   private List<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
 
      //the constructor is a no-arg constructor 
  
   /*  enter your code for Graphs 6 */ 
   public List<wVertex> getVertices()
   {
      return vertices;
   } 
   public Map<String, Integer> getNameToIndex()
   {
      return nameToIndex;
   }
   public wVertex getVertex(int i)
   {
      return vertices.get(i);
   }
   public wVertex getVertex(String vertexName)
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
   public void addVertex(String v)
   {
      if(getVertex(v) == null){
         wVertex cur = new wVertex(v);
         vertices.add(cur);
         if(!nameToIndex.containsKey(cur.getName()))
            nameToIndex.put(v, vertices.indexOf(cur));
      }
   }
   public void addEdge(String source, String target, double weight)
   {
      wVertex sec;
      wVertex fir = getVertex(source);
      if(fir != null)
      { 
         if(!nameToIndex.containsKey(target))
         {
            addVertex(target);
            sec = getVertex(target);
         }else{
            sec = getVertex(target);
         }
         fir.addEdge(sec,weight);
      }
   
   }
   public void minimumWeightPath(String vertexName)
   {
      Set<wVertex> set = new HashSet<wVertex>();
      for(wVertex a : vertices)
      {
         a.setMinDistance(Double.POSITIVE_INFINITY);
      }
      PriorityQueue<wVertex> pq = new PriorityQueue<wVertex>();
      wVertex v = getVertex(vertexName);
      v.setMinDistance(0);
      pq.add(v);
      while(!pq.isEmpty())
      {
         wVertex x = pq.remove();
         ArrayList<Edge> ar = x.getAdjacencies();
         set.add(x);
         for(Edge neighbor : ar)
         {
            wVertex temp = neighbor.target;
            if(!set.contains(temp))
            {
               if(x.getMinDistance() + neighbor.weight < temp.getMinDistance())
               {
                  temp.setMinDistance(x.getMinDistance() + neighbor.weight);
                  temp.setPrevious(x);
               }
               pq.add(temp);
            }
         }
      }
   }
   /*  enter your code for two new methods in Graphs 7 */
   public List<String> getShortestPathTo(wVertex v)
   {
      List<String> path = new ArrayList<String>();
      while(v != null)
      {
         path.add(v.getName());
         v = v.getPrevious();
      }
      Collections.reverse(path);
      
         /*for(wVertex a : vertices)
      {
         a.setMinDistance(Double.POSITIVE_INFINITY);
      }
      Set<wVertex> set = new HashSet<wVertex>();
      PriorityQueue<wVertex> pq = new PriorityQueue<wVertex>();
      v.setMinDistance(0);
      pq.add(v);
      while(!pq.isEmpty())
      {
         wVertex x = pq.remove();
         path.add(x.getName());
         ArrayList<Edge> ar = x.getAdjacencies();
         for(Edge neighbor : ar)
         {
            wVertex temp = neighbor.target;
            temp.setPrevious(x);
            if(!set.contains(temp))
            {
               if(x.getMinDistance() + neighbor.weight < temp.getMinDistance())
               {
                  temp.setMinDistance(x.getMinDistance() + neighbor.weight);
               }
               pq.add(temp);
            }
         }
      }*/
      
      return path;
   }
   public AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException
   {
      Scanner names = new Scanner(vertexNames);
      Scanner data = new Scanner(edgeListData);
      AdjListWeighted g = this;
      String s = names.nextLine();
      while(names.hasNextLine())
      {
         String cityName = names.nextLine();
         g.addVertex(cityName);
      }
      while(data.hasNextLine())
      {
         String city1 = data.next();
         String city2 = data.next();
         int distance = data.nextInt();
         g.addEdge(city1,city2,distance);
      }
      return g;
   }
   
}