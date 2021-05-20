package com.raajan.tree.checking;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.raajan.tree.TreeNode;

/**
 * https://leetcode.com/problems/subtree-of-another-tree/
 * 
 * Given the roots of two binary trees root and subRoot, return true if there is
 * a subtree of root with the same structure and node values of subRoot and
 * false otherwise.
 * 
 * A subtree of a binary tree tree is a tree that consists of a node in tree and
 * all of this node's descendants. The tree tree could also be considered as a
 * subtree of itself.
 * 
 * @author raajan
 *
 */
public class MultipleSubtreeExists {

	String splitter = ",";
	String nullVal = "null";

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder ser = new StringBuilder();
		buildString(root, ser);
		return ser.toString();
	}

	// Encodes a tree to a single string.
	public void buildString(TreeNode root, StringBuilder ser) {
		if (root == null) {
			ser.append(nullVal).append(splitter);
		} else {
			ser.append(root.val).append(splitter);
			buildString(root.left, ser);
			buildString(root.right, ser);
		}
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		Deque<String> queue = new LinkedList<>();
		queue.addAll(Arrays.asList(data.split(",")));
		return buildTree(queue);
	}

	public TreeNode buildTree(Deque<String> queue) {
		String val = queue.remove();
		if (val.equals("null")) {
			return null;
		} else {
			TreeNode node = new TreeNode(Integer.parseInt(val));

			node.left = buildTree(queue);
			node.right = buildTree(queue);
			return node;
		}
	}

	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		List<TreeNode> res = new LinkedList<>();
		postorder(root, new HashMap<>(), res);
		return res;
	}

	public String postorder(TreeNode cur, Map<String, Integer> map, List<TreeNode> res) {
		if (cur == null)
			return "#";
		String serial = cur.val + "," + postorder(cur.left, map, res) + "," + postorder(cur.right, map, res);
		map.put(serial, map.getOrDefault(serial, 0) + 1);
		if (map.get(serial) == 2)
			res.add(cur);
		return serial;
	}

}
