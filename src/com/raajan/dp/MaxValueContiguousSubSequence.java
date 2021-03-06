package com.raajan.dp;


/**
 * Find the contiguous sub sequence having the max sum.If all postive the it will be full array.If
 * it cpntains the negativenumber then it can be any where in between two.
 * 
 * @author raajan
 *
 */
public class MaxValueContiguousSubSequence {

  public static void main(String[] args) {
    int[] intputArray = {-2, 11, -4, 13, -5, 2};
    int[] intputArray1 = {1, -3, 4, -2, -1, 6};

    System.out.println(maxContiguousSum(intputArray));
    System.out.println(maxContiguousSumDP(intputArray));
    System.out.println(maxSumWithNoTwoContiguous(intputArray1));
  }

  /**
   * Time complexity O(N^2)
   * Space complexity O(N)
   * 
   * @param inputArray
   * @return
   */
  public static int maxContiguousSum(int[] inputArray) {
    int maxSum = 0;
    int start = -1;
    int end = -1;
    for (int i = 0; i < inputArray.length; i++) {
      int currentSum = 0;
      for (int j = i; j < inputArray.length; j++) {
        currentSum = currentSum + inputArray[j];
        if (maxSum < currentSum) {
          maxSum = currentSum;
          start = i;
          end = j;
        }
      }
    }
    System.out.println("SubarrayIndexes [" + start + ", " + end + "]");
    return maxSum;
  }

  /**
   * Lets assume M[i] is max sum among all the windows ending at A[i]. Then it can be written as
   * following. M[i] = Max{ M[i]+A[i], A[i]}
   * Time complexity O(N)
   * Space complexity O(N)
   * 
   * @param inputArray
   * @return
   */
  public static int maxContiguousSumDP(int[] inputArray) {
    int maxSum = 0;
    int currentSum = 0;
    int start = -1;
    int end = -1;
    for (int i = 0; i < inputArray.length; i++) {
      // currentSum = Math.max(currentSum + inputArray[i], inputArray[i]);
      if (currentSum + inputArray[i] > inputArray[i]) {
        currentSum = currentSum + inputArray[i];
      } else {
        currentSum = inputArray[i];
        start = i;
      }
      if (currentSum > maxSum) {
        end = i;
        maxSum = currentSum;
      }
    }
    System.out.println("SubarrayIndexes [" + start + ", " + end + "]");
    return maxSum;
  }

  /**
   * Lets assume M[i] is max sum without 2 contiguous from 1 to i. Then it can be written as
   * following.
   * M[i] = { Max{ M[i-2]+A[i], M[i-1] ;if n>2
   * Max{ A[0], A[1] }; if n =2
   * A[0] ; if n=1
   * }
   * Time complexity O(N)
   * Space complexity O(N)
   * 
   * @param inputArray
   * @return
   */
  public static int maxSumWithNoTwoContiguous(int[] inputArray) {
    int[] MAX_SUM = new int[inputArray.length];
    if (inputArray.length == 1)
      MAX_SUM[0] = inputArray[0];
    else if (inputArray.length == 2) {
      MAX_SUM[0] = Math.max(inputArray[0], inputArray[1]);
    } else {
      for (int i = 2; i < inputArray.length; i++) {
        MAX_SUM[i] = Math.max(MAX_SUM[i - 1], inputArray[i] + MAX_SUM[i - 2]);
      }
    }
    return MAX_SUM[inputArray.length - 1];
  }
}

