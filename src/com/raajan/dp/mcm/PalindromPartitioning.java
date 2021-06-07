package com.raajan.dp.mcm;

/**
 * Given a string, a partitioning of the string is a palindrome partitioning if every substring of
 * the partition is a palindrome. For example, “aba|b|bbabb|a|b|aba” is a palindrome partitioning of
 * “ababbbabbababa”. Determine the fewest cuts needed for a palindrome partitioning of a given
 * string. For example, minimum of 3 cuts are needed for “ababbbabbababa”. The three cuts are
 * “a|babbbab|b|ababa”. If a string is a palindrome, then minimum 0 cuts are needed. If a string of
 * length n containing all different characters, then minimum n-1 cuts are needed.
 * 
 * @author raajan
 *
 */
public class PalindromPartitioning {
  private static int[][] T;


  public static void main(String[] args) {
    String s = "abapbpbq";

    T = new int[s.length()][s.length()];
    for (int i = 0; i < s.length(); i++) {
      for (int j = 0; j < s.length(); j++) {
        T[i][j] = -1;
      }
    }

    System.out.println(minPalinPart(s, 0, s.length() - 1));
    System.out.println(minPalinPartMemo(s, 0, s.length() - 1));
  }


  /**
   * // i is the starting index and j is the ending index. i must be passed as 0 and j as n-1
   * minPalPartion(str, i, j) = 0 if i == j. // When string is of length 1.
   * minPalPartion(str, i, j) = 0 if str[i..j] is palindrome.
   * 
   * // If none of the above conditions is true, then minPalPartion(str, i, j) can be
   * // calculated recursively using the following formula.
   * minPalPartion(str, i, j) = Min { minPalPartion(str, i, k) + 1 +
   * minPalPartion(str, k+1, j) }
   * where k varies from i to j-1
   * 
   * @param s
   * @param i
   * @param j
   * @return
   */
  public static int minPalinPart(String s, int i, int j) {
    if (i >= j || isPalindrome(s, i, j)) {
      return 0;
    }

    int result = Integer.MAX_VALUE;
    for (int k = i; k < j; k++) {
      int tempResult = minPalinPart(s, i, k) + minPalinPart(s, k + 1, j) + 1;
      if (tempResult < result) {
        result = tempResult;
      }

    }

    return result;
  }


  public static int minPalinPartMemo(String s, int i, int j) {
    if (i >= j || isPalindrome(s, i, j)) {
      return 0;
    }

    if (T[i][j] != -1) {
      return T[i][j];
    }

    int result = Integer.MAX_VALUE;
    for (int k = i; k < j; k++) {
      int tempResult = minPalinPartMemo(s, i, k) + minPalinPartMemo(s, k + 1, j) + 1;
      if (tempResult < result) {
        result = tempResult;
      }
    }

    T[i][j] = result;
    return result;
  }

  private static boolean isPalindrome(String s, int i, int j) {
    while (i < j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }

      i++;
      j--;
    }

    return true;
  }
}
