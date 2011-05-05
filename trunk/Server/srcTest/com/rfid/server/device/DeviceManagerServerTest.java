package com.rfid.server.device;

import com.rfid.device.server.DeviceManagerServer;
import com.rfid.device.server.DeviceServer;
import com.rfid.test.common.SpringBeanUtils;

import junit.framework.TestCase;

public class DeviceManagerServerTest extends TestCase{

	public void testAssignDeviceToArea(){
		DeviceManagerServer server = (DeviceManagerServer)SpringBeanUtils.getBean("deviceManagerServer");
//		server.assignDeviceToArea(deviceId, areaId)
	}
}
