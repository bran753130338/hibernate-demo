package cn.itheima.a_hello;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.itheima.domain.Customer;

//测试Hibernate框架
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationcontext.xml")
public class Demo {

	@Autowired
	Configuration configuration;
	@Test
	//保存客户
	public void fun1(){
		configuration.configure();
		
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
	
		Transaction tx = session.beginTransaction();
		//----------------------------------------------
		Customer c = new Customer();
		c.setCust_name("tencent公司");
		
		session.save(c);//执行保存
		
		//----------------------------------------------
		tx.commit();
		session.close();
		sessionFactory.close();
	}
}
