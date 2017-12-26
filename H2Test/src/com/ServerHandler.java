package com;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerHandler implements Runnable {

	public static int count = 0;
	Socket socket = null;

	public ServerHandler(Socket socket) {
		count++;
		this.socket = socket;
		System.out.println("用户" + count + "接入");
	}
	@Override
	public void run() {
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(socket.getInputStream());
			while (true) {
				byte[] head = new byte[4];
				bis.read(head);
				byte[] data = new byte[Tool.byteArrayToInt(head)];
				bis.read(data);
				System.out.println(new String(data).trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
