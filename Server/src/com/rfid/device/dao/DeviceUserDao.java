package com.rfid.device.dao;

import java.util.List;

import com.rfid.common.db.GenericDao;
import com.rfid.device.po.DeviceUser;
import com.rfid.user.po.UserDetail;

public interface DeviceUserDao extends GenericDao<DeviceUser,Long> {

	public void save(DeviceUser transientInstance);

	public void delete(DeviceUser persistentInstance);

	public DeviceUser findById(java.lang.Long id);

	public List<DeviceUser> findByExample(DeviceUser instance);

	public List findByProperty(String propertyName, Object value);

	public List findAll();

	public DeviceUser merge(DeviceUser detachedInstance);

	public void attachDirty(DeviceUser instance);

	public void attachClean(DeviceUser instance);

}