package com.onakaumi.core.base.web.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.onakaumi.core.base.model.AbstractModel;
import com.onakaumi.core.base.web.action.support.ObjectSupportAction;

/**
 * 默认实现  七个方法。
 * 
 * @author Onakaumi
 * @since 2013-4-21 上午12:56:40
 *
 * @param <T>
 */
public abstract class AbstractAction<T extends AbstractModel<?>> extends
		ObjectSupportAction<T> implements IAction, SessionAware {
	private static final long serialVersionUID = 1L;

	private Map<String, Object> session;

	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	// **************************************************
	// **************************************************
	// **************************************************

	@Override
	public String index() {
		return SUCCESS;
	}

	@Override
	public String show() {
		return SUCCESS;
	}

	@Override
	public String editNew() {
		return SUCCESS;
	}

	@Override
	public String edit() {
		return SUCCESS;
	}

	@Override
	public String add() {
		return LIST;
	}

	@Override
	public String delete() {
		return LIST;
	}

	@Override
	public String update() {
		return LIST;
	}
}
