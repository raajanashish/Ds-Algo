package com.raajan.dp.lcs;

/**
 * Minimum number of deletions and insertions to transform one string into another
 * Input :
 * str1 = "heap", str2 = "pea"
 * Output :
 * Minimum Deletion = 2 and
 * Minimum Insertion = 1
 * Explanation:
 * p and h deleted from heap
 * Then, p is inserted at the beginning
 * One thing to note, though p was required yet
 * it was removed/deleted first from its position and
 * then it is inserted to some other position.
 * Thus, p contributes one to the deletion_count
 * and one to the insertion_count.
 * 
 * @author raajan
 *
 */

// Applications:
/**
 * Minimum number of insertion deletion to make a string palindrome, X --> Rev(X)
 * Here only one of insertion or deletion will be sufficient to change a string to palindrome.
 * 
 * @author raajan
 *
 */
public class MinNumOfInsertionDeletion {

  public static void main(String[] args) {
    String X = "heap";
    String Y = "pea";
    printMinDelAndInsert(X, Y);
  }


  public static void printMinDelAndInsert(String X, String Y) {
    int m = X.length();
    int n = Y.length();
    String lcs = LongestCommonSubSequence.getLCSDp(X, Y);
    System.out.println();
    System.out.println("Minimum number of deletion: " + (m - lcs.length()));
    System.out.println("Minimum number of insertion: " + (n - lcs.length()));
  }

}
