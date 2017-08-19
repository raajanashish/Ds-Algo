package com.raajan.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class MyCallableFuture implements Callable<String> {

	int num;

	public MyCallableFuture(int num) {
		this.num = num;
	}

	public String call() {
		System.out.println("Executing Callable for : " + num);
		return String.valueOf(this.num);
	}

	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		List<Future<String>> futures = new ArrayList<>();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 10; i++) {
			Future<String> future = executor.submit(new MyCallableFuture(i));
			futures.add(future);
		}

		for (Future<String> future : futures) {
			System.out.println("Future For : " + future.get());
		}

		executeUsingFutureTask();

	}

	private static void executeUsingFutureTask() throws InterruptedException, ExecutionException {
		List<FutureTask<String>> futures = new ArrayList<>();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 10; i++) {
			MyCallableFuture callable = new MyCallableFuture(i);
			FutureTask<String> future = new FutureTask<>(callable);
			futures.add(future);
			executor.execute(future);
		}

		for (FutureTask<String> future : futures) {
			System.out.println("Future Task For : " + future.get());
		}
	}
}
