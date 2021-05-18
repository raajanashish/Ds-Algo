package com.raajan.tree.traversal;

import java.util.ArrayList;
import java.util.List;

import com.raajan.dp.CatalanNumber;
import com.raajan.tree.Node;

/**
 * 
 * https://www.geeksforgeeks.org/find-all-possible-trees-with-given-inorder-traversal/
 * 
 * Let given inorder traversal be in[]. In the given traversal, all nodes in
 * left subtree of in[i] must appear before it and in right subtree must appear
 * after it. So when we consider in[i] as root, all elements from in[0] to
 * in[i-1] will be in left subtree and in[i+1] to n-1 will be in right subtree.
 * If in[0] to in[i-1] can form x different trees and in[i+1] to in[n-1] can
 * from y different trees then we will have x*y total trees when in[i] as root.
 * So we simply iterate from 0 to n-1 for root. For every node in[i],
 * recursively find different left and right subtrees. If we take a closer look,
 * we can notice that the count is basically n’th Catalan number. We have
 * discussed different approaches to find n’th Catalan number.
 * 
 * 1) Initialize list of Binary Trees as empty.
 * 
 * 2) For every element in[i] where i varies from 0 to n-1, do following
 * 
 * ......a) Create a new node with key as 'arr[i]', let this node be 'node'
 * 
 * ......b) Recursively construct list of all left subtrees. ` ......c)
 * Recursively construct list of all right subtrees.
 * 
 * 3) Iterate for all left subtrees
 * 
 * ......a) For current leftsubtree, iterate for all right subtrees Add current
 * left and right subtrees to 'node' and add 'node' to list.
 * 
 * 
 * 
 * @author raajan
 *
 */
public class AllBinaryTreesFromInorder {

	public static void main(String[] args) {
		int[] inorder = { 4, 5, 7 };
		List<Node> trees = getAllTheTrees(inorder, 0, inorder.length - 1);

		for (int i = 0; i < trees.size(); i++) {
			System.out.println();
			TreeTreversal.preorder(trees.get(i), new ArrayList<Integer>());
		}

		// verifying the count
		System.out.println("\nUsing Catalan Number : " + CatalanNumber.catalanNumberDP(inorder.length));
		System.out.println("No of Possible Tress : " + getAllPossibleTreesCount(inorder, 0, inorder.length - 1));
	}

	// Function for constructing all possible trees with given inorder traversal
	// stored in an array from arr[start] to arr[end]. This function return list of
	// trees.
	public static List<Node> getAllTheTrees(int[] inorder, int inStart, int inEnd) {
		// List to store all possible trees
		List<Node> trees = new ArrayList<Node>();

		/*
		 * if start > end then subtree will be empty so returning NULL in the list
		 */
		if (inStart > inEnd) {
			trees.add(null);
			return trees;
		}

		/*
		 * Iterating through all values from start to end for constructing left and
		 * right subtree recursively
		 */
		for (int i = inStart; i <= inEnd; i++) {
			/* Constructing left subtree */
			List<Node> leftTress = getAllTheTrees(inorder, inStart, i - 1);
			/* Constructing right subtree */
			List<Node> rightTrees = getAllTheTrees(inorder, i + 1, inEnd);

			/*
			 * Now looping through all left and right subtrees and connecting them to ith
			 * root below
			 */
			for (int l = 0; l < leftTress.size(); l++) {
				for (int r = 0; r < rightTrees.size(); r++) {

					// Making arr[i] as root
					Node root = new Node(inorder[i]);

					// Connecting left subtree
					root.left = leftTress.get(l);

					// Connecting right subtree
					root.right = rightTrees.get(r);

					// Adding this tree to list
					trees.add(root);
				}
			}
		}

		return trees;

	}

	/**
	 * This same as counting the calculating the catalan number.
	 * 
	 * @param inorder
	 * @param inStart
	 * @param inEnd
	 * @return
	 */
	public static int getAllPossibleTreesCount(int[] inorder, int inStart, int inEnd) {

		if (inStart >= inEnd) {
			return 1;
		}

		int count = 0;
		for (int i = inStart; i <= inEnd; i++) {
			count = count + getAllPossibleTreesCount(inorder, inStart, i - 1)
					* getAllPossibleTreesCount(inorder, i + 1, inEnd);
		}
		return count;
	}
}
