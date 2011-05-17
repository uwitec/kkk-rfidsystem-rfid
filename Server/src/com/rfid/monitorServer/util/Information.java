package com.rfid.monitorServer.util;


import java.io.Serializable;

/**
 * 
 * Description 封装所有的请求及内容
 *
 */
public class Information implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int type; //传输信息的类型
	public Object content;//信息中存放的内容对象 
	
	//设置类型常量以判断该信息的类型及通过此转向对应的处理方法
	public static final int Initilization = 0; // 第一次握手
	public static final int signal1 = 1; // 第一次握手
	public static final int signal2 = 2; // 第一次握手
	
	//构造器  请求类型  内容  
	public Information(int type, Object content) {
		this.type = type;
		this.content = content;
	}
	
	public Information(){}
	
	//Getter and Setter
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Information [content=" + content + ", type=" + type + "]";
	}
	

}
