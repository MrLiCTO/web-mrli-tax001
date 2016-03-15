package com.shilong.test.dao.impl;

import java.io.Serializable;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.shilong.test.dao.TestDao;
import com.shilong.test.entity.Person;

public class TestDaoImpl extends HibernateDaoSupport implements TestDao{
	
	@Override
	public void save(Person p) {
		getHibernateTemplate().save(p);
		
	}

	@Override
	public void update(Person p) {
		getHibernateTemplate().update(p);
		
	}

	@Override
	public Person findPerson(Serializable id) {
		return getHibernateTemplate().get(Person.class, id);
	}

	@Override
	public void deletePerson(Serializable id) {
		Person p=findPerson(id);
		getHibernateTemplate().delete(p);
		
	}

}
