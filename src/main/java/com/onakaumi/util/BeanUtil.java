package com.onakaumi.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

public class BeanUtil {

	public static Logger logger = Logger.getLogger(BeanUtil.class);

	/**
	 * 把src中不为null的属性赋值给dest（dest可能所有的属性都不为空）
	 * 
	 * @param source
	 *            源对象
	 * @param target
	 *            目的对象
	 * @return
	 */
	public static void mergeProperties(Object source, Object target,
			String... excludeProperties) {
		if (source == null || target == null) {
			logger.warn("source 或者 target 必须不能为null");
			return;
		}
		Class<?> clazz = target.getClass();
		if (!clazz.equals(source.getClass())) {
			logger.warn("source和target必须是同一个类的对象。");
			return;
		}

		Field[] fields = getAllFields(clazz);
		List<String> exs = Arrays.asList(excludeProperties);

		try {
			for (Field field : fields) {
				if (!Modifier.isPublic(field.getModifiers())) {
					// 设置可访问
					field.setAccessible(true);
				}

				String name = field.getName();
				if (exs.contains(name)) {
					// 跳出本次循环
					continue;
				}

				Object value = field.get(source);
				int mod = field.getModifiers();
				if (value != null && !Modifier.isFinal(mod)
						&& !Modifier.isStatic(mod)) {

					PropertyDescriptor descriptor = new PropertyDescriptor(
							name, clazz);
					Method writeMethod = descriptor.getWriteMethod();
					if (writeMethod != null) {
						if (!Modifier.isPublic(writeMethod.getModifiers())) {
							writeMethod.setAccessible(true);
						}
						// 赋值
						writeMethod.invoke(target, value);
					} else {
						logger.warn(name + "没有set方法。");
					}
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param clazz
	 * @return
	 */
	public static PropertyDescriptor[] getAllPropertyDescriptor(Class<?> clazz) {
		BeanInfo info = null;
		try {
			info = Introspector.getBeanInfo(clazz);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return info.getPropertyDescriptors();
	}

	/**
	 * @param clazz
	 * @return
	 */
	public static Field[] getAllFields(Class<?> clazz) {
		// 继承，。。？
		return clazz.getDeclaredFields();
	}
}
