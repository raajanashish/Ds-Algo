package com.raajan.dp.knapsack;

/**
 * This problem is based on unbounded knapsack problem.
 * 
 * @author raajan
 *
 */
public class CoinChange {

  public static void main(String[] args) {
    int[] coins = {1, 2, 3, 5};
    int sum = 4;
    System.out.println(coinChangeDp(coins, sum));
    System.out.println(coinChange(coins, coins.length, sum));
  }



  public static final int coinChange(int[] coins, int n, int sum) {
    // If sum is 0 then there is 1 solution
    // (do not include any coin)
    if (sum == 0) {
      return 1;

    }
    // If sum is less than 0 then no
    // solution exists
    if (sum < 0) {
      return 0;
    }
    // If there are no coins and sum
    // is greater than 0, then no
    // solution exist
    if (n <= 0 && sum > 0) {
      return 0;
    }

    return coinChange(coins, n - 1, sum) + coinChange(coins, n, sum - coins[n - 1]);

  }

  public static int coinChangeDp(int[] coins, int sum) {

    int[][] coinChange = new int[coins.length + 1][sum + 1];

    for (int i = 0; i <= coins.length; i++) {
      coinChange[i][0] = 1;
    }

    for (int s = 1; s <= sum; s++) {
      coinChange[0][s] = 0;
    }

    for (int i = 1; i <= coins.length; i++) {
      for (int s = 1; s <= sum; s++) {
        if (s < coins[i - 1]) {
          coinChange[i][s] = coinChange[i - 1][s];
        } else {
          coinChange[i][s] = coinChange[i - 1][s] + coinChange[i][s - coins[i - 1]];
        }
      }
    }


    return coinChange[coins.length][sum];
  }

}
