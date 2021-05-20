package com.raajan.tree.checking;

import com.raajan.tree.BinaryTree;
import com.raajan.tree.Node;

/**
 * find the level of given node in a tree.
 * 
 * @author raajan
 *
 */
public class FindLevelOfNode {

	public static void main(String[] args) {
		int[] treeArray = new int[] { 7, 5, 3, 4, 2, 12, 10, 13, 20, 19, 18 };
		Node root = BinaryTree.initialiseTree(treeArray);
		System.out.println(findLevel(root, 18, 0));
	}

	public static int findLevel(Node root, int targetNode, int level) {
		if (root == null) {
			return 0;
		}

		if (root.data == targetNode) {
			return level;
		} else {
			int leftLevel = findLevel(root.left, targetNode, level + 1);
			if (leftLevel == 0) {
				return findLevel(root.right, targetNode, level + 1);
			} else {
				return leftLevel;
			}
		}

	}

}
