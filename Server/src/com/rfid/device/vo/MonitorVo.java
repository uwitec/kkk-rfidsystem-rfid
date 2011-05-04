package com.rfid.device.vo;

import java.util.ArrayList;
import java.util.List;


public class MonitorVo {
	private Long id;
	private Long monitorId;
	private String monitorIp;
	private String monitorPort;
	private List<AreaVo> areas = new ArrayList<AreaVo>(0);
	public MonitorVo() {
		super();
	}
	public MonitorVo(Long id, Long monitorId, String monitorIp,
			String monitorPort, List<AreaVo> areas) {
		super();
		this.id = id;
		this.monitorId = monitorId;
		this.monitorIp = monitorIp;
		this.monitorPort = monitorPort;
		this.areas = areas;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMonitorId() {
		return monitorId;
	}
	public void setMonitorId(Long monitorId) {
		this.monitorId = monitorId;
	}
	public String getMonitorIp() {
		return monitorIp;
	}
	public void setMonitorIp(String monitorIp) {
		this.monitorIp = monitorIp;
	}
	public String getMonitorPort() {
		return monitorPort;
	}
	public void setMonitorPort(String monitorPort) {
		this.monitorPort = monitorPort;
	}
	public List<AreaVo> getAreas() {
		return areas;
	}
	public void setAreas(List<AreaVo> areas) {
		this.areas = areas;
	}
}
