package demo;

import java.io.*;
import java.net.*;

import java.text.SimpleDateFormat;   
import java.util.Date; 

public class Node{

	private int ID;
	public  byte[] NodeIP;
	public byte[] DataField;
	public int flag;
	public int level;
	//private DateTime LastRefreshTime;
		

	
	public static String getPreTime(String sj1, String jj) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}


	public int IDget(){
		return ID;	
	}
	public void IDset(int id){
		ID = id;
	}

	public byte[] NodeIPget(){
		return NodeIP;
	}
	public void NodeIPset(byte[] value){
		NodeIP = value;
	}

	public byte[] DataFieldget(){
		return DataField;
	}
	public void DataFieldset(byte[] value){
		DataField = value;
	}
	
	public int levelget(){
		return level;	
	}
	public void levelset(int id){
		level = id;
	}
	/*public DateTime getLastRefreshTime(){
		return LastRefreshTime;
	}
	public void setLastRefreshTime(DateTime value){
		LastRefreshTime = value;
	}*/

	public Node(){
		NodeIP = new byte[RfidOrder.IPLength];
        DataField = new byte[RfidOrder.DataFieldLength];
		flag = 0;
               // LastRefreshTime = null;
	}
}
