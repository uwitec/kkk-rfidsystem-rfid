package com.rfid.user.server;

public interface UserRoleServer {

	void updateUserToRole(Long userId, Long[] roleId) throws Exception ;
	
	void assignUserToRole(Long userId,Long[] roleId) throws Exception;
}
