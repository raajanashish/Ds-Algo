package com.raajan.dp.lcs;

/**
 * This is variation of lcs where another string will be taken as reverse of given one.
 * Let X[0..n-1] be the input sequence of length n and L(0, n-1) be the length of the longest
 * palindromic subsequence of X[0..n-1].
 * 
 * 
 * If last and first characters of X are same, then L(0, n-1) = L(1, n-2) + 2.
 * Else L(0, n-1) = MAX (L(1, n-1), L(0, n-2)).
 * 
 * 
 * // Every single character is a palindrome of length 1
 * L(i, i) = 1 for all indexes i in given sequence
 * 
 * // IF first and last characters are not same
 * If (X[i] != X[j]) L(i, j) = max{L(i + 1, j),L(i, j - 1)}
 * 
 * // If there are only 2 characters and both are same
 * Else if (j == i + 1) L(i, j) = 2
 * 
 * // If there are more than two characters, and first and last
 * // characters are same
 * Else L(i, j) = L(i + 1, j - 1) + 2
 * 
 * @author raajan
 * 
 */
// Applications of this problem
/**
 * 1 : Minimum number of deletion to make a string X to palindrome. Min no of deletion = X.length -
 * LPS(X)
 * 2 : Minimum number of insertions to make string a palindrome . Min no of insertions = X.length -
 * LPS(X)
 * Min no of deletion = X.length - LPS(X)
 * 
 * @author raajan
 *
 */
public class LongestPalindromeSubsequence {

  public static void main(String[] args) {
    String X = "GEEKSFORGEEKS";
    int m = X.length();
    System.out.println(longestPalindromicSubsequece(X));
    System.out.println(lpsRec(X, 0, m - 1));


    int[] array = {1, 2, 3, 4, 5, 6, 7};
    int sum = 0;
    for (int i = 0; i < array.length; i++) {
      if (array[i] % 2 == 0) {
        System.out.println("Even");
      } else {
        System.out.println("Odd");
      }
    }
  }

  /**
   * This approch is based on direct lcs application.
   * 
   * @param X
   * @return
   */
  public static String longestPalindromicSubsequece(String X) {
    String Y = new StringBuilder(X).reverse().toString();
    return LongestCommonSubSequence.getLCSRec(X, Y);
  }

  /**
   * This is not directly using the lcs, but a optimal substructure.
   */
  public static int lpsRec(String X, int i, int j) {
    if (i == j) {
      return 1;
    }

    // Base Case 2: If there are only 2
    // characters and both are same
    if (X.charAt(i) == X.charAt(j) && i + 1 == j) {
      return 2;
    }

    // If the first and last characters match
    if (X.charAt(i) == X.charAt(j)) {
      return lpsRec(X, i + 1, j - 1) + 2;
    } else {
      // If the first and last characters do not match
      return Math.max(lpsRec(X, i + 1, j), lpsRec(X, i, j - 1));
    }
  }



}
