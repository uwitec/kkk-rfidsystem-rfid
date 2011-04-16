package com.rfid.device.dao;

import java.util.List;

import com.rfid.common.db.GenericDao;
import com.rfid.device.po.Reader;
import com.rfid.user.po.UserDetail;

public interface ReaderDao extends GenericDao<Reader,Long>{

	public void save(Reader transientInstance);

	public void delete(Reader persistentInstance);

	public Reader findById(java.lang.Long id);

	public List<Reader> findByExample(Reader instance);

	public List findByProperty(String propertyName, Object value);

	public List<Reader> findByReaderid(Object readerid);

	public List<Reader> findByReaderIp(Object readerIp);

	public List findAll();

	public Reader merge(Reader detachedInstance);

	public void attachDirty(Reader instance);

	public void attachClean(Reader instance);

}