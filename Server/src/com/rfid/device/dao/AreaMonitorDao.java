package com.rfid.device.dao;

import java.util.List;

import com.rfid.common.db.GenericDao;
import com.rfid.device.po.AreaMonitor;
import com.rfid.user.po.UserDetail;

public interface AreaMonitorDao extends GenericDao<AreaMonitor,Long>{

	public void save(AreaMonitor transientInstance);

	public void delete(AreaMonitor persistentInstance);

	public AreaMonitor findById(java.lang.Long id);

	public List<AreaMonitor> findByExample(AreaMonitor instance);

	public List findByProperty(String propertyName, Object value);

	public List findAll();

	public AreaMonitor merge(AreaMonitor detachedInstance);

	public void attachDirty(AreaMonitor instance);

	public void attachClean(AreaMonitor instance);

}