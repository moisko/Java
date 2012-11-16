package com.github.multithreading;

import com.github.multithreading.api.IStore;
import com.github.multithreading.impl.Store;

public class Main {

	private static final int STORE_CAPACITY = 2;

	public static void main(String[] args) {

		IStore store = new Store(STORE_CAPACITY);

		System.out.println("Store with capacity " + store.getStoreCapacity()
				+ " successfully initialized.");

		createAndStartFirstProducer(store);
		createAndStartFirstConsumer(store);

		createAndStartSecondProducer(store);
		createAndStartSecondConsumer(store);

		createAndStartThirdProducer(store);
		createAndStartThirdConsumer(store);

	}

	private static void createAndStartFirstProducer(IStore store) {
		Producer p1 = new Producer(store, "p1");
		Thread pt1 = new Thread(p1);
		pt1.start();
	}

	private static void createAndStartFirstConsumer(IStore store) {
		Consumer c1 = new Consumer(store, "c1");
		Thread ct1 = new Thread(c1);
		ct1.start();
	}

	private static void createAndStartSecondProducer(IStore store) {
		Producer p2 = new Producer(store, "p2");
		Thread pt2 = new Thread(p2);
		pt2.start();
	}

	private static void createAndStartSecondConsumer(IStore store) {
		Consumer c1 = new Consumer(store, "c2");
		Thread ct1 = new Thread(c1);
		ct1.start();
	}

	private static void createAndStartThirdProducer(IStore store) {
		Producer p3 = new Producer(store, "p3");
		Thread pt3 = new Thread(p3);
		pt3.start();
	}

	private static void createAndStartThirdConsumer(IStore store) {
		Consumer c1 = new Consumer(store, "c3");
		Thread ct1 = new Thread(c1);
		ct1.start();
	}
}
