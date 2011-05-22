
package demo;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.locks.*;
import  java.util.concurrent.*;

import com.rfid.monitorServer.util.Information;
import com.rfid.monitorServer.vo.NodeVo;

public class deal extends Thread{

	public LinkedList <Equipment> EquipmentList = new LinkedList();        //暂时为实现
	//private ArrayBlockingQueue<E> 
	
	private BlockingQueue <T> blkq= new ArrayBlockingQueue(20);
	private final ReentrantLock lock = new ReentrantLock();
	private boolean isRun;
	private se se;
	public HashMap map = new HashMap(); //((byte)id,node)
	private ClientHandler cHandler;


	public void initall(){  //初始化所有属性
		se = new se(blkq);
		se.start();
		Equipment eq = new Equipment();
		byte[] tt = new byte[]{
                 (byte)48,(byte)-1,(byte)-1,(byte)-1,(byte)48
        };
		eq.idset(tt);
		map.put("/192.168.1.103",eq.Client);
		EquipmentList.add(eq);
		Information info;
		try{
			cHandler = new ClientHandler( new Socket( "localhost", 10000 ) );
			cHandler.dl = this;
			cHandler.sendInformation( new Information(1,null) );

			Object obj = cHandler.receiverQueue.take();// self-blocking to wait a received object
			if( obj instanceof Information ){
				//初始化列表
				System.out.println("===========init node and equipment=======");
					info = (Information)obj;
					//According to the type of information to execute this responding transaction or operation
					if( info.getType() == 6){//改 初始化NodeQueue 

						NodeVo[] ViVo = (NodeVo[])info.getContent();
						for(int i = 0 ; i < ViVo.length; i++){
							Node nd = new Node();
							byte[] tmpid = intToBytes(ViVo[i].getB());
							nd.IDset((int)tmpid[3]);
							System.out.print("++++++++++tmpid:" + tmpid[0] + " " + tmpid[1] + " " + tmpid[2]+ " " +tmpid[3]);
							nd.NodeIPset(new byte[]{(byte)tmpid[3], (byte)-1, (byte)-1, (byte)-1, (byte)tmpid[3]});
							String tmp = ViVo[i].getDeviceName();
							for(int k=0;k<(10-ViVo[i].getDeviceName().length());k++){
								tmp+=" ";
							}
							nd.DataFieldset(tmp.getBytes());
							eq.NodeQueue.add(nd);
							eq.map.put((byte)tmpid[3], nd);
						}
					//	tt = intToBytes(ViVo.getB());
					//		System.out.print("====================tt0:"+tt[0]);
					//	n = (Node)dl.EquipmentList.get(0).map.get(tt[0]);
					//	ViVo.setLevel(n.level);
					//	Information tmp = new Information(4,ViVo);
					//	senderQueue.put(tmp);
					
					
					}
			}
			cHandler.start();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		//标签出始化 
		/*Node nd1 = new Node();
		nd1.IDset(7);
		nd1.NodeIPset(new byte[]{(byte)7, (byte)-1, (byte)-1, (byte)-1, (byte)7});
		nd1.DataFieldset(new byte[]{(byte)11, (byte)6, (byte)56, (byte)10, (byte)10, (byte)1});
		eq.NodeQueue.add(nd1);
		eq.map.put((byte)7, eq.NodeQueue.get(0));

		
		Node nd2 = new Node();
		nd2.IDset(6);
		nd2.NodeIPset(new byte[]{(byte)6, (byte)-1, (byte)-1, (byte)-1, (byte)6});
		nd2.DataFieldset(new byte[]{(byte)-7, (byte)-13, (byte)75, (byte)-35, (byte)17, (byte)-3});
		eq.NodeQueue.add(nd2);
		eq.map.put((byte)6, eq.NodeQueue.get(1));*/
		

		
		isRunset(true);
		RfidOrder or = new RfidOrder();
		
	
	}
	public boolean isRunget(){
		return isRun;
	}
	public void isRunset(boolean value){
		isRun = value;	
	}
	private void DealOrder(Client client, byte[] orderbuff, int starIndex, int endIndex)
        {

            /*if (MesssageOut != null)
            {
                string strMessage = "Data Receive From IP:" + Encoding.Default.GetString(orderbuff, starIndex, endIndex);
                MesssageOut(strMessage);
            }*/
            if (RfidOrder.IsReaderInstrustion(orderbuff, starIndex))
            {
                ReaderInstruction readerInstruction =  RfidOrder.GetReaderInstrustion(orderbuff, starIndex);
                //if (client.IsCheck)
                {	
					//System.out.print("ip:"+client.ip);
                    Equipment equipment = client.Equipment;
                   // lock (equipment)
                    {
                        switch (readerInstruction.instructionType)
                        {
                            case Confirm_Communication:

                                if (equipment.Operational == rfidOperational.Read)
                                {

                                    SendData(client, RfidOrder.GetPCInstrustion(PCInstructionType.Request_Read));
                                }
                                else if (equipment.Operational == rfidOperational.Edit)
                                {
                                    SendData(client, RfidOrder.GetPCInstrustion(PCInstructionType.Request_Edit));
                                }
                                else
                                {
                                    SendData(client, RfidOrder.GetPCInstrustion(PCInstructionType.Request_EndCommunication));
                                }

                                break;
                            case Request_EndCommunication:
                                //equipment.Operational = rfidOperational.NULL;
                                SendData(client, RfidOrder.GetPCInstrustion(PCInstructionType.Confirm_EndCommunication));
								if(equipment.Operational == rfidOperational.Read){
									CheckFlag(client);
								}
                                if (equipment.EditNodeCountget() > 0)
                                {
                                    SetEdit(equipment);
                                }
                                else
                                {
                                    equipment.Operational = rfidOperational.NULL;
                                }
                                break;
                            case Confirm_Edit:
                                if (equipment.EditNodeCountget() > 0)
                                {
                                    SendEditNode(equipment);
                                }
                                else
                                    SendData(client, RfidOrder.GetPCInstrustion(PCInstructionType.Request_EndCommunication));
                                break;
                            case Edit_Failure:

                                //修改失败处理
                                if (equipment.EditNodeCountget() > 0)
                                {
                                    SendEditNode(equipment);
                                }
                                else
                                    SendData(client, RfidOrder.GetPCInstrustion(PCInstructionType.Request_EndCommunication));
                                break;
                            case Edit_Succeed:
                                //修改成功写入数据库
                                /*if (UpdateDBNode(equipment.NodeOffset.ID,equipment.NodeOffset.NodeIP,equipment.NodeOffset.DataField))
                                { 
                                    //修改节点表
                                    UpdateNode(equipment.NodeOffset);
                                }*/
                                if (equipment.EditNodeCountget() > 0)
                                {
                                    SendEditNode(equipment);
                                }
                                else
                                    SendData(client, RfidOrder.GetPCInstrustion(PCInstructionType.Request_EndCommunication));

                                break;
                            case Confirm_EndCommunication://若果该读写器修改列表有节点，就转入修改模式
                                if (equipment.EditNodeCountget() > 0)
                                {
                                    SetEdit(equipment);
                                }else
                                {
                                    equipment.Operational = rfidOperational.NULL;
                                }
                                break;
                        }
                    }
                }
                
            }
            else //if (RfidOrder.IsDataPackageInstrustion(orderbuff, starIndex))
            {
                //if (client.IsCheck)
                {
                    Equipment equipment = client.Equipment;
                    RFIDDataPackage dataPackage = RfidOrder.GetRFIDDataPackage(orderbuff, starIndex);
					Node tmpn;
					tmpn = (Node)equipment.map.get(dataPackage.nodeIP[0]);
					if(tmpn != null){	//存在的节点
						System.out.println("====================="+tmpn.IDget());
						tmpn.flag = 1;
						tmpn.level = 5;
					}
					else{//改 ，new Information 报告异常，新节点
						tmpn.level = 0;
						System.out.println("=========NewNode=========");
						NodeVo tmpcon = new NodeVo((int)dataPackage.nodeIP[0], tmpn.DataField.toString(), 0 );
						cHandler.sendInformation( new Information(2,tmpcon) );
						//cHandler.sendInformation( new Information(2,null) );
						
					}
                    /*Node node = null;
                    try
                    {
                        _NodeRWLock.EnterReadLock();
                        node = _NodeList.Single((n) => CompareBytes(n.NodeIP, dataPackage.nodeIP));
                    }
                    catch (InvalidOperationException)
                    {
                        //记录未验证节点接收事件**************
                        SendData(client, RfidOrder.GetPCInstrustion(PCInstructionType.Confirm_ReadData));
                        return;
                    }
                    finally
                    {
                        _NodeRWLock.ExitReadLock();
                    }
                    if (node != null)
                    {
                        lock (node)
                        {
                            if (!CompareBytes(node.DataField, dataPackage.dataField))
                            {
                                //写入数据库
                                if (UpdateDBNode(node.ID, node.NodeIP, dataPackage.dataField))
                                {
                                    Array.Copy(dataPackage.dataField, node.DataField, node.DataField.Length);
                                }
                            }
                            node.LastRefreshTime = DateTime.Now;
                        }
                        AddHistory(equipment.IP, node.NodeIP, dataPackage.dataField);
                    }*/
                    SendData(client, RfidOrder.GetPCInstrustion(PCInstructionType.Confirm_ReadData));
                }
                /*else
                {
                    client.DisConnect();
                }*/
            }
        }


		private void CheckFlag(Client client){
			Equipment equipment = client.Equipment;
			for(int i = 0; i < equipment.NodeQueue.size(); i++){
				Node tmpn = equipment.NodeQueue.get(i);
				if(tmpn.flag == 0){
					System.out.println("++++++++++++++++++node:"+tmpn.IDget()+"warning!!!");
					//改 发现异常  new Information
					NodeVo tmpcon = new NodeVo((int)tmpn.IDget(), tmpn.DataField.toString(), 10 );
					cHandler.sendInformation( new Information(2,tmpcon) );
					tmpn.level = 10;
				}
				tmpn.flag = 0;
			}


		}

		private int Findbyte(byte[] orderbuff, int starIndex, int endIndex, byte target1, byte target2)
        {

            for (int p = starIndex; p < endIndex; p++)
            {
                    if (orderbuff[p] == target1)
                        if (orderbuff[p + 1] == target2)
                            return p;
                
            }
            return -1;
        }


		private void GetOrder(Client client, byte[] orderbuff, int orderlen)
        {
            int pf, pl;
            int p = 0;
                pf = Findbyte(orderbuff, p, orderlen, (byte)0x47, (byte)0x74);
                if (pf != -1)
                {
                    pl = Findbyte(orderbuff, p, orderlen, (byte)0x8E, (byte)0xE8);
                    if (pl != -1)
                    {
                        pl++;
                        p = pl + 1;
                        //澶勭悊鏁版嵁锛岃繑鍥炴寚浠わ紱
						//System.out.println(" DealOrder : pf="+pf+"pl="+pl);
                        DealOrder(client, orderbuff, pf, pl);
                    }else System.out.println("unknow order");
                }
        }

	private void Getbyte(){
		try{
			T t = blkq.take();
			Object client;
			//System.out.println("ip:"+t.socket.getInetAddress().toString());
			client = map.get(t.socket.getInetAddress().toString());
			if(client == null){System.out.println("Getbyte:client=NUll");}
			GetOrder((Client)client , t.b, t.b.length);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void run(){
		while(true){
			Getbyte();
		}
	}


	private void SetEdit(Equipment equipment){
		//
		//
		//
		if (equipment.Operational == rfidOperational.NULL)
        {
			equipment.Operational = rfidOperational.Edit;
			SendData(equipment.Client, RfidOrder.GetPCInstrustion(PCInstructionType.Request_Communication));
         }
	}

	private void SendEditNode(Equipment equipment){
		//
		//
		equipment.PopEditNode();
		RFIDDataPackage t =new RFIDDataPackage();
            
                    t.readerIP = equipment.idget();
                    t.nodeIP = equipment.NodeOffsetget().NodeIPget();

                    t.dataField = equipment.NodeOffsetget().DataFieldget();
                
        SendData(equipment.Client, RfidOrder.GetDataPackageInstrustion(t));
	}
	private void SendData(Client client, byte[] Bytes)
    {
			try{
				client.socket = new Socket("192.168.1.103",8000);
			}catch(Exception ex){
				ex.printStackTrace();
			}
           client.senddata(client.socket, Bytes);
    }

	private void SetRead(Equipment equipment){
		if (equipment.Operational == rfidOperational.NULL)
        {
			equipment.Operational = rfidOperational.Read;
			SendData(equipment.Client, RfidOrder.GetPCInstrustion(PCInstructionType.Request_Communication));
        }
	}

	public void ReadCycleMethod() //需要一个线程运行
        {	
			Equipment equipment ;
			int eqnum = EquipmentList.size();
			int tmp = 0;
			//此处需要初始化 equipment.client;
			System.out.println("EquipmentList.size="+eqnum);
            while (isRunget())
            {
				lock.lock();
                try
                {
                    
                    for(int i = 0; i < eqnum;  i++ )
                    {
                        equipment = EquipmentList.get(i) ;
                            /*if (equipment.LReadTime != null)
                            {
                                if ( (DateTime.Now - (DateTime)equipment.LReadTime).TotalSeconds >= equipment.Refresh_Cycle)
                                {
                                    SetRead(equipment);
                                }
                            }
                            else*/
							System.out.print("SetRead\n");
                                SetRead(equipment);
                            
                        
                    }
                }
                finally
                {
                    lock.unlock();
                }

                try
				{	//休眠5秒
					System.out.print("sleep 5000\n");
					Thread.sleep(5000);
				}catch (Exception e)
				{
				}
				//修改标签
				/*tmp++;
				if(tmp==5){
					equipment = EquipmentList.get(0);
					Node nd = equipment.NodeQueue.get(0);
					nd.DataFieldset(new byte[]{(byte)11, (byte)6, (byte)56, (byte)10, (byte)10, (byte)1});
					equipment.PushEditNode(nd);
				
				}*/
            }
        }
		
		public static byte[] intToBytes(int num) {
			byte[] b = new byte[4];
			for (int i = 0; i < 4; i++) {
				b[i] = (byte) (num >>> (24 - i * 8));
			}
			return b;
		}

		public static void main(String[] args)throws Exception{		
			deal dl= new deal();
			dl.initall();
			dl.start();
			dl.ReadCycleMethod();

		}
}
