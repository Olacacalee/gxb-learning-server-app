package com.gxb.modules.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页模型
 * @author lh
 * @date 2015年5月25日
 */
@JsonInclude(Include.ALWAYS)
public class PageDomain<T> implements Serializable  {
	private static final long serialVersionUID = -7317172401486066839L;
	private static final int PAGE_SIZE = 10;
	
	/**
	 * 基础属性
	 */
	private int page = 1; // 当前页号
	private int total; // 查询总记录数
	
	private int size = PAGE_SIZE; // 页面显示记录数
	
	private int totalPages; // 总页数
	private int startRow; // 起始记录数
	private int endRow; // 结束记录数
	
	private Map<String, Object> queryParams = new HashMap<String, Object>();
	
	private List<Sort> sortList;	//排序键
	private List<T> pageList; // 当前页数据集合
	
	public PageDomain() {
		super();
	}
	
	public PageDomain(int curPage, int total) {
		this(curPage, PAGE_SIZE, total);
	}
	
	public PageDomain(int curPage, int pageSize, int total) {
		this(curPage, pageSize, total, null);
	}
	
	public PageDomain(int curPage, int pageSize, int total, List<T> pageList) {
		this(curPage, pageSize, total, pageList, null);
	}
	

	/**
	 * 
	 * @param curPage
	 *            当前页号
	 * @param pageSize
	 *            页面显示记录数(当输入pageSize=0 默认为10 )
	 * @param total
	 *            总记录数
	 *            当前页数据集合
	 */
	public PageDomain(int curPage, int pageSize, int total, List<T> pageList, Map<String, Object> queryParams) {
		this.page = curPage;
		this.total = total;
		
		this.pageList = pageList;
		this.queryParams = queryParams;
		// 计算基础信息
		this.refresh(curPage, total, pageSize);
	}
	
	public void refresh() {
		this.refresh(this.page, this.total, this.size);
	}

	public void refresh(int curPage, int total, int pageSize) {
		if (pageSize > 0) {
			this.size = pageSize;
		}

		/**
		 * 计算总页数
		 */
		this.totalPages = total / pageSize;
		if (total % pageSize > 0) {
			this.totalPages = this.totalPages + 1;
		}
		/**
		 * 计算 起始记录数,结束记录数
		 */
		this.startRow = ((curPage < 0 ? 1 : curPage) - 1) * pageSize;
		this.startRow = Math.min(this.startRow, total);
		this.endRow = Math.min(this.startRow + pageSize, total);
	}

	public int getCurPage() {
		return page;
	}

	public void setCurPage(int curPage) {
		this.page = curPage;
		refresh();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		refresh();
	}

	public int getPageSize() {
		return size;
	}

	public void setPageSize(int pageSize) {
		this.size = pageSize;
		refresh();
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, Object> queryParams) {
		this.queryParams = queryParams;
	}

	public List<T> getPageList() {
		return pageList;
	}

	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}

	public List<Sort> getSortList() {
		return sortList;
	}

	public void setSortList(String sortList) {
		this.sortList = Sort.convert(sortList);
	}

}
