package com.onakaumi.core.base.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.onakaumi.core.base.dao.IGenericDAO;
import com.onakaumi.core.base.model.AbstractModel;
import com.onakaumi.util.pagination.Pager;

public abstract class AbstractService<T extends AbstractModel<ID>, ID extends Serializable>
		implements IService<T, ID> {
	protected Logger log = Logger.getLogger(getClass());

	/**
	 * @return
	 */
	protected abstract IGenericDAO<T, ID> getGenericDAO();

	@Override
	public ID add(T entity) {
		return getGenericDAO().save(entity);
	}

	@Override
	public int findCount(Criterion... criterions) {
		return getGenericDAO().countByCriteria(criterions);
	}

	@Override
	public List<T> findAll(Order order) {
		return getGenericDAO().queryAll(order);
	}

	@Override
	public T findByIdentity(ID id) {
		return getGenericDAO().queryById(id);
	}

	@Override
	public Pager<T> findPage(int page, int rows, Order order,
			Criterion... criterions) {

		Pager<T> pager = new Pager<T>(findCount(), rows, page);

		List<T> list = getGenericDAO().queryByCriteria(pager.getBeginIndex(),
				pager.getPageSize(), order, criterions);

		pager.setList(list);
		return pager;
	}

	@Override
	public void modify(T entity) {
		getGenericDAO().update(entity);
	}

	@Override
	public boolean remove(ID id) {
		int i = getGenericDAO().deleteById(id);
		return i == 1 ? true : false;
	}

	@Override
	public int executeHqlUpdate(String hql, Object... params) {
		return getGenericDAO().execHQL(hql, params);
	}

}
