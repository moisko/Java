package com.github.multithreading.impl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.github.multithreading.api.IStore;

public class Store implements IStore {

	private static final int TIME_TO_WAIT = 2;

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
		return products.poll(TIME_TO_WAIT, TimeUnit.SECONDS);
	}

	public int getStoreCapacity() {
		return capacity;
	}
}