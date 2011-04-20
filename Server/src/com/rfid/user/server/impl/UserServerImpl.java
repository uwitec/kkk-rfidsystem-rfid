package com.rfid.user.server.impl;

import java.util.ArrayList;
import java.util.List;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;
import com.rfid.user.dao.UserDetailDao;
import com.rfid.user.dao.UsersDao;
import com.rfid.user.po.UserDetail;
import com.rfid.user.po.Users;
import com.rfid.user.server.UserServer;
import com.rfid.user.vo.UserDetailVo;
import com.rfid.user.vo.UsersVo;

public class UserServerImpl implements UserServer {

	private UsersDao usersDao;
	private UserDetailDao userDetailDao;
	
	public UserDetailDao getUserDetailDao() {
		return userDetailDao;
	}
	public void setUserDetailDao(UserDetailDao userDetailDao) {
		this.userDetailDao = userDetailDao;
	}
	public UsersDao getUsersDao() {
		return usersDao;
	}
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public UsersVo getUsersByLoginNamePassWord(String loginName, String passWord) throws Exception {
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

	/* (non-Javadoc)
	 * @see com.rfid.user.server.UserServer#loginAble(java.lang.String, java.lang.String)
	 */
	public boolean loginAble(String loginName, String passWord) {
		Users user = usersDao.findByLoginName(loginName).get(0);
		if(user == null)
			return false;
		else
			return true;
	}
	
	
	public UsersVo getUsersByUserId(Long userId) {
		Users user = usersDao.findByUserid((Long)userId).get(0);
		if(user == null)
			return null;
		else
			return user.toUsersVO();
	}
	
	public boolean regUsers(UsersVo userVo) throws Exception {
		//判断用户名是否已存在
		if(userVo.getLoginName()==null)
			throw new Exception("缺少用户名");
		else if(this.hasUserByLoginName(userVo.getLoginName()))
			throw new Exception("已存在该用户");
		
		if(userVo.getLoginPassword() == null)
			throw new Exception("缺少用户密码");

		Users user = new Users();
		user.setUserid(PoTools.getPoId(PoType.UserType));
		user.setLoginName(userVo.getLoginName());
		user.setLoginPassword(userVo.getLoginPassword());
		try{
			usersDao.save(user);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean hasUserByLoginName(String loginName){
		Users user = (Users) usersDao.findByLoginName((String)loginName);
		if(user == null)
			return false;
		else
			return true;
	}
	
	public List<UsersVo> getAllUsers() {
		List<UsersVo> list = new ArrayList<UsersVo>();
		List<Users> uList = usersDao.findAll();
		if(uList == null || uList.size()==0)
			return list;
		else{
			for(Users u : uList){
				UsersVo vo = u.toUsersVO();
				list.add(vo);
			}
			return list;
		}
	}
	public List<UserDetailVo> getAllUserDetail() {
		List<UserDetailVo> list = new ArrayList<UserDetailVo>();
		List<UserDetail> uList = userDetailDao.findAll();
		if(uList == null || uList.size()==0)
			return list;
		else{
			for(UserDetail u : uList){
				UserDetailVo vo = u.toUserDetailVo();
				list.add(vo);
			}
			return list;
		}
	}
	public UserDetailVo getUserDetailByLoginNamePassWord(String loginName,
			String passWord) throws Exception {
		List list = userDetailDao.findByLoginNamePassWord(loginName,passWord);
		if(list==null || list.size()==0)
			return null;
		else{
			UserDetail userDetail = (UserDetail) list.get(0);
			return userDetail.toUserDetailVo();
		}
	}
	public UserDetailVo getUserDetailByUserId(Long userId) {
		List list = userDetailDao.findByUserId(userId);
		if(list==null || list.size()==0)
			return null;
		else{
			UserDetail userDetail = (UserDetail) list.get(0);
			return userDetail.toUserDetailVo();
		}
	}
	public boolean regUsers(UserDetailVo userDetailVo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
