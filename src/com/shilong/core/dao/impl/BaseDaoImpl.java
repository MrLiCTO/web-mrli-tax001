package com.shilong.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shilong.core.dao.BaseDao;
import com.shilong.core.page.PageResult;
import com.shilong.core.util.QueryHelper;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	protected Class<T> clazz;

	public BaseDaoImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);

	}

	@Override
	public void delete(Serializable id) {
		this.getHibernateTemplate().delete(this.findObjectById(id));

	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);

	}

	@Override
	public T findObjectById(Serializable id) {

		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findObjects() {
		Query q = this.getSession().createQuery("from " + clazz.getSimpleName());
		return q.list();
	}

	@Override
	public List<T> findObjects(String hql, List<Object> parameter) {
		Query q = this.getSession().createQuery(hql);

		if (parameter != null) {
			for (int i = 0; i < parameter.size(); i++) {
				q.setParameter(i, parameter.get(i));
			}
		}
		return q.list();
	}

	@Override
	public List<T> findObjects(QueryHelper qh) {
		Query q = this.getSession().createQuery(qh.getQueryListHql());

		if (qh.getParameters() != null) {
			for (int i = 0; i < qh.getParameters().size(); i++) {
				q.setParameter(i, qh.getParameters().get(i));
			}
		}
		return q.list();
	}

	@Override
	public PageResult getPageResult(QueryHelper qh, int pageNo, int pageSize) {
		
		Query q = this.getSession().createQuery(qh.getQueryListHql());

		if (qh.getParameters() != null) {
			for (int i = 0; i < qh.getParameters().size(); i++) {
				q.setParameter(i, qh.getParameters().get(i));
			}
		}
		
		if(pageNo<1){
			pageNo=1;
		}
		List items=q.setFirstResult((pageNo-1)*pageSize)//
				.setMaxResults(pageSize)//
				.list();
		

		Query q1=this.getSession().createQuery(qh.getQueryCountHql());
		
		if (qh.getParameters() != null) {
			for (int i = 0; i < qh.getParameters().size(); i++) {
				q1.setParameter(i, qh.getParameters().get(i));
			}
		}
		
		Long totalCount=(Long) q1.uniqueResult();
		
		return new PageResult(totalCount, pageNo, pageSize, items);
	}

}
