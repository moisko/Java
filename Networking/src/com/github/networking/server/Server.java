package com.github.networking.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.github.networking.utils.IOUtils;
import com.github.networking.utils.Safe;

public class Server implements Runnable {

	private static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	private static final int NTHREAD = 100;

	private static final Executor exec = Executors.newFixedThreadPool(NTHREAD);

	private final int port;

	public Server(int port) {
		this.port = port;
	}

	@Override
	public void run() {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(port);
			System.out.println("Server is now listening on port " + port);
			while (true) {
				final Socket connection = ss.accept();
				System.out.println("Server accepted connection from "
						+ connection.getInetAddress().getHostAddress() + ":"
						+ connection.getPort());
				// Create a task
				Runnable task = new Runnable() {
					public void run() {
						handleRequest(connection);
					}
				};
				// Submit this task for execution
				exec.execute(task);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ss != null) {
				Safe.close(ss);
			}
		}
	}

	private void handleRequest(Socket connection) {
		try {
			BufferedReader br = IOUtils
					.createBufferedReaderFromClientConnection(connection);
			PrintWriter writer = IOUtils
					.createPrintWriterFromClientConnection(connection);
			// Read from client
			StringBuilder sb = new StringBuilder();
			String firstLine = br.readLine();
			sb.append(firstLine).append(LINE_SEPARATOR);
			sb.append("<server> Hello Client").append(LINE_SEPARATOR);
			sb.append("EOF");
			// Write the response to the client
			writer.println(sb.toString());
			// Flush
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		Thread serverThread = new Thread(new Server(4444));
		serverThread.start();
	}
}
