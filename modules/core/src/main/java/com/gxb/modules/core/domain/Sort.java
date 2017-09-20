package com.gxb.modules.core.domain;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 排序模型
 * 
 * @author lh
 * @date 2015年6月18日
 */
public class Sort {
	private String sort;	//排序键
	private Direction order;	//排序方式 asc,desc
	
	public Sort(){
		super();
	}
	
	public Sort(String sort){
		this.setSort(sort);
	}

	public String getSort() {
		return sort;
	}

	/**
	 * spilt string into sort field and order field
	 * 
	 * @param sort eg:created_at-,updated_at
	 * @author lh
	 * @date 2015年6月18日
	 */
	public void setSort(String sort) {
		if (StringUtils.isBlank(sort))
			return;
		
		sort = sort.trim();
		if (sort.contains("-")) {
			this.sort = sort.replace("-", "");
			this.order = Direction.DESC;
		} else {
			this.sort = sort;
			if(this.order == null){
				this.order = Direction.ASC;
			}
		}
	}

	public Direction getOrder() {
		return order;
	}

	@Override
	public String toString() {
		return sort + " " + order;
	}

	/**
	 * analyze sort string return Sort list
	 *  
	 * @author lh
	 * @date 2015年6月18日
	 */
	public static List<Sort> convert(String sortStr){
		if(StringUtils.isBlank(sortStr)) return null;
		List<Sort> sortList = new ArrayList<Sort>();
		for(String str :StringUtils.split(sortStr, ",")){
			Sort sort = new Sort();
			sort.setSort(str);
			
			sortList.add(sort);
		}
		return sortList;
	}
	
	/**
	 * validate and filter sort legality
	 *  
	 * @author lh
	 * @date 2015年6月18日
	 */
	public static List<Sort> validate(List<Sort> sortList, String... sortKeys){
		if(CollectionUtils.isEmpty(sortList)) return sortList;

		for (Iterator<Sort> it = sortList.iterator(); it.hasNext();) {
			Sort sort = it.next();
			if (sort == null || StringUtils.isEmpty(sort.getSort()))
				continue;
			if(!ArrayUtils.contains(sortKeys, sort.getSort().toLowerCase())){
				it.remove();
			}
			
		}
		return sortList;
	}
	
	/**
	 * validate and filter sort legality return String list
	 *  
	 * @author lh
	 * @date 2015年6月18日
	 */
	public static String validateAndConvert(List<Sort> sortList, String... sortKeys){
		if(ArrayUtils.isEmpty(sortKeys)) throw new RuntimeException("the filter sort key is require!");
		
		sortList = validate(sortList, sortKeys);
		if(CollectionUtils.isEmpty(sortList)){
			sortList = new ArrayList<Sort>(1);
			sortList.add(new Sort(sortKeys[0]+"-"));
		}
		
		List<String> strList = new ArrayList<String>();
		for(Sort sort : sortList){
			strList.add(sort.toString());
		}
		
		return StringUtils.join(strList, ",");
	}
	
}
