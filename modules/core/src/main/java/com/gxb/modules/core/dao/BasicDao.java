package com.gxb.modules.core.dao;

import com.gxb.modules.core.domain.FilterDomain;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author lh
 */
public interface BasicDao<T> {
	
	int getCt(Map<String, Object> map);
	
	T getById(Long id);
	
	T getByExample(T t);

	Map<Long, T> mapByIds(Set<Long> ids);
	
	List<T> listByFilter(FilterDomain<T> filterDomain);
	
	List<T> listByMap(Map<String, Object> map);
	
	List<T> listByExample(T t);

	Long save(T t);

	int saveList(List<T> list);

	int update(T t);
	
	int delete(Long id);
	
	int deleteByExample(T t);


}
