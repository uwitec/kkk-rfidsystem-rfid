package com.rfid.device.dao.impl;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rfid.common.db.hibernate.HibernateGenericDao;
import com.rfid.device.dao.AreaMonitorDao;
import com.rfid.device.po.Area;
import com.rfid.device.po.AreaMonitor;

/**
 * A data access object (DAO) providing persistence and search support for
 * AreaMonitor entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.rfid.device.po.AreaMonitor
 * @author MyEclipse Persistence Tools
 */

public class AreaMonitorDaoImpl extends HibernateGenericDao< AreaMonitor, Long> 
	implements AreaMonitorDao {
//	private static final Logger log = LoggerFactory
//			.getLogger(AreaMonitorDaoImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaMonitorDao#save(com.rfid.device.po.AreaMonitor)
	 */
	public void save(AreaMonitor transientInstance) {
		log.debug("saving AreaMonitor instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaMonitorDao#delete(com.rfid.device.po.AreaMonitor)
	 */
	public void delete(AreaMonitor persistentInstance) {
		log.debug("deleting AreaMonitor instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaMonitorDao#findById(java.lang.Long)
	 */
	public AreaMonitor findById(java.lang.Long id) {
		log.debug("getting AreaMonitor instance with id: " + id);
		try {
			AreaMonitor instance = (AreaMonitor) getHibernateTemplate().get(
					"com.rfid.device.po.AreaMonitor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaMonitorDao#findByExample(com.rfid.device.po.AreaMonitor)
	 */
	public List<AreaMonitor> findByExample(AreaMonitor instance) {
		log.debug("finding AreaMonitor instance by example");
		try {
			List<AreaMonitor> results = (List<AreaMonitor>) getHibernateTemplate()
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
	 * @see com.rfid.device.dao.impl.AreaMonitorDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding AreaMonitor instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AreaMonitor as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaMonitorDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all AreaMonitor instances");
		try {
			String queryString = "from AreaMonitor";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaMonitorDao#merge(com.rfid.device.po.AreaMonitor)
	 */
	public AreaMonitor merge(AreaMonitor detachedInstance) {
		log.debug("merging AreaMonitor instance");
		try {
			AreaMonitor result = (AreaMonitor) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaMonitorDao#attachDirty(com.rfid.device.po.AreaMonitor)
	 */
	public void attachDirty(AreaMonitor instance) {
		log.debug("attaching dirty AreaMonitor instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaMonitorDao#attachClean(com.rfid.device.po.AreaMonitor)
	 */
	public void attachClean(AreaMonitor instance) {
		log.debug("attaching clean AreaMonitor instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AreaMonitorDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (AreaMonitorDao) ctx.getBean("AreaMonitorDAO");
	}
}