package com.rfid.common.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 提供和泛型相关的一些方法.
 * 
 * @author
 * @version 0.1 初始版本，征集意见
 */
public class GenericsUtils {

	/**
	 * 日志
	 */
	private static final Log log = LogFactory.getLog(GenericsUtils.class);

	/**
	 * 私有化构造方法.
	 */
	private GenericsUtils() {
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的泛型参数的类型.
	 * 
	 * @param clazz
	 *            类型
	 * @return 父类的第一个泛型参数类型，如果无法确定类型，返回Object.class
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的泛型参数的类型.
	 * 
	 * @param clazz
	 *            类型
	 * @param index
	 *            泛型参数索引，从0开始
	 * @return 父类给定索引位置的泛型参数的类型，如果无法确定类型，返回Object.class
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			log.warn(clazz.getSimpleName()
					+ "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			log.warn("Index: " + index + ", Size of " + clazz.getSimpleName()
					+ "'s Parameterized Type: " + params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			log
					.warn(clazz.getSimpleName()
							+ " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		return (Class<?>) params[index];
	}

}
