package demo;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.*;

 
public class Equipment
    {
        int helpBuffLen = 256;

        //private int id;
        public byte[] id;
        public rfidOperational Operational;
        //private DateTime opTime;
        private int Refresh_Cycle;
        //private DateTime lReadTime;
        private LinkedList<Node> EditNodeQueue;
		public LinkedList<Node> NodeQueue;
		public HashMap map = new HashMap();
        private Node NodeOffset;
        private boolean isOnLine;
        public Client Client;
		public String ip;


		public final ReentrantLock lock = new ReentrantLock(); //设备锁


        
        /*public int ID
        {
            get { return id; }
            set { id = value; }
        }*/
        public byte[] idget() { return id; }
        public void idset(byte[] value) { id = value; }
        
        public rfidOperational Operationalget(){ return Operational; }
        public void Operationalset(rfidOperational value){
                /*if (value != Operational && value != rfidOperational.NULL)
                {
                    opTime = DateTime.Now;
                }
                if (_Operational == rfidOperational.Read && value != Operational)
                {
                    lReadTime = DateTime.Now;
                }*/
                Operational = value;
            }
        
        /*internal DateTime? OpTime
        {
            get { return opTime; }
        }*/
        public int Refresh_Cycleget() { return Refresh_Cycle; }
	public void Refresh_Cycleset(int value) { Refresh_Cycle = value; }
        


       /* public DateTime? LReadTime
        {
            get { return lReadTime; }
        }*/
        public int EditNodeCountget() 
		{ 
			return EditNodeQueue.size();
		}
        public Node NodeOffsetget()
		{
			return NodeOffset; 
		}
        

        public boolean IsOnLineget() { return isOnLine; }
        public void set(boolean value) { isOnLine = value; }
        

        /*internal ClientToken Client
        {
            get { return Client; }
            set { Client = value; }
        }*/

        public Equipment()
        {
            isOnLine = false;
            Operational = rfidOperational.NULL;
            id = new byte[RfidOrder.IPLength];
            EditNodeQueue = new LinkedList<Node>();
			NodeQueue = new LinkedList<Node>();
            NodeOffset = null;
            Refresh_Cycle = 5;
			ip = "192.168.1.103";
			Client = new Client(ip);
			Client.Equipment = this;
            //_lReadTime = null;
            //_opTime = null;
            //_Client = null;
        }

        public Node PopEditNode()
        {
           // lock (EditNodeQueue)
            {
                Node node = EditNodeQueue.pop();
                NodeOffset = node;
                return node;
            }
        }
        public void PushEditNode(Node editNode)
        {
            //lock (EditNodeQueue)
            {
                EditNodeQueue.push(editNode);
            }
        }

    }
    
    enum rfidOperational
    {
        Read,
        Edit,
        NULL,
    }
