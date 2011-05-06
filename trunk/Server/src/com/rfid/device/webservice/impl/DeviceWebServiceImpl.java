package com.rfid.device.webservice.impl;

import com.rfid.device.server.DeviceServer;
import com.rfid.device.webservice.DeviceWebService;

public class DeviceWebServiceImpl implements DeviceWebService {

	DeviceServer deviceServer;

	public DeviceServer getDeviceServer() {
		return deviceServer;
	}

}
