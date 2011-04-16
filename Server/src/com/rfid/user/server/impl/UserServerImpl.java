package com.rfid.user.server.impl;

import com.rfid.user.dao.UsersDao;
import com.rfid.user.po.Users;
import com.rfid.user.server.UserServer;
import com.rfid.user.vo.UsersVo;

public class UserServerImpl implements UserServer {

	private UsersDao usersDao;
	
	public UsersVo getUsersByLoginName(String loginName) {
		Users user = usersDao.findByLoginName(loginName).get(0);
		if(user==null)
			return null;
		else
			return user.toUsersVO();
	}

	public UsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public UsersVo getUsersByLoginNamePassWord(String loginName, String passWord) {
		// TODO Auto-generated method stub
		Users user = usersDao.findByLoginName(loginName).get(0);
		if(user==null)
			return null;
		else{
			if(user.getLoginPassword().equals(passWord))
				return user.toUsersVO();
			else
				return null;
		}
	}
}
