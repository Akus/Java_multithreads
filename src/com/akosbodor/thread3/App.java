package com.akosbodor.thread3;

// second technique without separate class

public class App {

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {

			@Override
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
		});

		t1.start();

	}

}
