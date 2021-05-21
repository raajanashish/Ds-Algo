package com.raajan.tree.checking;

import com.raajan.tree.TreeNode;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/submissions/
 * 
 * Given the root of a binary tree, return the length of the diameter of the
 * tree.
 * 
 * The diameter of a binary tree is the length of the longest path between any
 * two nodes in a tree. This path may or may not pass through the root.
 * 
 * The length of a path between two nodes is represented by the number of edges
 * between them.
 * 
 * @author raajan
 *
 */
public class PrintDiameterOfTree {

	int dia = Integer.MIN_VALUE;

	public int diameterOfBinaryTree(TreeNode root) {
		diameterOfBinaryTreeUtil(root);
		return dia;
	}

	public int diameterOfBinaryTreeUtil(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int lh = diameterOfBinaryTreeUtil(root.left);
		int rh = diameterOfBinaryTreeUtil(root.right);
		int height = Math.max(lh, rh) + 1;
		if (lh + rh > dia) {
			dia = lh + rh;
		}

		return height;

	}

}
