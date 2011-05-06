package com.rfid.monitorServer.server.impl;

import java.util.ArrayList;
import java.util.List;

import com.rfid.device.dao.AreaMonitorDao;
import com.rfid.device.dao.DeviceStatusDao;
import com.rfid.device.dao.MonitorDao;
import com.rfid.device.po.AreaMonitor;
import com.rfid.device.po.DeviceStatus;
import com.rfid.device.po.Monitor;
import com.rfid.device.vo.AreaVo;
import com.rfid.device.vo.ReaderVo;
import com.rfid.monitorServer.server.MonitorManagerServer;
import com.rfid.monitorServer.vo.MonitorAreaVo;
import com.rfid.monitorServer.vo.MonitorDeviceVo;

public class MonitorManagerServerImpl implements MonitorManagerServer {

	private MonitorDao monitorDao;
	private AreaMonitorDao areaMonitorDao;
	private DeviceStatusDao deviceStatusDao;
	
	public DeviceStatusDao getDeviceStatusDao() {
		return deviceStatusDao;
	}

	public void setDeviceStatusDao(DeviceStatusDao deviceStatusDao) {
		this.deviceStatusDao = deviceStatusDao;
	}

	public AreaMonitorDao getAreaMonitorDao() {
		return areaMonitorDao;
	}

	public void setAreaMonitorDao(AreaMonitorDao areaMonitorDao) {
		this.areaMonitorDao = areaMonitorDao;
	}

	public MonitorDao getMonitorDao() {
		return monitorDao;
	}

	public void setMonitorDao(MonitorDao monitorDao) {
		this.monitorDao = monitorDao;
	}

	@SuppressWarnings("unchecked")
	public List<MonitorAreaVo> getAllMonitorAreaAndDeviceByMonitorId(
			Long monitorId) throws Exception {
		List mList = monitorDao.findByMonitorId(monitorId);
		if(!this.hasObjectByList(mList))
				throw new Exception("不存在此监控器");
		Monitor monitor = (Monitor)mList.get(0);
		List<AreaMonitor> amList = areaMonitorDao.findByProperty("monitor.monitorId", monitorId);
		if(!this.hasObjectByList(amList))
			throw new Exception("监控器不存在监控区域");
		List<MonitorAreaVo> maList = new ArrayList<MonitorAreaVo>();
		for(AreaMonitor am : amList)
		{
			MonitorAreaVo maVo = new MonitorAreaVo();
			AreaVo areaVo = am.getArea().toAreaVo();
			ReaderVo readerVo = am.getArea().getReader().toReaderVo();
			areaVo.setReader(readerVo);
			maVo.setAreaVo(areaVo);
			List<DeviceStatus> dsList = deviceStatusDao.findLastByAreaId(areaVo.getAreaId());
			if(!this.hasObjectByList(dsList))
				continue;
//				throw new Exception("区域不存在监控设备");
			for(DeviceStatus ds : dsList){
				MonitorDeviceVo mdVo = new MonitorDeviceVo();
				mdVo.setDeviceId(ds.getDevice().getDeviceId());
				mdVo.setLevel(ds.getStatus().getLevel());
				mdVo.setStatusId(ds.getStatus().getStatusId());
				maVo.getDeviceList().add(mdVo);
			}
			
			maList.add(maVo);
		}
		return maList;
	}
	
	public boolean hasObjectByList(List list){
		if(list==null || list.size()<=0)
			return false;
		return true;
	}

}
