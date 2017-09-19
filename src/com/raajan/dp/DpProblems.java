package com.raajan.dp;

import java.util.ArrayList;
import java.util.List;

public class DpProblems {
   static int[][] lookup;
   static int XL, YL;

   public static void main(String[] args) {

      /*
       * String X = "ABCBDAB"; String Y = "BDCABA";
       * 
       * XL = X.length(); YL = Y.length(); System.out.println(lcsRecursion(X, Y));
       * 
       * lookup = new int[X.length() + 1][Y.length() + 1]; System.out.println(lcsDP(X, Y));
       * 
       * int[] input = {5, 5, 10, 100, 10, 5}; System.out.println(maxContigousSum(input));
       * System.out.println(start + "   " + end); System.out.println(maxSumWithNonContigous(input));
       */



      /*
       * int n = 5; CAT = new int[n + 1]; System.out.println(catalanNumber(n)); //
       * 10,2,5,20,30,25,30 int[] inputMatrix = {3, 100, 2, 2};
       * System.out.println(matrixChainOrder(inputMatrix));
       */

      /*
       * int[] weight = {1, 1, 1 10, 20, 30 }; int[] value = {10, 20, 30 60, 100, 120 }; int Wmax =
       * 2 50 ; System.out.println(knapsackRecursion(weight.length, Wmax, weight, value)); MaxValue
       * = new int[weight.length + 1][Wmax + 1]; System.out.println(knapsackDP(weight.length, Wmax,
       * weight, value));
       */


      /*int[] denomination = {500, 100, 50, 20, 10, 5, 2, 1};
      int value = 18;
      System.out.println(minCoin(denomination, denomination.length, value));
      table = new int[value + 1];
      for (int i = 0; i < table.length; i++)
         table[i] = Integer.MAX_VALUE;;
      System.out.println(minCoinDP(denomination, denomination.length, value));
      System.out.println();*/
	  
      int[] input = { 22, 9, 33, 21, 50, 41, 60, 70, 72, 1,2,3,};
      lisTable = new int[input.length+1];
      System.out.println(" Longest Common Sequence : "+longestIncreasingSequenceDP(input, input.length-1));
      //System.out.println(lis(input, input.length));
   }

	public static String lcsRecursion(String X, String Y) {
		int xLength = X.length();
		int yLength = Y.length();

		if (xLength == 0 || yLength == 0)
			return "";
		else if (X.charAt(xLength - 1) == Y.charAt(yLength - 1))
			return lcsRecursion(X.substring(0, xLength - 1),
					Y.substring(0, yLength - 1))
					+ X.charAt(xLength - 1);
		else {
			String a = lcsRecursion(X.substring(0, xLength - 1),
					Y.substring(0, yLength));
			String b = lcsRecursion(X.substring(0, xLength),
					Y.substring(0, yLength - 1));
			return a.length() > b.length() ? a : b;
		}
	}

   /**
    * It will return longest common sequence from array of character
    * 
    * @param X
    * @param Y
    * @return
    */
   public static String lcsDP(String X, String Y) {

      for (int i = 0; i < X.length(); i++) {

         for (int j = 0; j < Y.length(); j++) {
            if (X.charAt(i) == Y.charAt(j)) {
               lookup[i + 1][j + 1] = lookup[i][j] + 1;
            } else {
               lookup[i + 1][j + 1] = Math.max(lookup[i][j + 1], lookup[i + 1][j]);
            }
         }
      }


      int i = X.length(), j = Y.length();
      StringBuilder sb = new StringBuilder();
      while (i != 0 && j != 0) {
         if (lookup[i][j] == lookup[i - 1][j])
            i--;
         else if (lookup[i][j] == lookup[i][j - 1])
            j--;
         else {
            sb.append(X.charAt(j));
            i--;
            j--;
         }
      }
      return sb.reverse().toString();
   }

   static int start, end;

   public static int maxContigousSum(int[] input) {
      int max = input[0], sum = 0;
      int tempStart = 0;
      for (int i = 0; i < input.length; i++) {
         if (sum + input[i] < input[i]) {
            tempStart = i;
            sum = input[i];
         } else {
            sum = sum + input[i];
         }
         if (sum > max) {
            start = tempStart;
            max = sum;
            end = i;
         }
      }
      return max;
   }

