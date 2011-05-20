package com.rfid.monitorServer.server;

import java.util.List;

import com.rfid.monitorServer.vo.MonitorAreaVo;
import com.rfid.monitorServer.vo.MonitorDeviceVo;
import com.rfid.monitorServer.vo.NodeVo;

public interface MonitorManagerServer {

	/**
	 * 根据监控器的编号，获取该监控器下的区域，以及区域内的设备编号和设备状态
	 * @param monitorId
	 * @return
	 * @throws Exception 
	 */
	List<MonitorAreaVo> getAllMonitorAreaAndDeviceByMonitorId(Long monitorId) throws Exception;

	List<NodeVo> getNodeList(String readerIp) throws Exception;
	
	NodeVo[] getNodeArray(String readerIp) throws Exception;

	void updateDeviceState(NodeVo vo) throws Exception;
	
	void checkDeviceByNodeId(int NodeId) throws Exception;
}
