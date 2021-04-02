package com.raajan.dp.lcs;

/**
 * This problem is variation of lcs with just one condition.
 * 
 * @author raajan
 *
 */
public class LongestRepeatingSubsequence {


  public static void main(String[] args) {
    String X = "aabebcdd";
    int m = X.length();
    System.out.println(lrs(X, X, m, m));

  }

  /**
   * This problem is just the modification of Longest Common Subsequence problem. The idea is to
   * find the LCS(X, X) where str is the input string with the restriction that when both the
   * characters are same, they shouldn’t be on the same index in the two strings.
   * 
   * @param X
   * @return
   */
  public static String lrs(String X, String Y, int m, int n) {
    if (m == 0 || n == 0) {
      return "";
    }
    if (X.charAt(m - 1) == Y.charAt(n - 1) && m != n) {
      return lrs(X, Y, m - 1, n - 1) + X.charAt(m - 1);
    } else {
      String xlrs = lrs(X, Y, m - 1, n);
      String ylrs = lrs(X, Y, m, n - 1);
      return xlrs.length() >= ylrs.length() ? xlrs : ylrs;
    }
  }

  public static String lrcDp(String X, String Y) {
    
    
    
    return "";


  }
}
