package com.shilong.test;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.shilong.nsfw.user.service.UserService;

public class TestUserService {
ApplicationContext ctx;
	
	@Before
	public void locat() throws Exception {
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void testSpring() throws Exception {
//		UserService us=(UserService) ctx.getBean("userService");
//		System.out.print(us.getClass().getSimpleName());
		SessionFactory sf=(SessionFactory) ctx.getBean("sessionFactory");
		System.out.println(sf.getCurrentSession().toString());
	}
}
