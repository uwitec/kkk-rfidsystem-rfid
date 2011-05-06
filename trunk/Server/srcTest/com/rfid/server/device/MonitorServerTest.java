package com.rfid.server.device;

import java.util.List;

import com.rfid.device.server.AreaServer;
import com.rfid.device.server.MonitorServer;
import com.rfid.device.server.ReaderServer;
import com.rfid.device.vo.AreaVo;
import com.rfid.device.vo.MonitorVo;
import com.rfid.test.common.SpringBeanUtils;

import junit.framework.TestCase;


public class MonitorServerTest extends TestCase {

	public void testAddMonitor() throws Exception{
		MonitorServer server = (MonitorServer)SpringBeanUtils.getBean("monitorServer");
		MonitorVo vo = new MonitorVo();
		AreaServer aServer = (AreaServer)SpringBeanUtils.getBean("areaServer");
		List<AreaVo> list = aServer.findAllArea();
		vo.setAreas(list);
		vo.setMonitorIp("192.168.0.1");
		vo.setMonitorPort("8080");
		server.addMonitor(vo);
	}
	
	public void testAssignAreaToMonitor() throws Exception{
		MonitorServer server = (MonitorServer)SpringBeanUtils.getBean("monitorServer");
		server.assignAreaToMonitor(
				Long.parseLong("6002011050602382357")
			  , Long.parseLong("3002011050402592162"));
		//3002011050403212637
	}
}
