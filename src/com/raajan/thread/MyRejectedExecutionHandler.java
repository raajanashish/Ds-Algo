package com.raajan.thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejectedExecutionHandler implements RejectedExecutionHandler{

	@Override
	public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecotr) {
		MyRunnable myRunnable = (MyRunnable)runnable;
		System.out.println("Handling Rejected "+myRunnable.num );
	}

	
}
