package cn.itheima.b_api;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.Test;

import cn.itheima.domain.Customer;
import cn.itheima.utils.HibernateUtils;

public class TestSql {
	
	@Test
	//基本查询
	public void fun1(){
		Session session = HibernateUtils.openSession();
		
		Transaction transaction = session.beginTransaction();
		//书写sql
		String sql = "select * from cst_customer";
		NativeQuery<Customer> sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(Customer.class);
		List<Customer> list = sqlQuery.list();
		System.out.println(list);
		transaction.commit();
		session.close();
	}
	
	@Test
	//条件查询
	public void fun2(){
		Session session = HibernateUtils.openSession();
		
		Transaction transaction = session.beginTransaction();
		//书写sql
		String sql = "select * from cst_customer where cust_id = ?0";
		NativeQuery<Customer> sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setParameter(0, 4l);
		sqlQuery.addEntity(Customer.class);
		Customer result = sqlQuery.uniqueResult();
		System.out.println(result);
		transaction.commit();
		session.close();
	}
	
	@Test
	//分页查询
	public void fun3(){
		Session session = HibernateUtils.openSession();
		
		Transaction transaction = session.beginTransaction();
		//书写sql
		String sql = "select * from cst_customer limit ?0 , ?1";
		NativeQuery<Customer> sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setParameter(0, 1);
		sqlQuery.setParameter(1, 2);
		sqlQuery.addEntity(Customer.class);
		List<Customer> list = sqlQuery.list();
		System.out.println(list);
		transaction.commit();
		session.close();
	}


}
