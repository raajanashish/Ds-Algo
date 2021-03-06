package com.raajan.dp;

/**
 * It can also be expressed as number of binary search tree from N number of nodes.
 * Formula can be expressed AS Cn = SUM from i=1-->n (Ci)*(Cn-i-1)
 * It can be also term as b inomaila formula
 * 
 * Cn = (2nCn)/(n+1) = (2n)!/(n!*n+1!)
 * 
 * @author raajan
 *         Simaliar problem
 *         1 : Count the number of expressions containing n pairs of parentheses which are correctly
 *         matched. For n = 3, possible expressions are ((())), ()(()), ()()(), (())(), (()()).
 *         2 :Count the number of possible Binary Search Trees with n keys (See this)
 *         3 :Count the number of full binary trees (A rooted binary tree is full if every vertex
 *         has either two children or no children) with n+1 leaves.
 *         4 :Given a number n, return the number of ways you can draw n chords in a circle with 2 x
 *         n points such that no 2 chords intersect.
 *
 */
public class CatalanNumber {

  public static void main(String[] args) {
    int n = 6;
    System.out.println(catalanNumber(n));
    System.out.println(catalanNumberDP(n));
  }

  /**
   * Rersursive solution based
   * TimeComplexity O(4^n)
   * Space Complexity O(1)
   * 
   * @param n
   * @return
   */
  public static int catalanNumber(int n) {
    if (n == 0) {
      return 1;
    }
    int result = 0;
    for (int i = 0; i < n; i++) {
      result = result + catalanNumber(i) * catalanNumber(n - i - 1);
    }
    return result;
  }

  public static int catalanNumberDP(int n) {
    int[] catTable = new int[n + 1];
    catTable[0] = 1;
    catTable[1] = 1;
    for (int i = 2; i <= n; i++) {
      catTable[i] = 0;
      for (int j = 0; j < i; j++) {
        catTable[i] = catTable[i] + catTable[j] * catTable[i - j - 1];
      }
    }
    return catTable[n];

  }
}
