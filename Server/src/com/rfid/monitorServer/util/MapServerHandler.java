package com.rfid.monitorServer.util;

import java.util.HashMap;
import java.util.Map;

public class MapServerHandler {

	private static ServerHandler tempServerHandler;
	
	public static void setServerHandler(ServerHandler temp){
		if(tempServerHandler==null)
			tempServerHandler = temp;
	}
	
	public static ServerHandler getServerHandler(){
		return tempServerHandler;
	}
	
	private static Map<String,ServerHandler> MapServerHandler = new HashMap<String,ServerHandler>(0);
	
	public static void addMapServerHandler(String readIp,ServerHandler handler){
		MapServerHandler.put(readIp, handler);
	}
}
