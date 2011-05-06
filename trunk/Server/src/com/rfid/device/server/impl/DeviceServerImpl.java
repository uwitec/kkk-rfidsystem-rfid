package com.rfid.device.server.impl;

import java.util.ArrayList;
import java.util.List;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;
import com.rfid.device.dao.DeviceDao;
import com.rfid.device.dao.DeviceDetailDao;
import com.rfid.device.po.Device;
import com.rfid.device.po.DeviceDetail;
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

	//增加操作
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
	
	@SuppressWarnings("unchecked")
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

	//查询方法
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
	public List<DeviceDetailVo> getAllDeviceDetail() {
		List<DeviceDetail> list = deviceDetailDao.findAll();
		if(list==null || list.size()==0)
			return null;
		List<DeviceDetailVo> listVo = new ArrayList<DeviceDetailVo>();
		for(DeviceDetail dd : list){
			Device d = dd.getDevice();
			DeviceVo dVo = d.toDeviceVo();
			DeviceDetailVo ddVo = dd.toDeviceDetailVo();
			ddVo.setDeviceVo(dVo);
			listVo.add(ddVo);
		}
		return listVo;
	}

	public List<DeviceVo> getAllDeviceMonitor() {
		List<Device> list = deviceDao.findByMonitorEnable(1);
		if(list==null || list.size()==0)
			return null;
		List<DeviceVo> listVo = new ArrayList<DeviceVo>();
		for(Device d : list)
			listVo.add(d.toDeviceVo());
		return listVo;
	}
	
	@SuppressWarnings("unchecked")
	public List<DeviceDetailVo> getAllDeviceDetailMonitor() throws Exception {
		List<DeviceDetail> list = deviceDetailDao.findAll();
		if(list==null || list.size()==0)
			return null;
		List<DeviceDetailVo> listVo = new ArrayList<DeviceDetailVo>();
		for(DeviceDetail dd : list){
			Device d = dd.getDevice();
			if(d==null)
				throw new Exception("数据出错");
			if(d.getMonitorEnable()==1){
				DeviceVo dVo = d.toDeviceVo();
				DeviceDetailVo ddVo = dd.toDeviceDetailVo();
				ddVo.setDeviceVo(dVo);
				listVo.add(ddVo);
			}
		}
		return listVo;
	}
	
	public DeviceDetailVo getDeviceDetailByDeviceId(Long deviceId) {
		DeviceDetail dd = deviceDetailDao.findByDeviceId(deviceId);
		if(dd==null)
			return null;
		return dd.toDeviceDetailVo();
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

	//更新操作
	public boolean updateDevice(DeviceVo vo) throws Exception {
		try{
			Device d = VoToPoTools.toDevice(vo);
			deviceDao.update(d);
			return true;
		}catch(Exception ex)
		{	
			throw ex;
		}
	}

	public boolean updateDeviceDetail(DeviceDetailVo vo) throws Exception {
		// 只更改信息，不更改状态。
		try{
			DeviceDetail dd = VoToPoTools.toDeviceDetail(vo);
			deviceDetailDao.update(dd);
			return true;
		}catch(Exception ex)
		{	
			throw ex;
		}
	}

	

	public boolean deleteDevice(DeviceDetailVo vo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteDevice(DeviceVo vo) {
		// TODO Auto-generated method stub
		return false;
	}



	

}
