package com.rfid.device.dao.impl;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rfid.device.dao.NodesDao;
import com.rfid.device.po.Nodes;

/**
 * A data access object (DAO) providing persistence and search support for Nodes
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.rfid.device.po.Nodes
 * @author MyEclipse Persistence Tools
 */

public class NodesDaoImpl extends HibernateDaoSupport implements NodesDao {
	private static final Logger log = LoggerFactory.getLogger(NodesDaoImpl.class);
	// property constants
	public static final String NODE_ID = "nodeId";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.NodesDao#save(com.rfid.device.po.Nodes)
	 */
	public void save(Nodes transientInstance) {
		log.debug("saving Nodes instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.NodesDao#delete(com.rfid.device.po.Nodes)
	 */
	public void delete(Nodes persistentInstance) {
		log.debug("deleting Nodes instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.NodesDao#findById(java.lang.Long)
	 */
	public Nodes findById(java.lang.Long id) {
		log.debug("getting Nodes instance with id: " + id);
		try {
			Nodes instance = (Nodes) getHibernateTemplate().get(
					"com.rfid.device.po.Nodes", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.NodesDao#findByExample(com.rfid.device.po.Nodes)
	 */
	public List<Nodes> findByExample(Nodes instance) {
		log.debug("finding Nodes instance by example");
		try {
			List<Nodes> results = (List<Nodes>) getHibernateTemplate()
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
	 * @see com.rfid.device.dao.impl.NodesDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Nodes instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Nodes as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.NodesDao#findByNodeId(java.lang.Object)
	 */
	public List<Nodes> findByNodeId(Object nodeId) {
		return findByProperty(NODE_ID, nodeId);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.NodesDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all Nodes instances");
		try {
			String queryString = "from Nodes";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.NodesDao#merge(com.rfid.device.po.Nodes)
	 */
	public Nodes merge(Nodes detachedInstance) {
		log.debug("merging Nodes instance");
		try {
			Nodes result = (Nodes) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.NodesDao#attachDirty(com.rfid.device.po.Nodes)
	 */
	public void attachDirty(Nodes instance) {
		log.debug("attaching dirty Nodes instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.NodesDao#attachClean(com.rfid.device.po.Nodes)
	 */
	public void attachClean(Nodes instance) {
		log.debug("attaching clean Nodes instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static NodesDao getFromApplicationContext(ApplicationContext ctx) {
		return (NodesDao) ctx.getBean("NodesDAO");
	}

	public List<Nodes> findByDeviceId(Long deviceId) {
		// TODO Auto-generated method stub
		return null;
	}
}