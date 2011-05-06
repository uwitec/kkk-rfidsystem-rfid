package com.rfid.user.server.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;
import com.rfid.user.constants.UserEnumConstant.RoleTpyeName;
import com.rfid.user.dao.UserDetailDao;
import com.rfid.user.dao.UsersDao;
import com.rfid.user.po.Roles;
import com.rfid.user.po.UserDetail;
import com.rfid.user.po.UserRole;
import com.rfid.user.po.Users;
import com.rfid.user.server.RolesServer;
import com.rfid.user.server.UserServer;
import com.rfid.user.vo.RolesVo;
import com.rfid.user.vo.UserDetailVo;
import com.rfid.user.vo.UsersVo;
import com.rfid.user.vo.VoToPoTools;

public class UserServerImpl implements UserServer {

	private UsersDao usersDao;
	private UserDetailDao userDetailDao;
	
	private RolesServer roleServer;
	
	public RolesServer getRoleServer() {
		return roleServer;
	}
	public void setRoleServer(RolesServer roleServer) {
		this.roleServer = roleServer;
	}
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
		//给以默认角色
		if(user.getUserRoles() == null || user.getUserRoles().size()==0)
			throw new Exception("缺少用户角色设定");
		List<RolesVo> roleVos = userVo.getUserRoles();
		Set<UserRole> userRoles = new HashSet<UserRole>(0);
		for(RolesVo r : roleVos){
			UserRole ur = new UserRole();
			ur.setUsers(user);
			Roles role = VoToPoTools.toRoles(r);
			ur.setRoles(role);
			userRoles.add(ur);
		}
		user.setUserRoles(userRoles);
		
		try{
			usersDao.save(user);
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean hasUserByLoginName(String loginName){
		List list = usersDao.findByLoginName((String)loginName);
		if(list == null || list.size()==0)
			return false;
		Users user = (Users) list.get(0);
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
	public boolean regUsersDetail(UserDetailVo userDetailVo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
