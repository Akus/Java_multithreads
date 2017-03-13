package com.akosbodor.semaphore;

import java.util.concurrent.Semaphore;

public class Connection {

	private static Connection instance = new Connection();

	// limiting the number of connections in a given time with SEMAPHORES:
	private Semaphore sem = new Semaphore(10, true); // max ten connections and the first thread which acquires will get it

	private int connections = 0; // number of connections

	private Connection() {

	}

	public static Connection getInstance() {
		return instance;
	}

	public void connect() {

		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			doConnect();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		finally {
			sem.release();
		}

	}

	public void doConnect() throws InterruptedException {

		synchronized (this) {
			connections++;
			System.out.println("Current connections: " + connections);
		}

		// simulating work with Thread sleep:

		Thread.sleep(2000);

		synchronized (this) {
			connections--;
		}

		// if the code before throws an exception then release won't be
		// called!!!
		// sem.release(); // put this in the finally block instead!!!

	}

}
