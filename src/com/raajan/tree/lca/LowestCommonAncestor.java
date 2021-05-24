package com.raajan.tree.lca;

import com.raajan.tree.TreeNode;

/**
 * 
 * @author raajan
 *
 */
public class LowestCommonAncestor {

	boolean ancestor = false;
	TreeNode lca = null;

	/**
	 * if we have found the ancestor in left sub tree we do not need to travel right
	 * one;
	 * 
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public TreeNode lowestCommonAncestorOptimised(TreeNode root, TreeNode p, TreeNode q) {
		if (ancestor) {
			return lca;
		}
		if (root == null) {
			return null;
		}
		// if we found the node
		if (root.val == p.val || root.val == q.val) {
			return root;
		}

		TreeNode llca = lowestCommonAncestorOptimised(root.left, p, q);
		TreeNode rlca = lowestCommonAncestorOptimised(root.right, p, q);
		// just pass current as we found the common ancestor
		// !ancestor is being check to avoid the corner case when ancestor has ben for
		// in left just above and flow is going to right three now.so to avoid it.
		if (!ancestor & llca != null && rlca != null) {
			lca = root;
			ancestor = true;
			return root;
		}
		// just pass the which is not null
		return llca != null ? llca : rlca;
	}

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}
		// if we found the node
		if (root.val == p.val || root.val == q.val) {
			return root;
		}

		TreeNode llca = lowestCommonAncestor(root.left, p, q);
		TreeNode rlca = lowestCommonAncestor(root.right, p, q);
		// just pass current as we found the common ancestor
		if (llca != null && rlca != null) {
			return root;
		}
		// just pass the which is not null
		return llca != null ? llca : rlca;
	}
}
