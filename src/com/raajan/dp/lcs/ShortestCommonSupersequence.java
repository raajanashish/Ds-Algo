package com.raajan.dp.lcs;

/**
 * This can be also be solve by normal lcs pattern.
 * Let say X= "AGGTAB" and Y = "GXTXAYB"
 * scs(X, Y) = m+n - lcs(X,Y) where m = X.length() and n= Y.length()
 * 
 * 
 * @author raajan
 *
 */
public class ShortestCommonSupersequence {
  private static int[][] scs;

  public static void main(String[] args) {
    String X = "AGGTAB";
    String Y = "GXTXAYB";
    // String X = "geek";
    // String Y = "pqw";


    int m = X.length();
    int n = Y.length();

    System.out.println("Scs length using Rec: " + scsRec(X, Y, m, n));
    System.out.println("Scs using Rec: " + scsRecPrint(X, Y, m, n));
    System.out.println("Scs length using Dp: " + scsDP(X, Y, m, n));
    System.out.println("Scs using DP: " + scsDpPrint(X, Y, m, n, scs));
  }

  /**
   * This is a way based on optimal structure of sub problem. which is quite similar to lcs.
   * 
   * @param X
   * @param Y
   * @param m
   * @param n
   * @return
   */
  public static int scsRec(String X, String Y, int m, int n) {
    if (m == 0) {
      return n;
    }
    if (n == 0) {
      return m;
    }

    if (X.charAt(m - 1) == Y.charAt(n - 1)) {
      return scsRec(X, Y, m - 1, n - 1) + 1;
    } else {
      return Math.min(scsRec(X, Y, m - 1, n) + 1, scsRec(X, Y, m, n - 1) + 1);
    }

  }


  public static String scsRecPrint(String X, String Y, int m, int n) {
    if (m == 0) {
      return Y.substring(0, n);
    }
    if (n == 0) {
      return X.substring(0, m);
    }

    if (X.charAt(m - 1) == Y.charAt(n - 1)) {
      return scsRecPrint(X, Y, m - 1, n - 1) + X.charAt(m - 1);
    } else {
      String xScs = scsRecPrint(X, Y, m - 1, n) + X.charAt(m - 1);
      String yScs = scsRecPrint(X, Y, m, n - 1) + Y.charAt(n - 1);
      return xScs.length() < yScs.length() ? xScs : yScs;
    }

  }

  public static int scsDP(String X, String Y, int m, int n) {
    scs = new int[m + 1][n + 1];

    for (int i = 0; i <= m; i++) {

      for (int j = 0; j <= n; j++) {
        // initialisation
        if (0 == i) {
          scs[0][j] = j;
        } else if (0 == j) {
          scs[i][0] = i;
        } else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
          scs[i][j] = scs[i - 1][j - 1] + 1;
        } else {
          scs[i][j] = Math.min(scs[i - 1][j], scs[i][j - 1]) + 1;
        }
      }
    }

    return scs[m][n];
  }

  public static String scsDpPrint(String X, String Y, int m, int n, int[][] scs) {
    String scsString = new String();

    while (m >= 0 && n >= 0) {
      if (m == 0) {
        scsString = Y.substring(0, n) + scsString;
        break;
      }
      if (n == 0) {
        scsString = X.substring(0, m) + scsString;
        break;
      }

      if (X.charAt(m - 1) == Y.charAt(n - 1)) {
        scsString = X.charAt(m - 1) + scsString;
        m--;
        n--;

      } else {
        if (scs[m - 1][n] < scs[m][n - 1]) {
          scsString = X.charAt(m - 1) + scsString;
          m--;
        } else {
          scsString = Y.charAt(n - 1) + scsString;
          n--;
        }
      }
    }

    return scsString;
  }

}
