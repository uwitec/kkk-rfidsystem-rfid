package com.rfid.monitorServer.util;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.rfid.device.server.DeviceServer;
import com.rfid.monitorServer.server.MonitorManagerServer;
import com.rfid.monitorServer.vo.NodeVo;
import com.rfid.test.common.SpringBeanUtils;

/**
 * Handler Description: 封装了逻辑操作，和界面的交互，每个业务调用一个Handler处理
 * 该实例可以以线程启动的同时还可以调用其内部方法发送或者接收数据
 * 
 */
public class ServerHandler extends Thread {
	
	private Socket socket;// 搭建链接
	private BlockingQueue<Object> senderQueue;
	private BlockingQueue<Object> receiverQueue;
	private Sender aSender;
	private Receiver aReceiver;
	private boolean flag;
	Information info;
	private MonitorManagerServer monitorManagerServer;

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

	public MonitorManagerServer getMonitorManagerServer() {
		return monitorManagerServer;
	}

	public void setMonitorManagerServer(
			MonitorManagerServer monitorManagerServer) {
		this.monitorManagerServer = monitorManagerServer;
	}
	// 构造一个Handler，接收信息
	public ServerHandler(Socket aSocket) throws IOException {
		this.socket = aSocket;
		this.senderQueue = new LinkedBlockingQueue<Object>();// 创建一个无限制的阻塞队列
		this.receiverQueue = new LinkedBlockingQueue<Object>();
		aSender = new Sender(socket, senderQueue);
		// aSender.setDaemon(true);
		aReceiver = new Receiver(socket, receiverQueue);
		aReceiver.setDaemon(true);
		aSender.start();
		aReceiver.start();
		flag = true;
		monitorManagerServer = (MonitorManagerServer) SpringBeanUtils
		.getBean("monitorManagerServer");
	}

	public void sendInformation(Information info) throws IOException {
		// System.out.println("prepare to Send a info");
		try {
			System.out.println("Send a info");
			this.senderQueue.put(info);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	
	
	// ++001：reader访问中央服务器，
	//* reader发送读写器的ip给中央服务器
	//中央服务器返回该reader下应该读的标签号，仪器名，状态level的列表；
	private Information getAllNode(String readerIp) throws Exception{
		Information info = new Information();
		info.setType(6);
		
		NodeVo[] nodeVos = monitorManagerServer.getNodeArray(readerIp);
		info.setContent(nodeVos);
		return info;
	}

	private void updateState(NodeVo vo) throws Exception{
		monitorManagerServer.updateDeviceState(vo);
	}
	
	private Information checkState(NodeVo vo)throws Exception{
		Information info = new Information();
		info.setType(3);
		info.setContent(vo);
		return info;
	}
	
	private Information updateDeviceName(NodeVo vo){
		Information info = new Information();
		info.setType(5);
		info.setContent(vo);
		return info;
	}
	
	public void run() { // 服务端处理器就绪时通知客户端处理器可以构建完成
		System.out.println("I am ready");
		while (true) {
			try {// 接收响应，在用户端的处理
				Object obj = receiverQueue.take();// self-blocking to wait a
				String readerIp = this.socket.getInetAddress().toString();
				if(readerIp.indexOf("/")==0)
					readerIp = readerIp.substring(1,readerIp.length()).trim(); 
				if (obj instanceof Information) {
					info = (Information) obj;
					switch (info.getType()) {
					case 1:
						info = getAllNode(readerIp);
						senderQueue.put(info);
						flag = false;
						break;
					case 2:
						NodeVo vo2 = (NodeVo) info.getContent();
//						if(vo2!=null)
//							updateState(vo2);
						monitorManagerServer.updateDeviceNameToNode(6, "陈伟豪");
						flag = false;
						break;
					case 3:
						NodeVo vo3 = (NodeVo) info.getContent();
						if(vo3!=null){
							info = checkState(vo3);
							MapServerHandler.getServerHandler().sendInformation(info);
							//senderQueue.put(info);
							flag = false;
						}
						break;
					case 4:
						NodeVo vo4 = (NodeVo) info.getContent();
						if(vo4!=null){
							updateState(vo4);
							flag = false;
						}
						break;
					case 5:
						NodeVo vo5 = (NodeVo) info.getContent();
						if(vo5!=null){
							info = updateDeviceName(vo5);
							MapServerHandler.getServerHandler().sendInformation(info);
							flag = false;
						}
						break;
					default:
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}// 不断的去提取接收队列里面的对象
			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
