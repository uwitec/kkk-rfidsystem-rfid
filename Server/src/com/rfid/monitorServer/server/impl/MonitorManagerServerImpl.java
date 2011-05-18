package com.rfid.monitorServer.server.impl;

import java.util.ArrayList;
import java.util.List;

import com.rfid.device.dao.AreaDao;
import com.rfid.device.dao.AreaMonitorDao;
import com.rfid.device.dao.DeviceDetailDao;
import com.rfid.device.dao.DeviceStatusDao;
import com.rfid.device.dao.MonitorDao;
import com.rfid.device.dao.NodesDao;
import com.rfid.device.dao.ReaderDao;
import com.rfid.device.po.Area;
import com.rfid.device.po.AreaMonitor;
import com.rfid.device.po.DeviceStatus;
import com.rfid.device.po.Monitor;
import com.rfid.device.po.Nodes;
import com.rfid.device.po.Reader;
import com.rfid.device.server.DeviceServer;
import com.rfid.device.vo.AreaVo;
import com.rfid.device.vo.DeviceDetailVo;
import com.rfid.device.vo.DeviceVo;
import com.rfid.device.vo.ReaderVo;
import com.rfid.monitorServer.server.MonitorManagerServer;
import com.rfid.monitorServer.vo.MonitorAreaVo;
import com.rfid.monitorServer.vo.MonitorDeviceVo;
import com.rfid.monitorServer.vo.NodeVo;

public class MonitorManagerServerImpl implements MonitorManagerServer {

	private MonitorDao monitorDao;
	private AreaMonitorDao areaMonitorDao;
	private DeviceStatusDao deviceStatusDao;
	private ReaderDao readerDao;
	private AreaDao areaDao;
	private DeviceServer deviceServer;
	private NodesDao nodesDao;
	
	public NodesDao getNodesDao() {
		return nodesDao;
	}

	public void setNodesDao(NodesDao nodesDao) {
		this.nodesDao = nodesDao;
	}

	public DeviceServer getDeviceServer() {
		return deviceServer;
	}

	public void setDeviceServer(DeviceServer deviceServer) {
		this.deviceServer = deviceServer;
	}

	public AreaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	public ReaderDao getReaderDao() {
		return readerDao;
	}

	public void setReaderDao(ReaderDao readerDao) {
		this.readerDao = readerDao;
	}

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

	public List<NodeVo> getNodeList(String readerIp) throws Exception {
		List readerList = readerDao.findByReaderIp(readerIp);
		if(readerList == null || readerList.size()<=0)
			new Exception("此读写器Ip不存在");
		Reader reader = (Reader)readerList.get(0);
		List areaList = areaDao.findByProperty("reader.readerid", reader.getReaderid());
		if(areaList == null || areaList.size()<=0)
			new Exception("区域内不存在此读写器");
		Area area = (Area)areaList.get(0);
		List<DeviceVo> dList = deviceServer.getDeviceListByAreaId(area.getAreaId());
		if(dList == null || dList.size()<=0)
			new Exception("此区域内不存在监控设备");
		List<NodeVo> nvoList = new ArrayList<NodeVo>();
		for(DeviceVo dv : dList){
			List nList = nodesDao.findByProperty("device.deviceId", dv.getDeviceId());
			Nodes nodes = (Nodes)nList.get(0);
			NodeVo nv = new NodeVo();
			nv.setB(nodes.getNodeId());
			DeviceDetailVo ddVo 
				= deviceServer.getDeviceDetailByDeviceId(dv.getDeviceId());
			nv.setDeviceName(ddVo.getDeviceName());
			nvoList.add(nv);
		}
		return nvoList;
	}

	public NodeVo[] getNodeArray(String readerIp) throws Exception {
		List<NodeVo> list = getNodeList(readerIp);
		if(list == null || list.size()<=0)
			return new NodeVo[0];
		return (NodeVo[]) list.toArray();
	}

}
