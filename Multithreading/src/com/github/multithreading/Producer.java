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
		// Safe publication of Product
		Product newProduct = createNewProduct();
		try {
			store.addProductToStore(newProduct);
			System.out.println(newProduct.toString() + " added to store.");
		} catch (InterruptedException e) {
			System.err
					.println("InterruptedException occured while adding Product "
							+ newProduct.getId()
							+ " in thread "
							+ name
							+ " to store." + e.toString());

			Thread.currentThread().interrupt();
		}
	}

	private synchronized Product createNewProduct() {
		long productId = ProductIdGenerator.generateProductId();
		String productName = "p" + productId;
		Product newProduct = new Product(productId, productName);

		return newProduct;
	}

}
