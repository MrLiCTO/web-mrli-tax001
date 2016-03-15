package com.shilong.test.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shilong.test.dao.TestDao;
import com.shilong.test.entity.Person;
import com.shilong.test.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {
	
	@Resource
	private TestDao testDao;
	
	@Override
	public void say() {
		System.out.println("service saying hi ............");

	}

	@Override
	public void save(Person p) {
		testDao.save(p);
	}

	@Override
	public void update(Person p) {
		testDao.update(p);
		
	}

	@Override
	public Person findPerson(Serializable id) {
		
		Person p= this.testDao.findPerson(id);
		
		//this.testDao.deletePerson(id);
		
		return p;
	}

	@Override
	public void deletePerson(Serializable id) {
		this.testDao.deletePerson(id);
		
	}
	

}
