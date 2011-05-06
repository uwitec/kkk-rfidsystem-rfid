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

	public boolean hasAreaByAreaId(Long areaId);
	
	/**
	 * 查询该读写器是否已经分配在某区域内
	 * @param readerid
	 * @return 存在返回true，不存在返回false
	 */
	public boolean hasAreaByReaderId(Long readerid);

}