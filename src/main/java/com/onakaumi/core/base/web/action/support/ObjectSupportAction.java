package com.onakaumi.core.base.web.action.support;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * @author onakaumi
 * 
 * @param <T>
 */
public abstract class ObjectSupportAction<T> extends PageSupportAction<T>
		implements ModelDriven<T>, Preparable {
	private static final long serialVersionUID = 1L;

	/**
	 * 集合数据
	 */
	protected List<T> lists;

	/**
	 * modelDriven需要返回的对象,
	 * 
	 * prepare()方法会初始化entity;
	 */
	protected T model;

	public List<T> getLists() {
		return lists;
	}

	@Override
	public T getModel() {
		return model;
	}

	public void setLists(List<T> lists) {
		this.lists = lists;
	}

	public void setModel(T model) {
		this.model = model;
	}

}
