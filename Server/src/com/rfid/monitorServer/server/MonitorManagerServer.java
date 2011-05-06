package com.rfid.monitorServer.server;

import java.util.List;

import com.rfid.monitorServer.vo.MonitorAreaVo;
import com.rfid.monitorServer.vo.MonitorDeviceVo;

public interface MonitorManagerServer {

	/**
	 * 根据监控器的编号，获取该监控器下的区域，以及区域内的设备编号和设备状态
	 * @param monitorId
	 * @return
	 * @throws Exception 
	 */
	List<MonitorAreaVo> getAllMonitorAreaAndDeviceByMonitorId(Long monitorId) throws Exception;
}
