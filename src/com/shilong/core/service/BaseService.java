package com.shilong.core.service;

import java.io.Serializable;
import java.util.List;

import com.shilong.core.page.PageResult;
import com.shilong.core.util.QueryHelper;
import com.shilong.nsfw.info.entity.Info;

public interface BaseService<T> {
	// 增
	public void save(T t);

	// 删
	public void delete(Serializable id);

	// 改
	public void update(T t);

	// 查
	public T findObjectById(Serializable id);

	public List<T> findObjects();
	
	//查询
	@Deprecated
	List<T> findObjects(String hql, List<Object> parameter);
	
	public List<T> findObjects(QueryHelper q);
	
	public PageResult getPageResult(QueryHelper qh, int pageNo, int pageSize);
}
