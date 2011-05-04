package com.rfid.device.vo;

import java.util.ArrayList;
import java.util.List;

import com.rfid.user.vo.UsersVo;

public class DeviceVo {

	private Long id;
	private Long deviceId;
	private Integer monitorEnable;
	private List<UsersVo> usersVo = new ArrayList<UsersVo>(0);
	private StatusVo statusVo = new StatusVo();
	
	public DeviceVo() {
		super();
	}
	public DeviceVo(Long id, Long deviceId, Integer monitorEnable,
			List<UsersVo> usersVo, StatusVo statusVo) {
		super();
		this.id = id;
		this.deviceId = deviceId;
		this.monitorEnable = monitorEnable;
		this.usersVo = usersVo;
		this.statusVo = statusVo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}
	public Integer getMonitorEnable() {
		return monitorEnable;
	}
	public void setMonitorEnable(Integer monitorEnable) {
		this.monitorEnable = monitorEnable;
	}
	public List<UsersVo> getUsersVo() {
		return usersVo;
	}
	public void setUsersVo(List<UsersVo> usersVo) {
		this.usersVo = usersVo;
	}
	public StatusVo getStatusVo() {
		return statusVo;
	}
	public void setStatusVo(StatusVo statusVo) {
		this.statusVo = statusVo;
	}
	
	

}
