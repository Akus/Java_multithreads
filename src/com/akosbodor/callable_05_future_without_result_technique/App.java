package com.akosbodor.callable_05_future_without_result_technique;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newCachedThreadPool();

		Future<?> future = executor.submit(new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				Random random = new Random();

				int duration = random.nextInt(4000);

				// trying exception
				if (duration > 2000) {
					throw new IOException("Sleeping for too long.");	// call will throw this exception but future.get will throw the ExecutionException!!!
				}
				
				
				System.out.println("Starting...");

				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("Finished.");
				return null; // we can use duration with future
			}

		});

		executor.shutdown();

		try {
			System.out.println("Result is: " + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
		//	e.printStackTrace();
		//	System.out.println(e);
		//	System.out.println(e.getMessage());
			
			// get the original exception:
			IOException ex = (IOException) e.getCause();
					System.out.println(ex.getMessage());
		}
	}
}
