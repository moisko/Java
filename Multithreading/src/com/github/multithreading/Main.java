package com.github.multithreading;

import com.github.multithreading.api.IStore;
import com.github.multithreading.impl.Store;

public class Main {

	private static final int STORE_CAPACITY = 100;

	public static void main(String[] args) {

		IStore store = new Store(STORE_CAPACITY);

		createAndStartFirstPCPair(store);

		createAndStartSecondPCPair(store);

		createAndStartThirdPCPair(store);
	}

	private static void createAndStartFirstPCPair(IStore store) {
		Producer p1 = new Producer(store, "p1");
		Consumer c1 = new Consumer(store, "c1");
		Thread pt1 = new Thread(p1);
		Thread ct1 = new Thread(c1);
		pt1.start();
		ct1.start();
	}

	private static void createAndStartSecondPCPair(IStore store) {
		Producer p2 = new Producer(store, "p2");
		Consumer c2 = new Consumer(store, "c2");
		Thread pt2 = new Thread(p2);
		Thread ct2 = new Thread(c2);
		pt2.start();
		ct2.start();
	}

	private static void createAndStartThirdPCPair(IStore store) {
		Producer p3 = new Producer(store, "p3");
		Consumer c3 = new Consumer(store, "c3");
		Thread pt3 = new Thread(p3);
		Thread ct3 = new Thread(c3);
		pt3.start();
		ct3.start();
	}
}
