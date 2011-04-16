package com.rfid.device.dao.impl;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rfid.common.db.hibernate.HibernateGenericDao;
import com.rfid.device.dao.MonitorDao;
import com.rfid.device.po.Area;
import com.rfid.device.po.Monitor;

/**
 * A data access object (DAO) providing persistence and search support for
 * Monitor entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rfid.device.po.Monitor
 * @author MyEclipse Persistence Tools
 */

public class MonitorDaoImpl extends HibernateGenericDao<Monitor, Long> 
	implements MonitorDao {
//	private static final Logger log = LoggerFactory.getLogger(MonitorDaoImpl.class);
	// property constants
	public static final String MONITOR_ID = "monitorId";
	public static final String MONITOR_IP = "monitorIp";
	public static final String MONITOR_PORT = "monitorPort";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.MonitorDao#save(com.rfid.device.po.Monitor)
	 */
	public void save(Monitor transientInstance) {
		log.debug("saving Monitor instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.MonitorDao#delete(com.rfid.device.po.Monitor)
	 */
	public void delete(Monitor persistentInstance) {
		log.debug("deleting Monitor instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.MonitorDao#findById(java.lang.Long)
	 */
	public Monitor findById(java.lang.Long id) {
		log.debug("getting Monitor instance with id: " + id);
		try {
			Monitor instance = (Monitor) getHibernateTemplate().get(
					"com.rfid.device.po.Monitor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.MonitorDao#findByExample(com.rfid.device.po.Monitor)
	 */
	public List<Monitor> findByExample(Monitor instance) {
		log.debug("finding Monitor instance by example");
		try {
			List<Monitor> results = (List<Monitor>) getHibernateTemplate()
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
	 * @see com.rfid.device.dao.impl.MonitorDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Monitor instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Monitor as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.MonitorDao#findByMonitorId(java.lang.Object)
	 */
	public List<Monitor> findByMonitorId(Object monitorId) {
		return findByProperty(MONITOR_ID, monitorId);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.MonitorDao#findByMonitorIp(java.lang.Object)
	 */
	public List<Monitor> findByMonitorIp(Object monitorIp) {
		return findByProperty(MONITOR_IP, monitorIp);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.MonitorDao#findByMonitorPort(java.lang.Object)
	 */
	public List<Monitor> findByMonitorPort(Object monitorPort) {
		return findByProperty(MONITOR_PORT, monitorPort);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.MonitorDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all Monitor instances");
		try {
			String queryString = "from Monitor";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.MonitorDao#merge(com.rfid.device.po.Monitor)
	 */
	public Monitor merge(Monitor detachedInstance) {
		log.debug("merging Monitor instance");
		try {
			Monitor result = (Monitor) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.MonitorDao#attachDirty(com.rfid.device.po.Monitor)
	 */
	public void attachDirty(Monitor instance) {
		log.debug("attaching dirty Monitor instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.MonitorDao#attachClean(com.rfid.device.po.Monitor)
	 */
	public void attachClean(Monitor instance) {
		log.debug("attaching clean Monitor instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MonitorDao getFromApplicationContext(ApplicationContext ctx) {
		return (MonitorDao) ctx.getBean("MonitorDAO");
	}
}