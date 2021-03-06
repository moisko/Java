package com.github.networking.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import com.github.networking.utils.IOUtils;
import com.github.networking.utils.Safe;

public class ClientSession extends Thread {

	private final Socket connection;

	public ClientSession(Socket connection) {
		this.connection = connection;
	}

	public void run() {
		BufferedReader br = null;
		PrintWriter writer = null;
		try {
			br = IOUtils.createBufferedReaderFromClientConnection(connection);
			writer = IOUtils.createPrintWriterFromClientConnection(connection);
			while (!connection.isClosed()) {
				// Read what the client has sent to the server
				String message = readMessageFromClient(br);
				// Send back this message
				sendMessageToClient(writer, message);
			}
		} catch (IOException e) {
			System.err
					.println("Error occured while processing the client request: "
							+ e.toString());
		} finally {
			Safe.close(connection);
			Safe.close(br);
			Safe.close(writer);
		}
	}

	private String readMessageFromClient(BufferedReader br) throws IOException {
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

	private void sendMessageToClient(PrintWriter writer, String message)
			throws IOException {
		writer.println(message);
		writer.println();
	}
}
