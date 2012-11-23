package com.github.networking.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.github.networking.utils.IOUtils;
import com.github.networking.utils.Safe;

public class WorkerThread extends Thread {

	private final Socket connection;

	public WorkerThread(Socket connection) {
		this.connection = connection;
	}

	public void run() {
		try {
			while (!connection.isClosed()) {
				handleRequest(connection);
			}
		} catch (IOException e) {
			e.printStackTrace();

			Safe.close(connection);
		}
	}

	private void handleRequest(Socket connection) throws IOException {
		String message = readMessageFromClient(connection);
		sendMessageToClient(connection, message);
	}

	private String readMessageFromClient(Socket socket) throws IOException {
		BufferedReader br = IOUtils
				.createBufferedReaderFromClientConnection(socket);
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			if (line.isEmpty()) {
				break;
			}
			sb.append(line);
		}
		return sb.toString();
	}

	private void sendMessageToClient(Socket socket, String message)
			throws IOException {
		PrintWriter writer = IOUtils
				.createPrintWriterFromClientConnection(socket);
		writer.println(message);
		writer.println();
	}
}
