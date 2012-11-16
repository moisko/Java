package com.github.multithreading.impl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.github.multithreading.api.IStore;

public class Store implements IStore {

	private final int capacity;

	private final BlockingQueue<Product> products;

	public Store(int capacity) {
		this.capacity = capacity;
		this.products = new ArrayBlockingQueue<Product>(capacity);
	}

	public void addProduct(Product product) throws InterruptedException {
		products.put(product);
	}

	public Product getProduct() throws InterruptedException {
		return products.take();
	}

	public int getStoreCapacity() {
		return capacity;
	}
}