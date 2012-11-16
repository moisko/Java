package com.github.multithreading;

import com.github.multithreading.api.IProduct;

public class Producer {

	public IProduct produce(String name, long id) {
		return new Product(name, id);
	}
}
