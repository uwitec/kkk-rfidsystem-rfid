package com.rfid.common.webapp.filter;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;

/**
 * OpenSessionInViewFilter中默认将session的FlushMode设为FlushMode.NEVER，
 * 会引起InvalidDataAccessApiUsageException
 * 。因此在初始化之后先将FlushMode设为FlushMode.COMMIT，并修改了
 * {@link #closeSession(Session, SessionFactory)}方法的实现，在关闭Session之前先做
 * {@link Session#flush()}操作。
 * 
 * @author
 * 
 */
public class HibernateFilter extends OpenSessionInViewFilter {

	{
		super.setFlushMode(FlushMode.COMMIT);
	}

	/**
	 * 修改{@link #closeSession(Session, SessionFactory)}方法的实现，在关闭Session之前先做
	 * {@link Session#flush()}操作。
	 * 
	 * @param session
	 * @param factory
	 */
	@Override
	protected void closeSession(Session session, SessionFactory factory) {
		session.flush();
		super.closeSession(session, factory);
	}
}
