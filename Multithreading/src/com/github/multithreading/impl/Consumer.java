package com.github.multithreading.impl;

import com.github.multithreading.api.IStore;

public class Consumer {

	public Product consume(IStore store) throws InterruptedException {
		Product product = null;
		try {
			product = store.takeProduct();
		} catch (InterruptedException e) {
			System.err
					.println("InterruptedException occured in consumer thread "
							+ e.toString());
			throw e;
		}
		return product;
	}
}
