package com.raajan.array;

/**
 * https://www.geeksforgeeks.org/rearrange-array-maximum-minimum-form-set-2-o1-extra-space/
 * 
 * @author raajan
 *
 */
public class MaxMinAlternate {
	// Prints max at first position, min at second
	// position second max at third position, second
	// min at fourth position and so on.
	public static void rearrange(int arr[], int n) {
		// initialize index of first minimum and first
		// maximum element
		int max_idx = n - 1, min_idx = 0;

		// store maximum element of array
		int max_elem = arr[n - 1] + 1;

		// traverse array elements
		for (int i = 0; i < n; i++) {
			// at even index : we have to put
			// maximum element
			if (i % 2 == 0) {
				arr[i] += (arr[max_idx] % max_elem) * max_elem;
				max_idx--;
			}

			// at odd index : we have to put minimum element
			else {
				arr[i] += (arr[min_idx] % max_elem) * max_elem;
				min_idx++;
			}
		}

		// array elements back to it's original form
		for (int i = 0; i < n; i++)
			arr[i] = arr[i] / max_elem;
		
	}
	
	
	
}
