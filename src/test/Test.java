package test;

import java.util.LinkedList;

import com.raajan.linkedlist.ListNode;

public class Test extends ListNode {
	{
		System.out.println("non static block");
		;
	}
	static {
		LinkedList<String> list = new LinkedList<>();
		System.out.println("static");
	}

	public Test() {
		super();
	}

	public Test(int data) {
		// this();
		super(data);
	}

	public static void main(String[] args) {

	}

	static int[] frequency(String s) {
		int[] output = new int[26];
		int i = 0;
		while (i  >=0) {
			int startIndex = i;
			int endIndex = i;
			if (")".equals(s.substring(i, i + 2))) {
				int closeBkt = i -2;
				while (!"(".equals(s.substring(closeBkt, closeBkt + 1)))
					closeBkt++;
				endIndex = closeBkt + 1;
				i = endIndex;
			} else if ("#".equals(s.substring(i + 2, i + 3))) {
				endIndex = i + 2;
				i=endIndex+1;
				if ("(".equals(s.substring(endIndex + 1, endIndex + 2))) {
					int closeBkt = endIndex + 3;
					while (!")".equals(s.substring(closeBkt, closeBkt + 1)))
						closeBkt++;
					endIndex = closeBkt + 1;
					i = endIndex;
				}
			}
		}

		return output;
	}
}

class B {

	Test test = new Test();

}
