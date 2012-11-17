package com.github.multithreading;

import com.github.multithreading.api.IStore;
import com.github.multithreading.impl.Product;

public class Consumer implements Runnable {

	private final IStore store;

	private final String name;

	public Consumer(IStore store, String name) {
		this.store = store;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Product product = store.takeProductFromStore();
				if (product != null) {
					System.out.println("Consumer " + name
							+ " retrieved product " + product.getId()
							+ " from store.");
				} else {
					break;
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
