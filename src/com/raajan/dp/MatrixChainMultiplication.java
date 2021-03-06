package com.raajan.dp;

import com.sun.xml.internal.ws.server.sei.EndpointResponseMessageBuilder.Bare;

public class MatrixChainMultiplication {

  public static void main(String[] args) {
    int[] P = {40, 20, 30, 10, 30};
    // int P[] = new int[] { 1, 2, 3, 4 };

    System.out.println(matricsChainOrder(P, 1, P.length - 1));
    System.out.println(matricsChainOrderDp(P));
  }

  /**
   * If parenthesis is put at kth position which means [0, 1, k] is left side of metrics and [k+1,
   * k+2, ... n] is right metrics
   * Here Ai metrics is being represented by P[i-1]P[i]
   * 
   * @param P is arrray representing the metrics chain.
   * @param lowerEnd index where lowest parenthesis can be put
   * @param upperEnd max index at which parenthesis can be put
   * 
   */
  public static int matricsChainOrder(int[] P, int lowerEnd, int upperEnd) {

    if (lowerEnd == upperEnd) {
      return 0;
    }
    int minCount = Integer.MAX_VALUE;
    for (int k = lowerEnd; k < upperEnd; k++) {
      int tempResult = matricsChainOrder(P, lowerEnd, k) + matricsChainOrder(P, k + 1, upperEnd)
          + P[lowerEnd - 1] * P[k] * P[upperEnd];
      if (tempResult < minCount) {
        minCount = tempResult;
        System.out.print(k + ", ");
      }
    }
    return minCount;
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
    printParenthesis(1,P.length-1, P.length, bracket);
    return matMin[1][n - 1];
  }

  static char name='A';

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
