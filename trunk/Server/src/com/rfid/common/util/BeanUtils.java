package com.rfid.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 扩展Apache Commons BeanUtils, 提供一些反射方面缺失功能的封装.
 * 
 * @author
 * @version 0.1 初始版本，征集意见
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {

	/**
	 * 日志
	 */
	protected static final Log log = LogFactory.getLog(BeanUtils.class);

	/**
	 * 私有化构造方法.
	 */
	private BeanUtils() {
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 * 
	 * @param object
	 *            对象
	 * @param fieldName
	 *            属性名
	 * @return DeclaredField
	 * @throws NoSuchFieldException
	 *             如果没有该Field时抛出.
	 */
	public static Field getDeclaredField(Object object, String fieldName)
			throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(fieldName);
		return getDeclaredField(object.getClass(), fieldName);
	}

	/**
	 * 循环向上转型,获取类的DeclaredField.
	 * 
	 * @param clazz
	 *            类
	 * @param fieldName
	 *            属性名
	 * @return DeclaredField
	 * @throws NoSuchFieldException
	 *             如果没有该Field时抛出.
	 */
	@SuppressWarnings("unchecked")
	public static Field getDeclaredField(Class clazz, String fieldName)
			throws NoSuchFieldException {
		Assert.notNull(clazz);
		Assert.hasText(fieldName);
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		throw new NoSuchFieldException("No such field: " + clazz.getName()
				+ '.' + fieldName);
	}

	/**
	 * 暴力获取对象属性值,忽略private,protected修饰符的限制.
	 * 
	 * @param object
	 *            对象
	 * @param fieldName
	 *            属性名
	 * @return 属性值
	 * @throws NoSuchFieldException
	 *             如果没有该Field时抛出.
	 */
	public static Object forceGetProperty(Object object, String fieldName)
			throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(fieldName);

		Field field = getDeclaredField(object, fieldName);

		boolean accessible = field.isAccessible();
		field.setAccessible(true);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			log.info("error wont' happen");
		}
		field.setAccessible(accessible);
		return result;
	}

	/**
	 * 暴力设置对象属性值,忽略private,protected修饰符的限制.
	 * 
	 * @param object
	 *            对象
	 * @param fieldName
	 *            属性名
	 * @param newValue
	 *            属性值
	 * @throws NoSuchFieldException
	 *             如果没有该Field时抛出.
	 */
	public static void forceSetProperty(Object object, String fieldName,
			Object newValue) throws NoSuchFieldException {
		Assert.notNull(object);
		Assert.hasText(fieldName);

		Field field = getDeclaredField(object, fieldName);
		boolean accessible = field.isAccessible();
		field.setAccessible(true);
		try {
			field.set(object, newValue);
		} catch (IllegalAccessException e) {
			log.info("Error won't happen");
		}
		field.setAccessible(accessible);
	}

	/**
	 * 暴力调用对象方法, 忽略private,protected修饰符的限制.
	 * 
	 * @param object
	 *            对象
	 * @param methodName
	 *            方法名
	 * @param params
	 *            参数
	 * @return 方法返回值
	 * @throws NoSuchMethodException
	 *             如果没有该Method时抛出.
	 */
	public static Object invokePrivateMethod(Object object, String methodName,
			Object... params) throws NoSuchMethodException {
		Assert.notNull(object);
		Assert.hasText(methodName);
		@SuppressWarnings("unchecked")
		Class[] types = new Class[params.length];
		for (int i = 0; i < params.length; i++) {
			types[i] = params[i].getClass();
		}

		Class<?> clazz = object.getClass();
		Method method = null;
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				method = superClass.getDeclaredMethod(methodName, types);
				break;
			} catch (NoSuchMethodException e) {
				// 方法不在当前类定义,继续向上转型
			}
		}

		if (method == null)
			throw new NoSuchMethodException("No Such Method:"
					+ clazz.getSimpleName() + methodName);

		boolean accessible = method.isAccessible();
		method.setAccessible(true);
		Object result = null;
		try {
			result = method.invoke(object, params);
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		method.setAccessible(accessible);
		return result;
	}

	/**
	 * 按属性类型取得Field列表.
	 * 
	 * @param object
	 *            对象
	 * @param fieldClass
	 *            属性类型
	 * @return Field列表
	 */
	public static List<Field> getFieldsByType(Object object, Class<?> fieldClass) {
		List<Field> list = new ArrayList<Field>();
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getType().isAssignableFrom(fieldClass)) {
				list.add(field);
			}
		}
		return list;
	}

	/**
	 * 按属性名获得Field的类型.
	 * 
	 * @param clazz
	 *            类
	 * @param fieldName
	 *            属性名
	 * @return Field的类型
	 * @throws NoSuchFieldException
	 */
	public static Class<?> getPropertyType(Class<?> clazz, String fieldName)
			throws NoSuchFieldException {
		return getDeclaredField(clazz, fieldName).getType();
	}

	/**
	 * 获得属性的getter函数名称.
	 * 
	 * @param clazz
	 *            类
	 * @param fieldName
	 *            属性名
	 * @return getter函数名称
	 */
	public static String getGetterName(Class<?> clazz, String fieldName) {
		Assert.notNull(clazz, "Type required");
		Assert.hasText(fieldName, "FieldName required");

		if (clazz.getName().equals("boolean")) {
			return "is" + StringUtils.capitalize(fieldName);
		} else {
			return "get" + StringUtils.capitalize(fieldName);
		}
	}

	/**
	 * 获得属性的getter函数,如果找不到该方法,返回null.
	 * 
	 * @param clazz
	 *            类
	 * @param fieldName
	 *            属性名
	 * @return getter函数
	 */
	public static Method getGetterMethod(Class<?> clazz, String fieldName) {
		try {
			return clazz.getMethod(getGetterName(clazz, fieldName));
		} catch (NoSuchMethodException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}

