package com.raajan.dp.knapsack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This problem is based on unbounded knapsack problem. FInd the number of ways
 * the target amount can be attained using provided coins.
 * 
 * 
 * 
 * @author raajan
 *
 */
public class CoinChange {

	public static void main(String[] args) {
		int[] coins = { 186,419,83,408};
		int sum = 6249;
		System.out.println(coinChangeWaysDp(coins, sum));
		System.out.println(coinChangeWays(coins, coins.length, sum));
		System.out.println(minCoinNeeded(coins, sum));
		System.out.println(minCoinNeededDP(coins, sum));

	}

	public static final int coinChangeWays(int[] coins, int n, int sum) {
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

		return coinChangeWays(coins, n - 1, sum) + coinChangeWays(coins, n, sum - coins[n - 1]);

	}

	public static int coinChangeWaysDp(int[] coins, int sum) {

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

	/**
	 * Min number of coin needed to get amount
	 * 
	 * @param coins
	 * @param n
	 * @param sum
	 * @return
	 */

	static Map<Integer, Integer> map = new HashMap<>();

	public int coinChange(int[] coins, int amount) {
		int minCoinNeeded = minCoinNeeded(coins, amount);
		if (minCoinNeeded == Integer.MAX_VALUE) {
			return -1;
		}
		return minCoinNeeded;
	}

	public static final int minCoinNeeded(int[] coins, int sum) {
		if (sum == 0) {
			return 0;
		}

		if (sum < 0) {
			return Integer.MAX_VALUE;
		}

		if (map.containsKey(sum)) {
			return map.get(sum);
		}

		int minCoin = Integer.MAX_VALUE;

		for (int i = 1; i <= coins.length; i++) {
			int temp = minCoinNeeded(coins, sum - coins[i - 1]);
			if (temp != Integer.MAX_VALUE) {
				minCoin = Math.min(minCoin, temp + 1);
			}
		}
		if (map.containsKey(sum)) {
			return map.get(sum);
		}
		map.put(sum, minCoin);
		return minCoin;
	}

	public static int minCoinNeededDP(int[] coins, int sum) {
		int[][] coinMin = new int[coins.length + 1][sum + 1];

		for (int i = 0; i <= coins.length; i++) {
			Arrays.fill(coinMin[i], Integer.MAX_VALUE);
		}

		for (int i = 0; i <= coins.length; i++) {
			coinMin[i][0] = 0;
		}

		for (int j = 1; j <= coins.length; j++) {
			for (int i = 1; i <= sum; i++) {
				if (coins[j - 1] <= i&& (coinMin[j][i - coins[j - 1]] != Integer.MAX_VALUE)) {
						coinMin[j][i] = Math.min(coinMin[j-1][i], coinMin[j][i - coins[j - 1]] + 1);
					}
				}
			}
		return coinMin[coins.length][sum];
	}

}
