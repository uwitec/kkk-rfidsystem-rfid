package com.rfid.user.dao.impl;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rfid.common.db.hibernate.HibernateGenericDao;
import com.rfid.user.dao.UsersDao;
import com.rfid.user.po.Roles;
import com.rfid.user.po.Users;

/**
 * A data access object (DAO) providing persistence and search support for Users
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.rfid.user.po.Users
 * @author MyEclipse Persistence Tools
 */

public class UsersDaoImpl extends HibernateGenericDao<Users, Long> 
	implements UsersDao {
//	private static final Logger log = LoggerFactory.getLogger(UsersDaoImpl.class);
	// property constants
	public static final String USERID = "userid";
	public static final String LOGIN_NAME = "loginName";
	public static final String LOGIN_PASSWORD = "loginPassword";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UsersDao#save(com.rfid.user.po.Users)
	 */
	public void save(Users transientInstance) {
		log.debug("saving Users instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UsersDao#delete(com.rfid.user.po.Users)
	 */
	public void delete(Users persistentInstance) {
		log.debug("deleting Users instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UsersDao#findById(java.lang.Long)
	 */
	public Users findById(java.lang.Long id) {
		log.debug("getting Users instance with id: " + id);
		try {
			Users instance = (Users) getHibernateTemplate().get(
					"com.rfid.device.po.Users", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UsersDao#findByExample(com.rfid.user.po.Users)
	 */
	public List<Users> findByExample(Users instance) {
		log.debug("finding Users instance by example");
		try {
			List<Users> results = (List<Users>) getHibernateTemplate()
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
	 * @see com.rfid.user.dao.impl.UsersDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Users instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Users as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UsersDao#findByUserid(java.lang.Object)
	 */
	public List<Users> findByUserid(Object userid) {
		return findByProperty(USERID, userid);
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UsersDao#findByLoginName(java.lang.Object)
	 */
	public List<Users> findByLoginName(Object loginName) {
		return findByProperty(LOGIN_NAME, loginName);
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UsersDao#findByLoginPassword(java.lang.Object)
	 */
	public List<Users> findByLoginPassword(Object loginPassword) {
		return findByProperty(LOGIN_PASSWORD, loginPassword);
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UsersDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all Users instances");
		try {
			String queryString = "from Users";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UsersDao#merge(com.rfid.user.po.Users)
	 */
	public Users merge(Users detachedInstance) {
		log.debug("merging Users instance");
		try {
			Users result = (Users) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UsersDao#attachDirty(com.rfid.user.po.Users)
	 */
	public void attachDirty(Users instance) {
		log.debug("attaching dirty Users instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UsersDao#attachClean(com.rfid.user.po.Users)
	 */
	public void attachClean(Users instance) {
		log.debug("attaching clean Users instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UsersDao getFromApplicationContext(ApplicationContext ctx) {
		return (UsersDao) ctx.getBean("UsersDAO");
	}
}