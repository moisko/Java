package com.github.multithreading;

import com.github.multithreading.api.IStore;

public class Consumer implements Runnable {

	private final IStore store;

	private final String name;

	public Consumer(IStore store, String name) {
		this.store = store;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			store.takeProduct();
		} catch (InterruptedException e) {
			System.err
					.println("InterruptedException occured in consumer thread "
							+ name + e.toString());
		}
	}
}
