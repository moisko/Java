package com.github.multithreading.api;

import com.github.multithreading.impl.Product;

public interface IStore {

	public void addProduct(Product product) throws InterruptedException;

	public Product getProduct() throws InterruptedException;

	public int getStoreCapacity();
}
