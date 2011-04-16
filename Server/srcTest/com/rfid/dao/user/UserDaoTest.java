package com.rfid.dao.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rfid.device.po.Device;
import com.rfid.device.po.DeviceUser;
import com.rfid.test.common.MyLazyTestCase;
import com.rfid.test.common.SpringBeanUtils;
import com.rfid.user.dao.UsersDao;
import com.rfid.user.po.Roles;
import com.rfid.user.po.UserRole;
import com.rfid.user.po.Users;

import junit.framework.TestCase;

public class UserDaoTest extends MyLazyTestCase {

	public void testAddUser(){
		Users user = new Users();
		UsersDao userdao =  (UsersDao)SpringBeanUtils.getBean("usersDao");
		userdao.save(user);
	}
	
	public void testFindByLoginName(){
		UsersDao userdao =  (UsersDao)SpringBeanUtils.getBean("usersDao");
		Users user = userdao.findByLoginName("admin").get(0);
		Iterator it = user.getUserRoles().iterator();
		List<UserRole> roles = new ArrayList();
		while(it.hasNext()){
			roles.add((UserRole)it.next());
		}
		UserRole userrole = roles.get(0);
		Roles role = userrole.getRoles();
		int i = roles.size();
		assertTrue(i>0);
	}
	
	public void testUserAll(){
		UsersDao userdao =  (UsersDao)SpringBeanUtils.getBean("UsersDAO");
		Users user = userdao.findByLoginName("admin").get(0);
		Iterator it = user.getDeviceUsers().iterator();
		List<DeviceUser> devices = new ArrayList();
		while(it.hasNext()){
			devices.add((DeviceUser)it.next());
		}
		DeviceUser deviceUser = devices.get(0);
		Device device = deviceUser.getDevice();
		int i = devices.size();
		assertTrue(i>0);
	}
}
