package utilities;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * Handler
 * Description: ��װ���߼��������ͽ���Ľ�����ÿ��ҵ�����һ��Handler�����е�ҵ��Ҫ�ȷ���������Monitor�Ͻ��ø��������Ϣ��
 * ��ô��Server��newһ��Handler��socket,info��info�а���������Ϣ��Ȼ�����run()�ȴ�Monitor�ķ������ݡ�
 * Monitor�Ƿ���newһ��Handler��socket���ȴ���������Server������ٽ�һ������
 * 
 */
public class Handler extends Thread{
	private Socket socket;//�����
	private BlockingQueue<Object> senderQueue;
	private BlockingQueue<Object> receiverQueue;
	private Sender aSender = new Sender(socket,senderQueue);
	private Receiver aReceiver = new Receiver(socket,receiverQueue);
	private boolean flag = true;
	
	//����һ��Handler��������Ϣ
	public Handler(Socket socket){
		this.socket= socket;
		this.senderQueue=new LinkedBlockingQueue<Object>();//����һ�������Ƶ���������
		this.receiverQueue=new LinkedBlockingQueue<Object>();
		aSender.start();
		aReceiver.start();
		flag = true;
	}
	
	//����һ��Handler���ȷ�����Ϣ���ٵȴ�������Ϣ
	public Handler(Socket socket, Information initInfo) throws InterruptedException{
		this.socket= socket;
		this.senderQueue=new LinkedBlockingQueue<Object>();//����һ�������Ƶ���������
		this.receiverQueue=new LinkedBlockingQueue<Object>();
		aSender.start();
		aReceiver.start();
		senderQueue.put(initInfo);
		flag = true;
	}

	
	public void run(){
		// set connection if no initialization
		while( flag ) {
			try {//������Ӧ�����û��˵Ĵ���
				Object obj = receiverQueue.take();// self-blocking to wait a received object
				if( obj instanceof Information ){
					Information info = (Information)obj;
					//According to the type of information to execute this responding transaction or operation
					if(info.getType()==1){//�û��˵�½
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
