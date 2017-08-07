package tree;

import java.util.ArrayList;
import java.util.List;

public class RootToLeafPath {
	public static void getRootToLeafPath(Node node) {
		List<Integer> path = new ArrayList<>();
		path.add(node.data);
		getPaths(node, path);

	}

	private static void getPaths(Node node, List<Integer> path) {
		if (null == node.right && null == node.left) {
			System.out.println(path);
			path.remove(path.get(path.size() - 1));
			return;
		}

		if (null != node.left) {
			path.add(node.left.data);
			getPaths(node.left, path);
		}

		if (null != node.right) {
			path.add(node.right.data);
			getPaths(node.right, path);
		}

		path.remove(path.get(path.size() - 1));

	}

	public static void main(String[] args) {
		Node node = new BstTree().initialiseTree();
		getRootToLeafPath(node);
	}

}
