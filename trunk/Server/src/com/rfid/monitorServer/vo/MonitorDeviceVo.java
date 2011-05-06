package com.rfid.monitorServer.vo;

public class MonitorDeviceVo {

	private Long deviceId;
	private Long statusId;
	private int level;
	public MonitorDeviceVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MonitorDeviceVo(Long deviceId, Long statusId, int level) {
		super();
		this.deviceId = deviceId;
		this.statusId = statusId;
		this.level = level;
	}
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
