package com.raajan.tree.checking;

import java.util.ArrayList;
import java.util.List;

import com.raajan.tree.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-paths/ Given the root of a binary
 * tree, return all root-to-leaf paths in any order.
 * 
 * A leaf is a node with no children.
 * 
 * @author raajan
 *
 */
public class PrintRootToLeafPath {

	List<String> paths = new ArrayList<>();
	public List<String> binaryTreePathsOpt(TreeNode root) {
		List<String> res = new ArrayList<>();
		
		StringBuilder sb = new StringBuilder();
		binaryTreePathsOptUtil(res, root, sb);
		return res;
	}

	private void binaryTreePathsOptUtil(List<String> res, TreeNode root, StringBuilder sb) {
		if (root == null) {
			return;
		}
		int len = sb.length();
		sb.append(root.val);
		if (root.left == null && root.right == null) {
			res.add(sb.toString());
		} else {
			sb.append("->");
			binaryTreePathsOptUtil(res, root.left, sb);
			binaryTreePathsOptUtil(res, root.right, sb);
		}
		sb.setLength(len);
	}

	

	public List<String> binaryTreePaths(TreeNode root) {
		binaryTreePathsUtil(root, "");
		return paths;
	}

	public void binaryTreePathsUtil(TreeNode root, String curPath) {
		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			paths.add(curPath + Integer.toString(root.val));
			return;
		} else {
			curPath = curPath + Integer.toString(root.val) + "->";
			binaryTreePathsUtil(root.left, curPath);
			binaryTreePathsUtil(root.right, curPath);
			return;
		}

	}
}
