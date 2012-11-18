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

public class Server {

	private static final int NTHREAD = 100;

	private static final int SERVER_PORT = 4444;

	private static final Executor exec = Executors.newFixedThreadPool(NTHREAD);

	public static void main(String[] args) throws IOException {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(SERVER_PORT);
			System.out
					.println("Server is now listening on port " + SERVER_PORT);
			while (true) {
				final Socket connection = ss.accept();
				System.out.println("Server accepted connection from "
						+ connection.getInetAddress().getHostAddress() + ":"
						+ connection.getPort());
				// Create a task
				Runnable task = new Runnable() {
					public void run() {
						try {
							handleRequest(connection);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				};
				// Submit this task for execution
				exec.execute(task);
			}
		} finally {
			if (ss != null) {
				Safe.close(ss);
			}
		}
	}// end of main

	private static synchronized void handleRequest(Socket connection)
			throws IOException {
		BufferedReader br = IOUtils
				.createBufferedReaderFromClientConnection(connection);
		PrintWriter writer = IOUtils
				.createPrintWriterFromClientConnection(connection);

		// Read from client
		StringBuilder sb = new StringBuilder();

		String firstLine = readLineFromClient(br);
		sb.append(firstLine + "\n");
		sb.append("<server> Hello!\n");

		String secondLine = readLineFromClient(br);
		sb.append(secondLine + "\n");
		sb.append("<server> Bye!\n");

		// Write the response to the client
		writer.println(sb.toString());
		// Flush
		writer.flush();
	}

	private static String readLineFromClient(BufferedReader br)
			throws IOException {
		return br.readLine();
	}
}
