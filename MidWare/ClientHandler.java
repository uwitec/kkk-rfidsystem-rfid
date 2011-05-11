

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
 * @author Roy desc: ��װ���߼��������ͽ���Ľ�����
 */

public class ClientHandler extends Thread{
	
	private Socket socket;//�����
	private BlockingQueue senderQueue;
	private BlockingQueue receiverQueue;
	private boolean flag;
	public ClientHandler(Socket socket){

		this.socket= socket;
		this.senderQueue=new LinkedBlockingQueue();//����һ�������Ƶ���������
		this.receiverQueue=new LinkedBlockingQueue();
	}

	@SuppressWarnings("unchecked")
	public void run(){
		//�������ͺͽ��յ��̡߳��Ѷ��д��ݹ�ȥsenderQueue
		new Sender(socket,senderQueue).start();
		new Receiver(socket,receiverQueue).start();
		flag = true;
		while(flag){
			try {//������Ӧ�����û��˵Ĵ���
				Object obj = receiverQueue.take();
				if(obj instanceof Information){
					Information info = (Information)obj;
					
					if(info.getType()==Information.CLIENTLOGIN){//�û��˵�½
							
					}
					else if(info.getType()==1){
						//����˶Ͽ�����
						try {
							this.socket.close();//�رձ��̵߳�socket
						} catch (IOException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
					}
				}else if(obj instanceof Socket){
					//�˳�����
				}else{
					//�������������
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}//���ϵ�ȥ��ȡ�ռ�������Ķ���
			
		}
	}

	
}
