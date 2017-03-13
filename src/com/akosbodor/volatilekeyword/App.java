package com.akosbodor.volatilekeyword;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {

		Processor proc1 = new Processor();
		
		proc1.start(); // how to terminate the processor gracefully?
		
		System.out.println("Press return to stop...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		proc1.shutdown();  
		
	}

}
