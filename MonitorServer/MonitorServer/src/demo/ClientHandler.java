package demo;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.rfid.monitorServer.util.Information;
import com.rfid.monitorServer.vo.NodeVo;
/**
 * Handler
 * Description: 封装了逻辑操作，和界面的交互，每个业务调用一个Handler处理
 * 该实例可以以线程启动的同时还可以调用其内部方法发送或者接收数据
 * 
 */
public class ClientHandler extends Thread{
	private Socket socket;//搭建链接
	public BlockingQueue<Object> senderQueue;
	public BlockingQueue<Object> receiverQueue;
	private Sender aSender;
	private Receiver aReceiver;
	private boolean flag;
	public deal dl;
	Information info;
	
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
	
	
public static byte[] intToBytes(int num) {
	byte[] b = new byte[4];
	for (int i = 0; i < 4; i++) {
	   b[i] = (byte) (num >>> (24 - i * 8));
	}
	return b;
}

	public void run(){
		System.out.println("I am ready");
		while( flag ) {
			try {//接收响应，在用户端的处理
				Object obj = receiverQueue.take();// self-blocking to wait a received object
				System.out.println("Take a object");
				NodeVo ViVo;
				Node n;
				byte[] tt;

				if( obj instanceof Information ){
					info = (Information)obj;
					//According to the type of information to execute this responding transaction or operation
					switch( info.getType()){
						//改
					case 3://查看信息；
						ViVo = (NodeVo)info.getContent();
						tt = intToBytes(ViVo.getB());
							System.out.print("====================tt0:"+tt[3]);
						n = (Node)dl.EquipmentList.get(0).map.get(tt[3]);
						ViVo.setLevel(n.level);
						Information tmp = new Information(4,ViVo);
						senderQueue.put(tmp);
						//flag = false;
						break;

					case 5: //修改信息
						ViVo = (NodeVo)info.getContent();
						tt = intToBytes(ViVo.getB());
							System.out.print("====================tt0:"+tt[3]);
						n = (Node)dl.EquipmentList.get(0).map.get(tt[3]);
						n.DataFieldset(ViVo.getDeviceName().getBytes());
						dl.EquipmentList.get(0).PushEditNode(n);
						break;
					case 7:		//del
						ViVo = (NodeVo)info.getContent();
						tt = intToBytes(ViVo.getB());
						n = (Node)dl.EquipmentList.get(0).map.get(tt[3]);
						for(int i = 0; i<dl.EquipmentList.get(0).NodeQueue.size(); i++){
							if(n.IDget() == dl.EquipmentList.get(0).NodeQueue.get(i).IDget()){
								dl.EquipmentList.get(0).NodeQueue.remove(i);
								dl.EquipmentList.get(0).map.remove((byte)tt[3]);
								break;
							}
						}
						break;
					
					case 8:		//add
						ViVo = (NodeVo)info.getContent();
						tt = intToBytes(ViVo.getB());
						Node nd = new Node();
						nd.IDset((int)tt[3]);
						System.out.print("++++++++++tmpid:" + tt[0] + " " + tt[1] + " " + tt[2]+ " " +tt[3]);
						nd.NodeIPset(new byte[]{(byte)tt[3], (byte)-1, (byte)-1, (byte)-1, (byte)tt[3]});
						String tmpname = ViVo.getDeviceName();
						for(int k=0;k<(10-ViVo.getDeviceName().length());k++){
							tmpname+=" ";
						}
						nd.DataFieldset(tmpname.getBytes());
						dl.EquipmentList.get(0).NodeQueue.add(nd);
						dl.EquipmentList.get(0).map.put((byte)tt[3], nd);
						break;

					default:	
					}
				}
			}catch (InterruptedException e) {
				//e.printStackTrace();
			}//不断的去提取接收队列里面的对象		
		}
	}	
}
