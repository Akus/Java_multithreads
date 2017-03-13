package com.akosbodor.lowLevel;

public class App {

	static Processor processor = new Processor();

	public static void main(String[] args) throws InterruptedException {

		Thread t1 = new Thread(new Runnable() {

			public void run() {

				try {
					processor.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {

				try {
					processor.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		});

		
		// I'm trying to consume more so maybe getting list size below max but it produces too fast :)
		Thread t3 = new Thread(new Runnable() {

			public void run() {

				try {
					processor.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		});
		
		Thread t4 = new Thread(new Runnable() {

			public void run() {

				try {
					processor.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		});
		
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		
	}

}
