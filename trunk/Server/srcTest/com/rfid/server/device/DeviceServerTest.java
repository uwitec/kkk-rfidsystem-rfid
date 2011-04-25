package com.rfid.server.device;

import java.util.List;

import com.rfid.device.dao.DeviceDao;
import com.rfid.device.server.DeviceServer;
import com.rfid.device.vo.DeviceDetailVo;
import com.rfid.device.vo.DeviceVo;
import com.rfid.test.common.MyLazyTestCase;
import com.rfid.test.common.SpringBeanUtils;

import junit.framework.TestCase;

public class DeviceServerTest extends MyLazyTestCase {
	
	public void testGetAllDevice(){
		DeviceServer server = (DeviceServer)SpringBeanUtils.getBean("deviceServer");
		List list = server.getAllDevice();
		assertNotNull(list);
		assertTrue(list.size()>0);
	}
	
	public void testGetDeviceByDeviceId(){
		DeviceServer server = (DeviceServer)SpringBeanUtils.getBean("deviceServer");
		DeviceVo vo = server.getDeviceByDeviceId(Long.parseLong("2002011042403393164"));
		System.out.print(vo.getId());
		assertNotNull(vo);
	}
	
	public void testGetDeviceDetailByDeviceName(){
		DeviceServer server = (DeviceServer)SpringBeanUtils.getBean("deviceServer");
		DeviceDetailVo vo = server.getDeviceDetailByDeviceName("电脑服务器");
		System.out.print(vo.getManufactory());
		assertNotNull(vo);
	}
}
