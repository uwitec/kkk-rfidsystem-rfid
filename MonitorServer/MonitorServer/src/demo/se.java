package demo;

import java.io.*;
import java.net.*;
import  java.util.concurrent.*;

public class se extends Thread{
		public ServerSocket server;
		public Socket socket;
		public InputStream is ;
		public BlockingQueue bq;

	public se(BlockingQueue bq){
		this.bq = bq;
	}
	public void run(){
		
		try{
			 server = new ServerSocket(8000);
			
			// socket = server.accept();
			byte[] b =new byte[70];
			int offset = 0;
			System.out.println("server open");
			while(true){
				T tt = new T();
				socket = server.accept();
				is = socket.getInputStream();
				int len = is.read(b,0,30);
				System.out.print("getdata:");
				for(int i=0; i<len; i++){
					System.out.print((byte)(b[i] & 0xff) + " ");
				}
				tt.socket = socket;
				tt.b = b;
				bq.put(tt);
				System.out.println();
				offset=0;
			}
			
			//socket.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			//资源回收
			try{
				if(is != null){
					is.close();
				}
				if(socket !=null){
					socket.close();
				}
				//onlineList.remove(socket);//下线  异常出现的时候的下线情况
			}catch(Exception ee){
				ee.printStackTrace();
			}
		}
	}
	/*public static void main(String[] args)throws Exception{
		se server = new se();
		server.start();
	}*/
} ;

class T
{
	public Socket socket;
	public byte[] b;
};
