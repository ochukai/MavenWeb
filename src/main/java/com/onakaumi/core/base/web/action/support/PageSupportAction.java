package com.onakaumi.core.base.web.action.support;

import org.apache.log4j.Logger;

import com.onakaumi.util.pagination.Pager;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author onakaumi
 * 
 * @param <T>
 */
public abstract class PageSupportAction<T> extends ActionSupport {
	private static final long serialVersionUID = 1L;
	protected Logger log = Logger.getLogger(getClass());

	/**
	 * 当前页
	 */
	protected int page = 1;
	/**
	 * 每页条目数
	 */
	protected int rows = 10;
	/**
	 * 分页数据
	 */
	protected Pager<T> pager;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Pager<T> getPager() {
		return pager;
	}

	public void setPager(Pager<T> pager) {
		this.pager = pager;
	}

}
