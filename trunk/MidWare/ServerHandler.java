

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
 * ����˵Ŀ����� ��Ϊһ����������
 * 
 * @author Roy
 * 
 * �������罻�� �ͽ�����н���
 * 
 */
public class ServerHandler implements Runnable {

	private ServerSocket server;//����������
	private Socket socket;//�û�socket

	private ServerSocket serverSendFile;//�ļ����Ͷ˿ڣ�by-����  2011��4��15��11:59:11���
	//private Socket socket;

	private String root;
	public void setRoot(String root) {
		this.root = root;
	}
	private BlockingQueue<Information> senderQueue;
	private BlockingQueue<Information> receiverQueue;
	private Map<Socket,BlockingQueue> online;
	private Map<String,ServerHandler> onlineUser;//�����û����ͷ��͹ܵ���map

	private ServerSocket serverFile;
	private boolean flag=true;//���Ʒ�����Ƿ������������
	
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
	
	//Ҫ���͵Ķ�������--��Service�������
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
	//�رշ�����
	public void stopServer(){
		try {
			this.server.close();//�ص�������
			serverFlag = false;//�ǵķ���������ѭ�������û�����
		}catch(Exception e) {
			//e.printStackTrace();
		}		
	}
	private boolean serverFlag = true;
	//���ĸ��˿ڿ�������
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
			//����һ��Map��ά�����Ͷ��� socket-HashTable
			Map<Socket,BlockingQueue> online = new Hashtable();
			Map<String,ServerHandler> onlineUser = new Hashtable();
			
			while(serverFlag){
				Socket socket =server.accept();//���ϵĽ�������
				ServerHandler handler = new ServerHandler();
				handler.setSocket(socket);
				handler.setServerFile(serverFile);
				handler.setOnline(online);
				handler.setOnlineUser(onlineUser);
				handler.setRoot(root);
				new Thread(handler).start();//�����߳�
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}//��������˵��߼��������
	public void setServerFile(ServerSocket serverFile) {
		this.serverFile = serverFile;
	}
	//�߳�������ʱ����õķ���
	public void run(){
		
		new Sender(socket,this.senderQueue).start();
		new Receiver(socket,this.receiverQueue).start();


		//serverFileImpl.setSocket(socket);
		//serverFileImpl.setSenderQueue(senderQueue);

		transfer();//�����̴߳�����
	}
	public void transfer(){
		//����һ�������ڲ���
		new Thread(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(flag){
						
					try {
						//����˵ȴ���������
						Object obj = receiverQueue.take();
						if(obj instanceof Information){
							//����ת��-
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
