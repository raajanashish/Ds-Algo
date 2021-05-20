package com.raajan.tree.checking;

import com.raajan.tree.Node;

/**
 * From this we can learn how to travel across boundry of tree.
 * @author raajan
 *
 */
public class CheckCoverednUncoveredSum {
	public static void main(String[] args) {

	}

	Node root;

	/* Utility function to calculate sum of all node of tree */
	int sum(Node t) {
		if (t == null)
			return 0;
		return t.data + sum(t.left) + sum(t.right);
	}

	/*
	 * Recursive function to calculate sum of left boundary elements
	 */
	int uncoveredSumLeft(Node t) {
		/* If left node, then just return its data value */
		if (t.left == null && t.right == null)
			return t.data;

		/* If left is available then go left otherwise go right */
		if (t.left != null)
			return t.data + uncoveredSumLeft(t.left);
		else
			return t.data + uncoveredSumLeft(t.right);
	}

	/*
	 * Recursive function to calculate sum of right boundary elements
	 */
	int uncoveredSumRight(Node t) {
		/* If left node, then just return its data value */
		if (t.left == null && t.right == null)
			return t.data;

		/* If right is available then go right otherwise go left */
		if (t.right != null)
			return t.data + uncoveredSumRight(t.right);
		else
			return t.data + uncoveredSumRight(t.left);
	}

	// Returns sum of uncovered elements
	int uncoverSum(Node t) {
		/*
		 * Initializing with 0 in case we don't have left or right boundary
		 */
		int lb = 0, rb = 0;

		if (t.left != null)
			lb = uncoveredSumLeft(t.left);
		if (t.right != null)
			rb = uncoveredSumRight(t.right);

		/*
		 * returning sum of root node, left boundary and right boundary
		 */
		return t.data + lb + rb;
	}

	// Returns true if sum of covered and uncovered elements
	// is same.
	boolean isSumSame(Node root) {
		// Sum of uncovered elements
		int sumUC = uncoverSum(root);

		// Sum of all elements
		int sumT = sum(root);

		// Check if sum of covered and uncovered is same
		return (sumUC == (sumT - sumUC));
	}
}
