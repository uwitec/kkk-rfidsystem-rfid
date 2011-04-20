package com.rfid.server.user;

import java.util.List;

import com.rfid.test.common.MyLazyTestCase;
import com.rfid.test.common.SpringBeanUtils;
import com.rfid.user.server.UserServer;
import com.rfid.user.vo.UserDetailVo;
import com.rfid.user.vo.UsersVo;

public class UserServerTest extends MyLazyTestCase{
	
	public void testGetUserByLoginName() throws Exception{
		UserServer server = (UserServer)SpringBeanUtils.getBean("userServer");
		UsersVo vo = server.getUsersByLoginNamePassWord("admin", "admin");
		int i = vo.getUserRoles().size();
		assertTrue(i>0);
	}
	
	public void testGetUserDetailByLoginNamePassWord() throws Exception{
		UserServer server = (UserServer)SpringBeanUtils.getBean("userServer");
		UserDetailVo vo = server.getUserDetailByLoginNamePassWord("admin", "admin");
		int i = vo.getUsersVo().getUserRoles().size();
		assertTrue(i>0);
	}
	
	public void testGetUserDetailByUserId() throws Exception{
		UserServer server = (UserServer)SpringBeanUtils.getBean("userServer");
		UserDetailVo vo = server.getUserDetailByUserId(Long.parseLong("20110412152528001"));
		int i = vo.getUsersVo().getUserRoles().size();
		assertTrue(i>0);
	}
	
	public void testGetAllUsers(){
		UserServer server = (UserServer)SpringBeanUtils.getBean("userServer");
		List<UsersVo> list = server.getAllUsers();
		assertTrue(list.size()>0);
	}
	
	public void testGetAllUserDetail(){
		UserServer server = (UserServer)SpringBeanUtils.getBean("userServer");
		List<UserDetailVo> list = server.getAllUserDetail();
		assertTrue(list.size()>0);
	}

	public void testRegUsers(){
		
	}
	

}
