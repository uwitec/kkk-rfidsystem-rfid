package com.rfid.user.webservice;

import com.rfid.user.vo.UsersVo;

public interface UserWebService {

	UsersVo getUserByLoginNamePassWord(String loginName,String passWord);
	
	boolean isLoginUser(String loginName,String passWord);
	
	boolean regUser(UsersVo users);
	
}
