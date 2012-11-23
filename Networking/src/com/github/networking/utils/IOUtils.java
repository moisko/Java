package com.github.networking.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class IOUtils {

	public static BufferedReader createBufferedReaderFromClientConnection(
			Socket connection) throws IOException {
		InputStream is = connection.getInputStream();
		InputStreamReader ir = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(ir);
		return br;
	}

	public static PrintWriter createPrintWriterFromClientConnection(
			Socket connection) throws IOException {
		OutputStream os = connection.getOutputStream();
		PrintWriter writer = new PrintWriter(os, true);
		return writer;
	}
}
