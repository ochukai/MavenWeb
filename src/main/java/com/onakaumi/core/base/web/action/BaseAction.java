/**
 * 
 */
package com.onakaumi.core.base.web.action;

import java.io.Serializable;

import com.onakaumi.core.base.model.AbstractModel;
import com.onakaumi.core.base.service.IService;
import com.onakaumi.util.BeanUtil;
import com.onakaumi.util.StringUtil;
import com.opensymphony.xwork2.Preparable;

/**
 * 具体实现
 * 
 * @author Onakaumi
 * @since 2013-4-21 上午12:54:21
 * 
 */
@SuppressWarnings("unchecked")
public abstract class BaseAction<T extends AbstractModel<ID>, ID extends Serializable>
		extends AbstractAction<T> implements Preparable {
	private static final long serialVersionUID = 1L;

	/**
	 * 业务逻辑层通用接口，可指向所有实现该接口（或该接口的子接口）的对象 该对象通过依赖注入方式获取
	 */
	protected abstract IService<T, ID> service();

	@Override
	public T getModel() {
		return model;
	}

	/** 
	 * 
	 */
	@Override
	public void prepare() throws Exception {
		try {
			model = instanceById();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @return
	 */
	private T instanceById() {
		String rid = request.getParameter("id");
		ID id = null;
		T ret = null;
		if (StringUtil.isNotEmpty(rid)) {
			// 获取entity 的主键类型
			Class<?> c = BeanUtil.retrieveGenericType(getClass(), 1);
			if (c.equals(Integer.class)) {
				id = (ID) Integer.valueOf(rid);
			} else {
				id = (ID) rid;
			}

			ret = service().findByIdentity(id);
		}

		if (ret == null) {
			ret = instanceModel();
		}
		return ret;
	}

	/**
	 * @return
	 * 
	 */
	private T instanceModel() {
		Class<T> clazz = BeanUtil.retrieveGenericType(getClass());
		return BeanUtil.instance(clazz);
	}

	// **************************************************
	// *********************实现了增删改查。****************************
	// **************************************************

	@Override
	public String index() {
		try {
			pager = service().findPage(page, rows, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		try {
			service().add(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LIST;
	}

	@Override
	public String delete() {
		try {
			service().remove(model.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LIST;
	}

	@Override
	public String update() {
		try {
			service().modify(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LIST;
	}
}
