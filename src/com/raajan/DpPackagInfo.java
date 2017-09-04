package com.raajan;

/**
 * 
 * It holds meta info for package {@link com.raajan.dp}<br>
 * <br>
 * <br>
 * 
 * 
 * 
 *
 */
public class DpPackagInfo {


   public static void main(String[] args) {

      System.out.println(lcs("ABCBDAB", "BDCABA"));

   }

   public static String lcs(String X, String Y) {
      int xLength = X.length();
      int yLength = Y.length();

      if (xLength == 0 || yLength == 0)
         return "";
      else if (X.charAt(xLength - 1) == Y.charAt(yLength - 1))
         return lcs(X.substring(xLength - 1), Y.substring(yLength - 1)) + X.charAt(xLength - 1);
      else {
         String a = lcs(X.substring(xLength - 1), Y.substring(yLength));
         String b = lcs(X.substring(xLength), Y.substring(yLength - 1));
         return a.length() > b.length() ? a : b;
      }
   }

}
