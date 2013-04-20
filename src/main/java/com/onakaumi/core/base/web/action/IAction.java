package com.onakaumi.core.base.web.action;

/**
 * @author xiaoyee
 */
public abstract interface IAction {

	/**
	 * add， modify， delete
	 */
	static final String LIST = "list";
	static final String REGIST = "regist";
	static final String LOGON = "logon";

	// ******************************************
	// ***************基础方法********************
	// ******************************************

	/**
	 * 列出所有的
	 */
	public abstract String index();

	/**
	 * 列出一个。
	 * 
	 */
	public abstract String show();

	/**
	 * 创建
	 */
	public abstract String editNew();

	/**
	 * 增加一个
	 */
	public abstract String add();

	/**
	 * 删除一个
	 */
	public abstract String delete();

	/**
	 * 编辑
	 */
	public abstract String edit();

	/**
	 * 修改一个
	 */
	public abstract String update();
}
