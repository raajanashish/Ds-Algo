package com.raajan.tree;

public class TreeNode {
	public TreeNode left;
	public TreeNode right;
	public Integer val;
	public TreeNode parent;
	public Character charVal;

	public TreeNode(int value) {
		this.val = value;
	}

	public TreeNode(char charVal) {
		this.charVal = charVal;
	}

	public TreeNode() {
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}

	public String toString() {

		if (this == null) {
			return null;
		} else if (charVal == null) {
			String lData = left == null ? null : left.val.toString();
			String rData = right == null ? null : right.val.toString();
			return "[" + this.val + " --> " + lData + ", " + rData + "]";
		} else {
			String lData = left == null ? null : left.charVal.toString();
			String rData = right == null ? null : right.charVal.toString();
			return "[" + this.charVal + " --> " + lData + ", " + rData + "]";

		}

	}

}
