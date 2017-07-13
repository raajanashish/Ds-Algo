package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyExecutor {

	public static void main(String[] args) {
		ThreadFactory threadFcatory = Executors.defaultThreadFactory();
		MyRejectedExecutionHandler rejectedHandler = new MyRejectedExecutionHandler();
		ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 4, 10,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2),
				threadFcatory, rejectedHandler);

		for (int i = 0; i < 10; i++) {
			executor.execute(new MyRunnable(i));
		}
		
		executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads In Executor Thread Pool");
    
		executeByThreadPool();
	}

	static void executeByThreadPool(){
		ExecutorService executor = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 10; i++) {
			executor.execute(new MyRunnable(i));
		}
		executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads In Fixed Thread Pool");
    
		
	}
	
}
