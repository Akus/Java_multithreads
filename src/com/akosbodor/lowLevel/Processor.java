package com.akosbodor.lowLevel;

import java.util.LinkedList;
import java.util.Random;

public class Processor {

	// shared data between threads:
	private LinkedList<Integer> list = new LinkedList<Integer>();

	private final int LIMIT = 10;

	// synch with lock with wait and notify:

	private Object lock = new Object();

	public void produce() throws InterruptedException {

		int value = 0;

		while (true) {

			synchronized (lock) {
				while(list.size() == LIMIT) {
					lock.wait();
				}
				list.add(value++);
				lock.notify(); // wakes up the other thread's wait
			}
		}
	}

	public void consume() throws InterruptedException {
		Random random = new Random();
		while (true) {

			synchronized (lock) {

				while (list.size() == 0) {
					lock.wait();			// this is waked up by the other thread's notfiy
				}
				
				System.out.print("List size is: " + list.size());
				int value = list.removeFirst();
				System.out.println("; value is: " + value);
				lock.notify();

			}
			int currentRandom = random.nextInt(1000);
			Thread.sleep(currentRandom);
			System.out.println("Slept: " + (double) currentRandom /1000 + " sec");
			
			// if you don't miss any numbers or getting duplicates from the output then the synchronisation is working
		}

	}

}
