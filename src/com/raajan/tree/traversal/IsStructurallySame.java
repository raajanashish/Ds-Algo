package com.raajan.tree.traversal;

import java.util.ArrayList;
import java.util.List;

import com.raajan.tree.BinaryTree;
import com.raajan.tree.TreeNode;

/**
 * A tree can be folded if left and right subtrees of the tree are structure
 * wise mirror image of each other. An empty tree is considered as foldable.
 * 
 * 
 * Method 1 https://www.geeksforgeeks.org/foldable-binary-trees/
 * 
 * 1) If tree is empty, then return true.
 * 
 * 2) Convert the left subtree to its mirror image mirror(root->left);
 * 
 * 3) Check if the structure of left subtree and right subtree is same and store
 * the result. res = isStructSame(root->left, root->right); // isStructSame()
 * recursively compares structures of two subtrees and returns true if
 * structures are same
 * 
 * 4) Revert the changes made in step (2) to get the original tree.
 * mirror(root->left); 5) Return result res stored in step 2
 * 
 * @author raajan
 *
 */
public class IsStructurallySame {

	public static void main(String[] args) {
		// int[] treeArray = new int[] {25, 12, 15, 13, 30, 28, 29};
		int[] treeArray = new int[] { 25, 12, 15, 13, 35, 28, 29, 30, 14 };
		TreeNode root = BinaryTree.initialiseTree(treeArray);

		List<Integer> inorder = new ArrayList<Integer>();
		System.out.println(isFoldable(root));

		// checking the other info=
		mirror(root);
		TreeTreversal.inorder(root, inorder);
		System.out.println(inorder.toString());// which should in decreasing order
	}

	public static boolean isFoldable(TreeNode root) {
		if (root == null) {
			return true;
		}
		// convert left tree to its mirror image
		mirror(root.left);

		// if current left sub tree (which is mirror image of original one) and right
		// sub
		// tree are structurallys
		// same which means tree is foldable.
		boolean isFoldable = isStructSame(root.left, root.right);

		// reseting the left sub tree
		mirror(root.left);

		return isFoldable;
	}

	/**
	 * It will compare and return if two tree are structurally same.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isStructSame(TreeNode a, TreeNode b) {
		if (a == null && b == null) {
			return true;
		}

		if (a != null && b != null && isStructSame(a.left, b.left) && isStructSame(a.right, b.right)) {
			return true;
		}
		return false;
	}

	/**
	 * It will convert the tree to its mirror image.
	 * 
	 * @param root
	 */
	public static void mirror(TreeNode root) {

		if (root == null) {
			return;
		}

		mirror(root.left);
		mirror(root.right);

		TreeNode temp;
		temp = root.left;
		root.left = root.right;
		root.right = temp;
	}

}
