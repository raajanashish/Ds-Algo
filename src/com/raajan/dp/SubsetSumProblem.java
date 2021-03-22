package com.raajan.dp;

public class SubsetSumProblem {



  public static void main(String[] args) {
    int[] inputArray = {2, 5, 4, 7};
    int sum = 7;
    System.out.println(isSubsetsum(inputArray, sum, inputArray.length));
    System.out.println(subsetsumDP(inputArray, sum, inputArray.length));
    System.out.println(equalSubsetPartitioning(inputArray, inputArray.length));

  }


  public static boolean isSubsetsum(int[] inputArray, int sum, int n) {

    if ( n == 0) {
      return false;
    } else if (sum == 0) {
      return true;
    } 
    if(inputArray[n-1]>sum)
      {
      return isSubsetsum(inputArray, sum, n-1);
      }else {
      return isSubsetsum(inputArray, sum, n - 1)
          || isSubsetsum(inputArray, sum - inputArray[n - 1], n - 1);
    }

  }

  public static boolean subsetsumDP(int[] inputArray, int sum, int n) {
    boolean[][] subset = new boolean[n + 1][sum + 1];

    // If sum is 0, then answer is true
    for (int i = 0; i <= n; i++)
      subset[i][0] = true;


    for (int i = 1; i <= n; i++) {
      for (int s = 0; s <= sum; s++) {
        if (s > inputArray[i - 1]) {
          subset[i][s] = subset[i - 1][s] || subset[i-1][s - inputArray[i - 1]];
        } else {
          subset[i][s] = subset[i-1][s];
        }
      }
    }
    return subset[n][sum];

  }



  public static final boolean equalSubsetPartitioning(int[] inputArray, int n) {
    int sum = 0;
    for (int i = 0; i < inputArray.length; i++) {
      sum += inputArray[i];
    }

    if (sum % 2 == 1) {
      return false;
    }
    return isSubsetsum(inputArray, sum / 2, n);
  }

}
