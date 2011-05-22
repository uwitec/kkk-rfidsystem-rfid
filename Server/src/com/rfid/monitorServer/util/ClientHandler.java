package com.rfid.monitorServer.util;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * Handler
 * Description: 封装了逻辑操作，和界面的交互，每个业务调用一个Handler处理
 * 该实例可以以线程启动的同时还可以调用其内部方法发送或者接收数据
 * 
 */
public class ClientHandler extends Thread{
	private Socket socket;//搭建链接
	private BlockingQueue<Object> senderQueue;
	private BlockingQueue<Object> receiverQueue;
	private Sender aSender;
	private Receiver aReceiver;
	private boolean flag;
	Information info;
	
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public BlockingQueue<Object> getSenderQueue() {
		return senderQueue;
	}

	public void setSenderQueue(BlockingQueue<Object> senderQueue) {
		this.senderQueue = senderQueue;
	}

	public BlockingQueue<Object> getReceiverQueue() {
		return receiverQueue;
	}

	public void setReceiverQueue(BlockingQueue<Object> receiverQueue) {
		this.receiverQueue = receiverQueue;
	}

	public Sender getaSender() {
		return aSender;
	}

	public void setaSender(Sender aSender) {
		this.aSender = aSender;
	}

	public Receiver getaReceiver() {
		return aReceiver;
	}

	public void setaReceiver(Receiver aReceiver) {
		this.aReceiver = aReceiver;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Information getInfo() {
		return info;
	}

	public void setInfo(Information info) {
		this.info = info;
	}

	//构造一个Handler，接收信息
	public ClientHandler(Socket aSocket) throws InterruptedException{
		this.socket = aSocket;	
		this.senderQueue = new LinkedBlockingQueue<Object>();//创建一个无限制的阻塞队列
		this.receiverQueue = new LinkedBlockingQueue<Object>();
		aSender = new Sender( socket, senderQueue );
		aReceiver = new Receiver( socket, receiverQueue );
		aSender.start();
		aReceiver.start();
		flag = true;
		System.out.println("Construction Success");
	}
	
	public void sendInformation(Information info){
		//System.out.println("prepare to Send a info");
		try {
			System.out.println("Send a info");
			this.senderQueue.put(info);
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
		
	}
	
	public void run(){
		System.out.println("I am ready");
		while( flag ) {
			try {//接收响应，在用户端的处理
				Object obj = receiverQueue.take();// self-blocking to wait a received object
				System.out.println("Take a object");
				if( obj instanceof Information ){
					info = (Information)obj;
					//According to the type of information to execute this responding transaction or operation
					switch( info.getType()){
					case 1:
						info.setType(1);
						System.out.println("Received");
						senderQueue.put(info);
						flag = false;
						break;
					case 2:
					default:	
					}
				}
			}catch (InterruptedException e) {
				//e.printStackTrace();
			}//不断的去提取接收队列里面的对象		
		}
	}	
}
