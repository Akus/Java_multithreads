package com.akosbodor.volatilekeyword;

public class Processor extends Thread {

	private volatile boolean running = true; // on some systems this thread may cache this value!!! Has its own copy of running. Main thread has another.
	
	// to prevent caching we use volatile keyword: prevent threads caching variables or use thread synchronisation.
	
	public void run() {
		while(running) {
			System.out.println("Hello");
			try {
				Thread.sleep(100);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown() {
		running = false; // called from Main thread
	}
}
