package com.raajan.tree;

/**
 * This will be used to depict the binary tree.
 * 
 * @author raajan
 *
 */
public class BinaryTree {
  public Node root;


  /**
   * Initialize the tree with given array, if array is null internal array is used.
   * 
   * @param treeArray
   * @return
   */
  public static Node initialiseTree(int[] treeArray) {
    Node root = new Node();
    if (treeArray == null) {
      treeArray = new int[] {25, 12, 15, 13, 30, 28, 32, 27, 33, 14};
    }

    root.data = treeArray[0];
    Node pointer = root;
    for (int i = 1; i < treeArray.length; i++) {
      Node node = new Node(treeArray[i]);
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
}
