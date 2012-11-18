package com.github.networking.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

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
		Socket connection = null;
		BufferedReader br = null;
		PrintWriter writer = null;
		try {
			connection = new Socket(HOST, PORT);
			br = IOUtils.createBufferedReaderFromClientConnection(connection);
			writer = IOUtils.createPrintWriterFromClientConnection(connection);
			// Begin the communication with the server
			sendMessage(writer, "<client " + name + "> Hello Server");
			sendMessage(writer, "<client " + name + "> By Server");
			readMessage(br);
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

	private void sendMessage(PrintWriter writer, String message) {
		writer.println(message);
		writer.flush();
	}

	private void readMessage(BufferedReader br) throws IOException {
		String line;
		while ((line = br.readLine()) != null) {
			if (line.equals("<server> Bye!")) {
				break;
			}
			System.out.println(line);
		}
	}
}
