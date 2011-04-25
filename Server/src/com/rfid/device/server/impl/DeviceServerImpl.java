package com.rfid.device.server.impl;

import java.util.ArrayList;
import java.util.List;

import com.rfid.device.dao.DeviceDao;
import com.rfid.device.dao.DeviceDetailDao;
import com.rfid.device.po.Device;
import com.rfid.device.po.DeviceDetail;
import com.rfid.device.server.DeviceServer;
import com.rfid.device.vo.DeviceDetailVo;
import com.rfid.device.vo.DeviceVo;

public class DeviceServerImpl implements DeviceServer {

	private DeviceDao deviceDao;
	private DeviceDetailDao deviceDetailDao;
	
	public DeviceDetailDao getDeviceDetailDao() {
		return deviceDetailDao;
	}

	public void setDeviceDetailDao(DeviceDetailDao deviceDetailDao) {
		this.deviceDetailDao = deviceDetailDao;
	}

	public DeviceDao getDeviceDao() {
		return deviceDao;
	}

	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	public boolean addDevice(DeviceVo vo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteDevice(DeviceVo vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<DeviceVo> getAllDevice() {
		List<Device> list = deviceDao.findAll();
		if(list==null || list.size()==0)
			return null;
		List<DeviceVo> listVo = new ArrayList<DeviceVo>();
		for(Device d : list)
			listVo.add(d.toDeviceVo());
		return listVo;
	}

	
	@SuppressWarnings("unchecked")
	public DeviceVo getDeviceByDeviceId(Long deviceId) {
		List list = deviceDao.findByDeviceId(deviceId);
		if(list==null || list.size()==0)
			return null;
		Device d = (Device)list.get(0);
		return d.toDeviceVo();
	}

	@SuppressWarnings("unchecked")
	public DeviceDetailVo getDeviceDetailByDeviceName(String deviceName) {
		List list = deviceDetailDao.findByDeviceName(deviceName);
		if(list==null || list.size()==0)
			return null;
		DeviceDetail dd = (DeviceDetail) list.get(0);
		return dd.toDeviceDetailVo();
	}

	public boolean updateDevice(DeviceVo vo) {
		// TODO Auto-generated method stub
		return false;
	}

}
