package com.rfid.user.server.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;
import com.rfid.common.constants.EnumConstant.RoleType;
import com.rfid.user.constants.UserEnumConstant.RoleTpyeName;
import com.rfid.user.dao.RolesDao;
import com.rfid.user.dao.UserDetailDao;
import com.rfid.user.dao.UsersDao;
import com.rfid.user.po.Roles;
import com.rfid.user.po.UserDetail;
import com.rfid.user.po.UserRole;
import com.rfid.user.po.Users;
import com.rfid.user.server.RolesServer;
import com.rfid.user.server.UserRoleServer;
import com.rfid.user.server.UserServer;
import com.rfid.user.vo.RolesVo;
import com.rfid.user.vo.UserDetailVo;
import com.rfid.user.vo.UsersVo;
import com.rfid.user.vo.VoToPoTools;

public class UserServerImpl implements UserServer {

	private UsersDao usersDao;
	private UserDetailDao userDetailDao;
	private RolesDao rolesDao;
	private UserRoleServer userRoleServer;

	public UserRoleServer getUserRoleServer() {
		return userRoleServer;
	}

	public void setUserRoleServer(UserRoleServer userRoleServer) {
		this.userRoleServer = userRoleServer;
	}

	public RolesDao getRolesDao() {
		return rolesDao;
	}

	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}

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

	public UsersVo getUsersByLoginNamePassWord(String loginName, String passWord)
			throws Exception {
		Users user = usersDao.findByLoginName(loginName).get(0);
		if (user == null)
			return null;
		else {
			if (user.getLoginPassword().equals(passWord))
				return user.toUsersVO();
			else
				return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rfid.user.server.UserServer#loginAble(java.lang.String,
	 * java.lang.String)
	 */
	public boolean loginAble(String loginName, String passWord) {
		Users user = usersDao.findByLoginName(loginName).get(0);
		if (user == null)
			return false;
		else
			return true;
	}

	public UsersVo getUsersByUserId(Long userId) {
		Users user = usersDao.findByUserid((Long) userId).get(0);
		if (user == null)
			return null;
		else
			return user.toUsersVO();
	}

	public Long regUsers(UsersVo userVo) throws Exception {
		try {
			// 判断用户名是否已存在
			if (userVo.getLoginName() == null)
				throw new Exception("缺少用户名");
			else if (this.hasUserByLoginName(userVo.getLoginName()))
				throw new Exception("已存在该用户名");

			if (userVo.getLoginPassword() == null)
				throw new Exception("缺少用户密码");
			Users user = new Users();
			Long userId = PoTools.getPoId(PoType.UserType);
			user.setUserid(userId);
			user.setLoginName(userVo.getLoginName());
			user.setLoginPassword(userVo.getLoginPassword());
			usersDao.save(user);
			// 给以默认角色
			List ulist = usersDao.findByUserid(userId);
			if(ulist==null || ulist.size()<=0)
				throw new Exception("存储失败");
			user = (Users)ulist.get(0);
			if (user.getUserRoles() == null || user.getUserRoles().size() <= 0) {
				userRoleServer.assignUserToRole(userId
						,new Long[]{RoleType.UserType.getInfo()});
			} else {
				List<RolesVo> roleVos = userVo.getUserRoles();
				Long[] roleIds = new Long[roleVos.size()];
				int i = 0;
				for (RolesVo r : roleVos) {
					roleIds[i] = r.getRoleId();
					i++;
				}
				userRoleServer.assignUserToRole(userId,roleIds);
			}
			return userId;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}

	}

	@SuppressWarnings("unchecked")
	public boolean hasUserByLoginName(String loginName) {
		List list = usersDao.findByLoginName((String) loginName);
		if (list == null || list.size() == 0)
			return false;
		Users user = (Users) list.get(0);
		if (user == null)
			return false;
		else
			return true;
	}

	public List<UsersVo> getAllUsers() {
		List<UsersVo> list = new ArrayList<UsersVo>();
		List<Users> uList = usersDao.findAll();
		if (uList == null || uList.size() == 0)
			return list;
		else {
			for (Users u : uList) {
				UsersVo vo = u.toUsersVO();
				list.add(vo);
			}
			return list;
		}
	}

	public List<UserDetailVo> getAllUserDetail() {
		List<UserDetailVo> list = new ArrayList<UserDetailVo>();
		List<UserDetail> uList = userDetailDao.findAll();
		if (uList == null || uList.size() == 0)
			return list;
		else {
			for (UserDetail u : uList) {
				UserDetailVo vo = u.toUserDetailVo();
				list.add(vo);
			}
			return list;
		}
	}

	public UserDetailVo getUserDetailByLoginNamePassWord(String loginName,
			String passWord) throws Exception {
		List list = userDetailDao.findByLoginNamePassWord(loginName, passWord);
		if (list == null || list.size() == 0)
			return null;
		else {
			UserDetail userDetail = (UserDetail) list.get(0);
			return userDetail.toUserDetailVo();
		}
	}

	@SuppressWarnings("unchecked")
	public UserDetailVo getUserDetailByUserId(Long userId) {
		List<UserDetail> list = userDetailDao.findByUserId(userId);
		if (list == null || list.size() == 0)
			return null;
		else {
			UserDetail userDetail = (UserDetail) list.get(0);
			return userDetail.toUserDetailVo();
		}
	}

	public Long regUsersDetail(UserDetailVo userDetailVo) throws Exception {
		try {
			if (userDetailVo == null || userDetailVo.getUsersVo() == null)
				throw new Exception("请填写UserVo");
			UsersVo userVo = userDetailVo.getUsersVo();
			Long userId = this.regUsers(userVo);
			if (userId != null && userId > 0) {
				UserDetail ud = new UserDetail();
				List userList = usersDao.findByUserid(userId);
				if (userList == null || userList.size() <= 0)
					return Long.parseLong("-1");
				Users user = (Users) userList.get(0);
				ud.setUsers(user);
				if (userDetailVo.getBirthday() != null)
					ud.setBirthday(new Timestamp(userDetailVo.getBirthday()
							.getTime()));
				if (userDetailVo.getConnection() != null)
					ud.setConnection(userDetailVo.getConnection());
				ud.setRegisterTime(new Timestamp(new Date().getTime()));
				if (userDetailVo.getUserAddress() != null)
					ud.setUserAddress(userDetailVo.getUserAddress());
				if (userDetailVo.getUserName() != null)
					ud.setUserName(userDetailVo.getUserName());
				userDetailDao.save(ud);
				return userId;
			}
			return Long.parseLong("-1");
		} catch (Exception ex) {
			throw ex;
		}
	}

	public void updateUserDetail(UserDetailVo vo) throws Exception {
		UsersVo userVo = vo.getUsersVo();
		if(userVo==null || userVo.getUserid()==null || userVo.getUserid()<=0)
			throw new Exception("请设置UserVo");
		Long userId = userVo.getUserid();
		List<UserDetail> udList = userDetailDao.findByUserId(userId);
		if(udList==null || udList.size()<=0)
			throw new Exception("不存在该用户");
		UserDetail ud = udList.get(0);
		Users user = ud.getUsers();
		if(vo.getUsersVo()!=null){
			if(vo.getUsersVo().getLoginPassword()!=null){
				user.setLoginPassword(vo.getUsersVo().getLoginPassword());
				usersDao.update(user);
			}
		}
		if(vo.getBirthday()!=null)
			ud.setBirthday(new Timestamp(vo.getBirthday().getTime()));
		if(vo.getConnection()!=null)
			ud.setConnection(vo.getConnection());
		if(vo.getUserAddress()!=null)
			ud.setUserAddress(vo.getUserAddress());
		if(vo.getUserName()!=null)
			ud.setUserName(vo.getUserName());
		userDetailDao.update(ud);
	}

	public void deleteUserDetialByUserId(Long userId) throws Exception {
		List<UserDetail> udList = userDetailDao.findByUserId(userId);
		if(udList==null || udList.size()<=0)
			throw new Exception("不存在该用户");
		UserDetail ud = udList.get(0);
		Users user = ud.getUsers();
		userDetailDao.delete(ud);
		user.setLoginName(user.getUserid().toString());
		user.setLoginPassword(user.getUserid().toString());
		usersDao.update(user);
	}

}
