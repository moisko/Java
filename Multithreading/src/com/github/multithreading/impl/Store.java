package com.github.multithreading.impl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.github.multithreading.api.IStore;

public class Store implements IStore {

	private final int capacity;

	private final BlockingQueue<Product> products;

	public Store(int capacity) {
		this.capacity = capacity;
		this.products = new ArrayBlockingQueue<Product>(capacity);
	}

	public void addProductToStore(Product product) throws InterruptedException {
		products.put(product);
	}

	public Product takeProductFromStore() throws InterruptedException {
		Product product = products.poll(2, TimeUnit.SECONDS);
		return product;
	}

	public int getStoreCapacity() {
		return capacity;
	}
}