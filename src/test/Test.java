package test;

import java.util.LinkedList;

import linkedlist.Node;

public class Test extends Node{
	{
		System.out.println("non static block");;
	}
	static{
		LinkedList<String> list = new LinkedList<>();
		System.out.println("static");
	}
	public Test() {
		super();
	}
	public Test(int data) {
		//this();
		super(data);
	}
	
}

class B{
	
	Test test  = new Test();

}
