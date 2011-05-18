package com.rfid.monitorServer.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStartThread extends Thread {

	private static final int SERVER_PORT = 10000;
	
	public void run() {
		try {
			ServerHandler();// 启动开启服务，监听
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ServerHandler() throws Exception, IOException{
		ServerSocket aSocket = new ServerSocket( SERVER_PORT );
//		aSocket.setSoTimeout(20000);
		while(true){
			ServerHandler sHandler = new ServerHandler( aSocket.accept() );
			sHandler.start();
		}
	}
	

}
