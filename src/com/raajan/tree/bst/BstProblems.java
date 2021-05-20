package com.raajan.tree.bst;

import java.util.ArrayList;
import java.util.List;

import com.raajan.linkedlist.LinkedList;
import com.raajan.linkedlist.ListNode;
import com.raajan.tree.BstTree;
import com.raajan.tree.TreeNode;
import com.raajan.tree.traversal.TreeTreversal;

public class BstProblems {
   public static int PREV_NODE = Integer.MIN_VALUE;

   /**
    * It will check weather given binary tree is binary search tree or not
    * 
    * @param root
    * @return
    */
   public boolean isBstInorder(TreeNode root) {

      if (null == root)
         return true;

      if (!isBstInorder(root.left))
         return false;

      if (PREV_NODE > root.val)
         return false;
      PREV_NODE = root.val;

      return isBstInorder(root.right);
   }

   /**
    * It will return least common ancestor in binary search tree for given node, Its based on
    * preorder traversal
    * 
    * @param root
    * @param node1
    * @param node2
    * @return
    */
   public static TreeNode LCAForBSt(TreeNode root, TreeNode node1, TreeNode node2) {
      if (null == root)
         return null;

      if (Math.max(node1.val, node2.val) > root.val
            && Math.min(node1.val, node2.val) < root.val)
         return root;

      if (Math.max(node1.val, node2.val) < root.val)
         return LCAForBSt(root.left, node1, node2);

      if (Math.min(node1.val, node2.val) > root.val)
         return LCAForBSt(root.right, node1, node2);

      return null; // never be called
   }


   /**
    * convert an sorted array to binary search tree
    * 
    * @param sortedArrray
    * @param start
    * @param end
    */

   public static TreeNode SortedArrayToBST(Integer[] sortedArrray, int start, int end) {
      if (start > end)
         return null;

      int mid = start + (end - start) / 2;
      TreeNode root = new TreeNode(sortedArrray[mid]);
      TreeNode left = SortedArrayToBST(sortedArrray, start, mid - 1);
      TreeNode right = SortedArrayToBST(sortedArrray, mid + 1, end);
      root.left = left;
      root.right = right;
      return root;
   }

   public static void main(String[] args) {
      TreeNode root = new BstTree().initialiseTree();
      System.out.println(LCAForBSt(root, new TreeNode(27), new TreeNode(32)).val);
      List<Integer> inrder = new ArrayList<>();
      TreeTreversal.inorder(root, inrder);
      System.out.println(inrder);
      // System.out.println(kthLargestElementInBst(root, 10).data);
      LinkedList list = new LinkedList();
      for (Integer element : inrder)
         list.add(element);
      ListNode currentHead = list.head;
      inrder.clear();

      TreeTreversal.inorder(sortedLinkedListToBalencedBST(list), inrder);
      System.out.println(inrder);
      System.out.println(floor1(root, 11).val);
      System.out.println(ceiling(root, 26).val);

      /*
       * Node newRoot = SortedArrayToBST(inrder.toArray(new Integer[inrder.size()]), 0,
       * inrder.size() - 1); inrder.clear(); TreeTreversal.inorder(newRoot, inrder);
       * System.out.println(inrder);
       */
   }


   private static int count = 0;

   /**
    * Will return kth smallest element in binary search tree, using global variable to track count
    */
   public static TreeNode kthSmallestElementInBst(TreeNode root, int kth) {
      if (null == root)
         return null;

      TreeNode left = kthSmallestElementInBst(root.left, kth);
      if (null != left)
         return left;
      count++;
      if (count == kth)
         return root;
      return kthSmallestElementInBst(root.right, kth);
   }

   /**
    * Will return kth largest element in binary search tree, using global variable to track count
    */
   public static TreeNode kthLargestElementInBst(TreeNode root, int kth) {
      if (null == root)
         return null;

      TreeNode right = kthLargestElementInBst(root.right, kth);
      if (null != right)
         return right;
      count++;
      if (count == kth)
         return root;
      return kthLargestElementInBst(root.left, kth);
   }

   private static ListNode currenntHead;

   /**
    * It will build balanced binary search tree from ordered list
    * 
    * @param list
    * @return
    */
   public static TreeNode sortedLinkedListToBalencedBST(LinkedList list) {
      ListNode currentNode = list.head;
      int length = 0;
      while (null != currentNode) {
         currentNode = currentNode.next;
         length++;
      }
      currenntHead = list.head;
      return buildBalancedBST(0, length - 1);

   }

   /**
    * It will use the bottom123 up approach
    * 
    * @param currenntHead
    * @param start
    * @param end
    * @return
    */
   private static TreeNode buildBalancedBST(int start, int end) {
      if (start > end)
         return null;
      int mid = start + (end - start) / 2;
      TreeNode left = buildBalancedBST(start, mid - 1);
      TreeNode root = new TreeNode(currenntHead.data);
      root.left = left;
      if (null != currenntHead.next) {
         currenntHead = currenntHead.next;
      }
      root.right = buildBalancedBST(mid + 1, end);
      return root;
   }

   private static int prev = Integer.MIN_VALUE;

   public static TreeNode floor(TreeNode root, int key) {
      if (null == root)
         return null;

      floor(root.left, key);

      if (root.val == key)
         return root;
      else if (root.val > key)
         return new TreeNode(prev);

      prev = root.val;

      return floor(root.right, key);
   }

   public static TreeNode floor1(TreeNode root, int key) {
      if (null == root)
         return null;

      TreeNode left = floor(root.left, key);
      if (null != left)
         return left;
      if (root.val == key)
         return root;
      else if (root.val > key)
         return new TreeNode(prev);

      prev = root.val;

      return floor(root.right, key);
   }

   private static int ceilPrev = Integer.MAX_VALUE;

   public static TreeNode ceiling(TreeNode root, int key) {
      if (null == root)
         return null;

      ceiling(root.right, key);

      if (root.val == key)
         return root;
      else if (root.val < key)
         return new TreeNode(ceilPrev);

      ceilPrev = root.val;

      return ceiling(root.left, key);
   }
}
