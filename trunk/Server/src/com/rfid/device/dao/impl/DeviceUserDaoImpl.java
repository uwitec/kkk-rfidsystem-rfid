package com.rfid.device.dao.impl;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rfid.common.db.hibernate.HibernateGenericDao;
import com.rfid.device.dao.DeviceUserDao;
import com.rfid.device.po.Area;
import com.rfid.device.po.DeviceUser;

/**
 * A data access object (DAO) providing persistence and search support for
 * DeviceUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rfid.device.po.DeviceUser
 * @author MyEclipse Persistence Tools
 */

public class DeviceUserDaoImpl extends HibernateGenericDao<DeviceUser, Long> 
	implements DeviceUserDao {
//	private static final Logger log = LoggerFactory
//			.getLogger(DeviceUserDaoImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceUserDao#save(com.rfid.device.po.DeviceUser)
	 */
	public void save(DeviceUser transientInstance) {
		log.debug("saving DeviceUser instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceUserDao#delete(com.rfid.device.po.DeviceUser)
	 */
	public void delete(DeviceUser persistentInstance) {
		log.debug("deleting DeviceUser instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceUserDao#findById(java.lang.Long)
	 */
	public DeviceUser findById(java.lang.Long id) {
		log.debug("getting DeviceUser instance with id: " + id);
		try {
			DeviceUser instance = (DeviceUser) getHibernateTemplate().get(
					"com.rfid.device.po.DeviceUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceUserDao#findByExample(com.rfid.device.po.DeviceUser)
	 */
	public List<DeviceUser> findByExample(DeviceUser instance) {
		log.debug("finding DeviceUser instance by example");
		try {
			List<DeviceUser> results = (List<DeviceUser>) getHibernateTemplate()
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
	 * @see com.rfid.device.dao.impl.DeviceUserDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding DeviceUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from DeviceUser as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceUserDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all DeviceUser instances");
		try {
			String queryString = "from DeviceUser";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceUserDao#merge(com.rfid.device.po.DeviceUser)
	 */
	public DeviceUser merge(DeviceUser detachedInstance) {
		log.debug("merging DeviceUser instance");
		try {
			DeviceUser result = (DeviceUser) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceUserDao#attachDirty(com.rfid.device.po.DeviceUser)
	 */
	public void attachDirty(DeviceUser instance) {
		log.debug("attaching dirty DeviceUser instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceUserDao#attachClean(com.rfid.device.po.DeviceUser)
	 */
	public void attachClean(DeviceUser instance) {
		log.debug("attaching clean DeviceUser instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DeviceUserDao getFromApplicationContext(ApplicationContext ctx) {
		return (DeviceUserDao) ctx.getBean("DeviceUserDAO");
	}

	public List findByUserDevice(Long deviceId, Long userId) {
		log.debug("finding DeviceUser instance with property1: " + "deviceId:"
				+ ", value: " + deviceId
				+ " and property2:" + "userId"
				+ ", value: " + userId);
		try {
			String queryString = "from DeviceUser as model where "
					+ " model.device.deviceId " + "= ? "
					+ " and "
					+ " model.users.userid " + "= ? ";
			return getHibernateTemplate().find(queryString, deviceId , userId);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}