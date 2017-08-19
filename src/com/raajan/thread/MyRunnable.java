package com.raajan.thread;

public class MyRunnable implements Runnable {

	int num;

	public MyRunnable(int i) {
		num = i;
	}

	public void run() {
		Thread.currentThread().setName(" " + num);
		System.out.println("Executing Runnable for "
				+ Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
