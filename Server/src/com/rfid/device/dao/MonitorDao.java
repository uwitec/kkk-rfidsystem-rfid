package com.rfid.device.dao;

import java.util.List;

import com.rfid.common.db.GenericDao;
import com.rfid.device.po.Monitor;

public interface MonitorDao extends GenericDao<Monitor,Long>{

	public void save(Monitor transientInstance);

	public void delete(Monitor persistentInstance);

	public Monitor findById(java.lang.Long id);

	public List<Monitor> findByExample(Monitor instance);

	public List findByProperty(String propertyName, Object value);

	public List<Monitor> findByMonitorId(Object monitorId);

	public List<Monitor> findByMonitorIp(Object monitorIp);

	public List<Monitor> findByMonitorPort(Object monitorPort);

	public List findAll();

	public Monitor merge(Monitor detachedInstance);

	public void attachDirty(Monitor instance);

	public void attachClean(Monitor instance);

}