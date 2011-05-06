package com.rfid.device.server;

import com.rfid.device.vo.MonitorVo;

public interface MonitorServer {

	/**
	 * 增加监控器(一个监控器，监控多个区域)
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	Long addMonitor(MonitorVo vo) throws Exception;
	
	/**
	 * 指派区域由哪个监控器监控
	 * @param areaId
	 * @param monitorId
	 * @throws Exception 
	 */
	void assignAreaToMonitor(Long monitorId,Long...areaIds) throws Exception;
	
}
