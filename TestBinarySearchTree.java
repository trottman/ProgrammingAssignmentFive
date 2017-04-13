import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *CSCI-C 202 Assignment 5 
 * @author Tabitha Rottman
 * @since 4/6/2017
 * @version 1.0
 * @param <E>
 */
public class TestBinarySearchTree {

    /**
     *Main method that performs operations requested via Binary Search Trees. 
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

    long wordsFound=0;
    long wordsNotFound=0;
    long compsFound=0;
    long compsNotFound=0;
    int [] count = new int[1];
    
     BinarySearchTree[] d= new BinarySearchTree[26];
     
     for(int i =0; i<d.length; i++){
         d[i]=new BinarySearchTree<String>();
     }
     
    File dfile= new File("random_dictionary.txt");
    Scanner scan = new Scanner(dfile);
    while(scan.hasNext()){
       String temp = scan.nextLine().toLowerCase();
       d[temp.charAt(0)-97].insert(temp);
    }
   
    File olive = new File("oliver.txt");
    Scanner scan2= new Scanner(olive);
    while(scan2.hasNext()){
        String temp2 = scan2.next().toLowerCase();
       temp2=temp2.replaceAll("[^A-Za-z]", "");
       
       if(temp2.isEmpty()==false){
           if(d[temp2.charAt(0)-97].search(temp2, count)){
               wordsFound++;
               compsFound+=count[0];
           }
           else{
               wordsNotFound++;
               compsNotFound+=count[0];
           }
       }
    }
    long avgcompsFound=compsFound/wordsFound;
    long avgcompsNotFound= compsNotFound/wordsNotFound;
      System.out.println("The number of words found in the file are: " + wordsFound);
      System.out.println("The number of words not found in the file are: " + wordsNotFound);
      System.out.println("The number of comparisons found are: " + compsFound);
      System.out.println("The number of comparisons not found are: " + compsNotFound);
      System.out.println("The average number of words found to the number of comparisons is " + avgcompsFound);
      System.out.println("The average number of words not found to the number of comparisons is " + avgcompsNotFound);
  }
   
}

