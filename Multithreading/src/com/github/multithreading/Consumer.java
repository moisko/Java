package com.github.multithreading;

import com.github.multithreading.impl.Product;

public class Consumer implements Runnable {

	private final Product product;

	public Consumer(Product product) {
		this.product = product;
	}

	@Override
	public void run() {
		System.out.println("Product " + product.getId()
				+ " has been taken from store.");
	}
}
