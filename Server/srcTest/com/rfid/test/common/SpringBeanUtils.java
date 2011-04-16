package com.rfid.test.common;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanUtils {
	private static ApplicationContext applicationContext;
	private SpringBeanUtils(){
		
	}
	public static Object getBean(String beanName){
		if(applicationContext==null){
			applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");			
		}		
		return applicationContext.getBean(beanName);		
	}
}
