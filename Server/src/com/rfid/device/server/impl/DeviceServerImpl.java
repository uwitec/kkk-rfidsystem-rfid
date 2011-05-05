package com.rfid.device.server.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;
import com.rfid.device.dao.DeviceDao;
import com.rfid.device.dao.DeviceDetailDao;
import com.rfid.device.po.Device;
import com.rfid.device.po.DeviceDetail;
import com.rfid.device.po.DeviceStatus;
import com.rfid.device.server.DeviceServer;
import com.rfid.device.vo.DeviceDetailVo;
import com.rfid.device.vo.DeviceVo;
import com.rfid.device.vo.VoToPoTools;

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

	public Long addDevice(DeviceVo vo) throws Exception {
		Device d = new Device();
		if(vo==null)
			throw new Exception("输入对象为空");

		d = VoToPoTools.toDevice(vo);
		if(this.hasDeviceByDeviceId(d.getDeviceId()))
			throw new Exception("已存在该设备");
		else{
			d.setDeviceId(PoTools.getPoId(PoType.DeviceType));
			deviceDao.save(d);
			return d.getDeviceId();
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean hasDeviceByDeviceId(Long deviceId){
		List dList = deviceDao.findByDeviceId(deviceId);
		if(dList!=null && dList.size()>0)
			return true;
		else
			return false;
	}
	
	public Long addDeviceDetail(DeviceDetailVo vo) throws Exception {
		
		if(vo==null)
			throw new Exception("输入对象为空");
		
		DeviceDetail dd = new DeviceDetail();
		dd = VoToPoTools.toDeviceDetail(vo);
		Long deviceId = null;
		if(!this.hasDeviceByDeviceId(vo.getDeviceVo().getDeviceId()))
		{
			deviceId = this.addDevice(vo.getDeviceVo());
			if(deviceId == null || deviceId ==0)
				throw new Exception("设备插入失败");
		}
		List dList = deviceDao.findByDeviceId(deviceId);
		dd.setDevice((Device)dList.get(0));
		deviceDetailDao.save(dd);
		return deviceId;
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
		Device d = VoToPoTools.toDevice(vo);
		deviceDao.update(d);
		return false;
	}

	public boolean updateDeviceDetail(DeviceDetailVo vo) {
		// 只更改信息，不更改状态。
		DeviceDetail dd = VoToPoTools.toDeviceDetail(vo);
		deviceDetailDao.update(dd);
		return false;
	}

	

	public boolean deleteDevice(DeviceDetailVo vo) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<DeviceDetailVo> getAllDeviceDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	public DeviceDetailVo getDeviceDetailByDeviceId(Long deviceId) {
		// TODO Auto-generated method stub
		return null;
	}

}
