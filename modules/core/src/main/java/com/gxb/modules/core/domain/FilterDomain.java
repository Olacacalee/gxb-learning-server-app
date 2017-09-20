/**
 *  Copyright (c)  2014-2020 Gaoxiaobang, Inc.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of Gaoxiaobang, 
 *  Inc. ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into with Gaoxiaobang.
 */
package com.gxb.modules.core.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gxb.modules.utils.CollectionTools;
import com.gxb.modules.utils.StringTools;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections.MapUtils;

import java.io.Serializable;
import java.util.*;

/**
 * 
 * @author lh
 * @date 2015年10月26日
 */
@Getter
@ToString
@EqualsAndHashCode
@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility=Visibility.PROTECTED_AND_PUBLIC)
public class FilterDomain<T> implements Serializable {
	private static final long serialVersionUID = 8690890304953120687L;
	
	// paging
	private PagingDomain paging;
	// sort
	private List<Sort> sortList;
	// field
	private List<String> fieldList;
	// query
	private Map<String, Object> filter = new HashMap<String, Object>();
	// data
	private List<T> dataList;

	@JsonIgnore
	@Setter
	private Boolean autoPageOrder = true;

	public void setPaging(String paging) {
		Map<String, Object> map = StringTools.split2map(paging);
		if(MapUtils.isEmpty(map))	return;
		
		PagingDomain pagingDomain = new PagingDomain();
		
		Integer curPage = MapUtils.getInteger(map, "curPage");
		if (curPage != null)	pagingDomain.setCurPage(curPage);
		Integer pageSize = MapUtils.getInteger(map, "pageSize");
		if (pageSize != null)	pagingDomain.setPageSize(pageSize);
		
		this.paging = pagingDomain;
	}

	public void setPagingDomain(PagingDomain pagingDomain) {
		this.paging = pagingDomain;
	}
	
	public void setSort(String sortList) {
		String[] sortStr = StringTools.split(sortList, ",");
		if(CollectionTools.isEmpty(sortStr)) return;
		
		List<Sort> rsList = new ArrayList<Sort>();
		for(String sort : sortStr){
			rsList.add(new Sort(sort));
		}
		this.sortList = rsList;
	}

	public void setSortList(List<Sort> sortList) {
		this.sortList = sortList;
	}
	
	public void setField(String fieldList) {
		this.fieldList = Arrays.asList(StringTools.split(fieldList, ","));
	}

	public void setFieldList(List<String> fieldList) {
		this.fieldList = fieldList;
	}

	public void setFilter(String filter) {
		this.filter = StringTools.split2map(filter);
	}

	public void setFilterMap(Map<String, Object> filterMap) {
		this.filter = filterMap;
	}

	public FilterDomain<T> setDataList(List<T> dataList) {
		this.dataList = dataList;
		return this;
	}

}
