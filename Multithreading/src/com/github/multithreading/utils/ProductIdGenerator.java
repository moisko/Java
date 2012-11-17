package com.github.multithreading.utils;

import java.util.concurrent.atomic.AtomicLong;

public class ProductIdGenerator {

	private static final AtomicLong idCounter = new AtomicLong(0);

	public static long generateProductId() {
		return idCounter.incrementAndGet();
	}

}