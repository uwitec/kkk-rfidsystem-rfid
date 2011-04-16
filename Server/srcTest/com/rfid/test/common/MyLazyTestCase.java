package com.rfid.test.common;

import org.hibernate.HibernateException;
import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.CleanupFailureDataAccessException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import junit.framework.TestCase;

public abstract class MyLazyTestCase extends TestCase {

	private SessionFactory sessionFactory;
	private Session session;

	public void setUp() throws Exception {
		super.setUp();
		sessionFactory = (SessionFactory) getBean("sessionFactory");
		session = SessionFactoryUtils.getSession(sessionFactory, true);
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory,
				new SessionHolder(s));

	}

	protected Object getBean(String beanName) {
		// Code to get objects from Spring application context
		return SpringBeanUtils.getBean(beanName);
	}

	public void tearDown() throws Exception {
		super.tearDown();
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager
				.getResource(sessionFactory);
		Session s = holder.getSession();
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
		
		closeSessionIfNecessary(s, sessionFactory);
	}
	public static void closeSessionIfNecessary(Session session, SessionFactory sessionFactory)throws CleanupFailureDataAccessException { 
		   if (session == null || TransactionSynchronizationManager.hasResource(sessionFactory)){ 
		     return; 
		   } 
//		   logger.debug("Closing Hibernate session"); 
		   try { 
		     session.close(); 
		   }catch (JDBCException ex) { 
		     // SQLException underneath 
		     throw new CleanupFailureDataAccessException("Could not close Hibernate session", ex.getSQLException()); 
		   }catch (HibernateException ex) { 
		      throw new CleanupFailureDataAccessException("Could not close Hibernate session", ex); 
		   } 
		} 
}