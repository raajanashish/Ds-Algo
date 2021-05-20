package com.raajan.tree;

import java.util.LinkedList;
import java.util.Queue;

public class IsFullBinaryTree {
	TreeNode root;

	/* this function checks if a binary tree is full or not */
	boolean isFullTree(TreeNode node) {
		// if empty tree
		if (node == null)
			return true;

		// if leaf node
		if (node.left == null && node.right == null)
			return true;

		// if both left and right subtrees are not null
		// the are full
		if ((node.left != null) && (node.right != null))
			return (isFullTree(node.left) && isFullTree(node.right));

		// if none work
		return false;

	}

	public static boolean isFullBinaryTree(TreeNode root) {

		// if tree is empty
		if (root == null)
			return true;

		// queue used for level order traversal
		Queue<TreeNode> q = new LinkedList<TreeNode>();

		// push 'root' to 'q'
		q.add(root);

		// traverse all the nodes of the binary tree
		// level by level until queue is empty
		while (!q.isEmpty()) {
			// get the pointer to 'node' at front
			// of queue
			TreeNode node = q.peek();
			q.remove();

			// if it is a leaf node then continue
			if (node.left == null && node.right == null)
				continue;

			// if either of the child is not null and the
			// other one is null, then binary tree is not
			// a full binary tee
			if (node.left == null || node.right == null)
				return false;

			// push left and right childs of 'node'
			// on to the queue 'q'
			q.add(node.left);
			q.add(node.right);
		}

		// binary tree is a full binary tee
		return true;
	}

}