   public static int maxSumWithNonContigous(int[] input) {
      int[] MAX = new int[input.length + 1];
      MAX[0] = input[0];
      MAX[1] = input[0] > input[1] ? input[0] : input[1];
      for (int i = 2; i < input.length; i++) {
         MAX[i] = MAX[i - 1] > MAX[i - 2] + input[i] ? MAX[i - 1] : MAX[i - 2] + input[i];
      }
      return MAX[input.length - 1];
   }

   static int[] CAT;

   public static int catalanNumber(int n) {
      if (CAT[n] != 0)
         return CAT[n];
      for (int i = 1; i <= n; i++) {
         CAT[n] = CAT[n] + catalanNumber(i - 1) + catalanNumber(n - i);
      }
      return CAT[n];
   }


   public static int matrixChainOrder(int[] inputMatrix) { // length 8
      int[][] minCost = new int[inputMatrix.length][inputMatrix.length];
      int[][] multiPlicationPoint = new int[inputMatrix.length][inputMatrix.length];

      for (int chainLength = 2; chainLength < inputMatrix.length; chainLength++) {
         for (int chainStart = 1; chainStart <= inputMatrix.length - chainLength; chainStart++) {
            int chainEnd = chainStart + chainLength - 1;
            minCost[chainStart][chainEnd] = Integer.MAX_VALUE;
            for (int pointer = chainStart; pointer <= chainEnd; pointer++) {
               int currentMultiCost =
                     (minCost[chainStart][pointer] * minCost[pointer + 1][chainEnd])
                           + inputMatrix[chainStart - 1] * inputMatrix[pointer]
                                 * inputMatrix[chainEnd];

               if (currentMultiCost < minCost[chainStart][chainEnd])
                  minCost[chainStart][chainEnd] = currentMultiCost;
               multiPlicationPoint[chainStart][chainEnd] = pointer;
            }
         }
      }
      return minCost[1][inputMatrix.length - 1];
   }



   public static int knapsackRecursion(int N, int Wmax, int[] weight, int[] value) {
      if (N <= 0 || Wmax <= 0)
         return 0;

      return Integer.max(knapsackRecursion(N - 1, Wmax, weight, value),
            knapsackRecursion(N - 1, Wmax - weight[N - 1], weight, value) + value[N - 1]);
   }

   static int[][] MaxValue;

   public static int knapsackDP(int N, int Wmax, int[] weight, int[] value) {

      for (int i = 1; i <= N; i++) {

         for (int w = 0; w <= Wmax; w++) {
            if (i == 0 || w == 0)
               MaxValue[i][w] = 0;
            if (weight[i - 1] <= w)
               MaxValue[i][w] = Integer.max(MaxValue[i - 1][w],
                     MaxValue[i - 1][w - weight[i - 1]] + value[i - 1]);
            else
               MaxValue[i][w] = MaxValue[i - 1][w];
         }
      }
      printSelection(weight.length, Wmax, weight, value);
      return MaxValue[N][Wmax];
   }

   private static void printSelection(int n, int w, int[] weight, int[] value) {
      List<Integer> selected = new ArrayList<>();
      while (n >= 1 && w >= 1) {
         if (MaxValue[n - 1][w - weight[n - 1]] == MaxValue[n][w] - value[n - 1]) {
            selected.add(n - 1);
            w = w - weight[n - 1];
            n--;
         } else
            n--;
      }
      System.out.println("Selected : " + selected.toString());
   }


   public static int minCoin(int[] denomination, int size, int value) {
      if (value == 0)
         return 0;
      int minCoin = Integer.MAX_VALUE;
      for (int i = 0; i < denomination.length; i++) {
         int currentMin;
         if (denomination[i] <= value) {
            currentMin = minCoin(denomination, size, value - denomination[i]);
            if (currentMin != Integer.MAX_VALUE && currentMin + 1 < minCoin)
               minCoin = currentMin + 1;
         }
      }
      return minCoin;
   }
   static int[] table;

