package com.raajan.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * @author RaJaN
 * 
 */
public class BstTree {

   TreeNode root = initialiseTree();

   public static TreeNode initialiseTree() {
      TreeNode root = new TreeNode();
      int[] treeArray = new int[] {25, 12, 15, 13, 30, 28, 32, 27, 33, 14};
      root.val = 25;
      TreeNode pointer = root;
      for (int i = 1; i < treeArray.length; i++) {
         TreeNode node = new TreeNode();
         node.val = treeArray[i];
         pointer = root;
         while (null != pointer) {
            if (node.val > pointer.val) {
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

   public static void inorder(TreeNode root) {
      if (null == root)
         return;
      inorder(root.left);
      System.out.println(root.val);
      inorder(root.right);

   }

   public static TreeNode getNode(BstTree tree, int key) {
      TreeNode pointer = tree.root;
      while (null != pointer) {
         if (key == pointer.val)
            return pointer;
         else if (key > pointer.val) {
            pointer = pointer.right;
         } else
            pointer = pointer.left;
      }
      return null;
   }

   public static TreeNode successor(BstTree tree, int key) {
      TreeNode currentNode = getNode(tree, key);
      if (null != currentNode.left) {
         return getMax(currentNode.left);
      } else {
         // node which is right child of its parent
         TreeNode parent = currentNode.parent;
         while (null != parent.right && parent.right.val != currentNode.val) {
            currentNode = parent;
            parent = parent.parent;
         }
         return parent;
      }
   }

   /**
    * Will return successor of given node .. its assuming that that node is already present
    * 
    * @param root
    * @param key
    * @return
    */
   public static TreeNode successorForBst(TreeNode root, int key) {
      if (null == root)
         return null;
      TreeNode left = successorForBst(root.left, key);
      if (null != left)
         return left;
      if (root.val > key)
         return root;
      return successorForBst(root.right, key);
   }

   public static TreeNode predecessor(BstTree tree, int key) {
      TreeNode currentNode = getNode(tree, key);
      if (null != currentNode.right) {
         return getMin(currentNode.right);
      } else {
         // node which is left child of its parent
         TreeNode parent = currentNode.parent;
         while (null != parent.left && parent.left.val != currentNode.val) {
            currentNode = parent;
            parent = parent.parent;
         }
         return parent;
      }
   }

   public static TreeNode getMin(TreeNode root) {
      while (null != root.left)
         root = root.left;
      return root;
   }

   public static TreeNode getMax(TreeNode root) {
      while (null != root.right)
         root = root.right;
      return root;
   }

   public static void levelOrderTraversal(BstTree tree) {
      Queue<TreeNode> queue = new LinkedList<>();
      queue.offer(tree.root);
      queue.offer(null);
      List<Integer> levelList = new ArrayList<Integer>();
      while (!queue.isEmpty()) {
         TreeNode currentNode = queue.poll();
         if (null != currentNode) {
            levelList.add(currentNode.val);
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
      TreeNode targetNode = getNode(tree, dataNode);
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
            TreeNode sucessor = successor(tree, targetNode.val);
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
      // getAllAncestors(tree.root, getNode(tree, 14));
      System.out.println(successorForBst(tree.root, 31).val);
      /*
       * BstTree.inorder(com.raajan.tree.root); System.out.println("Succesor"); Node node =
       * getNode(com.raajan.tree, 30); System.out.println(node.parent.data);
       * System.out.println(successor(com.raajan.tree, 15).data);
       * System.out.println("Level Order Traversal"); levelOrderTraversal(com.raajan.tree);
       */
   }

   /**
    * It will print to get All Ancestor from given node in binary com.raajan.tree not necessary a
    * binary com.raajan.tree
    * 
    * @param root
    * @param targetNode
    * @return
    */
   public static boolean getAllAncestors(TreeNode root, TreeNode targetNode) {
      if (null == root)
         return false;
      if (root.val == targetNode.val) {
         System.out.println(root.val);
         return true;
      }

      if (getAllAncestors(root.left, targetNode)) {
         System.out.println(root.val);
         return true;
      }
      if (getAllAncestors(root.right, targetNode)) {
         System.out.println(root.val);
         return true;
      }
      return false;
   }
   
   
   
}
