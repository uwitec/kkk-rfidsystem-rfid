package com.rfid.device.server.impl;

import java.util.List;

import com.rfid.device.dao.DeviceDao;
import com.rfid.device.dao.NodesDao;
import com.rfid.device.po.Device;
import com.rfid.device.po.Nodes;
import com.rfid.device.server.NodesServer;

public class NodesServerImpl implements NodesServer {

	private DeviceDao deviceDao;
	private NodesDao nodesDao;
	
	public DeviceDao getDeviceDao() {
		return deviceDao;
	}

	public void setDeviceDao(DeviceDao deviceDao) {
		this.deviceDao = deviceDao;
	}

	public NodesDao getNodesDao() {
		return nodesDao;
	}

	public void setNodesDao(NodesDao nodesDao) {
		this.nodesDao = nodesDao;
	}

	public boolean addNodes(Long deviceId, int nodeId) {
		List dList = deviceDao.findByDeviceId(deviceId);
		if(dList == null || dList.size()<=0)
			return false;
		try{
			Device device = (Device)dList.get(0);
			Nodes nodes = new Nodes();
			nodes.setDevice(device);
			nodes.setNodeId(nodeId);
			nodesDao.save(nodes);
			return true;
		}catch(Exception ex){
			return false;
		}
	}

}
