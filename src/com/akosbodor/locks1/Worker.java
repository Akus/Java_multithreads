package com.akosbodor.locks1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

	private Random random = new Random();
	
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();

	public void stageOne() {
		try {
			Thread.sleep(1); 				// simulate some work
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		list1.add(random.nextInt(100));
	}
	
	public void stageTwo() {
		try {
			Thread.sleep(1); 				// simulate some work
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		list2.add(random.nextInt(100));
	}
	
	
	public void process() {
		
		for (int i = 0; i<1000; i++) {
			stageOne();
			stageTwo();
		}
	}
	
	// worker does some processing at stage one and stage two
	public void main() {
		System.out.println("Starting...");
		
		long start = System.currentTimeMillis();
		
		process();
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time take: " + (end - start));
		System.out.println("List1: " + list1.size() + "\nList2: " + list2.size());
	}
}
