package com.rfid.common.db.hibernate;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.rfid.common.db.GenericDao;
import com.rfid.common.db.support.Page;
import com.rfid.common.util.BeanUtils;
import com.rfid.common.util.GenericsUtils;

/**
 * Hibernate Dao的泛型基类。
 * 
 * 继承于Spring的<code>HibernateDaoSupport</code>，提供分页函数和若干便捷查询方法，并对返回值作了泛型类型转换。
 * 
 * @author
 * @version 0.1 初始版本，征集意见
 * 
 * @see {@link org.springframework.orm.hibernate3.support.HibernateDaoSupport}
 * 
 * @param <T>
 *            实体类型参数
 * @param <PK>
 *            主键类型参数
 */
public class HibernateGenericDao<T, PK extends Serializable> extends
		HibernateDaoSupport implements GenericDao<T, PK> {

	/**
	 * 日志
	 */
	protected final Log log = LogFactory.getLog(getClass());

	/**
	 * 实体类型
	 */
	protected final Class<T> entityClass;

	/**
	 * 构造函数, 将泛型T.class赋给entityClass.
	 */
	@SuppressWarnings("unchecked")
	public HibernateGenericDao() {
		entityClass = (Class<T>) GenericsUtils
				.getSuperClassGenricType(getClass());
	}

	/**
	 * 取得entityClass.
	 * 
	 * @return
	 */
	protected Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * 根据ID获取对象。
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T get(final PK id) {
		return (T) getHibernateTemplate().get(getEntityClass(), id);
	}

	/**
	 * 根据ID获取对象。
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T load(final PK id) {
		return (T) getHibernateTemplate().load(getEntityClass(), id);
	}

	/**
	 * 获取全部对象
	 * 
	 * @return
	 * @see HibernateGenericDao#getAll(Class)
	 * @deprecated 该方法当数据量大的时候会产生性能问题。
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	public List<T> loadAll() {
		return getHibernateTemplate().loadAll(getEntityClass());
	}

	/**
	 * 获取全部对象，带排序参数。
	 * 
	 * @param orderBy
	 * @param isAsc
	 * @see HibernateGenericDao#getAll(Class,String,boolean)
	 * @deprecated 该方法当数据量大的时候会产生性能问题。
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	public List<T> loadAll(final String orderBy, final boolean isAsc) {
		Assert.hasText(orderBy);
		if (isAsc) {
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(getEntityClass()).addOrder(
							Order.asc(orderBy)));
		} else {
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(getEntityClass()).addOrder(
							Order.desc(orderBy)));
		}
	}

	/**
	 * 保存对象。
	 * 
	 * @param t
	 */
	public void save(final T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * 更新对象。
	 * 
	 * @param o
	 */
	public void update(final T entity) {
		getHibernateTemplate().update(entity);
	}

	/**
	 * 删除对象。
	 * 
	 * @param o
	 */
	public void delete(final T entity) {
		getHibernateTemplate().delete(entity);
	}

	/**
	 * 根据ID删除对象。
	 * 
	 * @param id
	 */
	public void deleteById(final PK id) {
		delete(get(id));
	}

	/**
	 * 从Session的缓存中移除该实例。该实例所有的更改将不会被同步到数据库。
	 * 
	 * @param entity
	 */
	public void evit(T entity) {
		getHibernateTemplate().evict(entity);
	}

	/**
	 * 强制Session冲刷。将当前Session中所有维持在内存中的保存、更新和删除持久化状态同步到数据库。
	 * 该方法必须在事务提交和Session关闭之前调用。
	 * 建议只在相同的事务内后续操作依赖于之前操作对数据库的改变时使用，一般情况建议依赖于事务提交时的自动冲刷即可，无需手动调用此方法。
	 */
	public void flush() {
		getHibernateTemplate().flush();
	}

	/**
	 * 清除Session中缓存的所有对象，并取消当前Session中所有维持在内存中的保存、更新和删除持久化状态。
	 * 该方法不会关闭已经打开的迭代器或ScrollableResults实例。
	 */
	public void clear() {
		getHibernateTemplate().clear();
	}

	/**
	 * 取得对象的主键名，辅助函数。
	 * 
	 * @return
	 */
	protected String getIdName() {
		Assert.notNull(getEntityClass());
		ClassMetadata meta = getSessionFactory().getClassMetadata(
				getEntityClass());
		Assert.notNull(meta, "Class " + getEntityClass()
				+ " not define in hibernate session factory.");
		String idName = meta.getIdentifierPropertyName();
		Assert.hasText(idName, getEntityClass().getSimpleName()
				+ " has no identifier property define.");
		return idName;
	}

	/**
	 * 取得对象的主键值，辅助函数。
	 * 
	 * @param entity
	 * @return
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected Serializable getId(T entity) throws NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		Assert.notNull(entity);
		Assert.notNull(entityClass);
		return (Serializable) PropertyUtils.getProperty(entity, getIdName());
	}

	/**
	 * 根据HQL查询。该方法为protected，不在接口
	 * <code>GenericDao<T, PK extends Serializable></code>
	 * 中暴露，仅供子类DAO调用，保证HQL只能出现在DAO中。
	 * 
	 * @param hql
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql, Object... values) {
		Assert.hasText(hql);
		return (List<T>) getHibernateTemplate().find(hql, values);
	}

	
	/**
	 * 执行hql语句从数据库中查询最多maxResults条记录，HQL语句不可以含有参数
	 * 
	 * @param hql
	 *            查询使用hql语句
	 * @param maxResults
	 *            最多查询的记录数目
	 * 
	 * @return 以POJO形式封装的查询结果
	 * @deprecated 这个方法有时候有问题
	 */
	public List findWithMaxResults(final String hql, final int maxResults) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = createQuery(session, hql);
				query.setMaxResults(maxResults);
				return query.list();
			}
		});
	}
	/**
	 * 根据Map中的条件的Criteria查询。
	 * 
	 * @param map
	 *            Map中仅包含条件名与条件值，默认全部相同，可重载。
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(final Map map) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Criteria criteria = createCriteria(session);
				criteria.add(Restrictions.allEq(map));
				return criteria.list();
			}
		});
	}

	/**
	 * 根据属性名和属性值查询对象。
	 * 
	 * @param propertyName
	 * @param value
	 * @return 符合条件的对象列表
	 */
	@SuppressWarnings("unchecked")
	public List<T> findBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName);
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				return createCriteria(session,
						Restrictions.eq(propertyName, value)).list();
			}
		});
	}

	/**
	 * 根据属性名和属性值查询对象，带排序参数。
	 * 
	 * @param propertyName
	 * @param value
	 * @param orderBy
	 * @param isAsc
	 * @return 符合条件的对象列表
	 * @see HibernateGenericDao#findBy(Class,String,Object,String,boolean)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findBy(final String propertyName, final Object value,
			final String orderBy, final boolean isAsc) {
		Assert.hasText(propertyName);
		Assert.hasText(orderBy);
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				return createCriteria(session, orderBy, isAsc,
						Restrictions.eq(propertyName, value)).list();
			}
		});
	}

	/**
	 * 根据属性名和属性值查询唯一对象。如果对象不存在则返回null，如果存在多个对象则抛出异常。
	 * 
	 * @param propertyName
	 * @param value
	 * @return 符合条件的唯一对象 or null
	 */
	@SuppressWarnings("unchecked")
	public T findUniqueBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName);
		return (T) getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						return createCriteria(session,
								Restrictions.eq(propertyName, value))
								.uniqueResult();
					}
				});
	}

	/**
	 * 使用所给Session创建Query对象。
	 * 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数，可以在返回Query后自行设置。
	 * 留意可以连续设置，如下：
	 * 
	 * <pre>
	 * getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 
	 * 调用方式如下：
	 * 
	 * <pre>
	 *        createQuery(hql)
	 *        createQuery(hql,arg0);
	 *        createQuery(hql,arg0,arg1);
	 *        createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 * 
	 * @param session
	 * @param hql
	 * @param values
	 * @return
	 */
	protected Query createQuery(Session session, final String hql,
			final Object... values) {
		Assert.hasText(hql);
		Query query = session.createQuery(hql);
		for (int i = 0; values != null && i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}

	/**
	 * 用HibernateTemplate实现分页查询。
	 * 
	 * @param hql
	 * @param offset
	 * @param pageSize
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<T> getListForPage(final String hql, final int offset,
			final int pageSize, final Object... values) {
		return (List<T>) getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = createQuery(session, hql, values);
						query.setFirstResult(offset);
						query.setMaxResults(pageSize);
						return query.list();
					}
				});
	}

	/**
	 * 去除HQL的select子句，未考虑union的情况，用于pagedQuery。
	 * 
	 * @param hql
	 * @return
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, "HQL: \"" + hql
				+ "\" must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 去除HQL的order by子句，用于pagedQuery。
	 * 
	 * @param hql
	 * @return
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 使用HQL分页查询。该方法为protected，不在接口
	 * <code>GenericDao<T, PK extends Serializable></code>
	 * 中暴露，仅供子类DAO调用，保证HQL只能出现在DAO中。
	 * <p>
	 * 注意:此方法用了group by ,distinct等语句的时候,总数可能是不对的!
	 * 
	 * @param hql
	 * @param pageNo
	 *            页码，从1开始
	 * @param pageSize
	 *            分页大小
	 * @param values
	 * @return
	 */
	protected Page<T> pagedQuery(String hql, int pageNo, int pageSize,
			Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		if (hql.toLowerCase().indexOf("group by") > 0) {
			if (log.isWarnEnabled()) {
				log.warn("Using 'group by' may cause error!");
			}
		}
		String countQueryString = "select count (*) "+ removeSelect(removeOrders(hql));
        @SuppressWarnings("unchecked")
        List<Long> countlist = getHibernateTemplate().find(countQueryString, values);
        long totalCount = countlist.get(0);

        if (totalCount < 1)
        {
            return new Page<T>();
        }
        // 实际查询返回分页对象
        int startIndex = Page.getStartOfPage(pageNo, pageSize);
        List<T> list = getListForPage(hql, startIndex, pageSize, values);
        return new Page<T>(totalCount, startIndex, pageSize, list);
	}

	/**
	 * 分页查询,需要传入统计总数的HQL。该方法为protected，不在接口
	 * <code>GenericDao<T, PK extends Serializable></code>
	 * 中暴露，仅供子类DAO调用，保证HQL只能出现在DAO中。
	 * 
	 * @param hql
	 *            查询的HQL
	 * @param countHql
	 *            统计总数的HQL
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 */
	protected Page<T> pagedQuery(final String hql, final String countHql,
			final int pageNo, final int pageSize, Object... values) {
		@SuppressWarnings("unchecked")
        List<Long> countlist = getHibernateTemplate().find(countHql, values);
        long totalCount = 0;
        if (countlist != null && !countlist.isEmpty())
        {
            totalCount = countlist.get(0);
        }
		if (totalCount < 1) {
			return new Page<T>();
		}
		// 实际查询返回分页对象
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		List<T> list = getListForPage(hql, startIndex, pageSize, values);
		return new Page<T>(totalCount, startIndex, pageSize, list);
	}

	/**
	 * 执行HQL update语句。
	 * 
	 * @param hql
	 * @return
	 */
	protected int executeUpdate(final String hql, final Object... values) {
		Object o = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = createQuery(session, hql, values);
						int rows = query.executeUpdate();
						return new Integer(rows);
					}
				});
		int ret = (o != null) ? Integer.valueOf(o.toString()) : 0;
		return ret;
	}

	/**
	 * 使用所给Session和criterions创建Criteria对象。
	 * 
	 * @param <T>
	 * @param session
	 * @param criterions
	 * @return
	 */
	protected Criteria createCriteria(Session session, Criterion... criterions) {
		Criteria criteria = session.createCriteria(getEntityClass());
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 使用所给Session、排序字段、升降序参数、criterions创建Criteria对象。
	 * 
	 * @param <T>
	 * @param session
	 * @param orderBy
	 * @param isAsc
	 * @param criterions
	 * @return
	 */
	protected Criteria createCriteria(Session session, String orderBy,
			boolean isAsc, Criterion... criterions) {
		Assert.hasText(orderBy);
		Criteria criteria = createCriteria(session, criterions);
		if (isAsc) {
			criteria.addOrder(Order.asc(orderBy));
		} else {
			criteria.addOrder(Order.desc(orderBy));
		}
		return criteria;
	}

	/**
	 * 分页查询函数，使用已设好查询条件与排序的<code>Criteria</code>。
	 * 
	 * @param criteria
	 * @param pageNo
	 *            页码，从1开始
	 * @param pageSize
	 * @return 含总记录数和当前页数据的Page对象
	 */
	@SuppressWarnings("unchecked")
	protected Page<T> pagedQuery(Criteria criteria, int pageNo, int pageSize) {
		Assert.notNull(criteria);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		CriteriaImpl impl = (CriteriaImpl) criteria;

		// 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
		Projection projection = impl.getProjection();
		List<CriteriaImpl.OrderEntry> orderEntries;
		try {
			orderEntries = (List<CriteriaImpl.OrderEntry>) BeanUtils
					.forceGetProperty(impl, "orderEntries");
			BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		// 执行查询
		int totalCount = (Integer) criteria.setProjection(
				Projections.rowCount()).uniqueResult();

		// 将之前的Projection和OrderBy条件重新设回去
		criteria.setProjection(projection);
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}

		try {
			BeanUtils.forceSetProperty(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		// 返回分页对象
		if (totalCount < 1)
			return new Page<T>();

		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		List<T> list = criteria.setFirstResult(startIndex).setMaxResults(
				pageSize).list();
		return new Page<T>(totalCount, startIndex, pageSize, list);
	}

	/**
	 * 分页查询函数，根据entityClass和查询条件参数创建默认的<code>Criteria</code>。
	 * 
	 * @param pageNo
	 *            页码，从1开始
	 * @param pageSize
	 * @param criterions
	 * @return 含总记录数和当前页数据的Page对象
	 */
	@SuppressWarnings("unchecked")
	protected Page<T> pagedQuery(final int pageNo, final int pageSize,
			final Criterion... criterions) {
		return (Page<T>) getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = createCriteria(session, criterions);
						return pagedQuery(criteria, pageNo, pageSize);
					}
				});
	}

	/**
	 * 分页查询函数，根据entityClass和查询条件参数,排序参数创建默认的<code>Criteria</code>。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param pageNo
	 *            页码，从1开始
	 * @param pageSize
	 * @param orderBy
	 * @param isAsc
	 * @param criterions
	 * @return 含总记录数和当前页数据的Page对象
	 */
	@SuppressWarnings("unchecked")
	protected Page<T> pagedQuery(final int pageNo, final int pageSize,
			final String orderBy, final boolean isAsc,
			final Criterion... criterions) {
		return (Page<T>) getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = createCriteria(session, orderBy,
								isAsc, criterions);
						return pagedQuery(criteria, pageNo, pageSize);
					}
				});
	}

	/**
	 * 判断对象某些属性的值在数据库中唯一。
	 * 
	 * @param entity
	 * @param uniquePropertyNames
	 *            在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password"
	 */
	public boolean isUnique(final T entity, final String uniquePropertyNames) {
		Assert.hasText(uniquePropertyNames);
		Integer count = (Integer) getHibernateTemplate()
				.executeWithNativeSession(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(entityClass)
								.setProjection(Projections.rowCount());
						String[] nameList = uniquePropertyNames.split(",");
						try {
							// 循环加入唯一列
							for (String name : nameList) {
								criteria.add(Restrictions
										.eq(name, PropertyUtils.getProperty(
												entity, name)));
							}

							// 以下代码为了如果是update的情况，排除entity自身。
							String idName = getIdName();

							// 取得entity的主键值。
							Serializable id = getId(entity);

							// 如果id!=null，说明对象已存在，该操作为update，加入排除自身的判断。
							if (id != null) {
								criteria.add(Restrictions.not(Restrictions.eq(
										idName, id)));
							}
						} catch (Exception e) {
							ReflectionUtils.handleReflectionException(e);
						}
						return criteria.uniqueResult();
					}
				});
		return count == 0;
	}

	/**
	 * 字符串模糊查询
	 * 
	 * @param condition
	 * @return
	 */
	public String fuzzyQueryCondition(String condition) {
		StringBuffer sb = new StringBuffer();
		sb.append("%");
		sb.append(condition.trim());
		sb.append("%");
		return sb.toString();
	}
}

