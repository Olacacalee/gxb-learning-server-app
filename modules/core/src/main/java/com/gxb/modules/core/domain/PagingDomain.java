package com.gxb.modules.core.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 分页模型
 * 
 * @author lh
 * @date 2015年5月25日
 */
@Data
@EqualsAndHashCode
@ToString
@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility=Visibility.PROTECTED_AND_PUBLIC)
public class PagingDomain implements Serializable  {
	private static final long serialVersionUID = -7317172401486066839L;
	private static final int PAGE_SIZE = 10;
	
	/**
	 * 基础属性
	 */
	// 当前页号
	private Integer curPage = 1;
	// 查询总记录数
	private Integer total;
	// 页面显示记录数
	private Integer pageSize = PAGE_SIZE;

	// 总页数
	private Integer totalPage;
	// 起始记录数
	private Integer startRow;
	// 结束记录数
	private Integer endRow;

	public PagingDomain() {
		super();
		this.refresh();
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public PagingDomain(int curPage, int total) {
		this(curPage, PAGE_SIZE, total);
	}

	public PagingDomain(int curPage, int pageSize, int total) {
		this.setTotal(total);
		this.setCurPage(curPage);
		// 计算基础信息
		this.refresh();
	}
	
	/**
	 *  当前页大于总页数,重置为最后一页 
	 *  当前页小于1,重置为第一页
	 */
	public void setCurPage(int curPage) {
		this.curPage = Math.max(1, curPage);
		if(this.totalPage != null){
			this.curPage = Math.min(this.totalPage, this.curPage);
		}
		
		refreshRowRange();
	}

	/**
	 * 设置总数,刷新总页数, 及起始行
	 */
	public void setTotal(int total) {
		this.total = total;
		
		refresh();
	}

	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
		refresh();
	}
	
	private void refresh() {
		refreshTotalPage();
		refreshRowRange();
	}

	/**
	 * 刷新计算总页数
	 */
	private void refreshTotalPage() {
		if(this.total == null)	return;
		
		this.totalPage = this.total / this.pageSize;
		if (this.total % this.pageSize > 0) {
			this.totalPage = this.totalPage + 1;
		}
		
		this.setCurPage(this.curPage);
	}

	/**
	 * 计算 起始记录数,结束记录数
	 */
	private void refreshRowRange() {
		this.startRow = ((this.curPage < 1 ? 1 : this.curPage) - 1) * this.pageSize;
		this.endRow = this.startRow + this.pageSize;
		if(this.total != null){
			this.startRow = Math.min(this.startRow, this.total);
			this.endRow = Math.min(this.startRow + this.pageSize, this.total);
		}
	}

}
