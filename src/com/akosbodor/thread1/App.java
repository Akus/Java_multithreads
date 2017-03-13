package com.akosbodor.thread1;



public class App {

	public static void main(String[] args) {
		
		Runner runner1 = new Runner();
		runner1.start();
		
		Runner runner2 = new Runner();
		runner2.start();				// both threads running in the same time, you can run codes simultaneously
		
	}

}
