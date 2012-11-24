package com.github.networking.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.github.networking.utils.Safe;

public class Server implements Runnable {

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
				try {
					System.out.println("Server accepted connection from "
							+ connection.getInetAddress().getHostAddress()
							+ ":" + connection.getPort());

					Thread workerThread = new WorkerThread(connection);

					exec.execute(workerThread);
				} finally {
					Safe.close(connection);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ss != null) {
				Safe.close(ss);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		Thread serverThread = new Thread(new Server(4444));
		serverThread.start();
	}
}
