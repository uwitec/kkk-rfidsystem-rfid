package com.rfid.user.server;

import com.rfid.user.vo.UsersVo;

public interface UserServer {
	
	public UsersVo getUsersByLoginName(String loginName);
	
	public UsersVo getUsersByLoginNamePassWord(String loginName,String passWord);

}
