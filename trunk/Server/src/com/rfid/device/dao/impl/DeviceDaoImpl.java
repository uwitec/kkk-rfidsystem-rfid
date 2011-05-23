package com.rfid.device.dao.impl;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rfid.common.db.hibernate.HibernateGenericDao;
import com.rfid.device.dao.DeviceDao;
import com.rfid.device.po.Area;
import com.rfid.device.po.Device;

/**
 * A data access object (DAO) providing persistence and search support for
 * Device entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rfid.device.po.Device
 * @author MyEclipse Persistence Tools
 */

public class DeviceDaoImpl extends HibernateGenericDao<Device, Long> 
	implements DeviceDao {
//	private static final Logger log = LoggerFactory.getLogger(DeviceDaoImpl.class);
	// property constants
	public static final String DEVICE_ID = "deviceId";
	public static final String MONITOR_ENABLE = "monitorEnable";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDao#save(com.rfid.device.po.Device)
	 */
	public void save(Device transientInstance) {
		log.debug("saving Device instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDao#delete(com.rfid.device.po.Device)
	 */
	public void delete(Device persistentInstance) {
		log.debug("deleting Device instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public void deleteByDeviceId(Long deviceId) {
		log.debug("deleting Device instance");
		try {
			List obj = this.findByDeviceId(deviceId);
			if(obj==null || obj.size()<=0)
				return;
			getHibernateTemplate().delete(obj.get(0));
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDao#findById(java.lang.Long)
	 */
	public Device findById(java.lang.Long id) {
		log.debug("getting Device instance with id: " + id);
		try {
			Device instance = (Device) getHibernateTemplate().get(
					"com.rfid.device.po.Device", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDao#findByExample(com.rfid.device.po.Device)
	 */
	public List<Device> findByExample(Device instance) {
		log.debug("finding Device instance by example");
		try {
			List<Device> results = (List<Device>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Device instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Device as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDao#findByDeviceId(java.lang.Object)
	 */
	public List<Device> findByDeviceId(Object deviceId) {
		return findByProperty(DEVICE_ID, deviceId);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDao#findByMonitorEnable(java.lang.Object)
	 */
	public List<Device> findByMonitorEnable(Object monitorEnable) {
		return findByProperty(MONITOR_ENABLE, monitorEnable);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all Device instances");
		try {
			String queryString = "from Device";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDao#merge(com.rfid.device.po.Device)
	 */
	public Device merge(Device detachedInstance) {
		log.debug("merging Device instance");
		try {
			Device result = (Device) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDao#attachDirty(com.rfid.device.po.Device)
	 */
	public void attachDirty(Device instance) {
		log.debug("attaching dirty Device instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDao#attachClean(com.rfid.device.po.Device)
	 */
	public void attachClean(Device instance) {
		log.debug("attaching clean Device instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DeviceDao getFromApplicationContext(ApplicationContext ctx) {
		return (DeviceDao) ctx.getBean("DeviceDAO");
	}

	public List findByMonitorEnableByDeviceId(Long deviceId) {
		log.debug("finding Device instance with property: deviceId" 
				+ ", value: " + deviceId);
		try {
			String queryString = "from Device as model where " +
					" model.deviceId " + "= ?"
					+ " and model.monitorEnable " + "= 1" ;
			return getHibernateTemplate().find(queryString, deviceId);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}


}