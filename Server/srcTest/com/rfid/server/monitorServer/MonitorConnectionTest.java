package com.rfid.server.monitorServer;

import java.io.IOException;
import java.net.ServerSocket;

import com.rfid.monitorServer.util.ServerHandler;
import com.rfid.monitorServer.util.ServerStartThread;

public class MonitorConnectionTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket aSocket = new ServerSocket( 10000 );
		while(true){
			ServerHandler sHandler = new ServerHandler( aSocket.accept() );
			sHandler.start();
		}
	}

}
