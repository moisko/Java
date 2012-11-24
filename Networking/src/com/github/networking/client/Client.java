package com.github.networking.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		BufferedReader consoleReader = null;
		PrintWriter writer = null;
		try {
			connection = createClientConnection(host, port);

			System.out.println("Client " + Thread.currentThread().getName()
					+ " connected to sever on host " + host + ":" + port);

			consoleReader = new BufferedReader(new InputStreamReader(System.in));

			while (!connection.isClosed()) {
				sendMessage(connection, consoleReader);

				br = IOUtils
						.createBufferedReaderFromClientConnection(connection);

				readResponse(connection, br);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Safe.close(connection);
			Safe.close(br);
			Safe.close(consoleReader);
			Safe.close(writer);
		}
	}

	private void sendMessage(Socket connection, BufferedReader consoleReader)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = consoleReader.readLine()) != null) {
			if (line.isEmpty()) {
				String message = sb.toString();
				// Send message
				PrintWriter writer = IOUtils
						.createPrintWriterFromClientConnection(connection);
				writer.println(message);
				writer.println();
				break;
			}
			sb.append(line);
		}
	}

	private void readResponse(Socket connection, BufferedReader br)
			throws IOException {
		String line;
		while ((line = br.readLine()) != null) {
			if (line.isEmpty()) {
				break;
			}
			System.out.println(Thread.currentThread().getName() + ": " + line);
		}
	}

	private Socket createClientConnection(String host, int port)
			throws UnknownHostException, IOException {
		Socket connection = new Socket(host, port);
		return connection;
	}

	public static void main(String[] args) {
		Thread clientThread = new Thread(new Client("localhost", 4444));
		clientThread.setName(args[0]);
		clientThread.start();
	}
}
