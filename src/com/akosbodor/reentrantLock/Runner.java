package com.akosbodor.reentrantLock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	// we use here reentrant lock instead of the previous used synchronised
	// block
	private int count = 0;

	// once a thread is locking the other can't until thread one unlocks it.
	private Lock lock = new ReentrantLock();
	
	private Condition cond = lock.newCondition();

	private void increment() {

		for (int i = 0; i < 10000; i++) {
			count++;
		}
	}

	public void firstThread() throws InterruptedException {
		lock.lock();										// 1. lock
		
		
		System.out.println("First thread is waiting...");	// 2. sysout
		cond.await(); // like wait							// 3. unlock by cond.await()
		
		System.out.println("Woken up first thread!");		// 9. sysout
		
		try {
			increment();
		} finally {
			lock.unlock(); // comment this line out to test the signal without unlock!
		}
	}

	public void secondThread() throws InterruptedException {
		
		Thread.sleep(1000);
		
		lock.lock();										// 4. lock
		
		System.out.println("Press the return key!");		// 5. sysout
		new Scanner(System.in).nextLine();
		System.out.println("Got return key!");
		
		cond.signal();										// 6. waking up cond.await() thread
		
		try {
			increment();
		} finally {
			lock.unlock(); 									// 7. unlock
			System.out.println("Object is unlocked by Second Thread"); 	// 8. sysout
		}
	}

	public void finished() {

		System.out.println("Count is: " + count);			// 10. sysout count
	}

}
