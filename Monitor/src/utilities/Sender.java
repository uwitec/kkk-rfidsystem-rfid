package utilities;


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
	private BlockingQueue q;//�ó�Ҫ���͵���Ϣ����������
	ObjectOutputStream oos;
	private boolean isAlive=true;
	
	public Sender(){}
	public Sender(Socket socket,BlockingQueue q){
		this.socket=socket;
		this.q=q;
		try{
			oos=new ObjectOutputStream(socket.getOutputStream());
		}catch(Exception ex){
			//ex.printStackTrace();
		}
	}
	
	public void setAlive(boolean isAlive){
		this.isAlive=isAlive;
	}
	
	public void run(){
		//ͨ��object���Ѷ���д�������������
		try {
			while(isAlive){
				Object obj=q.take();
				oos.writeObject(obj);
				oos.flush();
			}
		}catch (Exception ex) {
			//ex.printStackTrace();
		}finally{
			//�ر���Դ

		}
			
	}
	//getters and setters......
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public BlockingQueue getQ() {
		return q;
	}
	public void setQ(BlockingQueue q) {
		this.q = q;
	}
}
