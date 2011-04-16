package com.rfid.device.dao;

import java.util.List;

import com.rfid.common.db.GenericDao;
import com.rfid.device.po.DeviceDetail;
import com.rfid.user.po.UserDetail;

public interface DeviceDetailDao extends GenericDao<DeviceDetail,Long>{

	public void save(DeviceDetail transientInstance);

	public void delete(DeviceDetail persistentInstance);

	public DeviceDetail findById(java.lang.Long id);

	public List<DeviceDetail> findByExample(DeviceDetail instance);

	public List findByProperty(String propertyName, Object value);

	public List<DeviceDetail> findByDeviceName(Object deviceName);

	public List<DeviceDetail> findByPrice(Object price);

	public List<DeviceDetail> findByManufactory(Object manufactory);

	public List<DeviceDetail> findByBuyer(Object buyer);

	public List<DeviceDetail> findByDeviceNum(Object deviceNum);

	public List findAll();

	public DeviceDetail merge(DeviceDetail detachedInstance);

	public void attachDirty(DeviceDetail instance);

	public void attachClean(DeviceDetail instance);

}