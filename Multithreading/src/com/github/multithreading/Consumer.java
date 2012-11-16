package com.github.multithreading;

import com.github.multithreading.api.IStore;
import com.github.multithreading.impl.Product;

public class Consumer {

	public Product consumeFromStore(IStore store) throws InterruptedException {
		try {
			Product product = store.takeProduct();

			return product;
		} catch (InterruptedException e) {
			System.err.println("InterruptedException occured in consumer "
					+ e.toString());
			throw e;
		}
	}
}
