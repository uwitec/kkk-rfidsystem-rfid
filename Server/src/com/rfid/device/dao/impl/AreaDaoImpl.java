package com.rfid.device.dao.impl;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rfid.common.db.hibernate.HibernateGenericDao;
import com.rfid.device.dao.AreaDao;
import com.rfid.device.po.Area;

/**
 * A data access object (DAO) providing persistence and search support for Area
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.rfid.device.po.Area
 * @author MyEclipse Persistence Tools
 */

public class AreaDaoImpl  extends HibernateGenericDao<Area, Long> 
	implements AreaDao {
//	private static final Logger log = LoggerFactory.getLogger(AreaDaoImpl.class);
	// property constants
	public static final String AREA_ID = "areaId";
	public static final String AREA_ADDRESS = "areaAddress";
	public static final String SCRIPTION = "scription";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaDao#save(com.rfid.device.po.Area)
	 */
	public void save(Area transientInstance) {
		log.debug("saving Area instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaDao#delete(com.rfid.device.po.Area)
	 */
	public void delete(Area persistentInstance) {
		log.debug("deleting Area instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaDao#findById(java.lang.Long)
	 */
	public Area findById(java.lang.Long id) {
		log.debug("getting Area instance with id: " + id);
		try {
			Area instance = (Area) getHibernateTemplate().get(
					"com.rfid.device.po.Area", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaDao#findByExample(com.rfid.device.po.Area)
	 */
	public List<Area> findByExample(Area instance) {
		log.debug("finding Area instance by example");
		try {
			List<Area> results = (List<Area>) getHibernateTemplate()
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
	 * @see com.rfid.device.dao.impl.AreaDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Area instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Area as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaDao#findByAreaId(java.lang.Object)
	 */
	public List<Area> findByAreaId(Object areaId) {
		return findByProperty(AREA_ID, areaId);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaDao#findByAreaAddress(java.lang.Object)
	 */
	public List<Area> findByAreaAddress(Object areaAddress) {
		return findByProperty(AREA_ADDRESS, areaAddress);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaDao#findByScription(java.lang.Object)
	 */
	public List<Area> findByScription(Object scription) {
		return findByProperty(SCRIPTION, scription);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all Area instances");
		try {
			String queryString = "from Area";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaDao#merge(com.rfid.device.po.Area)
	 */
	public Area merge(Area detachedInstance) {
		log.debug("merging Area instance");
		try {
			Area result = (Area) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaDao#attachDirty(com.rfid.device.po.Area)
	 */
	public void attachDirty(Area instance) {
		log.debug("attaching dirty Area instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.AreaDao#attachClean(com.rfid.device.po.Area)
	 */
	public void attachClean(Area instance) {
		log.debug("attaching clean Area instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AreaDao getFromApplicationContext(ApplicationContext ctx) {
		return (AreaDao) ctx.getBean("AreaDAO");
	}
}