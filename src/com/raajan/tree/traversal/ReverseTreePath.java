package com.raajan.tree.traversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.raajan.tree.BinaryTree;
import com.raajan.tree.TreeNode;

/**
 * Given a tree and node data, the task to reverse the path to that particular
 * Node. Link https://www.geeksforgeeks.org/reverse-tree-path/
 * 
 * @author raajan
 *
 */
public class ReverseTreePath {

	public static void main(String[] args) {
		int[] treeArray = { 5, 3, 4, 1, 2, 6, 7 };
		TreeNode root = BinaryTree.initialiseTree(treeArray);
		System.out.println("Inorder before change of tree path : ");
		TreeTreversal.inorder(root, new ArrayList<Integer>());
		reverseTreePath(root, 2);
		System.out.println("\n Inorder after change of tree path : ");
		TreeTreversal.inorder(root, new ArrayList<Integer>());
	}

	public static TreeNode reverseTreePath(TreeNode root, int data) {
		return reverseTreePathUtil(root, data, 0, new AtomicInteger(0), new HashMap<Integer, Integer>());
	}

	private static TreeNode reverseTreePathUtil(TreeNode root, int targetData, Integer currentLevel, AtomicInteger nextLevelPos,
			Map<Integer, Integer> pathMap) {

		if (null == root) {
			return null;
		}

		// Final condition if the node is found then
		if (root.val == targetData) {
			// store the value in it's level
			pathMap.put(currentLevel, root.val);
			// change the root value with the current next element of the map
			root.val = pathMap.get(nextLevelPos.get());
			// increment in nextlevel post for the next element
			nextLevelPos.incrementAndGet();
			return root;
		}

		// store the data in perticular level
		pathMap.put(currentLevel, root.val);

		// We go to right only when left does not contain given data. This way we make
		// sure that correct path node is stored in temp[]
		TreeNode leftNode, rightNode = null;
		leftNode = reverseTreePathUtil(root.left, targetData, currentLevel + 1, nextLevelPos, pathMap);
		if (leftNode == null) {
			rightNode = reverseTreePathUtil(root.right, targetData, currentLevel + 1, nextLevelPos, pathMap);
		}

		// If current node is part of the path, then do reversing.
		if (leftNode != null || rightNode != null) {
			root.val = pathMap.get(nextLevelPos.get());
			nextLevelPos.incrementAndGet();
			return leftNode != null ? leftNode : rightNode;
		}

		// return null if not element found
		return null;
	}
}
