package com.github.networking;

import com.github.networking.client.Client;

public class Main {

	public static void main(String[] args) {
		Thread c1 = new Thread(new Client("C1"));
		c1.start();

		Thread c2 = new Thread(new Client("C2"));
		c2.start();

		Thread c3 = new Thread(new Client("C3"));
		c3.start();
	}
}
