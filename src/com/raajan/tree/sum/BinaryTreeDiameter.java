package com.raajan.tree.sum;

import com.raajan.tree.BinaryTree;
import com.raajan.tree.TreeNode;

/**
 * The diameter of a tree (sometimes called the width) is the number of nodes on the longest path
 * between two end nodes, leaf nodes.
 * 
 * @author raajan
 *
 */
public class BinaryTreeDiameter {
  private static int finalDia;

  public static void main(String[] args) {
    //int[] treeArray = new int[] {15, 12, 25, 30, 28, 32, 27, 33, 26, 34, 35, 36, 24, 23};
    int[] treeArray = new int[] {25,10,12,45,40,35,32,31,50,51,52,52};
    TreeNode root = BinaryTree.initialiseTree(treeArray);
    diameter(root);
    System.out.println(diameter(root));
    //System.out.println(finalDia);


  }

  public static int diameter(TreeNode root) {
    // Base condition
    if (root == null) {
      return 0;
    }

    // Hypothesis step
    int lHeihgt = diameter(root.left);
    int rHeight = diameter(root.right);


    // Induction step
    // When current node is root node for dia path
    int currentHeight = 1 + Math.max(lHeihgt, rHeight);


    int tempDia = Math.max(currentHeight, 1 + lHeihgt + rHeight); // 1+lda+rda when current node is root node
                                                       // for dia path


    finalDia = Math.max(finalDia, tempDia);
    return currentHeight;
  }
}
