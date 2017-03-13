package com.akosbodor.thread1;

// first method to implement threads in java is to extend the Thread class

class Runner extends Thread {

	@Override
	public void run() {
		
		
		for (int i=0; i<10; i++) {
			System.out.println("Hello " + i);
			
			// slow down the thread
			try {
				Thread.sleep(100); 					// milliseconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
			
		}
	}
	
}