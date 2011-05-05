package com.rfid.device.dao.impl;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rfid.common.db.hibernate.HibernateGenericDao;
import com.rfid.device.dao.DeviceStatusDao;
import com.rfid.device.po.Area;
import com.rfid.device.po.DeviceStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * DeviceStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.rfid.device.po.DeviceStatus
 * @author MyEclipse Persistence Tools
 */

public class DeviceStatusDaoImpl extends HibernateGenericDao<DeviceStatus, Long> 
	implements DeviceStatusDao {
//	private static final Logger log = LoggerFactory
//			.getLogger(DeviceStatusDaoImpl.class);
	// property constants
	public static final String PREVIOUS_ID = "previousId";
	public static final String IS_ENABLE = "isEnable";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceStatusDao#save(com.rfid.device.po.DeviceStatus)
	 */
	public void save(DeviceStatus transientInstance) {
		log.debug("saving DeviceStatus instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceStatusDao#delete(com.rfid.device.po.DeviceStatus)
	 */
	public void delete(DeviceStatus persistentInstance) {
		log.debug("deleting DeviceStatus instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceStatusDao#findById(java.lang.Long)
	 */
	public DeviceStatus findById(java.lang.Long id) {
		log.debug("getting DeviceStatus instance with id: " + id);
		try {
			DeviceStatus instance = (DeviceStatus) getHibernateTemplate().get(
					"com.rfid.device.po.DeviceStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceStatusDao#findByExample(com.rfid.device.po.DeviceStatus)
	 */
	public List<DeviceStatus> findByExample(DeviceStatus instance) {
		log.debug("finding DeviceStatus instance by example");
		try {
			List<DeviceStatus> results = (List<DeviceStatus>) getHibernateTemplate()
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
	 * @see com.rfid.device.dao.impl.DeviceStatusDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding DeviceStatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DeviceStatus as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceStatusDao#findByPreviousId(java.lang.Object)
	 */
	public List<DeviceStatus> findByPreviousId(Object previousId) {
		return findByProperty(PREVIOUS_ID, previousId);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceStatusDao#findByIsEnable(java.lang.Object)
	 */
	public List<DeviceStatus> findByIsEnable(Object isEnable) {
		return findByProperty(IS_ENABLE, isEnable);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceStatusDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all DeviceStatus instances");
		try {
			String queryString = "from DeviceStatus";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceStatusDao#merge(com.rfid.device.po.DeviceStatus)
	 */
	public DeviceStatus merge(DeviceStatus detachedInstance) {
		log.debug("merging DeviceStatus instance");
		try {
			DeviceStatus result = (DeviceStatus) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceStatusDao#attachDirty(com.rfid.device.po.DeviceStatus)
	 */
	public void attachDirty(DeviceStatus instance) {
		log.debug("attaching dirty DeviceStatus instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceStatusDao#attachClean(com.rfid.device.po.DeviceStatus)
	 */
	public void attachClean(DeviceStatus instance) {
		log.debug("attaching clean DeviceStatus instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DeviceStatusDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (DeviceStatusDao) ctx.getBean("DeviceStatusDAO");
	}

	public DeviceStatus findLastStatus(Long deviceId) {
		
		return null;
	}

	public List findLastByDeviceId(Long deviceId) {
		log.debug("getting DeviceStatus instance with deviceId: " + deviceId);
		try {
			String queryString = "from DeviceStatus where isEnable = 1 and device.deviceId = ?";
			return getHibernateTemplate().find(queryString,deviceId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}