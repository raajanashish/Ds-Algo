package com.raajan.tree.checking;

import com.raajan.tree.TreeNode;

/**
 * From this we can learn how to travel across boundry of tree.
 * @author raajan
 *
 */
public class CheckCoverednUncoveredSum {
	public static void main(String[] args) {

	}

	TreeNode root;

	/* Utility function to calculate sum of all node of tree */
	int sum(TreeNode t) {
		if (t == null)
			return 0;
		return t.val + sum(t.left) + sum(t.right);
	}

	/*
	 * Recursive function to calculate sum of left boundary elements
	 */
	int uncoveredSumLeft(TreeNode t) {
		/* If left node, then just return its data value */
		if (t.left == null && t.right == null)
			return t.val;

		/* If left is available then go left otherwise go right */
		if (t.left != null)
			return t.val + uncoveredSumLeft(t.left);
		else
			return t.val + uncoveredSumLeft(t.right);
	}

	/*
	 * Recursive function to calculate sum of right boundary elements
	 */
	int uncoveredSumRight(TreeNode t) {
		/* If left node, then just return its data value */
		if (t.left == null && t.right == null)
			return t.val;

		/* If right is available then go right otherwise go left */
		if (t.right != null)
			return t.val + uncoveredSumRight(t.right);
		else
			return t.val + uncoveredSumRight(t.left);
	}

	// Returns sum of uncovered elements
	int uncoverSum(TreeNode t) {
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
		return t.val + lb + rb;
	}

	// Returns true if sum of covered and uncovered elements
	// is same.
	boolean isSumSame(TreeNode root) {
		// Sum of uncovered elements
		int sumUC = uncoverSum(root);

		// Sum of all elements
		int sumT = sum(root);

		// Check if sum of covered and uncovered is same
		return (sumUC == (sumT - sumUC));
	}
}
