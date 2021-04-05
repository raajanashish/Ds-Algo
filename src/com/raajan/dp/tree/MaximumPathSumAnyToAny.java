package com.raajan.dp.tree;

import com.raajan.tree.BinaryTree;
import com.raajan.tree.Node;

public class MaximumPathSumAnyToAny {

  private static int maxPathSum;
  public static void main(String[] args) {
    //int[] treeArray = new int[] {20, 2,1,3, 30, 32, 28, 33, 26, 34, 35, 36, 24, 23};
    int[] treeArray = new int[] {10,5,-3, 12};
    Node root = BinaryTree.initialiseTree(treeArray);
    maxPathSum(root);
    System.out.println(maxPathSum);


  }

  public static int maxPathSum(Node root) {
    // Base condition
    if (root == null) {
      return 0;
    }

    // Hypothesis step
    int lpsum = maxPathSum(root.left);
    int rpsum = maxPathSum(root.right);

    // Induction step
    // When current node is root node for path giving maximum path sum
    int pathSumToPass = Math.max(Math.max(lpsum, rpsum) + root.data, root.data);

    // data+lda+rda when current node is root node for path giving maximum path sum
    // Choose the case for maximum path sum
    int tempPathSum = Math.max(pathSumToPass, root.data + lpsum + rpsum);

    maxPathSum = Math.max(maxPathSum, tempPathSum);
    return pathSumToPass;
  }

}
