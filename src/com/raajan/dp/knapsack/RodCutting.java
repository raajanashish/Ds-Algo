package com.raajan.dp.knapsack;

public class RodCutting {

  public static void main(String[] args) {

    int[] price = {1, 5, 8, 9, 10, 17, 17, 20};
    int[] lengthArray = {1, 2, 3, 4, 5, 6, 7, 8};

    System.out.println(rodCut(price, price.length));
    System.out.println(rodCutDp(price, lengthArray));
    System.out.println(cutRodDpSpaceoptimised(price, price.length));
    System.out.println(
        Kanpsack.knapsackUnboundedDp(lengthArray.length, lengthArray.length, lengthArray, price));
  }

  /**
   * This will only work when price is given for every price smaller the original length of rod.
   * 
   * @param price
   * @param len
   * @return
   */
  public static int rodCut(int[] price, int len) {
    if (len <= 0) {
      return 0;
    }
    int maxValue = Integer.MIN_VALUE;
    for (int i = 1; i <= len; i++) {
      maxValue = Math.max(maxValue, price[i - 1] + rodCut(price, len - i));
    }

    return maxValue;
  }

  static int cutRodDpSpaceoptimised(int price[], int n) {
    int cutRod[] = new int[n + 1];
    cutRod[0] = 0;

    // Build the table val[] in bottom up manner and return
    // the last entry from the table
    for (int i = 1; i <= n; i++) {
      int max_val = Integer.MIN_VALUE;
      for (int j = 1; j <= i; j++)
        max_val = Math.max(max_val, price[j - 1] + cutRod[i - j]);
      cutRod[i] = max_val;
    }

    return cutRod[n];
  }

  /**
   * This problem is exactly same as unbounded knapsack problem.
   * 
   * @param priceArray
   * @param lengthArray
   * @return
   */
  public static int rodCutDp(int[] priceArray, int[] lengthArray) {
    int[][] rodCut = new int[priceArray.length + 1][lengthArray.length + 1];

    for (int i = 0; i <= priceArray.length; i++) {
      rodCut[i][0] = 0;
    }

    for (int l = 0; l <= lengthArray.length; l++) {
      rodCut[0][l] = 0;
    }

    for (int i = 1; i <= priceArray.length; i++) {
      for (int l = 1; l <= lengthArray.length; l++) {
        if (l >= lengthArray[i - 1]) {
          rodCut[i][l] =
              Math.max(rodCut[i - 1][l], rodCut[i][l - lengthArray[i - 1]] + priceArray[i - 1]);
        } else {
          rodCut[i][l] = rodCut[i - 1][l];
        }
      }

    }
    return rodCut[priceArray.length][lengthArray.length];
  }

}


