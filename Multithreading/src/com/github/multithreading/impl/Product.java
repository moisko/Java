package com.github.multithreading.impl;

import com.github.multithreading.api.IProduct;

public class Product implements IProduct {

	private final long id;

	private final String name;

	public Product(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Product " + name + " id[" + id + "]";
	}
}