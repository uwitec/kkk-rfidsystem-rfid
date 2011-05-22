package com.rfid.device.server;

import java.util.List;

import com.rfid.device.vo.NodesVo;
import com.rfid.monitorServer.vo.NodeVo;

public interface NodesServer {

	boolean addNodes(Long deviceId,int nodeId);
	
	List<NodesVo> getAllNodeList();
	
	void checkNodesByDeivceId(Long deviceId);

	NodeVo findNodeByDeviceId(Long deviceId);
}
