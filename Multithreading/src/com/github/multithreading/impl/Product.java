package com.github.multithreading.impl;

import com.github.multithreading.api.IProduct;

public class Product implements IProduct {

	private final long id;

	public Product(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
}