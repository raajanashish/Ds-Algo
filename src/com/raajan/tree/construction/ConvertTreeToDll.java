package com.raajan.tree.construction;

import com.raajan.tree.TreeNode;

/**
 * https://www.geeksforgeeks.org/in-place-convert-a-given-binary-tree-to-doubly-linked-list/
 * 
 * @author raajan
 * 
 *         Approach :: This can also be done using traversaling inorder and and
 *         build the doubly linked list
 *
 */
public class ConvertTreeToDll {
	
	
	TreeNode root;

	// head --> Pointer to head node of created doubly linked list
	TreeNode head;

	// Initialize previously visited node as NULL. This is
	// static so that the same value is accessible in all recursive
	// calls
	static TreeNode prev = null;

	// A simple recursive function to convert a given Binary tree
	// to Doubly Linked List
	// root --> Root of Binary Tree
	void BinaryTree2DoubleLinkedList(TreeNode root) {
		// Base case
		if (root == null)
			return;

		// Recursively convert left subtree
		BinaryTree2DoubleLinkedList(root.left);

		// Now convert this node
		if (prev == null)
			head = root;
		else {
			root.left = prev;
			prev.right = root;
		}
		prev = root;

		// Finally convert right subtree
		BinaryTree2DoubleLinkedList(root.right);
	}
	
	
	private void mian() {
		// TODO Auto-generated method stub

	}
}
