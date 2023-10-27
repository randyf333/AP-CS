// Name: 
// Date: 
import java.util.*;
import java.io.*;    
public class Sentence_Driver
{
   public static void main(String[] args)
   {
      System.out.println("PALINDROME TESTER");
      Sentence s = new Sentence( "\"Hello there!\" she said." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
      s = new Sentence( "A Santa lived as a devil at NASA." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
     
   
      s = new Sentence( "Flo, gin is a sin! I golf." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
   
      s = new Sentence( "Eva, can I stab bats in a cave?" );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
   
      s = new Sentence( "Madam, I'm Adam." );
      System.out.println( s.getSentence() );
      System.out.println( s.getNumWords() );
      System.out.println( s.isPalindrome() );
      System.out.println();
      
      s = new Sentence( "ABCDxCBA");
      System.out.println(s.getSentence());
      System.out.println(s.getNumWords());
      System.out.println(s.isPalindrome());
      
      s = new Sentence("acba");
      System.out.println(s.getSentence());
      System.out.println(s.getNumWords());
      System.out.println(s.isPalindrome());
      
   
   // Lots more test cases.  Test every line of code.  Test
   // the extremes, test the boundaries.  How many test cases do you need?
   
   }
}

class Sentence
{
   private String mySentence;
   private int myNumWords;
   
   //Precondition:  str is not empty.
   //               Words in str separated by exactly one blank.
   public Sentence( String str )
   { 
      mySentence = str;
   }
   
   public int getNumWords()
   {  
      myNumWords = 0;
      int count = 0;
      for(int x = 0; x < mySentence.length(); x++)
      {
         if(Character.isWhitespace(mySentence.charAt(x)))
            count++;
      }
      count++;
      myNumWords = count;
      return myNumWords;  
   }
   
   public String getSentence()
   {
      return mySentence; 
   }
   
   //Returns true if mySentence is a palindrome, false otherwise.
   public boolean isPalindrome()
   {
     
      String s = getSentence();
      s = removePunctuation(s);
      s = removeBlanks(s);
      s = lowerCase(s);
      int begin = 0;
      int end = s.length()-1;
      if(isPalindrome(s,begin,end) == true)
      {
         return true;
      }else{
         return false;
      }
      
   }
   //Precondition: s has no blanks, no punctuation, and is in lower case.
   //Returns true if s is a palindrome, false otherwise.
   public static boolean isPalindrome( String s, int start, int end )
   {
      
      if(start >= end)
      {
         return true;
      }
      if(s.charAt(start) == s.charAt(end))
      {
       return isPalindrome(s,start+1,end-1);//needs return
      }    
      return false;
      
   }
   //Returns copy of String s with all blanks removed.
   //Postcondition:  Returned string contains just one word.
   public static String removeBlanks( String s )
   {  
      //StringTokenizer st = new StringTokenizer(s);
      String string = s;
      string = string.replaceAll("\\s","");
      return string;
   }
   
   //Returns copy of String s with all letters in lowercase.
   //Postcondition:  Number of words in returned string equals
   //						number of words in s.
   public static String lowerCase( String s )
   {  
      String string = s.toLowerCase();
      return string;
   }
   
   //Returns copy of String s with all punctuation removed.
   //Postcondition:  Number of words in returned string equals
   //						number of words in s.
   public static String removePunctuation( String s )
   { 
      String punct = ".,'?!:;\"(){}[]<>"; 
      String string = s;
      int bindex = 0;
      
      char z = ' ';
      //StringTokenizer st = new StringTokenizer(s);
      /*for(int x = 0; x < s.length(); x++)
      {
         
         for(int y = 0; y< punct.length(); y++)
         {
            if(string.charAt(x) == punct.charAt(y))
            {
               string = string.replace(string.charAt(x),z);
            }
         }
                
      }*/
      string = string.replaceAll("\\p{Punct}","");
      return string;
   }
}

 /*****************************************
   
 PALINDROME TESTER
 "Hello there!" she said.
 4
 false
 
 A Santa lived as a devil at NASA.
 8
 true
 
 Flo, gin is a sin! I golf.
 7
 true
 
 Eva, can I stab bats in a cave?
 8
 true
 
 Madam, I'm Adam.
 3
 true

 **********************************************/

