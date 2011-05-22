package com.rfid.server.monitorServer;

import java.util.List;

import com.rfid.device.server.StatusServer;
import com.rfid.monitorServer.server.MonitorManagerServer;
import com.rfid.monitorServer.vo.MonitorAreaVo;
import com.rfid.monitorServer.vo.MonitorDeviceVo;
import com.rfid.monitorServer.vo.NodeVo;
import com.rfid.test.common.SpringBeanUtils;

import junit.framework.TestCase;

public class MonitorServerTest extends TestCase {

	public void testGetAllMonitorAreaAndDeviceByMonitorId() throws Exception, Exception{
		MonitorManagerServer server = (MonitorManagerServer)SpringBeanUtils.getBean("monitorManagerServer");
		List<MonitorAreaVo> malist = server.getAllMonitorAreaAndDeviceByMonitorId(
				Long.parseLong("6002011050602382357"));
		List<MonitorDeviceVo> mdList = malist.get(0).getDeviceList();
		System.out.println(malist.get(0).getAreaVo().getReader().getReaderIp());
		assertNotNull(mdList);
		assertTrue(mdList.size()>0);
		assertNotNull(malist);
		assertTrue(malist.size()>0);
	}
	
	public void testGetNodeList() throws Exception{
		MonitorManagerServer server = (MonitorManagerServer)SpringBeanUtils.getBean("monitorManagerServer");
		List list = server.getNodeList("192.168.1.1");
		assertNotNull(list);
		assertTrue(list.size()>0);
	}
	
	public void testUpdateDeviceState() throws Exception{
		MonitorManagerServer server 
			= (MonitorManagerServer)SpringBeanUtils.getBean("monitorManagerServer");
		NodeVo vo = new NodeVo();
		vo.setB(7);
		vo.setDeviceName("fff");
		vo.setLevel(5);
		server.updateDeviceState(vo);
	}
	
	public void testRemoveNodeMonitor() throws Exception{
		MonitorManagerServer server 
		= (MonitorManagerServer)SpringBeanUtils.getBean("monitorManagerServer");
		NodeVo[] vos = server.getNodeArray("127.0.0.1");
		server.removeNodeMonitor(vos[0]);
	}
}
