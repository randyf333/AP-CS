// Name:
// Date:

import java.util.*;
import java.io.*;

public class AreaFill
{
   private static char[][] grid = null;
   private static String filename = null;

   public static void main(String[] args) 
   {
      Scanner sc = new Scanner(System.in);
      while(true)  // what does this do?
      {
         System.out.print("Fill the Area of (-1 to exit): ");
         filename = sc.next();
         if(filename.equals("-1"))
         {
            sc.close();
            System.out.println("Good-bye");
            System.exit(0); 
            return;  
         }
         grid = read(filename);
         String theGrid = display(grid);
         System.out.println( theGrid );
         System.out.print( "1-Fill or 2-Fill-and-Count: ");
         int choice = sc.nextInt();
         switch(choice)
         {
            case 1:
               {
                  System.out.print("\nEnter ROW COL to fill from: ");
                  int row = sc.nextInt();
                  int col = sc.nextInt(); 
                  fill(grid, row, col, grid[row][col]);
                  System.out.println( display(grid) );
                  break;
               }
            case 2:
               {
                  System.out.print("\nEnter ROW COL to fill from: ");
                  int row = sc.nextInt();
                  int col = sc.nextInt();
                  int count = fillAndCount(grid, row, col, grid[row][col]);
                  System.out.println( display(grid) );
                  System.out.println("count = " + count);
                  System.out.println();
                  break;
               }
            default:
               System.out.print("\nTry again! ");
         }
               
      }
   }
   
   /**
    * Reads the contents of the file into a matrix.
    * Uses try-catch.
    * @param name The string representing the filename.
    * @returns A 2D array of chars.
    */
   public static char[][] read(String filename)
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(filename));
      }
      catch (Exception e)
      {
         System.out.println("File not found");
         return null;
      }
      /* enter your code here */
      int x = infile.nextInt();
      int y = infile.nextInt();
      char[][]array = new char[x][y];
      for(int a = 0; a < array.length; a++)
      {
         int c = 0;
         String input = infile.next();
         for(int b = 0; b < array[a].length; b++)
         {
            
            array[a][b] = input.charAt(c);
            c++;
         }
      }
      return array;
   }
   
   /**
    * @param g A 2-D array of chars.
    * @returns A string representing the 2D array.
    */
   public static String display(char[][] g)
   {
      String matrix = "";
      for(int x = 0; x < g.length; x++)
      {
         for(int y = 0; y < g[x].length; y++)
         {
            matrix = matrix+g[x][y];
         }
         matrix = matrix+"\n";
      }
      return matrix;
   }
   
   /**
    * Fills part of the matrix with a different character.
    * @param g A 2D char array.
    * @param r An int representing the row of the cell to be filled.
    * @param c An int representing the column of the cell to be filled.
    * @param ch The char which is being replaced.
    */
   public static void fill(char[][] g, int r, int c, char ch)
   {
      char star = '*';
      if(g[r][c] == ch){
         g[r][c] = star;
      }
      if(c-1 >= 0 && g[r][c-1] == ch ){
         fill(g,r,c-1,ch);
      }
      if(c+1 <= g[r].length-1 && g[r][c+1] == ch ){
         fill(g,r,c+1,ch);
      }
      if(r+1 <= g.length-1 && g[r+1][c] == ch){
         fill(g,r+1,c,ch);
      }
      if(r-1 >= 0 && g[r-1][c] == ch){
         fill(g,r-1,c,ch);
      }
   }
   
   /**
    * Fills part of the matrix with a different character.
    * Counts as you go.  Does not use a static variable.
    * @param g A 2D char array.
    * @param r An int representing the row of the cell to be filled.
    * @param c An int representing the column of the cell to be filled.
    * @param ch The char which is being replaced.
    * @return An int representing the number of characters that were replaced.
    */
   public static int fillAndCount(char[][] g, int r, int c, char ch)
   {
      char star = '*';
      int num = 0;
      if(g[r][c] == ch){
         num++;
         g[r][c] = star;
      }
      if(c-1 >= 0 && g[r][c-1] == ch ){
         num = num+ fillAndCount(g,r,c-1,ch);
      }
      if(c+1 <= g[r].length-1 && g[r][c+1] == ch ){
         num = num+ fillAndCount(g,r,c+1,ch);
      }
      if(r+1 <= g.length-1 && g[r+1][c] == ch){
         num = num + fillAndCount(g,r+1,c,ch);
      }
      if(r-1 >= 0 && g[r-1][c] == ch){
         num = num+ fillAndCount(g,r-1,c,ch);
      }
               
      return num; //never reached
   }
}