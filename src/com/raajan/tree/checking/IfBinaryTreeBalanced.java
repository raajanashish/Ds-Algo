package com.raajan.tree.checking;

import com.raajan.tree.TreeNode;

/**
 * https://leetcode.com/problems/balanced-binary-tree/submissions/
 * 
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as:
 * 
 * a binary tree in which the left and right subtrees of every node differ in
 * height by no more than 1
 * 
 * @author raajan
 *
 */
public class IfBinaryTreeBalanced {

	public boolean isBalanced(TreeNode root) {
		isBalancedUtil(root);
		return res;
	}

	boolean res = true;

	public int isBalancedUtil(TreeNode root) {
		if (res == false) {
			return 0;
		}
		if (root == null) {
			return 0;
		}

		int lh = isBalancedUtil(root.left);
		int rh = isBalancedUtil(root.right);

		if (Math.abs(lh - rh) > 1) {
			res = false;
		}
		return Math.max(lh, rh) + 1;
	}

}
