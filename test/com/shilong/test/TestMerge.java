package com.shilong.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shilong.test.entity.Person;
import com.shilong.test.service.TestService;

public class TestMerge {
	ApplicationContext ctx;
	
	@Before
	public void locat() throws Exception {
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void testSpring() throws Exception {
		TestService ts=(TestService) ctx.getBean("testService");
		ts.say();
	}
	
	@Test
	public void testHibernate() throws Exception {
		SessionFactory sf=(SessionFactory) ctx.getBean("sessionFactory");
		Person p=new Person("mrli1","manager1");
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		session.save(p);
		tr.commit();
		session.close();
	}
	
		@Test
		public void testService() throws Exception {
		TestService ts=(TestService) ctx.getBean("testService");
//		System.out.println(ts.findPerson("facaaff34ddbcb14014ddbcb15850000").getName()+"............");
		ts.deletePerson("facaaff34ddbcb14014ddbcb15850000");	
	}
		
		@Test
		public void testTxReadOnly() throws Exception {
		TestService ts=(TestService) ctx.getBean("testService");
//		System.out.println(ts.findPerson("facaaff34ddbcb14014ddbcb15850000").getName()+"............");
		ts.deletePerson("facaaff34ddce8a1014ddce8a2860000");	
	}
}
