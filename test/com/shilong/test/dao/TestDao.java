package com.shilong.test.dao;

import java.io.Serializable;

import com.shilong.test.entity.Person;

public interface TestDao {

	public void save(Person p);
	
	public void update(Person p);
	
	public Person findPerson(Serializable id);
	
	public void deletePerson(Serializable id);	
}
