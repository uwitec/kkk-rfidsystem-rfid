package com.rfid.dao.user;

import java.util.List;

import com.rfid.test.common.SpringBeanUtils;
import com.rfid.user.dao.UserDetailDao;

import junit.framework.TestCase;

public class UserDetailDaoTest extends TestCase{

	public void testFindAll(){
		UserDetailDao dao = (UserDetailDao)SpringBeanUtils.getBean("userDetailDao");
		List list = dao.findAll();
		assertTrue(list.size()>0);
	}
}
