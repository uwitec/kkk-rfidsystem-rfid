package com.rfid.device.dao.impl;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rfid.common.db.hibernate.HibernateGenericDao;
import com.rfid.device.dao.DeviceDetailDao;
import com.rfid.device.po.Area;
import com.rfid.device.po.DeviceDetail;

/**
 * A data access object (DAO) providing persistence and search support for
 * DeviceDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.rfid.device.po.DeviceDetail
 * @author MyEclipse Persistence Tools
 */

public class DeviceDetailDaoImpl extends HibernateGenericDao<DeviceDetail, Long> 
	implements DeviceDetailDao {
//	private static final Logger log = LoggerFactory
//			.getLogger(DeviceDetailDaoImpl.class);
	// property constants
	public static final String DEVICE_NAME = "deviceName";
	public static final String PRICE = "price";
	public static final String MANUFACTORY = "manufactory";
	public static final String BUYER = "buyer";
	public static final String DEVICE_NUM = "deviceNum";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDetailDao#save(com.rfid.device.po.DeviceDetail)
	 */
	public void save(DeviceDetail transientInstance) {
		log.debug("saving DeviceDetail instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDetailDao#delete(com.rfid.device.po.DeviceDetail)
	 */
	public void delete(DeviceDetail persistentInstance) {
		log.debug("deleting DeviceDetail instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDetailDao#findById(java.lang.Long)
	 */
	public DeviceDetail findById(java.lang.Long id) {
		log.debug("getting DeviceDetail instance with id: " + id);
		try {
			DeviceDetail instance = (DeviceDetail) getHibernateTemplate().get(
					"com.rfid.device.po.DeviceDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDetailDao#findByExample(com.rfid.device.po.DeviceDetail)
	 */
	public List<DeviceDetail> findByExample(DeviceDetail instance) {
		log.debug("finding DeviceDetail instance by example");
		try {
			List<DeviceDetail> results = (List<DeviceDetail>) getHibernateTemplate()
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
	 * @see com.rfid.device.dao.impl.DeviceDetailDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding DeviceDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DeviceDetail as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDetailDao#findByDeviceName(java.lang.Object)
	 */
	public List<DeviceDetail> findByDeviceName(Object deviceName) {
		return findByProperty(DEVICE_NAME, deviceName);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDetailDao#findByPrice(java.lang.Object)
	 */
	public List<DeviceDetail> findByPrice(Object price) {
		return findByProperty(PRICE, price);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDetailDao#findByManufactory(java.lang.Object)
	 */
	public List<DeviceDetail> findByManufactory(Object manufactory) {
		return findByProperty(MANUFACTORY, manufactory);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDetailDao#findByBuyer(java.lang.Object)
	 */
	public List<DeviceDetail> findByBuyer(Object buyer) {
		return findByProperty(BUYER, buyer);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDetailDao#findByDeviceNum(java.lang.Object)
	 */
	public List<DeviceDetail> findByDeviceNum(Object deviceNum) {
		return findByProperty(DEVICE_NUM, deviceNum);
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDetailDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all DeviceDetail instances");
		try {
			String queryString = "from DeviceDetail";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDetailDao#merge(com.rfid.device.po.DeviceDetail)
	 */
	public DeviceDetail merge(DeviceDetail detachedInstance) {
		log.debug("merging DeviceDetail instance");
		try {
			DeviceDetail result = (DeviceDetail) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDetailDao#attachDirty(com.rfid.device.po.DeviceDetail)
	 */
	public void attachDirty(DeviceDetail instance) {
		log.debug("attaching dirty DeviceDetail instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.rfid.device.dao.impl.DeviceDetailDao#attachClean(com.rfid.device.po.DeviceDetail)
	 */
	public void attachClean(DeviceDetail instance) {
		log.debug("attaching clean DeviceDetail instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DeviceDetailDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (DeviceDetailDao) ctx.getBean("DeviceDetailDAO");
	}
}