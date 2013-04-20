/**
 * 
 */
package com.onakaumi.core.base.web.action;

import java.io.Serializable;

import com.onakaumi.core.base.model.AbstractModel;

/**
 * 具体实现
 * 
 * @author Onakaumi
 * @since 2013-4-21 上午12:54:21
 * 
 */
public class BaseAction<T extends AbstractModel<ID>, ID extends Serializable>
		extends AbstractAction<T> {
	private static final long serialVersionUID = 1L;

	@Override
	public void prepare() throws Exception {

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
