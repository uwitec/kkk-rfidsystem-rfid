package com.rfid.device.server.impl;

import java.util.List;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;
import com.rfid.device.dao.AreaDao;
import com.rfid.device.dao.AreaMonitorDao;
import com.rfid.device.dao.MonitorDao;
import com.rfid.device.po.Area;
import com.rfid.device.po.AreaMonitor;
import com.rfid.device.po.Monitor;
import com.rfid.device.server.MonitorServer;
import com.rfid.device.vo.AreaVo;
import com.rfid.device.vo.MonitorVo;
import com.rfid.device.vo.VoToPoTools;

public class MonitorServerImpl implements MonitorServer {

	private MonitorDao monitorDao;
	private AreaDao areaDao;
	private AreaMonitorDao areaMonitorDao;
	
	public AreaMonitorDao getAreaMonitorDao() {
		return areaMonitorDao;
	}

	public void setAreaMonitorDao(AreaMonitorDao areaMonitorDao) {
		this.areaMonitorDao = areaMonitorDao;
	}

	public AreaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	public MonitorDao getMonitorDao() {
		return monitorDao;
	}

	public void setMonitorDao(MonitorDao monitorDao) {
		this.monitorDao = monitorDao;
	}

	public boolean hasMonitorByMonitorId(Long monitorId){
		List list = monitorDao.findByMonitorId(monitorId);
		if(list==null || list.size()<=0)
			return false;
		return true;
	}
	
	public Long addMonitor(MonitorVo vo) throws Exception{
		Monitor monitor = VoToPoTools.toMonitor(vo);
		if(hasMonitorByMonitorId(vo.getMonitorId()))
			throw new Exception("已存在该监视器");
		monitor.setMonitorId(PoTools.getPoId(PoType.MonitorType));
		if(vo.getAreas()==null || vo.getAreas().size()<=0)
		{
			monitorDao.save(monitor);
		}else{
			List<AreaVo> list = vo.getAreas();
			for(AreaVo aVo : list){
				List aList = areaMonitorDao.findByProperty("area.areaId", aVo.getAreaId());
				if(aList!=null && aList.size()>0)
					throw new Exception("编号为:"+aVo.getAreaId()+"区域已被分配");
				Area a = areaDao.findByAreaId(aVo.getAreaId()).get(0);
				AreaMonitor am = new AreaMonitor();
				am.setArea(a);
				am.setMonitor(monitor);
				monitor.getAreaMonitors().add(am);
			}
			monitorDao.save(monitor);
		}
		return monitor.getMonitorId();
	}

	public void assignAreaToMonitor(Long monitorId, Long... areaIds) throws Exception {
		List mList = monitorDao.findByMonitorId(monitorId);
		if(mList==null || mList.size()<=0)
			throw new Exception("不存在该监控器");
		Monitor monitor = (Monitor)mList.get(0);
		for(Long areaId : areaIds){
			List aList = areaDao.findByAreaId(areaId);
			if(aList==null || aList.size()<=0)
				throw new Exception("编号为:"+areaId+"区域不存在");
			List amaList = areaMonitorDao.findByProperty("area.areaId", areaId);
			if(amaList!=null && amaList.size()>0)
				throw new Exception("编号为:"+areaId+"区域已被分配");
			Area area = (Area)aList.get(0);
			AreaMonitor am = new AreaMonitor();
			am.setArea(area);
			am.setMonitor(monitor);
			areaMonitorDao.save(am);
		}
	}
}
