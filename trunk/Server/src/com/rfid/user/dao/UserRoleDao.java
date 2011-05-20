package com.rfid.user.dao;

import java.util.List;

import com.rfid.common.db.GenericDao;
import com.rfid.user.po.UserDetail;
import com.rfid.user.po.UserRole;

public interface UserRoleDao extends GenericDao<UserRole,Long>{

	public void save(UserRole transientInstance);

	public void delete(UserRole persistentInstance);

	public UserRole findById(java.lang.Long id);

	public List<UserRole> findByExample(UserRole instance);

	public List findByProperty(String propertyName, Object value);

	public List findAll();

	public UserRole merge(UserRole detachedInstance);

	public void attachDirty(UserRole instance);

	public void attachClean(UserRole instance);

	public boolean hasUserRole(Long userId, Long roleId);

}