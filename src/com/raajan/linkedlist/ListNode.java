package com.raajan.linkedlist;

import java.util.Comparator;

public class ListNode {
	public int data;
	public ListNode next;

	ListNode(int data, ListNode next){
		this.data= data;
		this.next =next;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + data;
		result = prime * result + ((next == null) ? 0 : next.hashCode());
		return result;
	}
	public ListNode(){};
	public ListNode(int data ){
		this.data = data;
		this.next= null;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListNode other = (ListNode) obj;
		if (data != other.data)
			return false;
		if (next == null) {
			if (other.next != null)
				return false;
		} else if (!next.equals(other.next))
			return false;
		return true;
	}
	public final  int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public ListNode getNext() {
		return next;
	}
	public void setNext(ListNode next) {
		this.next = next;
	}
	
	// 
	
	 class MyComperator<T> implements Comparator<T>{
		@Override
		public  int compare(T arg0 , T arg1) {
			// TODO Auto-generated method stub
			return 0;
		} 
	} 
}
