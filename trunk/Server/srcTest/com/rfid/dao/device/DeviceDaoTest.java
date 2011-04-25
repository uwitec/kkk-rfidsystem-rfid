package com.rfid.dao.device;

import java.util.List;

import com.rfid.device.dao.DeviceDao;
import com.rfid.test.common.MyLazyTestCase;
import com.rfid.test.common.SpringBeanUtils;
import com.rfid.user.server.UserServer;

import junit.framework.TestCase;

public class DeviceDaoTest extends MyLazyTestCase{
	
	public void testGetAllDevice(){
		DeviceDao deviceDao = (DeviceDao)SpringBeanUtils.getBean("deviceDao");
		List list = deviceDao.findAll();
		assertNotNull(list);
		assertTrue(list.size()>0);
	}
}
