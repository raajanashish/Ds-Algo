package com.raajan.dp.mcm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings of equal length (say n+1), S1[0…n] and S2[0…n]. If S2 is a scrambled form of
 * S1, then there must exist an index i such that at least one of the following conditions is true:
 * 
 * 
 * S2[0…i] is a scrambled string of S1[0…i] and S2[i+1…n] is a scrambled string of S1[i+1…n].
 * S2[0…i] is a scrambled string of S1[n-i…n] and S2[i+1…n] is a scrambled string of S1[0…n-i-1].
 * 
 * @author raajan
 *
 */
public class ScrambledString {
  private static Map<String, Boolean> map = new HashMap<String, Boolean>();

  public static void main(String[] args) {
    String X = "coder";
    String Y = "ocred";
    // String Y="oderc";
    System.out.println(isScraambled(X, Y));
    System.out.println(isScraambledMemo(X, Y));
    
  }


  public static boolean isScraambled(String X, String Y) {
    if (X.length() != Y.length()) {
      return false;
    }
    // Empty strings are scramble strings
    if (X.length() == 0) {
      return true;
    }


    // Equal strings are scramble strings
    if (X.equals(Y)) {
      return true;
    }


    // Converting string to
    // character array
    char[] tempArray1 = X.toCharArray();
    char[] tempArray2 = Y.toCharArray();

    // Checking condition for Anagram
    Arrays.sort(tempArray1);
    Arrays.sort(tempArray2);

    String copy_S1 = new String(tempArray1);
    String copy_S2 = new String(tempArray2);

    if (!copy_S1.equals(copy_S2)) {
      return false;
    }

    int n = X.length();
    for (int i = 1; i < X.length(); i++) {
      // Check if Y[0...i] is a scrambled
      // string of X[0...i] and if Y[i+1...n]
      // is a scrambled string of X[i+1...n]

      if (isScraambled(X.substring(0, i), Y.substring(0, i))
          && isScraambled(X.substring(i, n), Y.substring(i, n))) {
        return true;
      }

      if (isScraambled(X.substring(0, i), Y.substring(n - i, n))
          && isScraambled(X.substring(i, n), Y.substring(0, n - i))) {
        return true;
      }

      // if (isScraambled(X.substring(n - i, n), Y.substring(0, i))
      // && isScraambled(X.substring(0, n - i), Y.substring(i, n))) {
      // return true;
      // }

    }
    // If none of the above
    // conditions are satisfied
    return false;

  }


  public static boolean isScraambledMemo(String X, String Y) {
    if (X.length() != Y.length()) {
      return false;
    }
    // Empty strings are scramble strings
    if (X.length() == 0) {
      return true;
    }


    // Equal strings are scramble strings
    if (X.equals(Y)) {
      return true;
    }


    // Converting string to
    // character array
    char[] tempArray1 = X.toCharArray();
    char[] tempArray2 = Y.toCharArray();

    // Checking condition for Anagram
    Arrays.sort(tempArray1);
    Arrays.sort(tempArray2);

    String copy_S1 = new String(tempArray1);
    String copy_S2 = new String(tempArray2);

    if (!copy_S1.equals(copy_S2)) {
      return false;
    }
    String key = X + "_" + Y;
    if (map.containsKey(key)) {
      return map.get(key);
    }
    int n = X.length();

    for (int i = 1; i < X.length(); i++) {
      // Check if Y[0...i] is a scrambled
      // string of X[0...i] and if Y[i+1...n]
      // is a scrambled string of X[i+1...n]


      if (isScraambled(X.substring(0, i), Y.substring(0, i))
          && isScraambled(X.substring(i, n), Y.substring(i, n))) {
        map.put(key, true);
        return true;
      }

      if (isScraambled(X.substring(0, i), Y.substring(n - i, n))
          && isScraambled(X.substring(i, n), Y.substring(0, n - i))) {
        map.put(key, true);
        return true;
      }

      // if (isScraambled(X.substring(n - i, n), Y.substring(0, i))
      // && isScraambled(X.substring(0, n - i), Y.substring(i, n))) {
      // return true;
      // }

    }
    // If none of the above
    // conditions are satisfied
    map.put(key, false);
    return false;

  }

}
