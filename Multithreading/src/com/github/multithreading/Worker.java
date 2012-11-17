package com.github.multithreading;

import com.github.multithreading.api.IStore;
import com.github.multithreading.impl.Product;

public class Worker implements Runnable {

	private final IStore store;

	private final String name;

	public Worker(IStore store, String name) {
		this.store = store;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			System.out.println("Wroker thread has bee successfully created.");
			while (true) {
				Product product = store.takeProductFromStore();
				if (product == null) {
					break;
				} else {
					Consumer consumer = new Consumer(product);
					Thread consumerThread = new Thread(consumer);
					consumerThread.start();
				}
			}
		} catch (InterruptedException e) {
			System.err
					.println("InterruptedException occured in consumer thread "
							+ name + e.toString());

			Thread.currentThread().interrupt();
		}
	}
}
