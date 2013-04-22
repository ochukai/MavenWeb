package com.onakaumi.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 闫超
 * @date 2013-3-10
 * @Description 将对象转换为Json格式 <br>
 *              考虑到Set、List、Map等集合元素可能会产生深度递归，本类不提供对这些元素的json转换
 */
public class JsonUtil {

	/**
	 * TODO 如何一级递归
	 * 
	 * @param obj
	 * @return
	 */
	public static String object2json(Object obj) {
		StringBuilder json = new StringBuilder();
		if (obj == null) {
			json.append("\"\"");
		} else if (obj instanceof String) { // 字符串
			json.append("\"").append(string2json(obj.toString())).append("\"");
		} else if (obj instanceof Boolean || obj instanceof Number) {// 数字和布尔
			json.append(obj);
		} else if (obj instanceof Object[]) {
			json.append("\"Object[] hide\"");
		} else if (obj instanceof List) {
			json.append("\"List hide\"");
		} else if (obj instanceof Map) {
			json.append("\"Map hide\"");
		} else if (obj instanceof Set) {
			json.append("\"Set hide\"");
		} else if (obj instanceof Date) {
			json.append("\"").append(TimeFormat.Y_M_D_H_M_S.format((Date) obj))
					.append("\"");
		} else {
			json.append(bean2json(obj));
		}
		return json.toString();
	}

	/**
	 * @param bean
	 * @return
	 */
	public static String bean2json(Object bean) {
		if (bean == null) {
			return "\"\"";
		}

		StringBuilder json = new StringBuilder("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class)
					.getPropertyDescriptors();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}

		if (props != null) {
			try {
				for (int i = 0; i < props.length; i++) {
					// 名字肯定是字符串
					String name = string2json(props[i].getName());
					// value 转化成json
					Method read = props[i].getReadMethod();
					String value = object2json(read.invoke(bean));
					// 键值对，拼在一起
					json.append(name).append(":").append(value).append(",");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	/**
	 * @param list
	 * @return
	 */
	public static String list2json(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			try {
				for (Object obj : list) {
					json.append(object2json(obj));
					json.append(",");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * @param array
	 * @return
	 */
	public static String array2json(Object[] array) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (array != null && array.length > 0) {
			for (Object obj : array) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * @param map
	 * @return
	 */
	public static String map2json(Map<?, ?> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				json.append(object2json(key));
				json.append(":");
				json.append(object2json(map.get(key)));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	/**
	 * @param set
	 * @return
	 */
	public static String set2json(Set<?> set) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (set != null && set.size() > 0) {
			for (Object obj : set) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * @param s
	 * @return
	 */
	public static String string2json(String s) {
		if (s == null)
			return "";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		}
		return sb.toString();
	}
}
