/**
 * 
 */
package com.raajan.linkedlist;

/**
 * @author RaJaN
 *
 */
public class LinkedList {

   /**
    * @param args
    */

   public ListNode head;
   public ListNode tail;
   public int length;

   public boolean add(int data) {
      ListNode temp = new ListNode(data);
      if (head == null) {
         head = temp;
         tail = temp;
         length= 1;
      } else {
         tail.next = temp;
         tail = tail.next;
         length++;
      }
      return true;
   }

   public boolean add(int data, int position) {

      return false;

   }

   public boolean deleteByData(int data) {

      return false;
   }

   public boolean deleteByPosition(int position) {
      return false;
   }
}

