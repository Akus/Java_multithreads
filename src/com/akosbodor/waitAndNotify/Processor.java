package com.akosbodor.waitAndNotify;

import java.util.Scanner;

public class Processor {
	
	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Producer thread is running ...");
			wait();
			System.out.println("Resumed.");
		}
		
	}
	
	public void consume() throws InterruptedException {
		
		Scanner scanner = new Scanner(System.in); // handy for input
		Thread.sleep(2000); // to let the produce thread kick-off first
		
		// synch code block
		synchronized (this) {
			System.out.println("Waiting for return key.");
			scanner.nextLine();
			System.out.println("Return key pressed.");
			notify(); // notifies the other thread that it can wake up. Notifies all threads which are waiting for this lock
			// to prove that notify won't relinquish the lock until the end of the block:
			Thread.sleep(5000);
		}
	}

}
