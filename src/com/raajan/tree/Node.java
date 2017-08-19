package tree;

public class Node {
	public Node left;
	public Node right;
	public int data;
	public Node parent;

	public Node(int data) {
		this.data = data;
	}

	public Node() {
	}
}
