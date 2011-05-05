package com.rfid.device.server.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.rfid.device.dao.AreaDao;
import com.rfid.device.dao.DeviceDao;
import com.rfid.device.dao.DeviceStatusDao;
import com.rfid.device.dao.StatusDao;
import com.rfid.device.po.Area;
import com.rfid.device.po.Device;
import com.rfid.device.po.DeviceStatus;
import com.rfid.device.po.Status;
import com.rfid.device.server.DeviceManagerServer;

public class DeviceManagerServerImpl implements DeviceManagerServer {

	private DeviceDao deviceDao;
	private StatusDao statusDao;
	private AreaDao areaDao;
	private DeviceStatusDao deviceStatusDao;
	
	public DeviceStatusDao getDeviceStatusDao() {
		return deviceStatusDao;
	}

	public void setDeviceStatusDao(DeviceStatusDao deviceStatusDao) {
		this.deviceStatusDao = deviceStatusDao;
	}

	public AreaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	public StatusDao getStatusDao() {
		return statusDao;
	}

	public void setStatusDao(StatusDao statusDao) {
		this.statusDao = statusDao;
	}

	public DeviceDao getDeviceDao() {
		return deviceDao;
	}

	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	@SuppressWarnings("unchecked")
	public void modifyDeviceState(Long deviceId, Long statusId) throws Exception {
		List deviceList = deviceDao.findByDeviceId(deviceId);
		if(deviceList==null || deviceList.size()<=0)
			throw new Exception("不存在该设备");
		List sList = statusDao.findByStatusId(statusId);
		if(sList==null || sList.size()<=0)
			throw new Exception("不存在该状态字");
		Device d = (Device)deviceList.get(0);
		Status s = (Status)sList.get(0);
		DeviceStatus ds = new DeviceStatus();
		//判断是否未检测状态
	}

	public void assignDeviceToArea(Long deviceId, Long areaId) throws Exception {
		this.assignDeviceToArea(deviceId, areaId, null);
	}
	
	@SuppressWarnings("unchecked")
	public void assignDeviceToArea(Long deviceId, Long areaId,Long statusId) throws Exception {
		List dList = deviceDao.findByDeviceId(deviceId);
		if(dList==null || dList.size()<=0)
			throw new Exception("不存在该设备");
		List aList = areaDao.findByAreaId(areaId);
		if(aList==null || aList.size()<=0)
			throw new Exception("不存在该区域");
		Device device = (Device)dList.get(0);
		Area area = (Area)aList.get(0);
		//判断状态编号有无输入
		Status status = new Status();
		if(statusId!=null && statusId!=0){
			//有输入
			List sList = statusDao.findByStatusId(statusId);
			if(sList==null || sList.size()<=0)
				throw new Exception("不存在该状态字");
			status = (Status)sList.get(0);
		}else{
			//无输入
			List sList = statusDao.findByLevel(0);
			if(sList==null || sList.size()<=0)
				throw new Exception("数据出错");
			status = (Status)sList.get(0);
		}
		DeviceStatus ds = new DeviceStatus();
		//通过设备Id查找最新的记录
		List dsList = deviceStatusDao.findLastByDeviceId(deviceId);
		if(dsList==null || dsList.size()<=0){
			//不存在最新记录
			ds.setArea(area);
			ds.setDevice(device);
			ds.setIsEnable(1);
			ds.setMonitorTime(new Timestamp(new Date().getTime()));
			ds.setPreviousId(Long.parseLong("0"));
			ds.setStatus(status);
			deviceStatusDao.save(ds);
		}else{
			//存在最新记录，需要覆盖
			DeviceStatus oldDs = (DeviceStatus)dsList.get(0);
			oldDs.setIsEnable(0);
			Long oldDsId = oldDs.getId();
			deviceStatusDao.update(oldDs);
			ds.setArea(area);
			ds.setDevice(device);
			ds.setIsEnable(1);
			ds.setMonitorTime(new Timestamp(new Date().getTime()));
			ds.setPreviousId(oldDsId);
			ds.setStatus(status);
			deviceStatusDao.save(ds);
		}
	}


}
