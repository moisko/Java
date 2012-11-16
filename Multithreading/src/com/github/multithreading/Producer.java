package com.github.multithreading;

import com.github.multithreading.api.IProduct;
import com.github.multithreading.impl.Product;

public class Producer {

	public IProduct produceToStore(String name, long id) {
		return new Product(name, id);
	}
}
