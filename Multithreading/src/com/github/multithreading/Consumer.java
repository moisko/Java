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
			Product product = store.takeProduct();

			System.out.println("Product with id " + product.getId()
					+ " successfully retrieved from store.");
		} catch (InterruptedException e) {
			System.err
					.println("InterruptedException occured in consumer thread "
							+ name + e.toString());
		}
	}
}
