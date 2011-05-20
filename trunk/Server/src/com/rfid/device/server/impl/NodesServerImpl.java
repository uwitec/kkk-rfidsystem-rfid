package com.rfid.device.server.impl;

import java.util.ArrayList;
import java.util.List;

import com.rfid.device.dao.DeviceDao;
import com.rfid.device.dao.DeviceDetailDao;
import com.rfid.device.dao.NodesDao;
import com.rfid.device.po.Device;
import com.rfid.device.po.DeviceDetail;
import com.rfid.device.po.Nodes;
import com.rfid.device.server.NodesServer;
import com.rfid.device.vo.NodesVo;

public class NodesServerImpl implements NodesServer {

	private DeviceDao deviceDao;
	private NodesDao nodesDao;
	private DeviceDetailDao deviceDetailDao;
	
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

	public void checkNodesByDeivceId(Long deviceId) {
		// TODO Auto-generated method stub
		
	}

	public List<NodesVo> getAllNodeList() {
		//TODO
		return null;
//		List<Nodes> nList = nodesDao.findAll();
//		if(nList == null || nList.size()<=0)
//			return new ArrayList<NodeVo>(0);
//		List<NodeVo> voList = new ArrayList<NodeVo>();
//		for(Nodes n : nList){
//			NodeVo vo = new NodeVo();
//			Device d = n.getDevice();
//			DeviceDetail dd = (DeviceDetail)d.getDeviceDetails().iterator().next();
//			vo.setDeviceName(dd.getDeviceName());
//			vo.setB(n.getNodeId());
//			vo.setLevel(n.g)
//			voList.add(vo);
//		}
//		return voList;
	}

}
