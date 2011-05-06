package com.rfid.server.device;

import com.rfid.device.server.AreaServer;
import com.rfid.device.server.DeviceServer;
import com.rfid.device.server.ReaderServer;
import com.rfid.device.vo.AreaVo;
import com.rfid.device.vo.ReaderVo;
import com.rfid.test.common.SpringBeanUtils;

import junit.framework.TestCase;

public class AreaServerTest extends TestCase{

	public void testAddArea() throws Exception{
		AreaServer server = (AreaServer)SpringBeanUtils.getBean("areaServer");
		ReaderServer rserver = (ReaderServer)SpringBeanUtils.getBean("readerServer");
		ReaderVo rvo = rserver.getReaderById(Long.parseLong("6102011050403121961"));
//		ReaderVo rvo = rserver.getAllReader().get(0);
		AreaVo vo = new AreaVo();
		vo.setAreaAddress("广州大学理科北楼");
		vo.setReader(rvo);
		vo.setScription("电子楼的贵重设备检测");
		server.addArea(vo);
	}
	
	public void testAssignAreaReader() throws Exception{
		AreaServer server = (AreaServer)SpringBeanUtils.getBean("areaServer");
		server.assignAreaReader(Long.parseLong("3002011050402592162")
				, Long.parseLong("6102011050404212363"));
	}
}
