package com.github.multithreading;

import com.github.multithreading.api.IStore;
import com.github.multithreading.impl.Product;
import com.github.multithreading.utils.ProductIdGenerator;

public class Producer implements Runnable {

	private final IStore store;

	public Producer(IStore store) {
		this.store = store;
	}

	@Override
	public void run() {
		long productId = ProductIdGenerator.generateProductId();
		Product newProduct = new Product(productId);
		try {
			store.addProduct(newProduct);
		} catch (InterruptedException e) {
			System.err
					.println("InterruptedException occured in Producer thread "
							+ e.toString());
		}
	}

}
