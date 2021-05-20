package com.raajan.tree.traversal;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.raajan.tree.BstTree;
import com.raajan.tree.TreeNode;

/**
 * Inorder, Preorder, Postorder Build Tree using Inorder and Preorder Build Tree
 * using Inorder and Postorder
 * 
 * @author RaJaN
 *
 */
public class TreeTreversal {

	public static void main(String[] args) {
		TreeNode root = new BstTree().initialiseTree();

		/*
		 * List<Integer> inrder = new ArrayList<>(); inorder(root, inrder);
		 * System.out.println(inrder);
		 * 
		 * List<Integer> preorder = new ArrayList<>(); postorder(root, preorder);
		 * System.out.println(preorder); Node newRoot =
		 * getBinaryTreeFromPostOrder(inrder.toArray(new Integer[inrder.size()]),
		 * preorder.toArray(new Integer[preorder.size()])); inrder.clear();
		 * preorder.clear(); inorder(newRoot, inrder); System.out.println(inrder);
		 * postorder(newRoot, preorder); System.out.println(preorder);
		 */
		
		System.out.println();
		levelOrder(root);
		System.out.println();
		levelOrderWithoutQueue(root);

	}

	public static void inorder(TreeNode root, List<Integer> inorder) {
		if (null == root)
			return;
		inorder(root.left, inorder);
		System.out.print("  " + root.val);
		inorder.add(root.val);
		inorder(root.right, inorder);

	}

	public static void preorder(TreeNode root, List<Integer> preorder) {
		if (null == root)
			return;
		System.out.print("  " + root.val);
		preorder.add(root.val);
		preorder(root.left, preorder);
		preorder(root.right, preorder);

	}

	public static void postorder(TreeNode root, List<Integer> postorder) {
		if (null == root)
			return;
		inorder(root.left, postorder);
		inorder(root.right, postorder);
		postorder.add(root.val);
	}

	public static void inorderWithoutRec(TreeNode root) {
		if (root == null) {
			return;
		}

		Stack<TreeNode> st = new Stack<TreeNode>();
		TreeNode current = root;
		// traverse the tree
		while (current != null || !st.isEmpty()) {
			/*
			 * Reach the left most Node of the curr Node
			 */
			while (current != null) {
				/*
				 * place pointer to a tree node on the stack before traversing the node's left
				 * subtree
				 */
				st.push(current);
				current = current.left;
			}
			/* Current must be NULL at this point */
			current = st.pop();
			System.out.print("  " + current.val);
			/*
			 * we have visited the node and its left subtree. Now, it's right subtree's turn
			 */
			current = current.right;
		}

	}

	public static TreeNode leaseCommonAncestor(TreeNode root, int data1, int data2) {
		TreeNode leftLca, rightLca = null;

		if (root == null)
			return null;
		// if any of node matches then return because no need to travel beyond
		if (data1 == root.val || data2 == root.val)
			return root;

		leftLca = leaseCommonAncestor(root.left, data1, data2);
		rightLca = leaseCommonAncestor(root.right, data1, data2);

		if (null != leftLca && null != rightLca)
			return root;

		return null != leftLca ? leftLca : rightLca;
	}

	public static void levelOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			TreeNode currentNode = queue.poll();
			System.out.print("  " + currentNode.val);
			if (currentNode.left != null) {
				queue.add(currentNode.left);
			}
			if (currentNode.right != null) {
				queue.add(currentNode.right);
			}

		}
	}

	public static void levelOrderWithoutQueue(TreeNode root) {
		if (root == null)
			return;
		int height = height(root);

		for (int h = 1; h <= height; h++) {
			printLevelAtHeight(root, h);
		}
	}

	public static void printLevelAtHeight(TreeNode root, int level) {
		if (root == null) {
			return;
		}

		if (level == 1) {
			System.out.print("  " + root.val);
		} else {
			printLevelAtHeight(root.left, level - 1);
			printLevelAtHeight(root.right, level - 1);
		}
	}

	private static int height(TreeNode root) {

		if (root == null) {
			return 0;
		} else {
			/** Compute the height of left sub tree */
			int lheight = height(root.left);
			int rheight = height(root.right);

			if (lheight > rheight) {
				return lheight + 1;
			} else {
				return rheight + 1;
			}
		}

	}
}
