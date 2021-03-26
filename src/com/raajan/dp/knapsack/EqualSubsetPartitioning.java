package com.raajan.dp.knapsack;

public class EqualSubsetPartitioning {

  public static void main(String[] args) {
    int[] inputArray = {3, 2, 4};
    int sum1 = 0;
    int sum2 = 0;
    System.out.println(equalSubsetPartition(inputArray, sum1, sum2, inputArray.length));
  }


  public static boolean equalSubsetPartition(int[] inputArray, int sum1, int sum2, int n) {
    if (n ==0 && sum1==sum2) {
      return true;
    } else if (n==0) {
      return false;
    } else {
      return equalSubsetPartition(inputArray, sum1 + inputArray[n - 1], sum2,
          n - 1) == equalSubsetPartition(inputArray, sum1, sum2 + inputArray[n - 1], n - 1);

    }

  }
}
