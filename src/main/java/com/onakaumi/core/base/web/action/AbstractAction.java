package com.onakaumi.core.base.web.action;

import com.onakaumi.core.base.model.AbstractModel;
import com.onakaumi.core.base.web.action.support.DataSupportAction;

/**
 * 默认实现 七个方法。
 * 
 * @author Onakaumi
 * @since 2013-4-21 上午12:56:40
 * 
 * @param <T>
 */
public abstract class AbstractAction<T extends AbstractModel<?>> extends
		DataSupportAction<T> implements IAction {
	private static final long serialVersionUID = 1L;

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
