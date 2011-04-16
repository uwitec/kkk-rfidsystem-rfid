package com.rfid.device.dao;

import java.util.List;

import com.rfid.common.db.GenericDao;
import com.rfid.device.po.Status;
import com.rfid.user.po.UserDetail;

public interface StatusDao extends GenericDao<Status,Long>{

	public void save(Status transientInstance);

	public void delete(Status persistentInstance);

	public Status findById(java.lang.Long id);

	public List<Status> findByExample(Status instance);

	public List findByProperty(String propertyName, Object value);

	public List<Status> findByStatusId(Object statusId);

	public List<Status> findByScription(Object scription);

	public List<Status> findByLevel(Object level);

	public List findAll();

	public Status merge(Status detachedInstance);

	public void attachDirty(Status instance);

	public void attachClean(Status instance);

}