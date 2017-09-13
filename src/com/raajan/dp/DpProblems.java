package com.raajan.dp;

public class DpProblems {
   static int[][] lookup;
   static int XL, YL;

   public static void main(String[] args) {

      String X = "ABCBDAB";
      String Y = "BDCABA";

      XL = X.length();
      YL = Y.length();
      System.out.println(lcsRecursion(X, Y));

      lookup = new int[X.length() + 1][Y.length() + 1];
      System.out.println(lcsDP(X, Y));
   }

   public static String lcsRecursion(String X, String Y) {
      int xLength = X.length();
      int yLength = Y.length();

      if (xLength == 0 || yLength == 0)
         return "";
      else if (X.charAt(xLength - 1) == Y.charAt(yLength - 1))
         return lcsRecursion(X.substring(0, xLength - 1), Y.substring(0, yLength - 1))
               + X.charAt(xLength - 1);
      else {
         String a = lcsRecursion(X.substring(0, xLength - 1), Y.substring(0, yLength));
         String b = lcsRecursion(X.substring(0, xLength), Y.substring(0, yLength - 1));
         return a.length() > b.length() ? a : b;
      }
   }

   /**
    * It will return longest common sequence from array of character
    * 
    * @param X
    * @param Y
    * @return
    */
   public static String lcsDP(String X, String Y) {

      for (int i = 0; i < X.length(); i++) {

         for (int j = 0; j < Y.length(); j++) {
            if (X.charAt(i) == Y.charAt(j)) {
               lookup[i + 1][j + 1] = lookup[i][j] + 1;
            } else {
               lookup[i + 1][j + 1] = Math.max(lookup[i][j + 1], lookup[i + 1][j]);
            }
         }
      }


      int i = X.length(), j = Y.length();
      StringBuilder sb = new StringBuilder();
      while (i != 0 && j != 0) {
         if (lookup[i][j] == lookup[i - 1][j])
            i--;
         else if (lookup[i][j] == lookup[i][j - 1])
            j--;
         else {
            sb.append(X.charAt(j));
            i--;
            j--;
         }
      }
      return sb.reverse().toString();
   }
}
