package com.raajan.tree.sum;

import java.util.ArrayList;
import java.util.List;

import com.raajan.tree.TreeNode;

/**
 * https://leetcode.com/problems/path-sum-ii/
 * 
 * Given the root of a binary tree and an integer targetSum, return all
 * root-to-leaf paths where each path's sum equals targetSum.
 * 
 * A leaf is a node with no children
 * 
 * @author raajan
 *
 */
public class RootToLeafPathWithTargetSum {

	List<Integer> curPath = new ArrayList<>();
	List<List<Integer>> res = new ArrayList<>();

	public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
		pathSumUtil(root, targetSum, 0);
		return res;
	}

	private void pathSumUtil(TreeNode root, int targetSum, int curSum) {
		if (root == null) {
			return;
		}

		curPath.add(new Integer(root.val));
		curSum = curSum + root.val;
		int size = curPath.size();
		if (root.left == null && root.right == null && curSum == targetSum) {
			List<Integer> tmp = new ArrayList<>();
			tmp.addAll(curPath);
			res.add(tmp);
		} else {
			pathSumUtil(root.left, targetSum, curSum);
			pathSumUtil(root.right, targetSum, curSum);
		}
		curPath.remove(size - 1);
	}

}
