// Name:   
// Date:
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   void addEdge(int source, int target);
   void removeEdge(int source, int target);
   boolean isEdge(int from, int to);
   String toString();   //returns the grid as a String
   int edgeCount();
   List<Integer> getNeighbors(int source);
   //public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      //User-friendly functionality
{
   boolean isEdge(String from, String to);
   Map<String, Integer> getVertices();     
   void readNames(String fileName) throws FileNotFoundException;
   void readGrid(String fileName) throws FileNotFoundException;
   void displayVertices();
   void allPairsReachability();  // Warshall's Algorithm
}

interface Floyd
{
   int getCost(int from, int to);
   int getCost(String from, String to);
   void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix//,Warshall//,Floyd 
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
   /*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
	  
   /*  enter your code here  */  
   public AdjMat(int size)
   {
      grid = new int[size][size];
   }
   public void addEdge(int source, int target)
   {
      grid[source][target] = 1;
   }
   public void removeEdge(int source, int target)
   {
      if(isEdge(source,target))
      {
         grid[source][target] = 0;
      }else{
         System.out.println("Thats not an edge");
      }
   }
   public boolean isEdge(int from, int to)
   {
      if(grid[from][to] != 9999)
      {
         return true;
      }
      return false;
   }
   public String toString()   //returns the grid as a String
   {
      String gridString = "";
      for(int x = 0; x < grid.length; x++)
      {
         for(int y = 0; y < grid[x].length; y++)
         {
            gridString += grid[x][y] + " ";
         }
         gridString += "\n";
      }
      return gridString.trim();
   }
   public int edgeCount()
   {
      int count = 0;
      for(int x = 0; x < grid.length; x++)
      {
         for(int y = 0; y < grid[x].length; y++)
         {
            if(grid[x][y] != 9999 && grid[x][y] != 0)
            {
               count++;
            }
         }
      }
      return count;
   }
   public List<Integer> getNeighbors(int source)
   {
      List<Integer> neighbor = new LinkedList<Integer>();
      for(int y = 0; y < grid[source].length; y++)
      {
         if(grid[source][y] == 1)
         {
            neighbor.add(y);
         }
      }
      
      return neighbor;
   }
   public boolean isEdge(String from, String to)
   {
      int first = vertices.get(from);
      int second = vertices.get(to);
      if(grid[first][second] == 1)
      {
         return true;
      }
      return false;
   }
   public Map<String, Integer> getVertices()    
   {
      return vertices;
   }
   public void readNames(String fileName) throws FileNotFoundException
   {
      vertices = new TreeMap<String, Integer>();
      Scanner cities = new Scanner(new File(fileName));
      String count = cities.nextLine();
      int x = 0;
      while(cities.hasNextLine())
      {
         String name = cities.nextLine();
         vertices.put(name,x);
         x++;
      }
   }
   public void readGrid(String fileName) throws FileNotFoundException
   {
      Scanner path = new Scanner(new File(fileName));
      String count = path.nextLine();
      int x = 0; 
      int y = 0;
      while(path.hasNextInt())
      {
         if(y < grid.length)
         {
            int c = path.nextInt();
            grid[x][y] = c;
            y++;
         }else{
            y = 0;
            x++;
         }
      }
   }
   public void displayVertices()
   {
      for(String name : vertices.keySet())
      {
         String key = vertices.get(name)+"-"+name;
         System.out.println(key);
      }      
   }
   public void allPairsReachability()
   {
      for(int x = 0; x < grid.length; x++)
      {
         for(int y = 0; y < grid[x].length; y++)
         {
            if(grid[x][y] == 1)
            {
               for(int z = 0; z < grid.length; z++)
               {
                  if(grid[y][z] == 1)
                  {
                     grid[x][z] = 1;
                  }
               }
            }
         }
      }
   }  
   public ArrayList<String> getReachables(String city)  
   {
      return null;
   }
   
   public int getCost(int from, int to)
   {
      return grid[from][to];
   }
   public int getCost(String from, String to)
   {
      int first = vertices.get(from);
      int sec = vertices.get(to);
      return grid[first][sec];
   }
   public void allPairsWeighted()
   {
      for(int x = 0; x < grid.length; x++)
      {
         for(int y = 0; y < grid[x].length; y++)
         {
            for(int z = 0; z < grid.length; z++)
            {
               if(grid[y][x] + grid[x][z] < grid[y][z]){
                  grid[y][z] =  grid[y][x] + grid[x][z];          
               }
            }
         }
      }
   }
}
