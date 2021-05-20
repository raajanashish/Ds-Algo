package com.raajan.tree.traversal;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.raajan.tree.BstTree;
import com.raajan.tree.Node;

/**
 * Inorder, Preorder, Postorder Build Tree using Inorder and Preorder Build Tree
 * using Inorder and Postorder
 * 
 * @author RaJaN
 *
 */
public class TreeTreversal {

	public static void main(String[] args) {
		Node root = new BstTree().initialiseTree();

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

	public static void inorder(Node root, List<Integer> inorder) {
		if (null == root)
			return;
		inorder(root.left, inorder);
		System.out.print("  " + root.data);
		inorder.add(root.data);
		inorder(root.right, inorder);

	}

	public static void preorder(Node root, List<Integer> preorder) {
		if (null == root)
			return;
		System.out.print("  " + root.data);
		preorder.add(root.data);
		preorder(root.left, preorder);
		preorder(root.right, preorder);

	}

	public static void postorder(Node root, List<Integer> postorder) {
		if (null == root)
			return;
		inorder(root.left, postorder);
		inorder(root.right, postorder);
		postorder.add(root.data);
	}

	public static void inorderWithoutRec(Node root) {
		if (root == null) {
			return;
		}

		Stack<Node> st = new Stack<Node>();
		Node current = root;
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
			System.out.print("  " + current.data);
			/*
			 * we have visited the node and its left subtree. Now, it's right subtree's turn
			 */
			current = current.right;
		}

	}

	public static Node leaseCommonAncestor(Node root, int data1, int data2) {
		Node leftLca, rightLca = null;

		if (root == null)
			return null;
		// if any of node matches then return because no need to travel beyond
		if (data1 == root.data || data2 == root.data)
			return root;

		leftLca = leaseCommonAncestor(root.left, data1, data2);
		rightLca = leaseCommonAncestor(root.right, data1, data2);

		if (null != leftLca && null != rightLca)
			return root;

		return null != leftLca ? leftLca : rightLca;
	}

	public static void levelOrder(Node root) {
		if (root == null) {
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			Node currentNode = queue.poll();
			System.out.print("  " + currentNode.data);
			if (currentNode.left != null) {
				queue.add(currentNode.left);
			}
			if (currentNode.right != null) {
				queue.add(currentNode.right);
			}

		}
	}

	public static void levelOrderWithoutQueue(Node root) {
		if (root == null)
			return;
		int height = height(root);

		for (int h = 1; h <= height; h++) {
			printLevelAtHeight(root, h);
		}
	}

	public static void printLevelAtHeight(Node root, int level) {
		if (root == null) {
			return;
		}

		if (level == 1) {
			System.out.print("  " + root.data);
		} else {
			printLevelAtHeight(root.left, level - 1);
			printLevelAtHeight(root.right, level - 1);
		}
	}

	private static int height(Node root) {

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
