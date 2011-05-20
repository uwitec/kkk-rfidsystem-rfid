package com.rfid.user.server;

import com.rfid.user.vo.RolesVo;

public interface RolesServer {

	public RolesVo getRoleByRoleId(Long roleId);
	
	public RolesVo getRoleByRoleName(String roleName);
	
	public Long addRole(RolesVo vo) throws Exception;
}
