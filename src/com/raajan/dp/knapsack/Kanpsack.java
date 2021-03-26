package com.raajan.dp.knapsack;

public class Kanpsack {


  public static void main(String[] args) {
    int value[] = new int[] {1000, 400, 300};
    int weight[] = new int[] {10, 20, 30};
    int wMax = 50;
    int n = value.length;
    System.out.println(knapsack(n, wMax, weight, value));
    System.out.println(knapsackDP(n, wMax, weight, value));
    System.out.println(knapsackUnbounded(n, wMax, weight, value));
    System.out.println(knapsackUnboundedDp(n, wMax, weight, value));

  }

  public static int knapsack(int n, int wMax, int[] weight, int[] value) {
    if (n <= 0 || wMax <= 0) {
      return 0;
    }
    return Math.max(knapsack(n - 1, wMax, weight, value),
        value[n - 1] + knapsack(n - 1, wMax - weight[n - 1], weight, value));
  }


  public static int knapsackDP(int n, int wMax, int[] weight, int[] value) {
    int[][] knapsack = new int[n + 1][wMax + 1];

    for (int i = 1; i <= n; i++) {
      for (int w = 1; w <= wMax; w++) {
        if (weight[i - 1] > w) {
          knapsack[i][w] = knapsack[i - 1][w];
        } else {
          knapsack[i][w] =
              Math.max(value[i - 1] + knapsack[i - 1][w - weight[i - 1]], knapsack[i - 1][w]);
        }
      }
    }
    return knapsack[n][wMax];
  }

  /**
   * The only twik that need to do is just take element every time.
   * 
   * @param n
   * @param wMax
   * @param weight
   * @param value
   * @return
   */
  public static int knapsackUnboundedDp(int n, int wMax, int[] weight, int[] value) {
    int[][] knapsack = new int[n + 1][wMax + 1];
    // for no weight and no value it has already been assigned to zero
    for (int i = 1; i <= n; i++) {
      for (int w = 1; w <= wMax; w++) {
        if (weight[i - 1] > w) {
          knapsack[i][w] = knapsack[i - 1][w];
        } else {
          knapsack[i][w] =
              Math.max(value[i - 1] + knapsack[i][w - weight[i - 1]], knapsack[i - 1][w]);
        }
      }
    }

    return knapsack[n][wMax];
  }

  public static int knapsackUnbounded(int n, int wMax, int[] weight, int[] value) {
    if (wMax <= 0 || n <= 0) {
      return 0;
    }
    int maxValue = Integer.MIN_VALUE;
    for (int i = 1; i <= n; i++) {
      maxValue =
          Math.max(maxValue, knapsack(n, wMax - weight[i - 1], weight, value) + value[i - 1]);
    }

    return maxValue;

  }

}
