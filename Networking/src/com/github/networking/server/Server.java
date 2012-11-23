package com.github.networking.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.github.networking.utils.IOUtils;
import com.github.networking.utils.Safe;

public class Server implements Runnable {

	private static final int NTHREAD = 100;

	private static final Executor exec = Executors.newFixedThreadPool(NTHREAD);

	private static final ConcurrentMap<Integer, Socket> CONNECTION_POOL = new ConcurrentHashMap<Integer, Socket>();

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

				CONNECTION_POOL.put(connection.getPort(), connection);

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

							CONNECTION_POOL.remove(connection);

							Safe.close(connection);
						}
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

	private void handleRequest(Socket connection) throws IOException {
		String message = readMessageFromClient(connection);
		for (Integer port : CONNECTION_POOL.keySet()) {
			if (port != connection.getPort()) {
				Socket socket = CONNECTION_POOL.get(port);
				sendMessageToClient(socket, message);
			}
		}
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
		writer.flush();
	}

	public static void main(String[] args) throws IOException {
		Thread serverThread = new Thread(new Server(4444));
		serverThread.start();
	}
}
