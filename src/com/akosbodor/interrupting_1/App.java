package com.akosbodor.interrupting_1;

import java.util.Random;

public class App {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Starting...");
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				
				// really time consuming job:
				
				Random ran = new Random();
				
				// 1E6 = one million
				for (int i = 0; i<1E8; i++) {
					
					// asking the thread if it's interrupted or not:
					/*
					if (Thread.currentThread().isInterrupted()) {
						System.out.println("Interrupted!");
						break;
					}
					*/
					
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						System.out.println("Interrupted via Thread.sleep()");
						break;
					}
					
					
					Math.sin(ran.nextDouble());
					
				}
				
			}
			
		});
		t1.start();
		
		Thread.sleep(500);
		
		t1.interrupt();			// sets a flag that it interrupted but this method won't stop the thread!
		
		t1.join();
		
		
		System.out.println("Finished.");
	}
}
