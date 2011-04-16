package com.rfid.user.dao;

import java.util.List;

import com.rfid.common.db.GenericDao;
import com.rfid.user.po.UserDetail;
import com.rfid.user.po.Users;

public interface UsersDao extends GenericDao<Users,Long>{

	public void save(Users transientInstance);

	public void delete(Users persistentInstance);

	public Users findById(java.lang.Long id);

	public List<Users> findByExample(Users instance);

	public List findByProperty(String propertyName, Object value);

	public List<Users> findByUserid(Object userid);

	public List<Users> findByLoginName(Object loginName);

	public List<Users> findByLoginPassword(Object loginPassword);

	public List findAll();

	public Users merge(Users detachedInstance);

	public void attachDirty(Users instance);

	public void attachClean(Users instance);

}