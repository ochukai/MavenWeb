package com.onakaumi.core.base.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.onakaumi.core.base.model.AbstractModel;
import com.onakaumi.util.pagination.Pager;

/**
 * 只是实现最基本的 增删该查, 也起到一个 规范方法名称的作用
 * <p>
 * 
 * TODO 这里命名应该具体化。
 * 
 * @author xiaoyee
 * 
 * @param <T>
 * @param <ID>
 */
public interface IService<T extends AbstractModel<ID>, ID extends Serializable> {

	/**
	 * 获取所有的
	 * 
	 * @return 所有的
	 */
	public abstract List<T> findAll(Order order);

	/**
	 * 查询结果支持分业
	 * 
	 * @param page
	 *            页
	 * @param rows
	 *            每页的条目数
	 * @param order
	 *            排序
	 * @param criterions
	 *            条件
	 * @return 包含分页信息的结果集
	 */
	public abstract Pager<T> findPage(int page, int rows, Order order,
			Criterion... criterions);

	/**
	 * 根据主键 获取一个
	 * 
	 * @param id
	 *            主键的值
	 * @return id 对应的那个实体
	 */
	public abstract T findByIdentity(ID id);

	/**
	 * 修改实体
	 * 
	 * @param entity
	 *            需要修改的实体
	 */
	public abstract void modify(T entity);

	/**
	 * 根据主键删除一个
	 * 
	 * @param id
	 *            主键
	 * @return 删除结果
	 */
	public abstract boolean remove(ID id);

	/**
	 * 添加一个
	 * 
	 * @param entity
	 *            带添加实体, 没有主键
	 * @return 新添加的实体的主键
	 */
	public abstract ID add(T entity);

	/**
	 * 数量
	 * 
	 * @param criterions
	 *            条件
	 * @return 数量
	 */
	public abstract int findCount(Criterion... criterions);

	/**
	 * 执行 hql语句更新操作， 没有必要有，可完全按照函数名称来
	 * 
	 * @param hql
	 * @return
	 */
	public abstract int executeHqlUpdate(String hql, Object... params);

}
