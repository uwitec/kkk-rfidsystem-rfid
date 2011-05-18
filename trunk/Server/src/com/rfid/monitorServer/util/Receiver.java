package com.rfid.monitorServer.util;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

/**
 * Receiver description: receive information from the source directed by socket
 * and then put it into queue
 */
public class Receiver extends Thread {

	private Socket socket;
	private BlockingQueue<Object> receivingQueue;// 存放要接收的消息的阻塞队列
//	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	public Receiver() {
	}

	public Receiver(Socket socket, BlockingQueue<Object> aQueue) {
		this.socket = socket;
		this.receivingQueue = aQueue;
		try {
//			oos = new ObjectOutputStream(socket.getOutputStream()); 
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void run() {
		try {
			while (true) { // 从socket中获取一个对象放到队列中
				Object obj = ois.readObject();
				receivingQueue.put(obj);
				System.out.println("get a object");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// getters and setters......
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setAQueue(BlockingQueue<Object> aQueue) {
		this.receivingQueue = aQueue;
	}

	public BlockingQueue<Object> getAQueue() {
		return receivingQueue;
	}
}
