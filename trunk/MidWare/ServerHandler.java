

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 服务端的控制器 作为一个调度中心
 * 
 * @author Roy
 * 
 * 进行网络交互 和界面进行交互
 * 
 */
public class ServerHandler implements Runnable {

	private ServerSocket server;//服务器对象
	private Socket socket;//用户socket

	private ServerSocket serverSendFile;//文件发送端口，by-丰子  2011年4月15日11:59:11添加
	//private Socket socket;

	private String root;
	public void setRoot(String root) {
		this.root = root;
	}
	private BlockingQueue<Information> senderQueue;
	private BlockingQueue<Information> receiverQueue;
	private Map<Socket,BlockingQueue> online;
	private Map<String,ServerHandler> onlineUser;//保存用户名和发送管道的map

	private ServerSocket serverFile;
	private boolean flag=true;//控制服务端是否继续处理请求
	
	public ServerHandler(){
		
		this.senderQueue=new LinkedBlockingQueue();
		this.receiverQueue= new LinkedBlockingQueue();
	}
	
	public void setFlag(boolean b) {
		this.flag = b;
	}
	
	public void setSocket(Socket socket){
		this.socket=socket;
	}
	public Socket getSocket(){
		return this.socket;
	}
	public void setServerSocket(ServerSocket server) {
		this.server = server;
	}
	public ServerSocket getServerSocket() {
		return this.server;
	}
	public void setOnline(Map online){
		this.online=online;
	}
	public void setOnlineUser(Map<String,ServerHandler> onlineUser){
		this.onlineUser=onlineUser;
	}
	public Map<String,ServerHandler> getOnlineUser(){
		return this.onlineUser;
	}
	
	//要发送的对象。请求--在Service层给调用
	public void sendInfomation(Information info){
		try {
			this.senderQueue.put(info);
		} catch (InterruptedException e) {
			//e.printStackTrace();
		}
	}
	public static void main(String args[]){
		
		ServerHandler server = new ServerHandler();
		server.startOn(8989);
	}
	//关闭服务器
	public void stopServer(){
		try {
			this.server.close();//关掉服务器
			serverFlag = false;//是的服务器不再循环接收用户请求
		}catch(Exception e) {
			//e.printStackTrace();
		}		
	}
	private boolean serverFlag = true;
	//在哪个端口开启服务
	public void startOn(int port){		
		try {
			ServerSocket server = new ServerSocket(port);
			this.setServerSocket(server);
			serverFile = new ServerSocket(9999);
			File rootFile=new File("C:\\ECMFTP");
			if(!rootFile.exists()){
				rootFile.mkdirs();
			}
			root=rootFile.getAbsolutePath();
			//创建一个Map来维护发送队列 socket-HashTable
			Map<Socket,BlockingQueue> online = new Hashtable();
			Map<String,ServerHandler> onlineUser = new Hashtable();
			
			while(serverFlag){
				Socket socket =server.accept();//不断的接收请求
				ServerHandler handler = new ServerHandler();
				handler.setSocket(socket);
				handler.setServerFile(serverFile);
				handler.setOnline(online);
				handler.setOnlineUser(onlineUser);
				handler.setRoot(root);
				new Thread(handler).start();//开启线程
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}//开启服务端的逻辑处理操作
	public void setServerFile(ServerSocket serverFile) {
		this.serverFile = serverFile;
	}
	//线程启动的时候调用的方法
	public void run(){
		
		new Sender(socket,this.senderQueue).start();
		new Receiver(socket,this.receiverQueue).start();


		//serverFileImpl.setSocket(socket);
		//serverFileImpl.setSenderQueue(senderQueue);

		transfer();//开启线程处理类
	}
	public void transfer(){
		//创建一个匿名内部类
		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(flag){
						
					try {
						//服务端等待处理请求
						Object obj = receiverQueue.take();
						if(obj instanceof Information){
							//向下转型-
							Information info = (Information)obj;
							if(info.getType()==Information.CLIENTLOGIN){
							}
						
						}
					}catch (InterruptedException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}	
				}
			}
		}.start();
		
	}
}
