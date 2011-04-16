package com.rfid.device.dao;

import java.util.List;

import com.rfid.common.db.GenericDao;
import com.rfid.device.po.DeviceStatus;
import com.rfid.user.po.UserDetail;

public interface DeviceStatusDao extends GenericDao<DeviceStatus,Long>{

	public void save(DeviceStatus transientInstance);

	public void delete(DeviceStatus persistentInstance);

	public DeviceStatus findById(java.lang.Long id);

	public List<DeviceStatus> findByExample(DeviceStatus instance);

	public List findByProperty(String propertyName, Object value);

	public List<DeviceStatus> findByPreviousId(Object previousId);

	public List<DeviceStatus> findByIsEnable(Object isEnable);

	public List findAll();

	public DeviceStatus merge(DeviceStatus detachedInstance);

	public void attachDirty(DeviceStatus instance);

	public void attachClean(DeviceStatus instance);

}