package com.rfid.server.user;

import com.rfid.test.common.MyLazyTestCase;
import com.rfid.test.common.SpringBeanUtils;
import com.rfid.user.server.UserServer;
import com.rfid.user.vo.UsersVo;

public class UserServerTest extends MyLazyTestCase{
	
	public void testGetUserByLoginName(){
		UserServer server = (UserServer)SpringBeanUtils.getBean("userServer");
		UsersVo vo = server.getUsersByLoginName("admin");
		int i = vo.getUserRoles().size();
		assertTrue(i>0);
	}

}
