package com.raajan.tree;

public class Node {
  public Node left;
  public Node right;
  public Integer data;
  public Node parent;

  public Node(int data) {
    this.data = data;
  }

  public Node() {}

  public String toString() {

    if (this == null) {
      return null;
    } else {
      String lData = left == null ? null : left.data.toString();
      String rData = right == null ? null : right.data.toString();
      return "[" + this.data + " --> " + lData + ", " + rData +"]";
    }

  }

}
