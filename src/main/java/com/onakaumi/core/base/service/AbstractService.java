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
	protected abstract IGenericDAO<T, ID> dao();

	@Override
	public ID add(T entity) {
		return dao().save(entity);
	}

	@Override
	public int findCount(Criterion... criterions) {
		return dao().countByCriteria(criterions);
	}

	@Override
	public List<T> findAll(Order order) {
		return dao().queryAll(order);
	}

	@Override
	public T findByIdentity(ID id) {
		return dao().queryById(id);
	}

	@Override
	public Pager<T> findPage(int page, int rows, Order order,
			Criterion... criterions) {

		Pager<T> pager = new Pager<T>(findCount(), rows, page);

		List<T> list = dao().queryByCriteria(pager.getBeginIndex(),
				pager.getPageSize(), order, criterions);

		pager.setList(list);
		return pager;
	}

	@Override
	public void modify(T entity) {
		dao().update(entity);
	}

	@Override
	public boolean remove(ID id) {
		int i = dao().deleteById(id);
		return i == 1 ? true : false;
	}

	@Override
	public int executeHqlUpdate(String hql, Object... params) {
		return dao().execHQL(hql, params);
	}

}
