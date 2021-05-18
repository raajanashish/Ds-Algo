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
		Integer[] inorder = { 4, 2, 5, 1, 6, 3 };
		Integer[] preorder = { 1, 2, 4, 5, 3, 6 };
		printPostOrderFromPreOrder(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);

		System.out.println();
		levelOrder(root);
		System.out.println();
		levelOrderWithoutQueue(root);

	}

	public static Node getBinaryTreeFromPreOrder(Integer[] inorder, Integer[] preorder) {
		return buildTreeFromPreOrder(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);

	}

	public static Node buildTreeFromPreOrder(Integer[] inorder, int inStart, int inEnd, Integer[] preOrder,
			int preStart, int preEnd) {

		if (inStart > inEnd || preStart > preEnd)
			return null;

		Node root = new Node(preOrder[preStart]);
		int inRootIndex = inStart;
		while (inRootIndex <= inEnd) {
			if (root.data == inorder[inRootIndex])
				break;
			inRootIndex++;
		}
		int leftSubTreeSize = inRootIndex - inStart;
		int rightSubTreSize = inEnd - inRootIndex;
		root.left = buildTreeFromPreOrder(inorder, inStart, inRootIndex - 1, preOrder, preStart + 1,
				preStart + leftSubTreeSize);
		root.right = buildTreeFromPreOrder(inorder, inRootIndex + 1, inEnd, preOrder, preEnd - rightSubTreSize + 1,
				preEnd);
		return root;
	}

	/**
	 * Instead of assignong to left sub tree and right sub tree we need to actually
	 * print the root.
	 * 
	 * @param inorder
	 * @param inStart
	 * @param inEnd
	 * @param preOrder
	 * @param preStart
	 * @param preEnd
	 */
	public static void printPostOrderFromPreOrder(Integer[] inorder, int inStart, int inEnd, Integer[] preOrder,
			int preStart, int preEnd) {

		if (inStart > inEnd || preStart > preEnd)
			return;

		Node root = new Node(preOrder[preStart]);
		int inRootIndex = inStart;
		while (inRootIndex <= inEnd) {
			if (root.data == inorder[inRootIndex])
				break;
			inRootIndex++;
		}
		int leftSubTreeSize = inRootIndex - inStart;
		int rightSubTreSize = inEnd - inRootIndex;
		printPostOrderFromPreOrder(inorder, inStart, inRootIndex - 1, preOrder, preStart + 1,
				preStart + leftSubTreeSize);
		printPostOrderFromPreOrder(inorder, inRootIndex + 1, inEnd, preOrder, preEnd - rightSubTreSize + 1, preEnd);
		System.out.print("  " + root.data);
		;
	}

	public static Node getBinaryTreeFromPostOrder(Integer[] inorder, Integer[] postorder) {
		return buildTreeFromPostOrder(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);

	}

	public static Node buildTreeFromPostOrder(Integer[] inorder, int inStart, int inEnd, Integer[] postOrder,
			int postStart, int postEnd) {

		if (inStart > inEnd || postStart > postEnd)
			return null;

		Node currentNode = new Node(postOrder[postEnd]);
		int rootIndex = inStart;
		while (rootIndex <= inEnd) {
			if (currentNode.data == inorder[rootIndex])
				break;
			rootIndex++;
		}

		int leftSubTreeSize = rootIndex - inStart;
		int rightSubTreSize = inEnd - rootIndex;
		currentNode.left = buildTreeFromPostOrder(inorder, inStart, rootIndex - 1, postOrder, postStart,
				postStart + leftSubTreeSize - 1);
		currentNode.right = buildTreeFromPostOrder(inorder, rootIndex + 1, inEnd, postOrder,
				postStart + leftSubTreeSize, postEnd - 1);
		return currentNode;
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
