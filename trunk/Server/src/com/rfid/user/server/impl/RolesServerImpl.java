package com.rfid.user.server.impl;

import java.util.List;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;
import com.rfid.common.constants.EnumConstant.RoleType;
import com.rfid.user.dao.RolesDao;
import com.rfid.user.po.Roles;
import com.rfid.user.server.RolesServer;
import com.rfid.user.vo.RolesVo;

public class RolesServerImpl implements RolesServer {

	RolesDao rolesDao;

	public RolesDao getRolesDao() {
		return rolesDao;
	}

	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}

	public RolesVo getRoleByRoleId(Long roleId) {
		List list = rolesDao.findByRoleId(roleId);
		if (list == null || list.size() == 0)
			return null;
		Roles r = (Roles) list.get(0);
		return r.toRolesVo();
	}

	public RolesVo getRoleByRoleName(String roleName) {
		List list = rolesDao.findByRoleName(roleName);
		if (list == null || list.size() == 0)
			return null;
		Roles r = (Roles) list.get(0);
		return r.toRolesVo();
	}

	public Long addRole(RolesVo vo) throws Exception {
		try {
			if (vo == null)
				throw new Exception("值对象为空");
			Roles role = new Roles();
			if (vo.getRoleName() != null && !"".equals(vo.getRoleName()))
				role.setRoleName(vo.getRoleName());
			else
				throw new Exception("请输入类型名");
			if(vo.getRoleId()!=null && vo.getRoleId()>0){
				List rList = rolesDao.findByRoleId(vo.getRoleId());
				if(rList!=null && rList.size()>0)
					throw new Exception("已存在该类型");
				else
					role.setRoleId(vo.getRoleId());
			}else
				throw new Exception("请输入类型编号");
			if(vo.getIsEnable()!=null && vo.getIsEnable()==0)
				role.setIsEnable(vo.getIsEnable());
			else
				role.setIsEnable(1);
			if (vo.getRoleNote() != null)
				role.setRoleNote(vo.getRoleNote());
			rolesDao.save(role);
			return vo.getRoleId();
		} catch (Exception ex) {
			throw ex;
		}
	}

}
