package com.akosbodor.synchronizedkeyword;

public class SynchronizedApp {

	private int count = 0; //another option is to make this atomic integer with a special class

	// the second thread has to wait until the first one releases the intrensive log of the object
	public synchronized void increment() {
		count++;
	}
	
	public static void main(String[] args) {

		SynchronizedApp app = new SynchronizedApp();
		app.doWork();

	}

	public void doWork() {

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 0; i < 10000; i++) {
					increment();
				}

			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 0; i < 10000; i++) {
					increment();
				}

			}

		});

		t1.start();
		t2.start();

		try {
			t1.join();				// we have to join (wait until finished) as start always returns immediately
			t2.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Count is: " + count); // we expect to have count 10000 but it's 14973 and sometimes 20000!!!
		/*
		 * count++ looks like atomic operation but it has three steps: count = count + 1; get, add one, store it back in count. 
		 * These three steps take long time and more things can happen
		 */

	}

}
