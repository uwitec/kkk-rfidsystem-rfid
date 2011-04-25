package com.rfid.user.server.impl;

import java.util.List;

import com.rfid.user.dao.RolesDao;
import com.rfid.user.po.Roles;
import com.rfid.user.server.RolesServer;
import com.rfid.user.vo.RolesVo;

public class RolesServerImpl implements RolesServer{

	RolesDao rolesDao;
	
	public RolesDao getRolesDao() {
		return rolesDao;
	}

	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}

	public RolesVo getRoleByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	public RolesVo getRoleByRoleName(String roleName) {
		// TODO Auto-generated method stub
		List list = rolesDao.findByRoleName(roleName);
		if(list == null || list.size()==0)
			return null;
		Roles r = (Roles)list.get(0);
		return r.toRolesVo();
	}

}
