package com.raajan.dp.lcs;


/**
 * This will give you longest common sub string which will be continuous .
 * 
 * @author raajan
 *
 */
public class LongestCommonSubString {
  private static int[][] lookup;

  public static void main(String[] args) {
    String X = "OldSite:GeeksforGeeks.org";
    String Y = "NewSite:GeeksQuiz.com";
    //String X = "abcdxyz";
    //String Y = "xyzabcd";
    // System.out.println(lcSubStringRec(X, Y, X.length(), Y.length(), ""));

    System.out.println(lcSubStringDp(X, Y));
  }



  public static String lcSubStringRec(String X, String Y, int m, int n, String lcs) {
    if (m == 0 || n == 0) {
      return "";
    } else if (X.charAt(X.length() - 1) == Y.charAt(Y.length() - 1)) {
      return lcSubStringRec(X, Y, m - 1, n - 1, X.charAt(m - 1) + lcs);
    } else {
      String xLcs = lcSubStringRec(X, Y, m - 1, n, "");
      String yLcs = lcSubStringRec(X, Y, m, n - 1, "");
      return xLcs.length() >= yLcs.length() ? xLcs : yLcs;
    }
  }


  public static int lcSubStringDp(String X, String Y) {
    int m = X.length();
    int n = Y.length();
    int lcsRow = 0;
    int lcsCol = 0;
    lookup = new int[m + 1][n + 1];
    int maxLen = 0;
    for (int i = 0; i <= m; i++) {
      // System.out.println();
      for (int j = 0; j <= n; j++) {
        if (i == 0 || j == 0) {
          lookup[i][j] = 0;
        } else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
          lookup[i][j] = lookup[i - 1][j - 1] + 1;
          if (lookup[i][j] > maxLen) {
            maxLen = lookup[i][j];
            lcsRow = i;
            lcsCol = j;

          }

        } else {
          lookup[i][j] = 0;
        }
        // System.out.print(lookup[i][j] + " ");

      }
    }
    System.out.println(printLcs(X, lookup, lcsRow, lcsCol));
    return maxLen;
  }

  public static String printLcs(String X, int[][] lookup, int lcsRow, int lcsCol) {
    StringBuilder lcs = new StringBuilder();
    while (lookup[lcsRow][lcsCol] > 0) {
      lcs.append(X.charAt(lcsRow - 1));
      lcsCol--;
      lcsRow--;
    }
    return lcs.reverse().toString();
  }

}
