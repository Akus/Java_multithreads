package com.akosbodor.synchronizedkeyword;

public class UnsynchronizedApp {

	private int count = 0;

	public static void main(String[] args) {

		UnsynchronizedApp app = new UnsynchronizedApp();
		app.doWork();

	}

	public void doWork() {

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 0; i < 10000; i++) {
					count++;
				}

			}

		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = 0; i < 10000; i++) {
					count++;
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
