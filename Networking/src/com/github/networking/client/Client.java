package com.github.networking.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.github.networking.utils.IOUtils;
import com.github.networking.utils.Safe;

public class Client implements Runnable {

	private final String host;

	private final int port;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
		Socket connection = null;
		BufferedReader br = null;
		PrintWriter writer = null;
		try {
			connection = createClientConnection(host, port);
			System.out.println("Client " + Thread.currentThread().getName()
					+ " connected to sever on host " + host + ":" + port);
			sendMessage(connection, "Hello from "
					+ Thread.currentThread().getName());
			readMessage(connection);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Safe.close(connection);
			Safe.close(br);
			Safe.close(writer);
		}
	}

	private void sendMessage(Socket connection, String message)
			throws IOException {
		PrintWriter writer = IOUtils
				.createPrintWriterFromClientConnection(connection);
		writer.println(message);
		writer.println();// End of client message
		writer.flush();
	}

	private void readMessage(Socket connection) throws IOException {
		BufferedReader br = IOUtils
				.createBufferedReaderFromClientConnection(connection);
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(Thread.currentThread().getName() + ": " + line);
		}
	}

	private Socket createClientConnection(String host, int port)
			throws UnknownHostException, IOException {
		Socket connection = new Socket(host, port);
		return connection;
	}

	public static void main(String[] args) {
		Thread ct1 = new Thread(new Client("localhost", 4444));
		ct1.setName("C1");
		ct1.start();

		Thread ct2 = new Thread(new Client("localhost", 4444));
		ct2.setName("C2");
		ct2.start();

		Thread ct3 = new Thread(new Client("localhost", 4444));
		ct3.setName("C3");
		ct3.start();
	}
}
