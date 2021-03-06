package com.raajan.dp;

public class LongestCommonSubSequence {

  private static int[][] lookup;

  public static void main(String[] args) {
    String x = "abpcqr";
    String y = "pqabctrr";

    System.out.println(getLCSRec(x, y));
    System.out.println(getLCSDp(x, y));
  }

  public static String getLCSDp(String X, String Y) {
    lookup = new int[X.length() + 1][Y.length() + 1];
    for (int i = 0; i <= X.length(); i++) {
      System.out.println();
      for (int j = 0; j <= Y.length(); j++) {
        if (i == 0 || j == 0) {
          lookup[i][j] = 0;
        } else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
          lookup[i][j] = lookup[i - 1][j - 1] + 1;
        } else {
          lookup[i][j] = Math.max(lookup[i - 1][j], lookup[i][j - 1]);
        }
        System.out.print(lookup[i][j] + "  ");
      }
    }

    StringBuilder lcs = new StringBuilder();
    int x = X.length();
    int y = Y.length();
    while (x > 0 && y > 0) {
      if (lookup[x][y] == lookup[x - 1][y - 1] + 1) {
        lcs.append(X.charAt(x - 1));
        x--;
        y--;
        // System.out.println("[" + x + " ," + y + "]");

      } else if (lookup[x - 1][y] <= lookup[x][y]) {
        x--;
        // System.out.println("[" + x + " ," + y + "]");
      } else {
        y--;
        // System.out.println("[" + x + " ," + y + "]");
      }
    }

    return lcs.reverse().toString();

  }

  public static String getLCSRec(String X, String Y) {
    if (X.length() == 0 || Y.length() == 0) {
      return "";
    } else if (X.charAt(X.length() - 1) == Y.charAt(Y.length() - 1)) {
      return getLCSRec(X.substring(0, X.length() - 1), Y.substring(0, Y.length() - 1))
          + X.charAt(X.length() - 1);
    } else {
      String a = getLCSRec(X.substring(0, X.length() - 1), Y.substring(0, Y.length()));
      String b = getLCSRec(X.substring(0, X.length()), Y.substring(0, Y.length() - 1));
      return a.length() >= b.length() ? a : b;
    }
  }

}
