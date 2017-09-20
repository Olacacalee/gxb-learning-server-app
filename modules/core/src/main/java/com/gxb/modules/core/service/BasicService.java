package com.gxb.modules.core.service;

import com.gxb.modules.core.dao.BasicDao;
import com.gxb.modules.core.domain.FilterDomain;

import java.util.List;
import java.util.Map;
import java.util.Set;


public abstract class BasicService<T> {
	protected abstract BasicDao<T> getDAO();

	public Map<Long, T> mapByIds(Set<Long> ids) {
		return this.getDAO().mapByIds(ids);
	}

	public List<T> listByFilter(FilterDomain<T> filterDomain){
		return this.getDAO().listByFilter(filterDomain);
	}
	
	public List<T> listByExample(T t) {
		return this.getDAO().listByExample(t);
	}
	
	public List<T> listByMap(Map<String, Object> map) {
		return this.getDAO().listByMap(map);
	}
	
	public int getCt(Map<String, Object> map) {
		return this.getDAO().getCt(map);
	}
	
	public T getById(Long id) {
		return this.getDAO().getById(id);
	}

	public T getByExample(T t) {
		return this.getDAO().getByExample(t);
	}

	public boolean save(T t) {
		return this.getDAO().save(t) > 0;
	}
	
	public boolean saveList(List<T> list) {
		return this.getDAO().saveList(list) > 0 ;
	}

	public int update(T t) {
		return this.getDAO().update(t);
	}
	
	public boolean delete(long id){
		return this.getDAO().delete(id) > 0;
	}

	public int deleteByExample(T t){
		return this.getDAO().deleteByExample(t);
	}

}
