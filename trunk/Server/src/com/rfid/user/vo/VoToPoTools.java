package com.rfid.user.vo;

import com.rfid.user.po.Roles;
import com.rfid.user.po.Users;

public class VoToPoTools {
	
	public static Users toRoles(UsersVo vo){
		Users u = null;
		if(vo == null)
			return null;
		u = new Users();
		if(vo.getId()!=null)
			u.setId(vo.getId());
		if(vo.getLoginName() != null && "".equals(vo.getLoginName()))
			u.setLoginName(vo.getLoginName());
		if(vo.getLoginPassword()!=null && "".equals(vo.getLoginName()))
			u.setLoginPassword(vo.getLoginPassword());
		if(vo.getUserid()!=null && vo.getUserid()!=0)
			u.setUserid(vo.getUserid());
		return u;
	}

	public static Roles toRoles(RolesVo vo){
		Roles r = null;
		if(vo == null)
			return null;
		r = new Roles();
		if(vo.getId()!=null)
			r.setId(vo.getId());
		if(vo.getIsEnable()!=null && vo.getIsEnable()!=0)
			r.setIsEnable(vo.getIsEnable());
		if(vo.getRoleId()!=null && vo.getRoleId()!=0)
			r.setRoleId(vo.getRoleId());
		if(vo.getRoleName()!=null && "".equals(vo.getRoleName()))
			r.setRoleName(vo.getRoleName());
		if(vo.getRoleNote()!=null && "".equals(vo.getRoleNote()))
			r.setRoleNote(vo.getRoleNote());
		return r;
	}
}
