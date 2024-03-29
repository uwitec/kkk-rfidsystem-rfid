package com.rfid.device.server.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;
import com.rfid.device.dao.AreaDao;
import com.rfid.device.dao.DeviceDao;
import com.rfid.device.dao.DeviceDetailDao;
import com.rfid.device.dao.DeviceStatusDao;
import com.rfid.device.po.Area;
import com.rfid.device.po.Device;
import com.rfid.device.po.DeviceDetail;
import com.rfid.device.po.DeviceStatus;
import com.rfid.device.server.DeviceServer;
import com.rfid.device.server.NodesServer;
import com.rfid.device.vo.DeviceDetailVo;
import com.rfid.device.vo.DeviceVo;
import com.rfid.device.vo.StatusVo;
import com.rfid.device.vo.VoToPoTools;
import com.rfid.monitorServer.server.MonitorManagerServer;
import com.rfid.monitorServer.vo.NodeVo;

public class DeviceServerImpl implements DeviceServer {

	private DeviceDao deviceDao;
	private DeviceDetailDao deviceDetailDao;
	private AreaDao areaDao;
	private DeviceStatusDao deviceStatusDao;
	private MonitorManagerServer monitorManagerServer;
	private NodesServer nodesServer;
	
	public NodesServer getNodesServer() {
		return nodesServer;
	}

	public void setNodesServer(NodesServer nodesServer) {
		this.nodesServer = nodesServer;
	}

	public MonitorManagerServer getMonitorManagerServer() {
		return monitorManagerServer;
	}

	public void setMonitorManagerServer(MonitorManagerServer monitorManagerServer) {
		this.monitorManagerServer = monitorManagerServer;
	}

	public AreaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	public DeviceStatusDao getDeviceStatusDao() {
		return deviceStatusDao;
	}

	public void setDeviceStatusDao(DeviceStatusDao deviceStatusDao) {
		this.deviceStatusDao = deviceStatusDao;
	}

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
			d.setMonitorEnable(1);
			deviceDao.save(d);
//			DeviceDetail dd = new DeviceDetail();
//			Device device = this.findDeviceByDeviceId(d.getDeviceId());
//			dd.setDevice(device);
//			deviceDetailDao.save(dd);
			return d.getDeviceId();
		}
	}
	
	@SuppressWarnings("unchecked")
	private boolean hasDeviceByDeviceId(Long deviceId){
		List dList = deviceDao.findByMonitorEnableByDeviceId(deviceId);
		if(dList!=null && dList.size()>0){
			return true;
		}
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
		{//如果不存在则新建一个
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
//		DeviceDetailVo vo = new DeviceDetailVo();
//		DeviceVo deviceVo = dd.getDevice().toDeviceVo();
//		StatusVo statusVo = 
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
			if(vo.getDeviceVo()==null){
				throw new Exception("请填写deviceVo");
			}
			else if(vo.getDeviceVo().getDeviceId() == null 
					|| vo.getDeviceVo().getDeviceId()<=0){
				throw new Exception("请填写deviceId");
			}
			Long deviceId = vo.getDeviceVo().getDeviceId();
			DeviceDetail dd = deviceDetailDao.findByDeviceId(deviceId);
			if(dd==null)
				throw new Exception("不存在该仪器");
			if(vo.getBuyer()!=null && !"".equals(vo.getBuyer()))
				dd.setBuyer(vo.getBuyer());
			if(vo.getDeviceName()!=null && !"".equals(vo.getDeviceName()))
				dd.setDeviceName(vo.getDeviceName());
			if(vo.getDeviceNum()!=null && vo.getDeviceNum()>=0)
				dd.setDeviceNum(vo.getDeviceNum());
			if(vo.getManufactory()!=null && !"".equals(vo.getManufactory()))
				dd.setManufactory(vo.getManufactory());
			if(vo.getPrice()!=null && vo.getPrice()>=0)
				dd.setPrice(vo.getPrice());
			if(vo.getPurchaseDate()!=null)
				dd.setPurchaseDate(new Timestamp(vo.getPurchaseDate().getTime()));
			deviceDetailDao.update(dd);
			NodeVo nodeVo = nodesServer.findNodeByDeviceId(deviceId);
			if(nodeVo!=null)
				monitorManagerServer.updateDeviceNameToNode(nodeVo.getB(), dd.getDeviceName());
			return true;
		}catch(Exception ex)
		{	
			throw ex;
		}
	}

	public boolean deleteDeviceDetailByDeviceId(Long deviceId) throws Exception {
		try{
			NodeVo vo = nodesServer.findNodeByDeviceId(deviceId);
			Device device = this.findDeviceByDeviceId(deviceId);
			device.setMonitorEnable(0);
			deviceDao.save(device);
			deviceDetailDao.deleteByDeviceId(deviceId);
			monitorManagerServer.removeNodeMonitor(vo);
			
			return true;
		}catch(Exception ex)
		{
			throw ex;
		}
	}

	public boolean deleteDeviceByDeviceId(Long deviceId) throws Exception {
		try{
			deviceDao.deleteByDeviceId(deviceId);
			return true;
		}catch(Exception ex){
			throw ex;
		}
	}

	public boolean setDeviceMonitorDisable(Long deviceId) throws Exception {
		List list = deviceDao.findByDeviceId(deviceId);
		if(list==null || list.size()==0)
			throw new Exception("不存在该设备");
		Device device = (Device)list.get(0);
		device.setMonitorEnable(0);
		deviceDao.update(device);
		return true;
	}

	public boolean setDeviceMonitorEnable(Long deviceId) throws Exception {
		List list = deviceDao.findByDeviceId(deviceId);
		if(list==null || list.size()==0)
			throw new Exception("不存在该设备");
		Device device = (Device)list.get(0);
		device.setMonitorEnable(1);
		deviceDao.update(device);
		return true;
	}

	public List<DeviceVo> getDeviceListByAreaId(Long areaId) throws Exception {
		List aList = areaDao.findByAreaId(areaId);
		if(aList==null || aList.size()<=0)
			throw new Exception("不存在该区域");
		List<DeviceStatus> list = deviceStatusDao.findLastByAreaId(areaId);
		if(list==null || list.size()<=0)
			return new ArrayList<DeviceVo>();
		List<DeviceVo> voList = new ArrayList<DeviceVo>();
		for(DeviceStatus ds : list){
			DeviceVo vo = ds.getDevice().toDeviceVo();
			voList.add(vo);
		}
		return voList;
	}

	private Device findDeviceByDeviceId(Long deviceId) throws Exception{
		List list = deviceDao.findByDeviceId(deviceId);
		if(list==null || list.size()==0)
			throw new Exception("不存在该设备");
		Device device = (Device)list.get(0);
		return device;
	}
}
