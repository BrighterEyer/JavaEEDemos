package com;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9999);
			while (true) {
				Socket socket = serverSocket.accept();
				ServerHandler hm = new ServerHandler(socket);
				Thread t = new Thread(hm);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
