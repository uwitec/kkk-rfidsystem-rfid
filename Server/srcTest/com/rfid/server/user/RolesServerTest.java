package com.rfid.server.user;

import com.rfid.common.constants.EnumConstant.RoleType;
import com.rfid.test.common.SpringBeanUtils;
import com.rfid.user.dao.UserDetailDao;
import com.rfid.user.po.Roles;
import com.rfid.user.server.RolesServer;
import com.rfid.user.vo.RolesVo;

import junit.framework.TestCase;

public class RolesServerTest extends TestCase {
	
	public void testGetRoleByRoleName(){
		RolesServer server = (RolesServer)SpringBeanUtils.getBean("rolesServer");
		RolesVo r = server.getRoleByRoleName("Admin");
		System.out.println(r.getRoleName());
		assertNotNull(r);
	}
	
	public void testAddRole() throws Exception{
		RolesServer server = (RolesServer)SpringBeanUtils.getBean("rolesServer");
		RolesVo vo = new RolesVo();
		vo.setRoleName("DeviceAdmin");
		vo.setRoleNote("设备管理员");
		RoleType type = RoleType.DeviceType;
		Long roleId = type.getInfo();
		vo.setRoleId(roleId);
		roleId = server.addRole(vo);
		assertTrue(roleId>0);
	}

}
