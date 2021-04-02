package com.raajan.dp.mcm;

public class MatrixChainMultiplication {
  private static int[][] T;

  public static void main(String[] args) {
    int[] P = {40, 20, 30, 10, 30};
    // int P[] = new int[] { 1, 2, 3, 4 };

    T = new int[P.length][P.length];
    for (int i = 0; i < P.length; i++) {
      for (int j = 0; j < P.length; j++) {
        T[i][j] = -1;
      }
    }

    System.out.println(matricsChainMultiplication(P, 1, P.length - 1));
    System.out.println(mcmMemo(P, 1, P.length - 1));
    System.out.println(matricsChainOrderDp(P));
  }

  /**
   * If parenthesis is put at kth position which means [0, 1, k] is left side of metrics and [k+1,
   * k+2, ... n] is right metrics
   * Here Ai metrics is being represented by P[i-1]P[i]
   * 
   * @param array is arrray representing the metrics chain.
   * @param i index where lowest parenthesis can be put
   * @param j max index at which parenthesis can be put
   * 
   */
  public static int matricsChainMultiplication(int[] array, int i, int j) {

    if (i >= j) {
      return 0;
    }

    int result = Integer.MAX_VALUE;
    for (int k = i; k < j; k++) {
      int tempResult = matricsChainMultiplication(array, i, k)
          + matricsChainMultiplication(array, k + 1, j) + array[i - 1] * array[k] * array[j];
      if (tempResult < result) {
        result = tempResult;
        System.out.print(k + ", ");
      }
    }
    return result;
  }

  /**
   * This top recursive approach using memoisation.WHich is termed as top-down approach in term of
   * DP.
   * 
   * @param array
   * @param i
   * @param j
   * @return
   */

  public static int mcmMemo(int[] array, int i, int j) {

    if (i >= j) {
      return 0;
    }

    if (T[i][j] != -1) {
      return T[i][j];
    }

    int result = Integer.MAX_VALUE;
    for (int k = i; k < j; k++) {
      int tempResult = matricsChainMultiplication(array, i, k)
          + matricsChainMultiplication(array, k + 1, j) + array[i - 1] * array[k] * array[j];
      if (tempResult < result) {
        result = tempResult;
        System.out.print(k + ", ");
      }
    }
    return T[i][j] = result;
  }



  public static int matricsChainOrderDp(int[] P) {
    int[][] matMin = new int[P.length][P.length];
    int[][] bracket = new int[P.length][P.length];
    int n = P.length;
    for (int matLen = 2; matLen < n; matLen++) {
      for (int i = 1; i < n - matLen + 1; i++) {
        int j = i + matLen - 1;

        if (j == n) {
          continue;
        }
        matMin[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
          int q = matMin[i][k] + matMin[k + 1][j] + P[i - 1] * P[k] * P[j];
          if (q < matMin[i][j]) {
            matMin[i][j] = q;
            bracket[i][j] = k;

          }
        }

      }

    }
    printParenthesis(1, P.length - 1, P.length, bracket);
    return matMin[1][n - 1];
  }

  static char name = 'A';

  public static void printParenthesis(int i, int j, int n, int[][] bracket) {
    if (j == i) {
      System.out.print(name++);
      return;
    }

    System.out.print("(");
    printParenthesis(i, bracket[i][j], n, bracket);
    printParenthesis(bracket[i][j] + 1, j, n, bracket);
    System.out.print(")");

  }
}
