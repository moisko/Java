package com.github.multithreading;

import com.github.multithreading.api.IStore;
import com.github.multithreading.impl.Product;

public class Consumer implements Runnable {

	private final IStore store;

	public Consumer(IStore store) {
		this.store = store;
	}

	@Override
	public void run() {
		try {
			Product product = store.takeProduct();

			System.out.println("Product with id " + product.getId()
					+ " retrieved from store.");
		} catch (InterruptedException e) {
			System.err
					.println("InterruptedException occured in consumer thread"
							+ e.toString());
		}
	}
}
