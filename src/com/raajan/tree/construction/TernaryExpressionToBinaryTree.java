package com.raajan.tree.construction;

import java.util.Stack;

import com.raajan.tree.TreeNode;

/**
 * 
 * https://leetcode.com/discuss/interview-question/124826/ternary-expression-to-binary-tree
 * 
 * Let's assume the input ternary expression is valid, we can build the tree in
 * preorder manner.
 * 
 * Each time we scan two characters, the first character is either ? or :, the
 * second character holds the value of the tree node. When we see ?, we add the
 * new node to left. When we see :, we need to find out the ancestor node that
 * doesn't have a right node, and make the new node as its right child.
 * 
 * Time complexity is O(n).
 * 
 * @author raajan
 *
 */

public class TernaryExpressionToBinaryTree {
	public static void main(String[] args) {
		String expression = "a?b?c:d:e";
		char[] expChar = expression.toCharArray();

		TreeNode root = convertUsingStack(expChar);
		System.out.println();

	}

	public static TreeNode convertUsingStack(char[] expr) {
		if (expr.length == 0) {
			return null;
		}

		TreeNode root = new TreeNode(expr[0]);

		Stack<TreeNode> stack = new Stack<>();
		stack.add(root);

		for (int i = 1; i < expr.length; i += 2) {
			TreeNode node = new TreeNode(expr[i + 1]);

			if (expr[i] == '?') {
				stack.peek().left = node;
			}

			if (expr[i] == ':') {
				stack.pop();
				while (stack.peek().right != null) {
					stack.pop();
				}
				stack.peek().right = node;
			}

			stack.push(node);
		}

		return root;
	}

}
