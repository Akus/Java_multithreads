package com.akosbodor.countdownlatch1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) {

		CountDownLatch latch = new CountDownLatch(3);
		
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		// three processors
		for (int i=0; i<3; i++) {
			
			executor.submit(new Processor(latch));
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // it waits until the CountDownLatch reaches zero
		
		System.out.println("Completed.");
	}
	
	

}
