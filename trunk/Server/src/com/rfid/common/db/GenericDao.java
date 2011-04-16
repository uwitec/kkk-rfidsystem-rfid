package com.rfid.common.db;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * Dao的泛型接口。
 * 
 * @author
 * @version 0.1 初始版本，征集意见
 * 
 * @param <T>
 *            实体类型参数
 * @param <PK>
 *            主键类型参数
 */
public interface GenericDao<T, PK extends Serializable> {

	/**
	 * 根据ID获取对象。
	 * 
	 * @param id
	 * @return
	 */
	public T get(final PK id);

	/**
	 * 根据ID获取对象。
	 * 
	 * @param id
	 * @return
	 */
	public T load(final PK id);

	/**
	 * 获取全部对象
	 * 
	 * @return
	 * @see HibernateGenericDao#getAll(Class)
	 * @deprecated 该方法当数据量大的时候会产生性能问题。
	 */
	@Deprecated
	public List<T> loadAll();

	/**
	 * 获取全部对象，带排序参数。
	 * 
	 * @param orderBy
	 * @param isAsc
	 * @see HibernateGenericDao#getAll(Class,String,boolean)
	 * @deprecated 该方法当数据量大的时候会产生性能问题。
	 */
	@Deprecated
	public List<T> loadAll(final String orderBy, final boolean isAsc);

	/**
	 * 保存对象。
	 * 
	 * @param t
	 */
	public void save(final T entity);

	/**
	 * 更新对象。
	 * 
	 * @param o
	 */
	public void update(final T entity);

	/**
	 * 删除对象。
	 * 
	 * @param o
	 */
	public void delete(final T entity);

	/**
	 * 根据ID删除对象。
	 * 
	 * @param id
	 */
	public void deleteById(final PK id);

	/**
	 * 从Session的缓存中移除该实例。该实例所有的更改将不会被同步到数据库。
	 * 
	 * @param entity
	 */
	public void evit(T entity);

	/**
	 * 强制Session冲刷。将当前Session中所有维持在内存中的保存、更新和删除持久化状态同步到数据库。
	 * 该方法必须在事务提交和Session关闭之前调用。
	 * 建议只在相同的事务内后续操作依赖于之前操作对数据库的改变时使用，一般情况建议依赖于事务提交时的自动冲刷即可，无需手动调用此方法。
	 */
	public void flush();

	/**
	 * 清除Session中缓存的所有对象，并取消当前Session中所有维持在内存中的保存、更新和删除持久化状态。
	 * 该方法不会关闭已经打开的迭代器或ScrollableResults实例。
	 */
	public void clear();

	/**
	 * 根据Map中的条件的Criteria查询。
	 * 
	 * @param map
	 *            Map中仅包含条件名与条件值，默认全部相同，可重载。
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(final Map map);

	/**
	 * 根据属性名和属性值查询对象。
	 * 
	 * @param propertyName
	 * @param value
	 * @return 符合条件的对象列表
	 */
	public List<T> findBy(final String propertyName, final Object value);

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
	public List<T> findBy(final String propertyName, final Object value,
			final String orderBy, final boolean isAsc);

	/**
	 * 根据属性名和属性值查询唯一对象。如果对象不存在则返回null，如果存在多个对象则抛出异常。
	 * 
	 * @param propertyName
	 * @param value
	 * @return 符合条件的唯一对象 or null
	 */
	public T findUniqueBy(final String propertyName, final Object value);

	/**
	 * 判断对象某些属性的值在数据库中唯一。
	 * 
	 * @param entity
	 * @param uniquePropertyNames
	 *            在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password"
	 */
	public boolean isUnique(final T entity, final String uniquePropertyNames);

}
