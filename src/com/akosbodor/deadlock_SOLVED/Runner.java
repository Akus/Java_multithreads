package com.akosbodor.deadlock_SOLVED;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

	private Account acc1 = new Account();
	private Account acc2 = new Account();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	// let's create a method which prevents deadlock by acquiring locks in any
	// order!

	private void acquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException {
		// in practice you should use cases for timeouts but now infinite while
		// loop will be okay:
		while (true) {

			// try to acquire locks

			boolean gotFirstLock = false;
			boolean gotSecondLock = false;

			try {
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			} finally {

				if (gotFirstLock && gotSecondLock) {
					return; // if we have both of the lock we return from the
							// method
				}

				if (gotFirstLock) {
					firstLock.unlock(); // give other threads chance to aquire
										// it
				}

				if (gotSecondLock) {
					secondLock.unlock();
				}
			}

			// if locks not aquired

			// sleep and try again
			Thread.sleep(1);

		}

	}

	public void firstThread() throws InterruptedException {

		// create random transfers from acc1 to acc2

		Random random = new Random();

		for (int i = 0; i < 10000; i++) {

			acquireLocks(lock1, lock2);

			try {
				Account.transfer(acc1, acc2, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}

		}

	}

	public void secondThread() throws InterruptedException {
		// create random transfers from acc2 to acc1

		Random random = new Random();

		for (int i = 0; i < 10000; i++) {

			acquireLocks(lock2, lock1);

			try {
				Account.transfer(acc2, acc1, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}

		}
	}

	public void finished() {

		System.out.println("Account 1 balance: " + acc1.getBalance());
		System.out.println("Account 2 balance: " + acc2.getBalance());
		System.out.println("TOTAL balance: " + (acc1.getBalance() + acc2.getBalance()));

	}

}
