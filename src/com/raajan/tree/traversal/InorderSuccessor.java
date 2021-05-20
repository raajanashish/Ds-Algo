package com.raajan.tree.traversal;

import com.raajan.tree.BinaryTree;
import com.raajan.tree.TreeNode;

/**
 * This will give the inorder successor of binary tree.
 * 
 * 
 * Method 1
 * 
 * Get the inorder traversal list of given binary tree and then in inorder
 * traversal next node of X will its inorder successor.
 * 
 * 
 * Method 2
 * 
 * Given root, and node X
 * 
 * 1) Right child of node is not NULL. If the right child of the node is not
 * NULL then the inorder successor of this node will be the leftmost node in
 * it’s right subtree.
 * 
 * 
 * 2) Right Child of the node is NULL. If the right child of node is NULL. Then
 * we keep finding the parent of the given node x, say p such that p->left = x.
 * For example in the above given tree, inorder successor of node 5 will be 1.
 * First parent of 5 is 2 but 2->left != 5. So next parent of 2 is 1, now
 * 1->left = 2. Therefore, inorder successor of 5 is 1.
 * 
 * 
 * Below is the algorithm for this case:
 * 
 * ......Suppose the given node is x. Start traversing the tree from root node
 * to find x recursively.
 *
 * .....If root == x, stop recursion otherwise find x recursively for left and
 * right subtrees.
 * 
 * ......Now after finding the node x, recur­sion will back­track to the root.
 * Every recursive call will return the node itself to the calling function, we
 * will store this in a temporary node say temp.Now, when it back­tracked to its
 * par­ent which will be root now, check whether root.left = temp, if not , keep
 * going up .
 * 
 * 3) If node is the rightmost node. If the node is the rightmost node in the
 * given tree. For example, in the above tree node 6 is the right most node. In
 * this case, there will be no inorder successor of this node. i.e. Inorder
 * Successor of the rightmost node in a tree is NULL.
 * 
 * 
 * @author raajan
 *
 */
public class InorderSuccessor {

	public static void main(String[] args) {
		int[] treeArray = { 4, 2, 1, 3, 5, 6 };
		TreeNode root = BinaryTree.initialiseTree(treeArray);
		TreeNode x = new TreeNode(3);
		inorderSuccessor(root, x);
	}

	public static TreeNode inorderSuccessor(TreeNode root, TreeNode x) {

		if (x.right != null) {
			return leftMostNode(root.right);
		} else if (x.right == null) {
			TreeNode rightMostNode = rightMostNode(root);

			// case3: If x is the right most node
			if (rightMostNode.val == x.val) {
				System.out.print("No inorder successor! Right most node.\n");
				return null;
			} else {
				findInorderRecursive(root, x);
				return temp;
			}
		}

		return null;
	}

	// function to find left most node in a tree
	public static TreeNode leftMostNode(TreeNode root) {

		while (root != null && root.left != null) {
			return root = root.left;
		}
		return root;
	}

	// function to find right most node in a tree
	static TreeNode rightMostNode(TreeNode node) {
		while (node != null && node.right != null)
			node = node.right;
		return node;
	}

	private static TreeNode temp;

	// recursive function to find the Inorder Scuccessor
	// when the right child of node x is null
	static TreeNode findInorderRecursive(TreeNode root, TreeNode x) {
		if (root == null) {
			return null;
		}
		if (root.val == x.val || (temp = findInorderRecursive(root.left, x)) != null
				|| (temp = findInorderRecursive(root.right, x)) != null) {
			if (temp != null) {
				if (root.left.val == temp.val) {
					System.out.print("Inorder Successor of " + x.val);
					System.out.print(" is " + root.val + "\n");
					// This is the required parent, inorder successor of x
					return null;
				}
			}
			return root;
		}
		return null;
	}
}
