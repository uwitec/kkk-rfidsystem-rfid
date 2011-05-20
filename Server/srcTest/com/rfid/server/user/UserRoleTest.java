package com.rfid.server.user;

import com.rfid.test.common.SpringBeanUtils;
import com.rfid.user.server.UserRoleServer;
import com.rfid.user.server.UserServer;

import junit.framework.TestCase;

public class UserRoleTest extends TestCase {

	public void testAssignUserToRole() throws Exception{
		UserRoleServer server = (UserRoleServer)SpringBeanUtils.getBean("userRoleServer");
		server.assignUserToRole(
				Long.parseLong("1002011042402555728")
				, new Long[]{Long.parseLong("50005")});
	}
}
