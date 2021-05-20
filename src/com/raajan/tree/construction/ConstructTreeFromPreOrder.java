package com.raajan.tree.construction;

import com.raajan.tree.TreeNode;

/**
 * One can build directly print the postorder of the same tree or build the tree
 * from using preorder and inorder.
 * 
 * @author raajan
 *
 */
public class ConstructTreeFromPreOrder {

	public static void main(String[] args) {
		Integer[] inorder = { 4, 2, 5, 1, 6, 3 };
		Integer[] preorder = { 1, 2, 4, 5, 3, 6 };
		printPostOrderFromPreOrder(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);

	}

	public static TreeNode getBinaryTreeFromPreOrder(Integer[] inorder, Integer[] preorder) {
		return buildTreeFromPreOrder(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);

	}

	public static TreeNode buildTreeFromPreOrder(Integer[] inorder, int inStart, int inEnd, Integer[] preOrder,
			int preStart, int preEnd) {

		if (inStart > inEnd || preStart > preEnd)
			return null;

		TreeNode root = new TreeNode(preOrder[preStart]);
		int inRootIndex = inStart;
		while (inRootIndex <= inEnd) {
			if (root.val == inorder[inRootIndex])
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

		TreeNode root = new TreeNode(preOrder[preStart]);
		int inRootIndex = inStart;
		while (inRootIndex <= inEnd) {
			if (root.val == inorder[inRootIndex])
				break;
			inRootIndex++;
		}
		int leftSubTreeSize = inRootIndex - inStart;
		int rightSubTreSize = inEnd - inRootIndex;
		printPostOrderFromPreOrder(inorder, inStart, inRootIndex - 1, preOrder, preStart + 1,
				preStart + leftSubTreeSize);
		printPostOrderFromPreOrder(inorder, inRootIndex + 1, inEnd, preOrder, preEnd - rightSubTreSize + 1, preEnd);
		System.out.print("  " + root.val);
		;
	}

	public static TreeNode getBinaryTreeFromPostOrder(Integer[] inorder, Integer[] postorder) {
		return buildTreeFromPostOrder(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);

	}

	public static TreeNode buildTreeFromPostOrder(Integer[] inorder, int inStart, int inEnd, Integer[] postOrder,
			int postStart, int postEnd) {

		if (inStart > inEnd || postStart > postEnd)
			return null;

		TreeNode currentNode = new TreeNode(postOrder[postEnd]);
		int rootIndex = inStart;
		while (rootIndex <= inEnd) {
			if (currentNode.val == inorder[rootIndex])
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

}
