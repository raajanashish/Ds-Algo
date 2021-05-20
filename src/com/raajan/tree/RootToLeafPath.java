package com.raajan.tree;

import java.util.ArrayList;
import java.util.List;

public class RootToLeafPath {
   public static void getRootToLeafPath(TreeNode node) {
      List<Integer> path = new ArrayList<>();
      path.add(node.val);
      getPaths(node, path);
   }

   private static void getPaths(TreeNode node, List<Integer> path) {
      if (null == node.right && null == node.left) {
         System.out.println(path);
         path.remove(path.get(path.size() - 1));
         return;
      }
      if (null != node.left) {
         path.add(node.left.val);
         getPaths(node.left, path);
      }
      if (null != node.right) {
         path.add(node.right.val);
         getPaths(node.right, path);
      }
      path.remove(path.get(path.size() - 1));
   }

   public static void main(String[] args) {
      TreeNode node = new BstTree().initialiseTree();
      getRootToLeafPath(node);
   }

}