   public static int minCoinDP(int[] coins, int noOfDenomination, int V) {
      /*
       * if (value < 0) return -1; else if (value == 0) return 0; if (table[value] != -1) return
       * table[value]; int minNoOfCoins = Integer.MAX_VALUE; for (int i = 0; i <
       * denomination.length; i++) { minNoOfCoins = Integer.min(minNoOfCoins,
       * coinChangeDP(denomination, noOfDenomination, value - denomination[i])); } return
       * table[value] = minNoOfCoins + 1;
       */


      table[0] = 0;
      for (int currentValue = 0; currentValue <= V; currentValue++) {
         for (int coinIndex = 0; coinIndex < coins.length; coinIndex++) {
            if (coins[coinIndex] <= currentValue) {
               int minNoCoins = table[currentValue - coins[coinIndex]];
               table[currentValue] = Integer.min(table[currentValue], minNoCoins + 1);
            }
         }
      }
      return table[V];

   }
   


   static int globalSeqSize=0;
	public static int longestIncreasingSequenceRec(int[] input, int m) {
		if (m == 0)
			return 1;
		int maxSeqUpToHere = 1/* Integer.MIN_VALUE */;

		for (int i = 0; i < m; i++) {
			int res = longestIncreasingSequenceRec(input, i);
			/*if (input[i - 1] < input[m - 1] && res + 1 > maxSeqUpToHere) {
				maxSeqUpToHere = res + 1;
			}*/
			if (input[i] < input[m ] && res + 1 > maxSeqUpToHere)
				maxSeqUpToHere = Integer.max(res+1, maxSeqUpToHere);
		}
		globalSeqSize = Integer.max(maxSeqUpToHere, globalSeqSize);
		return maxSeqUpToHere;
	}

	private static int[] lisTable ;

	public static int longestIncreasingSequenceDP(int[] input, int m) {

		for (int i = 1; i < input.length; i++)
			lisTable[i] = 1;

		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < i; j++) {
				if (input[j] < input[i] && lisTable[i] < lisTable[j] + 1)
					lisTable[i] = lisTable[j] + 1;
			}
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < m; i++)
			if (max < lisTable[i])
				max = lisTable[i];
		printLIS(input);
		return max;
	}
	
	private static void printLIS(int[] input) {
		int maxIndex = -1;
		int max = -1;
		for (int i = 0; i < lisTable.length; i++)
			if (max < lisTable[i]) {
				max = lisTable[i];
				maxIndex = i;
			}
		max = input[maxIndex];
		
		for (int i = maxIndex; i >= 0; i--) {
			if (input[i] <= max) {
				System.out.print("  " + input[i]);
				max = input[i];
			}
		}
	}
	/* To make use of recursive calls, this function must return
	   two things:
	   1) Length of LIS ending with element arr[n-1]. We use
	      max_ending_here for this purpose
	   2) Overall maximum as the LIS may end with an element
	      before arr[n-1] max_ref is used this purpose.
	   The value of LIS of full array of size n is stored in
	   *max_ref which is our final result */
	 static int max_ref=1;
	static int _lis(int arr[], int n)
	   {
	       // base case
	       if (n == 1)
	           return 1;
	 
	       // 'max_ending_here' is length of LIS ending with arr[n-1]
	       int res, max_ending_here = 1;
	 
	        /* Recursively get all LIS ending with arr[0], arr[1] ...
	           arr[n-2]. If   arr[i-1] is smaller than arr[n-1], and
	           max ending with arr[n-1] needs to be updated, then
	           update it */
	        for (int i = 1; i < n; i++)
	        {
	            res = _lis(arr, i);
	            if (arr[i-1] < arr[n-1] && res + 1 > max_ending_here)
	                max_ending_here = res + 1;
	        }
	 
	        // Compare max_ending_here with the overall max. And
	        // update the overall max if needed
	        if (max_ref < max_ending_here)
	           max_ref = max_ending_here;
	 
	        // Return length of LIS ending with arr[n-1]
	        return max_ending_here;
	   }
	 
	    // The wrapper function for _lis()
	    static int lis(int arr[], int n)
	    {
	        // The max variable holds the result
	         max_ref = 1;
	 
	        // The function _lis() stores its result in max
	        _lis( arr, n);
	 
	        // returns max
	        return max_ref;
	    }
	
	
}
