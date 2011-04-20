package com.rfid.webService.user;

import com.rfid.test.common.MyLazyTestCase;
import com.rfid.test.common.SpringBeanUtils;
import com.rfid.user.vo.UsersVo;
import com.rfid.user.webservice.UserWebService;

public class UserWebServiceTest extends MyLazyTestCase{
	
	public void testGetUserByLoginNamePassWord() throws Exception{
//		UserWebService wb = new UserWebServiceImpl();
		UserWebService server = (UserWebService)SpringBeanUtils.getBean("userWebService");
		UsersVo vo = server.getUserByLoginNamePassWord("admin","");
		int i = vo.getUserRoles().size();
		assertTrue(i>0);
	}
	
	public void testIsLoginUser() throws Exception{
		UserWebService server = (UserWebService)SpringBeanUtils.getBean("userWebService");
		assertTrue(server.isLoginUser("admin", "admin"));
	}

}
