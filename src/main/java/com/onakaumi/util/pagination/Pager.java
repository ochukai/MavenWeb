package com.onakaumi.util.pagination;

import java.util.List;

public class Pager<T> {

	/**
	 * 默认设定每页显示记录数为10
	 */
	public static final int DEFAULT_PAGE_SIZE = 10;

	/**
	 * 默认当前为第一页.
	 */
	public static final int DEFAULT_CURRENT_PAGE = 1;

	private int totalItems = -1; // 总记录数
	private int pageSize = DEFAULT_PAGE_SIZE; // 每页行数
	private int currentPage = DEFAULT_CURRENT_PAGE; // 当前页码
	private int totalPages = -1; // 总页数

	private boolean isFirstPage; // 是否第一页
	private boolean isLastPage; // 是否最后一页
	private boolean hasNext; // 是否有下一页
	private boolean hasPrevious; // 是否有前一页

	/**
	 * 数据集
	 */
	private List<T> list;

	/**
	 * 构造方法
	 */
	public Pager() {
		super();
	}

	public Pager(int totalItems) {
		this(totalItems, DEFAULT_PAGE_SIZE, DEFAULT_CURRENT_PAGE);
	}

	public Pager(int totalItems, int currentPage) {
		this(totalItems, DEFAULT_PAGE_SIZE, currentPage);
	}

	public Pager(int totalItems, int pageSize, int currentPage) {
		super();
		setTotalItems(totalItems);
		setPageSize(pageSize);
		setCurrentPage(currentPage);
		setTotalPages();
		setHasNext();
		setHasPrevious();
		setFirstPage();
		setLastPage();
	}

	/**
	 * @return 获取开始的页数
	 */
	public int getBeginIndex() {
		return (currentPage - 1) * pageSize;
	}

	/**
	 * @return 当前页
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	public List<T> getList() {
		return list;
	}

	/**
	 * 获取下一页的页码 如果不是会讲curPage + 1
	 * 
	 * @return 下一页的页码
	 */
	public int getNextPage() {
		if (currentPage + 1 >= this.totalPages) {
			return this.totalPages;
		} else {
			return currentPage + 1;
		}
	}

	/**
	 * @return 每页的数目
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @return 前一页的页码 如果存在
	 */
	public int getPreviousPage() {
		if (currentPage - 1 <= 1) {
			return 1;
		} else {
			return currentPage - 1;
		}
	}

	/**
	 * @return 总条数
	 */
	public int getTotalItems() {
		return totalItems;
	}

	/**
	 * @return 总页数
	 */
	public int getTotalPages() {
		if (totalPages == -1) {
			setTotalPages();
		}
		return totalPages;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	// 修正计算当前页
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setFirstPage() {
		this.isFirstPage = (currentPage == 1);
	}

	public void setHasNext() {
		this.hasNext = (currentPage < this.totalPages);
	}

	public void setHasPrevious() {
		this.hasPrevious = currentPage > 1;
	}

	public void setLastPage() {
		this.isLastPage = currentPage >= this.totalPages;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public void setPageSize(int pageSize) {
		if (pageSize <= 0) {
			System.out.println("pageSize should > 0.");
			this.pageSize = 0;
		} else {
			this.pageSize = pageSize;
		}
	}

	public void setTotalItems(int totalItems) {
		if (totalItems <= 0) {
			System.out.println("totalItems should > 0.");
			this.totalItems = 0;
		} else {
			this.totalItems = totalItems;
		}
	}

	public void setTotalPages() {
		this.totalPages = (totalItems % pageSize == 0) ? totalItems / pageSize
				: (totalItems / pageSize) + 1;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pager [totalItems=").append(totalItems)
				.append(", pageSize=").append(pageSize)
				.append(", currentPage=").append(currentPage)
				.append(", totalPages=").append(totalPages)
				.append(", isFirstPage=").append(isFirstPage)
				.append(", isLastPage=").append(isLastPage)
				.append(", hasNext=").append(hasNext).append(", hasPrevious=")
				.append(hasPrevious).append("]");
		return builder.toString();
	}
}
