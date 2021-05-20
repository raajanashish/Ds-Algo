package com.raajan.tree.checking;

import com.raajan.tree.BinaryTree;
import com.raajan.tree.TreeNode;

/**
 * find if two nodes are siblings.
 * 
 * @author raajan
 *
 */
public class TwoNodesAreSiblings {
	public static void main(String[] args) {
		int[] treeArray = new int[] { 7, 5, 3, 4, 2, 12, 10, 13, 20, 19, 18 };
		TreeNode root = BinaryTree.initialiseTree(treeArray);
		System.out.println(isSibling(root, 10, 13));
	}

	public static boolean isSibling(TreeNode root, int node1, int node2) {
		if (root == null) {
			return false;
		}

		if ((root.left != null && root.right != null) && ((root.left.val == node1 && root.right.val == node2)
				|| (root.left.val == node2 && root.right.val == node2))) {
			return true;
		}
		return isSibling(root.left, node1, node2) || isSibling(root.right, node1, node2);

	}
}
