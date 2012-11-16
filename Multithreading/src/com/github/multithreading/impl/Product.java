package com.github.multithreading.impl;

import com.github.multithreading.api.IProduct;

public class Product implements IProduct {

	private final String name;

	private final long id;

	public Product(String name, long id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}
}