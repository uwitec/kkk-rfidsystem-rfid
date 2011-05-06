package com.rfid.server.device;

import com.rfid.device.server.DeviceManagerServer;
import com.rfid.device.server.DeviceServer;
import com.rfid.test.common.SpringBeanUtils;

import junit.framework.TestCase;

public class DeviceManagerServerTest extends TestCase{

	public void testAssignDeviceToArea() throws Exception{
		DeviceManagerServer server = (DeviceManagerServer)SpringBeanUtils.getBean("deviceManagerServer");
		server.assignDeviceToArea(
				Long.parseLong("2002011042403393164")
			  , Long.parseLong("3002011050403205539"));
	}
	
	public void testModifyDeviceState() throws Exception{
		DeviceManagerServer server = (DeviceManagerServer)SpringBeanUtils.getBean("deviceManagerServer");
		server.modifyDeviceState(
				Long.parseLong("2002011050505111287")
				, Long.parseLong("2102011042403303091"));
//				Long.parseLong("2002011042403393164")
//			  , Long.parseLong("3002011050403205539"));
	}
	
	public void testAssignDeviceToUser() throws Exception{
		DeviceManagerServer server = (DeviceManagerServer)SpringBeanUtils.getBean("deviceManagerServer");
		server.assignDeviceToUser(
				Long.parseLong("2002011042403393164")
				, Long.parseLong("1002011042402555728"));
	}
}
