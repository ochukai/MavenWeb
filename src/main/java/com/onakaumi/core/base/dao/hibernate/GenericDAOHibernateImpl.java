package com.onakaumi.core.base.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.onakaumi.core.base.dao.IGenericDAO;
import com.onakaumi.core.base.model.AbstractModel;

@SuppressWarnings("unchecked")
public abstract class GenericDAOHibernateImpl<T extends AbstractModel<ID>, ID extends Serializable>
		implements IGenericDAO<T, ID> {

	protected Logger logger = Logger.getLogger(getClass());
	private SessionFactory sessionFactory;
	private Class<T> clazz;

	/**
	 * 构造函数的目的是 将第一个泛型参数转化为class。
	 */
	public GenericDAOHibernateImpl() {
		clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * @return 回话操作对象
	 */
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int countByCriteria(Criterion... criterion) {
		Criteria c = getSession().createCriteria(clazz).setProjection(
				Projections.rowCount());// 设置计数

		for (Criterion crit : criterion) {
			c.add(crit);// 卓个添加查询条件
		}

		return ((Long) c.uniqueResult()).intValue();
	}

	@Override
	@Deprecated
	public int countByExample(T example, String... excludeProperty) {
		return queryByExample(example, null, excludeProperty).size();
	}

	@Override
	public int deleteById(ID id) {

		StringBuffer sb = new StringBuffer("delete from ").append(
				clazz.getSimpleName()).append(" o where o.id = ?");

		return getSession().createQuery(sb.toString()).setParameter(0, id)
				.executeUpdate();
	}

	@Override
	public void deleteEntity(T entity) {
		getSession().delete(entity);
	}

	@Override
	public int execHQL(String hql, Object... params) {
		Query q = setParameters(getSession().createQuery(hql), params);
		return q.executeUpdate();
	}

	@Override
	public int execSQL(String sql, Object... params) {
		SQLQuery sq = (SQLQuery) setParameters(
				getSession().createSQLQuery(sql), params);
		return sq.executeUpdate();
	}

	@Override
	public T queryByCriteria(Criterion... criterion) {
		Criteria c = getSession().createCriteria(clazz);

		for (Criterion crit : criterion) {
			c.add(crit);
		}

		logger.debug("in GenericDAOHibernate getByCriteria....");
		return (T) c.uniqueResult();
	}

	@Override
	public List<T> queryAll(Order order) {

		/*
		 * Criteria crit = getSession().createCriteria(clazz); if (null !=
		 * order) { crit.addOrder(order); } return crit.list();
		 */

		return queryByCriteria(0, 0, order);
	}

	@Override
	public List<T> queryByCriteria(Order order, Criterion... criterion) {
		return queryByCriteria(0, 0, order, criterion);
	}

	@Override
	public List<T> queryByCriteria(int first, int nums, Order order,
			Criterion... criterion) {
		Criteria c = getSession().createCriteria(clazz);

		if (nums != 0) {
			c.setFirstResult(first).setMaxResults(nums);
		}

		if (null != order) {
			c.addOrder(order);
		}

		for (Criterion crit : criterion) {
			c.add(crit);
		}

		return c.list();
	}

	@Override
	public List<T> queryByExample(T exampleInstance, int first, int nums,
			Order order, String... excludeProperty) {
		Criteria crit = getSession().createCriteria(clazz);
		Example example = Example.create(exampleInstance)// 创建一个example
				.excludeNone() // 排除null
				.excludeZeroes() // 排除 0
				.enableLike(MatchMode.ANYWHERE) // 全部模糊查询
				.ignoreCase(); // 不区分大小写

		if (null != order) {
			crit.addOrder(order);
		}

		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}

		crit.add(example);

		if (nums != 0) {
			crit.setFirstResult(first).setMaxResults(nums);
		}

		return crit.list();
	}

	@Override
	public List<T> queryByExample(T example, Order order,
			String... excludeProperty) {
		return queryByExample(example, 0, 0, null, excludeProperty);
	}

	@Override
	public T queryByExample(T example, String... excludeProperty) {
		List<T> dummy = queryByExample(example, null, excludeProperty);
		return (dummy.size() == 1) ? dummy.get(0) : null;
	}

	@Override
	public List<T> queryByHQL(String hql, Object... params) {
		Query q = setParameters(getSession().createQuery(hql), params);
		return q.list();
	}

	@Override
	public T queryById(ID id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> queryBySQL(String sql, Object... params) {
		// setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
		SQLQuery sq = (SQLQuery) setParameters(
				getSession().createSQLQuery(sql), params);
		return sq.list();
	}

	@Override
	public ID save(T entity) {
		return (ID) getSession().save(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public int updateByExample(T entity) {
		return 0;
	}

	/**
	 * 设置参数
	 * 
	 * @param query
	 * @param params
	 * @return
	 */
	private Query setParameters(Query query, Object... params) {
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query;
	}

}
