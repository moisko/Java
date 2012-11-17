package com.github.multithreading;

import com.github.multithreading.api.IStore;
import com.github.multithreading.impl.Product;
import com.github.multithreading.utils.ProductIdGenerator;

public class Producer implements Runnable {

	private final IStore store;

	private final String name;

	public Producer(IStore store, String name) {
		this.store = store;
		this.name = name;
	}

	@Override
	public void run() {
		long productId = ProductIdGenerator.generateProductId();
		Product newProduct = new Product(productId);
		try {
			store.addProduct(newProduct);
			System.out.println("Producer " + name + " added product "
					+ newProduct.getId() + " to store.");
		} catch (InterruptedException e) {
			System.err
					.println("InterruptedException occured while adding Product "
							+ newProduct.getId()
							+ " in thread "
							+ name
							+ " to store " + e.toString());

			Thread.currentThread().interrupt();
		}
	}

}
