package com.raajan.tree;

public class Node {
	public Node left;
	public Node right;
	public Integer data;
	public Node parent;
	public Character charData;

	public Node(int data) {
		this.data = data;
	}

	public Node(char charData) {
		this.charData = charData;
	}

	public Node() {
	}

	public String toString() {

		if (this == null) {
			return null;
		} else if (charData == null) {
			String lData = left == null ? null : left.data.toString();
			String rData = right == null ? null : right.data.toString();
			return "[" + this.data + " --> " + lData + ", " + rData + "]";
		} else {
			String lData = left == null ? null : left.charData.toString();
			String rData = right == null ? null : right.charData.toString();
			return "[" + this.charData + " --> " + lData + ", " + rData + "]";

		}

	}

}
