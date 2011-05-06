package com.rfid.dao.device;

import java.util.List;

import com.rfid.device.dao.AreaMonitorDao;
import com.rfid.device.server.MonitorServer;
import com.rfid.test.common.SpringBeanUtils;

import junit.framework.TestCase;

public class AreaMonitorDaoTest extends TestCase {

	public void testFindAreaListByMonitorId(){
		AreaMonitorDao server = (AreaMonitorDao)SpringBeanUtils.getBean("areaMonitorDao");
		List list = server.findAreaListByMonitorId(Long.parseLong("6002011050602382357"));
		System.out.println(list.size());
		assertNotNull(list);
		assertTrue(list.size()>0);
	}
}
