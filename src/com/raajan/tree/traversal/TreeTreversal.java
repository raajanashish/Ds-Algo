package com.raajan.tree.traversal;

import java.util.ArrayList;
import java.util.List;

import com.raajan.tree.BstTree;
import com.raajan.tree.Node;

/**
 * Inorder, Preorder, Postorder Build Tree using Inorder and Preorder Build Tree using Inorder and
 * Postorder
 * 
 * @author RaJaN
 *
 */
public class TreeTreversal {

   public static Node getBinaryTreeFromPreOrder(Integer[] inorder, Integer[] preorder) {
      return buildTreeFromPreOrder(inorder, 0, inorder.length - 1, preorder, 0,
            preorder.length - 1);

   }

   public static Node buildTreeFromPreOrder(Integer[] inorder, int inStart, int inEnd,
         Integer[] preOrder, int preStart, int preEnd) {

      if (inStart > inEnd || preStart > preEnd)
         return null;

      Node currentNode = new Node(preOrder[preStart]);
      int rootIndex = inStart;
      while (rootIndex <= inEnd) {
         if (currentNode.data == inorder[rootIndex])
            break;
         rootIndex++;
      }
      int leftSubTreeSize = rootIndex - inStart;
      int rightSubTreSize = inEnd - rootIndex;
      currentNode.left = buildTreeFromPreOrder(inorder, inStart, rootIndex - 1, preOrder,
            preStart + 1, preStart + leftSubTreeSize);
      currentNode.right = buildTreeFromPreOrder(inorder, rootIndex + 1, inEnd, preOrder,
            preEnd - rightSubTreSize + 1, preEnd);
      return currentNode;
   }

   public static Node getBinaryTreeFromPostOrder(Integer[] inorder, Integer[] postorder) {
      return buildTreeFromPostOrder(inorder, 0, inorder.length - 1, postorder, 0,
            postorder.length - 1);

   }

   public static Node buildTreeFromPostOrder(Integer[] inorder, int inStart, int inEnd,
         Integer[] postOrder, int postStart, int postEnd) {

      if (inStart > inEnd || postStart > postEnd)
         return null;

      Node currentNode = new Node(postOrder[postEnd]);
      int rootIndex = inStart;
      while (rootIndex <= inEnd) {
         if (currentNode.data == inorder[rootIndex])
            break;
         rootIndex++;
      }

      int leftSubTreeSize = rootIndex - inStart;
      int rightSubTreSize = inEnd - rootIndex;
      currentNode.left = buildTreeFromPostOrder(inorder, inStart, rootIndex - 1, postOrder,
            postStart, postStart + leftSubTreeSize - 1);
      currentNode.right = buildTreeFromPostOrder(inorder, rootIndex + 1, inEnd, postOrder,
            postStart + leftSubTreeSize, postEnd - 1);
      return currentNode;
   }

   public static void inorder(Node root, List<Integer> inorder) {
      if (null == root)
         return;
      inorder(root.left, inorder);
      // System.out.println(root.data);
      inorder.add(root.data);
      inorder(root.right, inorder);

   }

   public static void preorder(Node root, List<Integer> preorder) {
      if (null == root)
         return;
      preorder.add(root.data);
      preorder(root.left, preorder);
      preorder(root.right, preorder);

   }

   public static void postorder(Node root, List<Integer> postorder) {
      if (null == root)
         return;
      inorder(root.left, postorder);
      inorder(root.right, postorder);
      postorder.add(root.data);
   }

   public static void main(String[] args) {
      Node root = new BstTree().initialiseTree();


      /*
       * List<Integer> inrder = new ArrayList<>(); inorder(root, inrder);
       * System.out.println(inrder);
       * 
       * List<Integer> preorder = new ArrayList<>(); postorder(root, preorder);
       * System.out.println(preorder); Node newRoot = getBinaryTreeFromPostOrder(inrder.toArray(new
       * Integer[inrder.size()]), preorder.toArray(new Integer[preorder.size()])); inrder.clear();
       * preorder.clear(); inorder(newRoot, inrder); System.out.println(inrder); postorder(newRoot,
       * preorder); System.out.println(preorder);
       */


      System.out.println(leaseCommonAncestor(root, 30, 33).data);
   }

   public static Node leaseCommonAncestor(Node root, int data1, int data2) {
      Node leftLca, rightLca = null;

      if (root == null)
         return null;
      // if any of node matches then return because no need to travel beyond
      if (data1 == root.data || data2 == root.data)
         return root;

      leftLca = leaseCommonAncestor(root.left, data1, data2);
      rightLca = leaseCommonAncestor(root.right, data1, data2);

      if (null != leftLca && null != rightLca)
         return root;

      return null != leftLca ? leftLca : rightLca;
   }
}
