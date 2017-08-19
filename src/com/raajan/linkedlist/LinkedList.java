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
	
	Node head;
	Node tail;
	int length;

	public boolean add(int data){
		Node  temp = new Node(data);
		if(head == null)
			{head=temp;
			tail = temp;
			}
		else
			{tail.next=temp;
			tail=tail.next;
			}
		return false;
	}
	
	public boolean add(int data, int position){
		
		return false;
		
	}

	public boolean deleteByData(int data){
		
		return false;
	}

	public boolean deleteByPosition(int position){
		return false;
	}
}

