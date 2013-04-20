package com.onakaumi.core.base.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import com.onakaumi.core.base.model.AbstractModel;

/**
 * 基本的 增删改查。<br>
 * <b>查询</b>包括了：
 * <ul>
 * <li>hql</li>
 * <li>sql</li>
 * <li>criteria</li>
 * <li>example</li>
 * </ul>
 * <b>计数</b>也有criteria 和 example的实现， 没有写最基本的hql。因为用起来不方便<br>
 * <br>
 * 更新方法 还不是很完善<br>
 * <br>
 * <b>保存</b>和<b>删除 </b>就比较简单了。但是没有写批量操作的方法。 <br>
 * <br>
 * 
 * @author xiaoyee
 * @param <T>
 *            model
 * @param <ID>
 *            主键
 */
public interface IGenericDAO<T extends AbstractModel<ID>, ID extends Serializable> {

	// // **************************************
	// // *************计数方法********************
	// // **************************************
	/**
	 * 获取某一条件下的数量
	 * 
	 * @param criterion
	 *            查询条件
	 * @return 数量
	 */
	public abstract int countByCriteria(Criterion... criterion);

	/**
	 * 获取与给定 实体 相似的 实体的数量<br>
	 * <b>这一个速度会很慢</b>
	 * 
	 * @param example
	 * @param excludeProperty
	 * @return 数量
	 */
	@Deprecated
	public abstract int countByExample(T example, String... excludeProperty);

	// // **************************************
	// // *************删除方法********************
	// // **************************************
	/**
	 * 根据传入的Id 删除一个
	 * 
	 * @param id
	 * @return
	 */
	public abstract int deleteById(ID id);

	/**
	 * 删除一个对象
	 * 
	 * @param entity
	 */
	public abstract void deleteEntity(T entity);

	// // **************************************
	// // *************查询方法*****结果为集合********************
	// // **************************************
	/**
	 * 得到所有的
	 * 
	 * @param order
	 * 
	 * @return 所有的实体
	 */
	public abstract List<T> queryAll(Order order);

	// // *************criteria********************
	/**
	 * 查询分页， nums=0时 不分页
	 * 
	 * @param startIndex
	 *            开始位置
	 * @param pageSize
	 *            查询数量
	 * @param order
	 *            排序方式, 可以为null
	 * @param criterion
	 *            条件
	 * @return 结果的集合
	 */
	public abstract List<T> queryByCriteria(int first, int nums, Order order,
			Criterion... criterion);

	/**
	 * 查询 不带分页
	 * 
	 * @param order
	 *            排序方式, 可以为null
	 * @param criterion
	 *            条件
	 * @return
	 */
	public abstract List<T> queryByCriteria(Order order, Criterion... criterion);

	// // *************example********************
	/**
	 * 通过给定的一个对象，查找与其匹配的对象。支持分页
	 * 
	 * @param example
	 * @param first
	 * @param nums
	 * @param order
	 * @param excludeProperty
	 * @return
	 */
	public abstract List<T> queryByExample(T example, int first, int nums,
			Order order, String... excludeProperty);

	/**
	 * 通过给定的一个对象，查找与其匹配的对象。不支持分页
	 * 
	 * @param example
	 * @param order
	 * @param excludeProperty
	 * @return
	 */
	public abstract List<T> queryByExample(T example, Order order,
			String... excludeProperty);

	// // *************sql********************

	/**
	 * 使用 sql语句查询
	 * 
	 * @param sql
	 *            sql语句 包含？或者没有
	 * @param params
	 *            按顺序 写好参数
	 * @return
	 */
	public abstract List<T> queryBySQL(String sql, Object... params);

	// // ************hql********************
	/**
	 * 使用 hql语句查询
	 * 
	 * @param sql
	 *            hql语句 包含？或者没有
	 * @param params
	 *            按顺序 写好参数
	 * @return
	 */
	public abstract List<T> queryByHQL(String hql, Object... params);

	// // **************************************
	// // *************查询方法*****结果为单个实体********************
	// // **************************************

	/**
	 * 查找一个 如果数量不为一个 (多于一个 或少与一个 都不返回)
	 * 
	 * @param example
	 * @param excludeProperty
	 * @return
	 */
	public abstract T queryByExample(T example, String... excludeProperty);

	/**
	 * 根据条件查询一个
	 * 
	 * @param criterion
	 * @return
	 */
	public abstract T queryByCriteria(Criterion... criterion);

	/**
	 * 通过主键标识查找某个对象。
	 * 
	 * @param serializable
	 * @return
	 */
	public abstract T queryById(ID serializable);

	// // **************************************
	// // *************保存方法*************************
	// // **************************************
	/**
	 * 保存对象。
	 * 
	 * @param entity
	 * @return
	 */
	public abstract ID save(T entity);

	/**
	 * 保存或更新
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(T entity);

	// // **************************************
	// // *************更新方法*************************
	// // **************************************
	/**
	 * 更新对象。
	 * 
	 * @param entity
	 * @return
	 */
	public abstract void update(T entity);

	/**
	 * 还没有正式完成<br>
	 * 更新部分可能真的有用, 但是现在好像还不是很有用<br>
	 * <ol>
	 * <li>得到新对象(用户填写修改表单,全部或者部分属性修改后的 对象)</li>
	 * <li>对于新对象为 <b>null</b>, <b>""</b>, 0 ... 的属性 不予考虑, 即 首先过滤一遍. id为空 直接返回</li>
	 * <li>然后通过 要更新的id得到以前的对象(持久化的对象)</li>
	 * <li>判断新对象与旧对象不同的属性, 添加到待更新队列. 或者替换旧对象的相关属性.</li>
	 * <li>最后持久化就对象</li>
	 * </ol>
	 * 
	 * <pre>
	 * 	<li>对于 null或empty, 0, 可以设置两个标志位, 更新时选择是否要将 考虑在内</li>
	 * </pre>
	 * 
	 * @return 受影响的行数, 1 成功, 0 失败
	 */
	public int updateByExample(T entity);

	// // **************************************
	// // *************更新 删除 增加 综合方法，直接写语句查询********************
	// // **************************************
	/**
	 * update sql， 包括 update insert delete
	 * 
	 * @param sql
	 *            查询语句
	 * @param params
	 *            参数
	 * @return
	 */
	public abstract int execSQL(String sql, Object... params);

	/**
	 * 包括 update insert delete
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            参数
	 * @return
	 */
	public abstract int execHQL(String hql, Object... params);
}
