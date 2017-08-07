package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BstTree {

	Node root = initialiseTree();

	public Node initialiseTree() {
		Node root = new Node();
		int[] treeArray = new int[] { 25, 12, 15, 13, 30, 28, 32, 27, 33, 14 };
		root.data = 25;
		Node pointer = root;
		for (int i = 1; i < treeArray.length; i++) {
			Node node = new Node();
			node.data = treeArray[i];
			pointer = root;
			while (null != pointer) {
				if (node.data > pointer.data) {
					if (null == pointer.right) {
						pointer.right = node;
						node.parent = pointer;
						break;
					} else
						pointer = pointer.right;
				} else {
					if (null == pointer.left) {
						pointer.left = node;
						node.parent = pointer;
						break;
					} else
						pointer = pointer.left;
				}
			}
		}
		return root;
	}

	public static void inorder(Node root) {
		if (null == root)
			return;
		inorder(root.left);
		System.out.println(root.data);
		inorder(root.right);

	}

	public static Node getNode(BstTree tree, int key) {
		Node pointer = tree.root;
		while (null != pointer) {
			if (key == pointer.data)
				return pointer;
			else if (key > pointer.data) {
				pointer = pointer.right;
			} else
				pointer = pointer.left;
		}
		return null;
	}

	public static Node successor(BstTree tree, int key) {
		Node currentNode = getNode(tree, key);
		if (null != currentNode.right) {
			return getMin(currentNode.right);
		} else {
			Node parent = currentNode.parent;
			while (null != parent.left && parent.left.data != currentNode.data) {
				currentNode = parent;
				parent = parent.parent;
			}
			return parent;
		}
	}

	private static Node getMin(Node root) {
		while (null != root.left)
			root = root.left;
		return root;
	}

	public static void levelOrderTraversal(BstTree tree) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(tree.root);
		queue.offer(null);
		List<Integer> levelList = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			Node currentNode = queue.poll();
			if (null != currentNode) {
				levelList.add(currentNode.data);
				if (null != currentNode.left)
					queue.offer(currentNode.left);
				if (null != currentNode.right)
					queue.offer(currentNode.right);

			} else {
				System.out.println(levelList.toString());
				levelList.clear();
				if (!queue.isEmpty())
					queue.offer(null);
			}
		}
	}

	public void deleteNode(BstTree tree, int dataNode) {
		Node targetNode = getNode(tree, dataNode);
		if (null == targetNode.right && null == targetNode.left) {
			if (targetNode.parent == targetNode.parent.left)
				targetNode.parent.left = null;
			else
				targetNode.parent.right = null;
		} else if (null == targetNode.left) {
			targetNode.parent.right = targetNode.right;
			targetNode.right.parent = targetNode.parent;
		} else if (null == targetNode.right) {
			targetNode.parent.left = targetNode.left;
			targetNode.left.parent = targetNode.parent;
		} else {
			if (null != targetNode.right.left) {
				Node sucessor = successor(tree, targetNode.data);
				sucessor.left = targetNode.left;
				targetNode.left.parent = sucessor;

				sucessor.parent.left = sucessor.right;
				if (null != sucessor.right)
					sucessor.right.parent = sucessor.parent;

				targetNode.parent.right = sucessor;
				sucessor.parent = targetNode.parent;
			}
		}

	}

	public static void main(String[] args) {
		BstTree tree = new BstTree();

		BstTree.inorder(tree.root);
		System.out.println("Succesor");
		Node node = getNode(tree, 30);
		// System.out.println(node.parent.data);
		System.out.println(successor(tree, 15).data);
		System.out.println("Level Order Traversal");
		levelOrderTraversal(tree);
	}
}
