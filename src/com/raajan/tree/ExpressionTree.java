package com.raajan.tree;

import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/expression-tree/
 * 
 * The expression tree is a binary tree in which each internal node corresponds
 * to the operator and each leaf node corresponds to the operand
 * 
 * Infix Expression :: Inorder traversal of expression tree
 * 
 * Postfix Expression :: Post order traversal of expression tree
 * 
 * 
 * Evaluating the expression represented by an expression tree:
 * 
 * Let t be the expression tree
 * 
 * If t is not null then If t.value is operand then Return t.value
 * 
 * A =solve(t.left);
 * 
 * B = solve(t.right)
 * 
 * // calculate applies operator 't.value' on A and B, and returns value
 * 
 * Return calculate(A, B, t.value)
 * 
 * 
 * 
 * Construction of Expression Tree:
 * 
 * Now For constructing an expression tree we use a stack. We loop through input
 * expression and do the following for every character.
 * 
 * If a character is an operand push that into the stack
 * 
 * If a character is an operator pop two values from the stack make them its
 * child and push the current node again.
 * 
 * @author raajan
 *
 */
public class ExpressionTree {

	public static void main(String[] args) {
		char[] charArray = { '6', '5', '+', '3', '2', '*', '4', '*', '-' };
		Node root = constructExpressionTree(charArray);
		System.out.println(evaluateExep(root));
		
	}

	public static Node constructExpressionTree(char[] postfixExpression) {
		Stack<Node> st = new Stack<Node>();
		Node r, left, right;

		for (int i = 0; i < postfixExpression.length; i++) {
			if (!isOperator(postfixExpression[i])) {
				r = new Node(postfixExpression[i]);
				st.push(r);
			} else {
				r = new Node(postfixExpression[i]);

				right = st.pop();
				left = st.pop();
				r.right = right;
				r.left = left;
				st.push(r);
			}

		}

		r = st.peek();
		st.pop();
		return r;

	}

	public static Boolean isOperator(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
			return true;
		}
		return false;
	}

	public static int evaluateExep(Node root) {

		if (root == null) {
			return 0;
		}

		if (!isOperator(root.charData)) {
			return Character.getNumericValue(root.charData);
		} else {
			int leftResult = evaluateExep(root.left);
			int rightResult = evaluateExep(root.right);

			char operator = root.charData;

			if ('+' == operator) {
				return leftResult + rightResult;
			} else if ('-' == operator) {
				return leftResult - rightResult;
			} else if ('*' == operator) {
				return leftResult * rightResult;
			} else {
				return leftResult / rightResult;
			}
		}
	}

}
