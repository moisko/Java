package com.github.multithreading;

import com.github.multithreading.api.IProduct;

public class Product implements IProduct {

	private final String name;

	private final String id;

	public Product(String name, String id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}
}