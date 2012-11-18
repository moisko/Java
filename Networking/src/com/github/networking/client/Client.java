package com.github.networking.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.github.networking.utils.IOUtils;
import com.github.networking.utils.Safe;

public class Client implements Runnable {

	private static final String HOST = "localhost";

	private static final int PORT = 4444;

	private final String name;

	public Client(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("Client " + getClientName() + " started.");

		Socket connection = null;
		BufferedReader br = null;
		PrintWriter writer = null;
		try {
			connection = createSocketConnection(HOST, PORT);
			System.out.println("Client " + getClientName()
					+ " connected to sever on host " + HOST + ":" + PORT);
			sendMessage(connection, "<client " + name + "> Hello Server");
			readMessage(connection);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Safe.close(connection);
			Safe.close(br);
			Safe.close(writer);
		}
	}

	public String getClientName() {
		return name;
	}

	private void sendMessage(Socket connection, String message)
			throws IOException {
		PrintWriter writer = IOUtils
				.createPrintWriterFromClientConnection(connection);
		writer.println(message);
		writer.flush();
	}

	private void readMessage(Socket connection) throws IOException {
		BufferedReader br = IOUtils
				.createBufferedReaderFromClientConnection(connection);
		String line;
		while ((line = br.readLine()) != null) {
			if (line.equals("<server> EOF")) {
				// Signal for closing the connection has been received
				break;
			}
			System.out.println(line);
		}
	}

	private Socket createSocketConnection(String host, int port)
			throws UnknownHostException, IOException {
		Socket connection = new Socket(HOST, PORT);
		return connection;
	}
}
