package com.rfid.monitorServer.vo;

import java.io.Serializable;

public class NodeVo implements Serializable{

	public NodeVo(int b, String deviceName, int level) {
		super();
		this.b = b;
		this.deviceName = deviceName;
		this.level = level;
	}
	public NodeVo() {
		super();
	}
	/**
	 * 对应节点编码
	 */
	int b;
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	String deviceName;
	int level;
}
