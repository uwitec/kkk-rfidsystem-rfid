package com.rfid.monitorServer.vo;

import java.util.ArrayList;
import java.util.List;

import com.rfid.device.vo.AreaVo;

public class MonitorAreaVo {

	private List<MonitorDeviceVo> deviceList = new ArrayList<MonitorDeviceVo>();
	private AreaVo areaVo;
	public MonitorAreaVo() {
		super();
	}
	public MonitorAreaVo(List<MonitorDeviceVo> deviceList, AreaVo areaVo) {
		super();
		this.deviceList = deviceList;
		this.areaVo = areaVo;
	}
	public List<MonitorDeviceVo> getDeviceList() {
		return deviceList;
	}
	public void setDeviceList(List<MonitorDeviceVo> deviceList) {
		this.deviceList = deviceList;
	}
	public AreaVo getAreaVo() {
		return areaVo;
	}
	public void setAreaVo(AreaVo areaVo) {
		this.areaVo = areaVo;
	}
	
}
