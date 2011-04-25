package com.rfid.dao.device;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;
import com.rfid.device.dao.DeviceDao;
import com.rfid.device.dao.StatusDao;
import com.rfid.device.po.Status;
import com.rfid.test.common.SpringBeanUtils;

import junit.framework.TestCase;

public class StatusDaoTest extends TestCase {

	public void testAddStauts(){
		StatusDao dao = (StatusDao)SpringBeanUtils.getBean("statusDao");
		Status s = new Status();
		s.setLevel(5);
		s.setScription("存在");
		s.setStatusId(PoTools.getPoId(PoType.StatusType));
		dao.save(s);
	}
}
