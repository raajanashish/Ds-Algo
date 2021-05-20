package com.raajan.tree.checking;

import com.raajan.tree.TreeNode;

/**
 * https://leetcode.com/problems/symmetric-tree/
 * 
 * Given the root of a binary tree, check whether it is a mirror of itself
 * (i.e., symmetric around its center).
 * 
 * @author raajan
 *
 */
public class IsTreeSymmatric {

	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		return isSymmetricUtil(root.left, root.right);
	}

	public boolean isSymmetricUtil(TreeNode left, TreeNode right) {
		if (left == null || right == null) {
			return left == right;
		}

		if (left.val != right.val) {
			return false;
		}
		return isSymmetricUtil(left.left, right.right) && isSymmetricUtil(left.right, right.left);
	}
}
