package com.raajan.tree.checking;

import com.raajan.tree.TreeNode;

/**
 * 
 * https://leetcode.com/problems/subtree-of-another-tree/
 * 
 * Given the roots of two binary trees root and subRoot, return true if there is
 * a subtree of root with the same structure and node values of subRoot and
 * false otherwise.
 * 
 * A subtree of a binary tree tree is a tree that consists of a node in tree and
 * all of this node's descendants. The tree tree could also be considered as a
 * subtree of itself.
 * 
 * @author raajan
 *
 */
public class SubTreeOfAnotherTree {

	public boolean isSubtree(TreeNode root, TreeNode subRoot) {
		if (root == null) {
			return false;
		}

		if (subRoot == null) {
			return true;
		}

		if (isIdentical(root, subRoot)) {
			return true;
		}

		return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
	}

	public boolean isIdentical(TreeNode left, TreeNode right) {
		if (left == null && right == null)
			return true;
		if (left == null || right == null)
			return false;
		return left.val == right.val && (isIdentical(left.left, right.left)) && (isIdentical(left.right, right.right));

	}
}
