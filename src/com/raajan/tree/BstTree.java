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

   Node root = initialiseTree();

   public Node initialiseTree() {
      Node root = new Node();
      int[] treeArray = new int[] {25, 12, 15, 13, 30, 28, 32, 27, 33, 14};
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
      if (null != currentNode.left) {
         return getMax(currentNode.left);
      } else {
         // node which is right child of its parent
         Node parent = currentNode.parent;
         while (null != parent.right && parent.right.data != currentNode.data) {
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
   public static Node successorForBst(Node root, int key) {
      if (null == root)
         return null;
      Node left = successorForBst(root.left, key);
      if (null != left)
         return left;
      if (root.data > key)
         return root;
      return successorForBst(root.right, key);
   }

   public static Node predecessor(BstTree tree, int key) {
      Node currentNode = getNode(tree, key);
      if (null != currentNode.right) {
         return getMin(currentNode.right);
      } else {
         // node which is left child of its parent
         Node parent = currentNode.parent;
         while (null != parent.left && parent.left.data != currentNode.data) {
            currentNode = parent;
            parent = parent.parent;
         }
         return parent;
      }
   }

   public static Node getMin(Node root) {
      while (null != root.left)
         root = root.left;
      return root;
   }

   public static Node getMax(Node root) {
      while (null != root.right)
         root = root.right;
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
      // getAllAncestors(tree.root, getNode(tree, 14));
      System.out.println(successorForBst(tree.root, 31).data);
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
   public static boolean getAllAncestors(Node root, Node targetNode) {
      if (null == root)
         return false;
      if (root.data == targetNode.data) {
         System.out.println(root.data);
         return true;
      }

      if (getAllAncestors(root.left, targetNode)) {
         System.out.println(root.data);
         return true;
      }
      if (getAllAncestors(root.right, targetNode)) {
         System.out.println(root.data);
         return true;
      }
      return false;
   }
}
