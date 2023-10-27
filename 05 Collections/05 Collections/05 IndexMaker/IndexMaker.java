// Name:      
// Date:
// This program takes a text file, creates an index (by line numbers)
// for all the words in the file and writes the index
// into the output file.  The program prompts the user for the file names.

import java.util.*;
import java.io.*;

public class IndexMaker
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(inFileName));
      String outFileName = "fishIndex.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      indexDocument(inputFile, outputFile);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
   {
      DocumentIndex index = new DocumentIndex();
      String line = null;
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      for(IndexEntry entry : index)
         outputFile.println(entry);
   }   
}
class DocumentIndex extends ArrayList<IndexEntry>
{
    //constructors
    
   public DocumentIndex()
   {
      super();
   }
   public DocumentIndex(int size)
   {
      super(size);
   }   
  /** extracts all the words from str, skipping punctuation and whitespace 
 and for each word calls addWord().  In this situation, a good way to 
 extract while also skipping punctuation is to use String's split method, 
 e.g., str.split("[., \"!?]")       */
   public void addAllWords(String str, int lineNum) 
   {
      if(str.isEmpty())
         return;
       
      for(String word : str.split("[., \"!?]"))
         if(word.length() == 0)
         {
         
         }else{
            addWord(word,lineNum);
         }
   }
    
   /** calls foundOrInserted, which returns a position.  At that position,  
   updates that IndexEntry's list of line numbers with lineNum. */
   public void addWord(String word, int lineNum)
   {
      int x = foundOrInserted(word);
      IndexEntry entry = get(x);
      entry.add(lineNum);
   }
        
    /** traverses this DocumentIndex and compares word to the words in the 
    IndexEntry objects in this list, looking for the correct position of 
    word. If an IndexEntry with word is not already in that position, the 
    method creates and inserts a new IndexEntry at that position. The 
    method returns the position of either the found or the inserted 
    IndexEntry.*/
   private int foundOrInserted(String word)
   {
      int index = 0;
      //int check = 0;
      while(true)
      {
         if(index >= size())
         {
            add(index,new IndexEntry(word));
            return index;
         }
         IndexEntry entry = get(index);
         int x = word.toUpperCase().compareTo(entry.getWord());
         if(x > 0)
         {
            index++;
         }else if(x < 0)
         {
            add(index,new IndexEntry(word));
            return index;
         }else{
            return index;
         }
      }
   }
}
   
class IndexEntry implements Comparable<IndexEntry>
{
     //fields
   private String word;
   private ArrayList<Integer> numsList;
     //constructors
   public IndexEntry(String string)
   {
      string = string.toUpperCase();
      numsList = new ArrayList<Integer>();
      word = string;
   }
   
        /**  appends num to numsList, but only if it is not already in that list.    
          */
   public void add(int num)
   {
      int check = 0;
      for(int x : numsList)
      {
         if(num == x)
            check = 1;
      }
      if(check != 1)
         numsList.add(num);
   }
      
   	/** this is a standard accessor method  */
   public String getWord()
   {
      return word;
   }
   
   public int compareTo(IndexEntry o)
   {
      return word.compareTo(o.getWord());
   }     
     /**  returns a string representation of this Index Entry.  */
   public String toString()
   {
      String list = "";
      for(int x = 0; x < numsList.size(); x++)
      {
         if(x != numsList.size()-1)
         {
            list = list+numsList.get(x)+", ";
         }else{
            list = list+numsList.get(x);
         }
      }
      
      return word + " "+list;
   }
   
}

