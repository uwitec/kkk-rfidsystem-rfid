package com.rfid.user.dao;

import java.util.List;

import com.rfid.common.db.GenericDao;
import com.rfid.user.po.Roles;
import com.rfid.user.po.UserDetail;

public interface UserDetailDao  extends GenericDao<UserDetail,Long>{

	public void save(UserDetail transientInstance);

	public void delete(UserDetail persistentInstance);

	public UserDetail findById(java.lang.Long id);

	public List<UserDetail> findByExample(UserDetail instance);

	public List findByProperty(String propertyName, Object value);

	public List<UserDetail> findByUserName(Object userName);

	public List<UserDetail> findByUserAddress(Object userAddress);

	public List<UserDetail> findByConnection(Object connection);

	public List findAll();

	public UserDetail merge(UserDetail detachedInstance);

	public void attachDirty(UserDetail instance);

	public void attachClean(UserDetail instance);

}