

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

/**
 * 发送器
 * @author Roy
 * desc: 接收器线程，从指定的socket的网络输出流读取消息存放到阻塞队列中
 */
public class Receiver extends Thread{
	
	private Socket socket;
	private BlockingQueue q;//存放要接收的消息的阻塞队列
	private ObjectInputStream ois;
	private boolean isAlive=true;
	
	public Receiver(){}
	public Receiver(Socket socket,BlockingQueue q){
		this.socket=socket;
		this.q=q;
		try{
			ois=new ObjectInputStream(socket.getInputStream());
		}catch(Exception ex){
			//ex.printStackTrace();
		}
	}
	
	public void setAlive(boolean isAlive){
		this.isAlive=isAlive;
	}
	
	public void run(){
		try{
			while(isAlive){
				Object obj=ois.readObject();
				q.put(obj);
			}
		}catch(Exception ex){
			//ex.printStackTrace();
		}finally{
			try {
				q.put(socket);//socket下线了
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
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
