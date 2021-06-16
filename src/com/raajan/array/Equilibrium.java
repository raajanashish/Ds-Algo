package com.raajan.array;

/**
 * https://practice.geeksforgeeks.org/problems/equilibrium-point-1587115620/1
 * 
 * Input: A[] = {-7, 1, 5, 2, -4, 3, 0}
 * 
 * Output: 3 3 is an equilibrium index, ss * because: A[0] + A[1] + A[2] = A[4]
 * + A[5] + A[6]
 * 
 * @author raajan
 *
 */
public class Equilibrium {
	int equilibrium(int arr[], int n) {
		int sum = 0; // initialize sum of whole array
		int leftsum = 0; // initialize leftsum

		/* Find sum of the whole array */
		for (int i = 0; i < n; ++i)
			sum += arr[i];

		for (int i = 0; i < n; ++i) {
			sum -= arr[i]; // sum is now right sum for index i

			if (leftsum == sum)
				return i;

			leftsum += arr[i];
		}

		/* If no equilibrium index found, then return 0 */
		return -1;

	}
}