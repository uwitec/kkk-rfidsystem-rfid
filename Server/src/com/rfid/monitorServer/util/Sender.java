package com.rfid.monitorServer.util;


import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

/**
 * Sender
 * description: respond for sender the content which is in the queue to the destination directed by socket 
 *  
 */
public class Sender extends Thread{
	
	private Socket socket;
	private BlockingQueue<Object> sendingQueue;//拿出要发送的消息的阻塞队列
	ObjectOutputStream oos;

	
	public Sender(){}
	public Sender(Socket socket,BlockingQueue<Object> senderQueue){
		this.socket = socket;
		this.sendingQueue = senderQueue;
		try{
			oos=new ObjectOutputStream( socket.getOutputStream() );
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	public void run(){
		//通过object流把对象写到网络输出流中
		try {
			while( true ){//从队列中提取一个对象放到socket中
				
				System.out.println("Send a object");
				Object obj = sendingQueue.take(); 
				oos.writeObject(obj);
				oos.flush();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
			
	}
	//getters and setters......
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public BlockingQueue<Object> getQ() {
		return sendingQueue;
	}
	public void setQ(BlockingQueue<Object> q) {
		this.sendingQueue = q;
	}
}
