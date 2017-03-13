package com.akosbodor.threadpool1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		// adding 5 tasks to executor
		for(int i = 0; i<5; i++) {
			executor.submit(new Processor(i));
		}
		
		// stop after done
		
		executor.shutdown(); // it will wait for all the threads until done
		
		System.out.println("All tasks submitted.");
		
		// this will wait one day (or 10 sec for example)
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed.");
	}

}
