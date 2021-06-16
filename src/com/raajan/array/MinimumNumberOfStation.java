package com.raajan.array;

/**
 * https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/
 * 
 * @author raajan
 *
 */
public class MinimumNumberOfStation {

	public static void main(String[] args) {

	}

	public static int minNoOfStation(int[] arr, int[] dep) {
		int paltformNeed = 1;
		Integer max = Integer.MAX_VALUE;

		for (int i = 0; i < arr.length; i++) {
			int count = 1;
			for (int j = 1 + 1; j < arr.length; j++) {
				if ((arr[j] >= arr[i] && dep[i] >= arr[j]) || arr[j] <= arr[i] && dep[j] >= arr[i]) {
					count++;

				}

			}
			max = Math.max(count, count);
		}

		return max;
	}
}
