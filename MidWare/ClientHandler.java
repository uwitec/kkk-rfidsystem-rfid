

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import javax.swing.JOptionPane;


/**
 * 
 * @author Roy desc: 封装了逻辑操作，和界面的交互等
 */

public class ClientHandler extends Thread{
	
	private Socket socket;//搭建链接
	private BlockingQueue senderQueue;
	private BlockingQueue receiverQueue;
	private boolean flag;
	public ClientHandler(Socket socket){

		this.socket= socket;
		this.senderQueue=new LinkedBlockingQueue();//创建一个无限制的阻塞队列
		this.receiverQueue=new LinkedBlockingQueue();
	}

	@SuppressWarnings("unchecked")
	public void run(){
		//启动发送和接收的线程。把队列传递过去senderQueue
		new Sender(socket,senderQueue).start();
		new Receiver(socket,receiverQueue).start();
		flag = true;
		while(flag){
			try {//接收响应，在用户端的处理
				Object obj = receiverQueue.take();
				if(obj instanceof Information){
					Information info = (Information)obj;
					
					if(info.getType()==Information.CLIENTLOGIN){//用户端登陆
							
					}
					else if(info.getType()==1){
						//服务端断开连接
						try {
							this.socket.close();//关闭本线程的socket
						} catch (IOException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
					}
				}else if(obj instanceof Socket){
					//退出操作
				}else{
					//其他的情况处理
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}//不断的去提取收件箱里面的对象
			
		}
	}

	
}
