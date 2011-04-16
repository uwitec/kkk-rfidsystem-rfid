package com.rfid.user.webservice.impl;

import com.rfid.user.server.UserServer;
import com.rfid.user.vo.UsersVo;
import com.rfid.user.webservice.UserWebService;

public class UserWebServiceImpl implements UserWebService {

	UserServer userServer;
	
	public UsersVo getUserByLoginNamePassWord(String loginName, String passWord) {
		// TODO Auto-generated method stub
		UsersVo vo = userServer.getUsersByLoginName(loginName);
		if(vo==null)
			return null;
		else
			return vo;
	}

	public UserServer getUserServer() {
		return userServer;
	}

	public void setUserServer(UserServer userServer) {
		this.userServer = userServer;
	}

	public boolean isLoginUser(String loginName, String passWord) {
		// TODO Auto-generated method stub
		UsersVo vo = userServer.getUsersByLoginNamePassWord(loginName, passWord);
		if(vo==null)
			return false;
		return true;
	}

	public boolean regUser(UsersVo users) {
		// TODO Auto-generated method stub
		
		return false;
	}

	
}
