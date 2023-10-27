// Name:   Randy Fu
// Date:   9/15/2020
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
      
      /*  extension only    */
      String pigLatin = pig("What!?");
      System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      pigLatin = pig("{(Hello!)}");
      System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      pigLatin = pig("\"McDonald???\"");
      System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"*/
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   public static String pig(String s)
   {
      if(s.length() == 0)
         return "";
      String bpun = "";
      String epun = "";
      String word = "";
      String move = "";
      int check1=0;
      int check2=0;
      int check3 = 0;
      int check4 = 0;
      int check5 = 0;
      int bin = 0;//Index of beginning punct
      int ein = s.length();//Index of ending punct
      int fvowl = 0;//Index of first vowel
      char yy = 'y';
      char Y = 'Y';
      String yvowels = vowels;
      String add = "";
      int score = 0;
      String caps = letters.substring(0,26);
      String part = "";
      //remove and store the beginning punctuation 
     
      for(int x = 0; x < s.length(); x++)
      {
         if(check1 == 0)
         {
            for(int y = 0; y < punct.length(); y++)
            {
               if(s.charAt(x) == punct.charAt(y))
               {
                  bpun = bpun + s.charAt(x);
                  bin++;
               }
            }
            for(int z = 0; z < letters.length(); z++)
            {
               if(s.charAt(x) == letters.charAt(z))
               {
                  check1 = 1;
                  
               }
            }
         }
      }
     
      //remove and store the ending punctuation 
    
      for(int a = s.length(); a > 0; a--)
      {
         if(check2 == 0)
         {
            for(int b = 0; b < punct.length(); b++)
            {
               if(s.charAt(a-1) == punct.charAt(b))
               {
                  epun = s.charAt(a-1)+epun;
                  ein--;//might need fix
               }
            }
            for(int c = 0; c < letters.length(); c++)
            {
               if(s.charAt(a-1) == letters.charAt(c))
               {
                  check2 = 1;
               }
            }
         }
      }
      
         
      //START HERE with the basic case:
      //     find the index of the first vowel
      //     y is a vowel if it is not the first letter
      //     qu
      if(s.charAt(bin) != yy)
      {
         yvowels = yvowels+yy+Y;
      }
      
      
      for(int d = 0; d < s.length(); d++)
      {
         if(check3 == 0)
         {
            for(int e = 0; e < yvowels.length(); e++)
            {
               if(s.charAt(d) == yvowels.charAt(e))
               {
                  fvowl = d;
                  check3 = 1;
               }
            }
         }
      }
     
      move = s.substring(bin,fvowl);
      part = s.substring(fvowl,ein);
          
      for(int f = 0; f < vowels.length(); f++)
      {
         if(check4 == 0)
         {
            if(s.charAt(bin) == vowels.charAt(f))
            {
               add = "way";
               check4 = 1;
               
            }else{
               add = "ay";
            }
         }
      }
   
   
      if(s.contains("qu") == true && s.indexOf("qu",bin) < fvowl)
      {
         move = s.substring(bin,fvowl+1);
         part = s.substring(fvowl+1,ein);
      }else if(s.contains("Qu")==true && s.indexOf("Qu",bin) < fvowl)
      {
         move = s.substring(bin,fvowl+1);
         part = s.substring(fvowl+1,ein);
      }else if(s.contains("QU")==true && s.indexOf("QU",bin) < fvowl)
      {
         move = s.substring(bin,fvowl+1);
         part = s.substring(fvowl+1,ein);
      }else if(s.contains("qU")==true && s.indexOf("qU",bin)<fvowl)
      {
         move = s.substring(bin,fvowl+1);
         part = s.substring(fvowl+1,ein);
      }             
                       //if no vowel has been found
      for(int g = 0; g < s.length(); g++)
      {
         if(score == 0)
         {
            for(int h = 0; h < yvowels.length(); h++)
            {
               if(s.charAt(g) == yvowels.charAt(h))
               {
                  score = 1;
               }
            }
         }
      }
      if(score == 0)
      {
         return "**** NO VOWEL ****";
      }
        
      //is the first letter capitalized?
      for(int j = 0; j < vowels.length(); j++)
      {
         if(s.charAt(bin) == vowels.charAt(j))
         {
            part = s.substring(fvowl,ein);
            check5 = 1;
         }
      }
      if(check5 == 0)
      {
         for(int k = 0; k < caps.length(); k++)
         {
            if(s.charAt(bin) == caps.charAt(k))
            {
               String cmove = move.substring(0,1).toLowerCase();
               move = cmove + move.substring(1);
               String lpart = part.substring(0,1).toUpperCase();
               part = lpart+part.substring(1);
            }
         }
      }
      //return the piglatinized word 
      
      word = bpun+part+move+add+epun;
   
      return word;
     
      
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line for
      //infile.delimiter();
      
      while(infile.hasNextLine())
      {
         StringTokenizer st = new StringTokenizer(infile.nextLine());
         while(st.hasMoreTokens())
         {
            
            String s = pig(st.nextToken());
            outfile.print(s);
            if(st.hasMoreTokens())
            {
               outfile.print(" ");
            }
         }
         
         outfile.println();
      }
      
        
    
      outfile.close();
      infile.close();
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   public static String pigReverse(String s)
   {
      int bin = 0;
      int ein = s.length();
      int check1 = 0;
      int check2 = 0;
      int checkC = 0;
      String bpun = "";
      String epun = "";
      char temp;
      String result = "";
      String caps = letters.substring(0,26);
   
      for(int x = 0; x < s.length(); x++)
      {
         if(check1 == 0)
         {
            for(int y = 0; y < punct.length(); y++)
            {
               if(s.charAt(x) == punct.charAt(y))
               {
                  bpun = bpun + s.charAt(x);
                  bin++;
               }
            }
            for(int z = 0; z < letters.length(); z++)
            {
               if(s.charAt(x) == letters.charAt(z))
               {
                  check1 = 1;
                  
               }
            }
         }
      }
     
      //remove and store the ending punctuation 
    
      for(int a = s.length(); a > 0; a--)
      {
         if(check2 == 0)
         {
            for(int b = 0; b < punct.length(); b++)
            {
               if(s.charAt(a-1) == punct.charAt(b))
               {
                  epun = s.charAt(a-1)+epun;
                  ein--;//might need fix
               }
            }
            for(int c = 0; c < letters.length(); c++)
            {
               if(s.charAt(a-1) == letters.charAt(c))
               {
                  check2 = 1;
               }
            }
         }
      }
      String word = s.substring(bin,ein);
      int begin = 0; 
      int end = word.length()-1;
      char[] base = new char[word.length()];
      for(int x = 0; x < word.length(); x++)
      {
         base[x] = word.charAt(x);
      }
      while(end>begin)
      {
         temp = base[begin];
         base[begin] = base[end];
         base[end] = temp;
         end--;
         begin++;
      }
      for(int z = 0; z < word.length(); z++)
      {
         result = result + base[z];
      }
      for(int a = 0; a < caps.length(); a++)
      {
         if(result.charAt(result.length()-1) == caps.charAt(a))
         {
            String lmove = result.substring(result.length()-1,result.length()).toLowerCase();
            String cpart = result.substring(0,1).toUpperCase();
            result = cpart + result.substring(1,result.length()-1) + lmove;
         }
      }
      return bpun + result + epun;
   }
}
