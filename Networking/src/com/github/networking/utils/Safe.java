package com.github.networking.utils;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Safe {

	public static void close(Closeable connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Socket connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(ServerSocket connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
