package com.rfid.device.dao;

import java.util.List;

import com.rfid.common.db.GenericDao;
import com.rfid.device.po.Area;
import com.rfid.user.po.UserDetail;

public interface AreaDao extends GenericDao<Area,Long>{

	public void save(Area transientInstance);

	public void delete(Area persistentInstance);

	public Area findById(java.lang.Long id);

	public List<Area> findByExample(Area instance);

	public List findByProperty(String propertyName, Object value);

	public List<Area> findByAreaId(Object areaId);

	public List<Area> findByAreaAddress(Object areaAddress);

	public List<Area> findByScription(Object scription);

	public List findAll();

	public Area merge(Area detachedInstance);

	public void attachDirty(Area instance);

	public void attachClean(Area instance);

}