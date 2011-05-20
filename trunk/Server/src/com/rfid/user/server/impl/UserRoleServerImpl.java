package com.rfid.user.server.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.rfid.user.dao.RolesDao;
import com.rfid.user.dao.UserRoleDao;
import com.rfid.user.dao.UsersDao;
import com.rfid.user.po.Roles;
import com.rfid.user.po.UserRole;
import com.rfid.user.po.Users;
import com.rfid.user.server.UserRoleServer;

public class UserRoleServerImpl implements UserRoleServer {

	private UsersDao usersDao;
	private RolesDao rolesDao;
	private UserRoleDao userRoleDao;
	
	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}


	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}


	public RolesDao getRolesDao() {
		return rolesDao;
	}


	public void setRolesDao(RolesDao rolesDao) {
		this.rolesDao = rolesDao;
	}


	public UsersDao getUsersDao() {
		return usersDao;
	}


	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void updateUserToRole(Long userId, Long[] roleId) throws Exception {
		try{
			List<Users> ulist = usersDao.findByUserid(userId);
			if(ulist==null || ulist.size()<=0)
				throw new Exception("不存在该用户");
			Users user = (Users)ulist.get(0);
			Set<UserRole> userRoles = new HashSet<UserRole>(0);
			Set<UserRole> oldUserRoles = user.getUserRoles();
			Iterator it = oldUserRoles.iterator();
			int i = 0;
			while(it.hasNext()){
				Long rId = roleId[i];
				UserRole oldUR = (UserRole)it.next();
				for(int k=0;k<roleId.length;k++){
					if(!oldUR.getRoles().getRoleId().equals(rId)){
						userRoleDao.delete(oldUR);
						break;
					}
				}
				i++;
			}
			for(Long rId : roleId)
			{
				List<Roles> list = rolesDao.findByRoleId(rId);
				if (list == null || list.size() <= 0)
					throw new Exception("不存在该用户类型");
				Roles role = (Roles) list.get(0);
				UserRole ur = new UserRole();
				ur.setRoles(role);
				ur.setUsers(user);
				if(!userRoleDao.hasUserRole(userId,rId))
					userRoleDao.save(ur);
			}
		}catch(Exception ex){
			throw ex;
		}
	}

	public void assignUserToRole(Long userId, Long[] roleId) throws Exception {
		try{
			List<Users> ulist = usersDao.findByUserid(userId);
			if(ulist==null || ulist.size()<=0)
				throw new Exception("不存在该用户");
			Users user = (Users)ulist.get(0);
			for(Long rId : roleId){
				List<Roles> list = rolesDao.findByRoleId(rId);
				if (list == null || list.size() <= 0)
					throw new Exception("不存在该用户类型");
				Roles role = (Roles) list.get(0);
				UserRole ur = new UserRole();
				ur.setRoles(role);
				ur.setUsers(user);
				if(!userRoleDao.hasUserRole(userId,rId))
					userRoleDao.save(ur);
		}
		}catch(Exception ex){
			throw ex;
		}
//		if (user.getUserRoles() == null || user.getUserRoles().size() <= 0) {
//		List list = rolesDao.findByRoleId(RoleType.UserType.getInfo());
//		if (list == null || list.size() <= 0)
//			throw new Exception("数据问题");
//		Roles role = (Roles) list.get(0);
//		
//	} else {
//		List<RolesVo> roleVos = userVo.getUserRoles();
//		for (RolesVo r : roleVos) {
//			UserRole ur = new UserRole();
//			ur.setUsers(user);
//			Roles role = VoToPoTools.toRoles(r);
//			ur.setRoles(role);
//			ur.setUsers(user);
//			userRoles.add(ur);
//		}
//	}
//	user.setUserRoles(userRoles);
	}

}
