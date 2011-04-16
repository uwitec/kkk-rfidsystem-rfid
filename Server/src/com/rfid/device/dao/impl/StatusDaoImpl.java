package com.rfid.device.dao.impl;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rfid.common.db.hibernate.HibernateGenericDao;
import com.rfid.device.dao.StatusDao;
import com.rfid.device.po.Area;
import com.rfid.device.po.Status;

/**
 * A data access object (DAO) providing persistence and search support for
 * Status entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rfid.device.po.Status
 * @author MyEclipse Persistence Tools
 */

public class StatusDaoImpl extends HibernateGenericDao<Status, Long> 
	implements StatusDao {
//	private static final Logger log = LoggerFactory.getLogger(StatusDaoImpl.class);
	// property constants
	public static final String STATUS_ID = "statusId";
	public static final String SCRIPTION = "scription";
	public static final String LEVEL = "level";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.StatusDao#save(com.rfid.device.po.Status)
	 */
	public void save(Status transientInstance) {
		log.debug("saving Status instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.StatusDao#delete(com.rfid.device.po.Status)
	 */
	public void delete(Status persistentInstance) {
		log.debug("deleting Status instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.StatusDao#findById(java.lang.Long)
	 */
	public Status findById(java.lang.Long id) {
		log.debug("getting Status instance with id: " + id);
		try {
			Status instance = (Status) getHibernateTemplate().get(
					"com.rfid.device.po.Status", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.StatusDao#findByExample(com.rfid.device.po.Status)
	 */
	public List<Status> findByExample(Status instance) {
		log.debug("finding Status instance by example");
		try {
			List<Status> results = (List<Status>) getHibernateTemplate()
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
	 * @see com.rfid.device.dao.impl.StatusDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Status instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Status as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.StatusDao#findByStatusId(java.lang.Object)
	 */
	public List<Status> findByStatusId(Object statusId) {
		return findByProperty(STATUS_ID, statusId);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.StatusDao#findByScription(java.lang.Object)
	 */
	public List<Status> findByScription(Object scription) {
		return findByProperty(SCRIPTION, scription);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.StatusDao#findByLevel(java.lang.Object)
	 */
	public List<Status> findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.StatusDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all Status instances");
		try {
			String queryString = "from Status";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.StatusDao#merge(com.rfid.device.po.Status)
	 */
	public Status merge(Status detachedInstance) {
		log.debug("merging Status instance");
		try {
			Status result = (Status) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.StatusDao#attachDirty(com.rfid.device.po.Status)
	 */
	public void attachDirty(Status instance) {
		log.debug("attaching dirty Status instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.StatusDao#attachClean(com.rfid.device.po.Status)
	 */
	public void attachClean(Status instance) {
		log.debug("attaching clean Status instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StatusDao getFromApplicationContext(ApplicationContext ctx) {
		return (StatusDao) ctx.getBean("StatusDAO");
	}
}