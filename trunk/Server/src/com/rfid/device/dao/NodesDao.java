package com.rfid.device.dao;

import java.util.List;

import com.rfid.device.po.Nodes;

public interface NodesDao {

	public void save(Nodes transientInstance);

	public void delete(Nodes persistentInstance);

	public Nodes findById(java.lang.Long id);

	public List<Nodes> findByExample(Nodes instance);

	public List findByProperty(String propertyName, Object value);

	public List<Nodes> findByNodeId(Object nodeId);

	public List findAll();

	public Nodes merge(Nodes detachedInstance);

	public void attachDirty(Nodes instance);

	public void attachClean(Nodes instance);

	public List<Nodes> findByDeviceId(Long deviceId);

}