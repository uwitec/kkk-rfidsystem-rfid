package com.rfid.device.dao.impl;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rfid.common.db.hibernate.HibernateGenericDao;
import com.rfid.device.dao.ReaderDao;
import com.rfid.device.po.Area;
import com.rfid.device.po.Reader;

/**
 * A data access object (DAO) providing persistence and search support for
 * Reader entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rfid.device.po.Reader
 * @author MyEclipse Persistence Tools
 */

public class ReaderDaoImpl extends HibernateGenericDao<Reader, Long> 
	implements ReaderDao {
//	private static final Logger log = LoggerFactory.getLogger(ReaderDaoImpl.class);
	// property constants
	public static final String READERID = "readerid";
	public static final String READER_IP = "readerIp";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.ReaderDao#save(com.rfid.device.po.Reader)
	 */
	public void save(Reader transientInstance) {
		log.debug("saving Reader instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.ReaderDao#delete(com.rfid.device.po.Reader)
	 */
	public void delete(Reader persistentInstance) {
		log.debug("deleting Reader instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.ReaderDao#findById(java.lang.Long)
	 */
	public Reader findById(java.lang.Long id) {
		log.debug("getting Reader instance with id: " + id);
		try {
			Reader instance = (Reader) getHibernateTemplate().get(
					"com.rfid.device.po.Reader", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.ReaderDao#findByExample(com.rfid.device.po.Reader)
	 */
	public List<Reader> findByExample(Reader instance) {
		log.debug("finding Reader instance by example");
		try {
			List<Reader> results = (List<Reader>) getHibernateTemplate()
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
	 * @see com.rfid.device.dao.impl.ReaderDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Reader instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Reader as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.ReaderDao#findByReaderid(java.lang.Object)
	 */
	public List<Reader> findByReaderid(Object readerid) {
		return findByProperty(READERID, readerid);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.ReaderDao#findByReaderIp(java.lang.Object)
	 */
	public List<Reader> findByReaderIp(Object readerIp) {
		return findByProperty(READER_IP, readerIp);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.ReaderDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all Reader instances");
		try {
			String queryString = "from Reader";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.ReaderDao#merge(com.rfid.device.po.Reader)
	 */
	public Reader merge(Reader detachedInstance) {
		log.debug("merging Reader instance");
		try {
			Reader result = (Reader) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.ReaderDao#attachDirty(com.rfid.device.po.Reader)
	 */
	public void attachDirty(Reader instance) {
		log.debug("attaching dirty Reader instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.ReaderDao#attachClean(com.rfid.device.po.Reader)
	 */
	public void attachClean(Reader instance) {
		log.debug("attaching clean Reader instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReaderDao getFromApplicationContext(ApplicationContext ctx) {
		return (ReaderDao) ctx.getBean("ReaderDAO");
	}
}