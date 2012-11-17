package com.github.multithreading.api;

import com.github.multithreading.impl.Product;

public interface IStore {

	public void addProductToStore(Product product) throws InterruptedException;

	public Product takeProductFromStore() throws InterruptedException;

	public int getStoreCapacity();
}
