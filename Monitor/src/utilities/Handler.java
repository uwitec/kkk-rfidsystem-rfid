package utilities;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * Handler
 * Description: 封装了逻辑操作，和界面的交互，每个业务调用一个Handler处理，有的业务要先发送命令，如叫Monitor上交该负责域的信息，
 * 那么，Server端new一个Handler（socket,info）info中包含命令信息。然后进入run()等待Monitor的返回数据。
 * Monitor那方就new一个Handler（socket）等待接收来自Server的命令，再进一步处理
 * 
 */
public class Handler extends Thread{
	private Socket socket;//搭建链接
	private BlockingQueue<Object> senderQueue;
	private BlockingQueue<Object> receiverQueue;
	private Sender aSender = new Sender(socket,senderQueue);
	private Receiver aReceiver = new Receiver(socket,receiverQueue);
	private boolean flag = true;
	
	//构造一个Handler，接收信息
	public Handler(Socket socket){
		this.socket= socket;
		this.senderQueue=new LinkedBlockingQueue<Object>();//创建一个无限制的阻塞队列
		this.receiverQueue=new LinkedBlockingQueue<Object>();
		aSender.start();
		aReceiver.start();
		flag = true;
	}
	
	//构造一个Handler，先发送信息，再等待接收信息
	public Handler(Socket socket, Information initInfo) throws InterruptedException{
		this.socket= socket;
		this.senderQueue=new LinkedBlockingQueue<Object>();//创建一个无限制的阻塞队列
		this.receiverQueue=new LinkedBlockingQueue<Object>();
		aSender.start();
		aReceiver.start();
		senderQueue.put(initInfo);
		flag = true;
	}

	
	public void run(){
		// set connection if no initialization
		while( flag ) {
			try {//接收响应，在用户端的处理
				Object obj = receiverQueue.take();// self-blocking to wait a received object
				if( obj instanceof Information ){
					Information info = (Information)obj;
					//According to the type of information to execute this responding transaction or operation
					if(info.getType()==1){//用户端登陆
						info.setType(0);
						System.out.println("Received");
						senderQueue.put(info);
						flag = false;
					}
					else if( info.getType() == 0){
						System.out.println("Closed");
						flag = false;
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
