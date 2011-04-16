package com.rfid.user.dao.impl;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rfid.common.db.hibernate.HibernateGenericDao;
import com.rfid.user.dao.RolesDao;
import com.rfid.user.po.Roles;

/**
 * A data access object (DAO) providing persistence and search support for Roles
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.rfid.user.po.Roles
 * @author MyEclipse Persistence Tools
 */

public class RolesDaoImpl  extends HibernateGenericDao<Roles, Long> 
	implements RolesDao {
//	private static final Logger log = LoggerFactory.getLogger(RolesDaoImpl.class);
	// property constants
	public static final String ROLE_ID = "roleId";
	public static final String ROLE_NAME = "roleName";
	public static final String ROLE_NOTE = "roleNote";
	public static final String IS_ENABLE = "isEnable";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.RolesDao#save(com.rfid.user.po.Roles)
	 */
	public void save(Roles transientInstance) {
		log.debug("saving Roles instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.RolesDao#delete(com.rfid.user.po.Roles)
	 */
	public void delete(Roles persistentInstance) {
		log.debug("deleting Roles instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.RolesDao#findById(java.lang.Long)
	 */
	public Roles findById(java.lang.Long id) {
		log.debug("getting Roles instance with id: " + id);
		try {
			Roles instance = (Roles) getHibernateTemplate().get(
					"com.rfid.device.po.Roles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.RolesDao#findByExample(com.rfid.user.po.Roles)
	 */
	public List<Roles> findByExample(Roles instance) {
		log.debug("finding Roles instance by example");
		try {
			List<Roles> results = (List<Roles>) getHibernateTemplate()
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
	 * @see com.rfid.user.dao.impl.RolesDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Roles instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Roles as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.RolesDao#findByRoleId(java.lang.Object)
	 */
	public List<Roles> findByRoleId(Object roleId) {
		return findByProperty(ROLE_ID, roleId);
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.RolesDao#findByRoleName(java.lang.Object)
	 */
	public List<Roles> findByRoleName(Object roleName) {
		return findByProperty(ROLE_NAME, roleName);
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.RolesDao#findByRoleNote(java.lang.Object)
	 */
	public List<Roles> findByRoleNote(Object roleNote) {
		return findByProperty(ROLE_NOTE, roleNote);
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.RolesDao#findByIsEnable(java.lang.Object)
	 */
	public List<Roles> findByIsEnable(Object isEnable) {
		return findByProperty(IS_ENABLE, isEnable);
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.RolesDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all Roles instances");
		try {
			String queryString = "from Roles";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.RolesDao#merge(com.rfid.user.po.Roles)
	 */
	public Roles merge(Roles detachedInstance) {
		log.debug("merging Roles instance");
		try {
			Roles result = (Roles) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.RolesDao#attachDirty(com.rfid.user.po.Roles)
	 */
	public void attachDirty(Roles instance) {
		log.debug("attaching dirty Roles instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.user.dao.impl.RolesDao#attachClean(com.rfid.user.po.Roles)
	 */
	public void attachClean(Roles instance) {
		log.debug("attaching clean Roles instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RolesDao getFromApplicationContext(ApplicationContext ctx) {
		return (RolesDao) ctx.getBean("RolesDAO");
	}
}