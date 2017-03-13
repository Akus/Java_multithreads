package com.akosbodor.thread2;

//second method to implement threads in java is implement Runnable and pass it to the Constructor of the Thread class

class Runner implements Runnable {
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello " + i);

			// slow down the thread
			try {
				Thread.sleep(100); // milliseconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}