package com.rfid.user.dao.impl;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rfid.common.db.hibernate.HibernateGenericDao;
import com.rfid.user.dao.UserDetailDao;
import com.rfid.user.po.Roles;
import com.rfid.user.po.UserDetail;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserDetail entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.rfid.user.po.UserDetail
 * @author MyEclipse Persistence Tools
 */

public class UserDetailDaoImpl extends HibernateGenericDao<UserDetail, Long> 
	implements UserDetailDao {
//	private static final Logger log = LoggerFactory
//			.getLogger(UserDetailDaoImpl.class);
	// property constants
	public static final String USER_NAME = "userName";
	public static final String USER_ADDRESS = "userAddress";
	public static final String CONNECTION = "connection";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UserDetailDao#save(com.rfid.user.po.UserDetail)
	 */
	public void save(UserDetail transientInstance) {
		log.debug("saving UserDetail instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UserDetailDao#delete(com.rfid.user.po.UserDetail)
	 */
	public void delete(UserDetail persistentInstance) {
		log.debug("deleting UserDetail instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UserDetailDao#findById(java.lang.Long)
	 */
	public UserDetail findById(java.lang.Long id) {
		log.debug("getting UserDetail instance with id: " + id);
		try {
			UserDetail instance = (UserDetail) getHibernateTemplate().get(
					"com.rfid.device.po.UserDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UserDetailDao#findByExample(com.rfid.user.po.UserDetail)
	 */
	public List<UserDetail> findByExample(UserDetail instance) {
		log.debug("finding UserDetail instance by example");
		try {
			List<UserDetail> results = (List<UserDetail>) getHibernateTemplate()
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
	 * @see com.rfid.user.dao.impl.UserDetailDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding UserDetail instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from UserDetail as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UserDetailDao#findByUserName(java.lang.Object)
	 */
	public List<UserDetail> findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UserDetailDao#findByUserAddress(java.lang.Object)
	 */
	public List<UserDetail> findByUserAddress(Object userAddress) {
		return findByProperty(USER_ADDRESS, userAddress);
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UserDetailDao#findByConnection(java.lang.Object)
	 */
	public List<UserDetail> findByConnection(Object connection) {
		return findByProperty(CONNECTION, connection);
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UserDetailDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all UserDetail instances");
		try {
			String queryString = "from UserDetail";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UserDetailDao#merge(com.rfid.user.po.UserDetail)
	 */
	public UserDetail merge(UserDetail detachedInstance) {
		log.debug("merging UserDetail instance");
		try {
			UserDetail result = (UserDetail) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UserDetailDao#attachDirty(com.rfid.user.po.UserDetail)
	 */
	public void attachDirty(UserDetail instance) {
		log.debug("attaching dirty UserDetail instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.UserDetailDao#attachClean(com.rfid.user.po.UserDetail)
	 */
	public void attachClean(UserDetail instance) {
		log.debug("attaching clean UserDetail instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserDetailDao getFromApplicationContext(ApplicationContext ctx) {
		return (UserDetailDao) ctx.getBean("UserDetailDAO");
	}

	public List findByLoginNamePassWord(String loginName, String passWord) {
		log.debug("finding UserDetail instance with property: loginName"
				+ ", value: " + loginName 
				+";passWord, value: " + passWord);
		try {
			String queryString = "select ud " +
					"from UserDetail as ud,Users as u " +
					"where u.loginName = ? " +
						"and u.loginPassword = ? " +
						"and u.userid = ud.users.userid";
			return getHibernateTemplate().find(queryString, new Object[]{loginName,passWord});
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserId(Long userId) {
		log.debug("finding UserDetail instance with property: userId"
				+ ", value: " + userId);
		try {
			String queryString = "select ud " +
					"from UserDetail as ud,Users as u " +
					"where u.userid = ? " +
						"and u.userid = ud.users.userid";
			return getHibernateTemplate().find(queryString,userId);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
}