package com.rfid.user.dao;

import java.util.List;

import com.rfid.common.db.GenericDao;
import com.rfid.user.po.Roles;

public interface RolesDao extends GenericDao<Roles,Long>{

	public void save(Roles transientInstance);

	public void delete(Roles persistentInstance);

	public Roles findById(java.lang.Long id);

	public List<Roles> findByExample(Roles instance);

	public List findByProperty(String propertyName, Object value);

	public List<Roles> findByRoleId(Object roleId);

	public List<Roles> findByRoleName(Object roleName);

	public List<Roles> findByRoleNote(Object roleNote);

	public List<Roles> findByIsEnable(Object isEnable);

	public List findAll();

	public Roles merge(Roles detachedInstance);

	public void attachDirty(Roles instance);

	public void attachClean(Roles instance);

}