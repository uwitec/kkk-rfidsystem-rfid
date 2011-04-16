package com.rfid.device.dao;

import java.util.List;

import com.rfid.common.db.GenericDao;
import com.rfid.device.po.Device;
import com.rfid.user.po.UserDetail;

public interface DeviceDao extends GenericDao<Device,Long>{

	public void save(Device transientInstance);

	public void delete(Device persistentInstance);

	public Device findById(java.lang.Long id);

	public List<Device> findByExample(Device instance);

	public List findByProperty(String propertyName, Object value);

	public List<Device> findByDeviceId(Object deviceId);

	public List<Device> findByMonitorEnable(Object monitorEnable);

	public List findAll();

	public Device merge(Device detachedInstance);

	public void attachDirty(Device instance);

	public void attachClean(Device instance);

}