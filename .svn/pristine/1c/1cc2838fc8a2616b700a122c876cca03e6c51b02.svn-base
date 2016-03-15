package com.shilong.core.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import com.shilong.core.dao.BaseDao;
import com.shilong.core.page.PageResult;
import com.shilong.core.service.BaseService;
import com.shilong.core.util.QueryHelper;

public class BaseserviceImpl<T> implements BaseService<T> {
	
	private BaseDao<T> baseDao;
	
	
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void save(T t) {
		baseDao.save(t);
		
	}

	@Override
	public void delete(Serializable id) {
		baseDao.delete(id);
		
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}

	@Override
	public T findObjectById(Serializable id) {
		return baseDao.findObjectById(id);
	}

	@Override
	public List<T> findObjects() {
		return baseDao.findObjects();
	}

	@Override
	public List<T> findObjects(String hql, List<Object> parameter) {
		return baseDao.findObjects(hql, parameter);
	}

	@Override
	public List<T> findObjects(QueryHelper q) {
		
		return baseDao.findObjects(q);
	}

	@Override
	public PageResult getPageResult(QueryHelper qh, int pageNo, int pageSize) {
		
		return baseDao.getPageResult(qh, pageNo, pageSize);
	}

}
