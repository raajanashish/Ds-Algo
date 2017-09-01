package com.raajan.tree.bst;

import java.util.ArrayList;
import java.util.List;

import com.raajan.linkedlist.LinkedList;
import com.raajan.linkedlist.ListNode;
import com.raajan.tree.BstTree;
import com.raajan.tree.Node;
import com.raajan.tree.traversal.TreeTreversal;

public class BstProblems {
   public static int PREV_NODE = Integer.MIN_VALUE;

   /**
    * It will check weather given binary tree is binary search tree or not
    * 
    * @param root
    * @return
    */
   public boolean isBstInorder(Node root) {

      if (null == root)
         return true;

      if (!isBstInorder(root.left))
         return false;

      if (PREV_NODE > root.data)
         return false;
      PREV_NODE = root.data;


      return isBstInorder(root.right);
   }

   /**
    * It will return least common ancestor in binary search tree for given node
    * 
    * @param root
    * @param node1
    * @param node2
    * @return
    */
   public static Node LCAForBSt(Node root, Node node1, Node node2) {
      if (null == root)
         return root;

      if (Math.min(node1.data, node2.data) > root.data)
         return LCAForBSt(root.left, node1, node2);

      if (Math.max(node1.data, node2.data) > root.data)
         return LCAForBSt(root.right, node1, node2);

      return root;
   }


   /**
    * convert an sorted array to binary search tree
    * 
    * @param sortedArrray
    * @param start
    * @param end
    */

   public static Node SortedArrayToBST(Integer[] sortedArrray, int start, int end) {
      if (start > end)
         return null;

      int mid = start + (end - start) / 2;
      Node root = new Node(sortedArrray[mid]);
      Node left = SortedArrayToBST(sortedArrray, start, mid - 1);
      Node right = SortedArrayToBST(sortedArrray, mid + 1, end);
      root.left = left;
      root.right = right;
      return root;
   }

   public static void main(String[] args) {
      Node root = new BstTree().initialiseTree();
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
      System.out.println(floor(root, 26).data);
      System.out.println(ceiling(root, 26).data);

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
   public static Node kthSmallestElementInBst(Node root, int kth) {
      if (null == root)
         return null;

      Node left = kthSmallestElementInBst(root.left, kth);
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
   public static Node kthLargestElementInBst(Node root, int kth) {
      if (null == root)
         return null;

      Node right = kthLargestElementInBst(root.right, kth);
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
   public static Node sortedLinkedListToBalencedBST(LinkedList list) {
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
   private static Node buildBalancedBST(int start, int end) {
      if (start > end)
         return null;
      int mid = start + (end - start) / 2;
      Node left = buildBalancedBST(start, mid - 1);
      Node root = new Node(currenntHead.data);
      root.left = left;
      if (null != currenntHead.next) {
         currenntHead = currenntHead.next;
      }
      root.right = buildBalancedBST(mid + 1, end);
      return root;
   }
   private static int prev = Integer.MIN_VALUE;
   public static Node floor(Node root, int key) {
      if (null == root)
         return null;

      floor(root.left, key);

      if (root.data == key)
         return root;
      else if (root.data > key)
         return new Node(prev);

      prev = root.data;

      return floor(root.right, key);
   }

   private static int ceilPrev = Integer.MAX_VALUE;

   public static Node ceiling(Node root, int key) {
      if (null == root)
         return null;

      ceiling(root.right, key);

      if (root.data == key)
         return root;
      else if (root.data < key)
         return new Node(ceilPrev);

      ceilPrev = root.data;

      return ceiling(root.left, key);
   }

}
