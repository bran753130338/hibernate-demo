package cn.itheima.b_api;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import cn.itheima.domain.Customer;
import cn.itheima.utils.HibernateUtils;

public class TestHql {

	@Test
	public void fun1() {
		//获得Session
		Session session = HibernateUtils.openSession();
		//打开事务
		Transaction transaction = session.beginTransaction();
		//执行操作
		//HQL语句
		String hql = "from cn.itheima.domain.Customer"; //查询所有的Customer对象
		//根据HQL语句创建查询对象
		Query<Customer> query = session.createQuery(hql,Customer.class);
		//根据查询对象获得查询结果
		List<Customer> list = query.list();
		System.out.println(list);
		//提交事务，关闭资源
		transaction.commit();
		session.close();
	}
	
	@Test
	public void fun2() {
		//获得Session
		Session session = HibernateUtils.openSession();
		//打开事务
		Transaction transaction = session.beginTransaction();
		//执行操作
		//HQL语句
		String hql = "from cn.itheima.domain.Customer where cust_id=4"; //查询cust_id=4的Customer对象
		//根据HQL语句创建查询对象
		Query<Customer> query = session.createQuery(hql,Customer.class);
		//根据查询对象获得查询结果
		Customer result = query.uniqueResult();
		//提交事务，关闭资源
		System.out.println(result);
		transaction.commit();
		session.close();
	}
	
	@Test
	public void fun3() {
		//获得Session
		Session session = HibernateUtils.openSession();
		//打开事务
		Transaction transaction = session.beginTransaction();
		//执行操作
		//HQL语句
		//String hql = "from cn.itheima.domain.Customer where cust_id = ?0"; //查询cust_id=?的Customer对象
		String hql = "from cn.itheima.domain.Customer where cust_id = :cust_id";
		//根据HQL语句创建查询对象
		Query<Customer> query = session.createQuery(hql,Customer.class);
		//根据查询对象获得查询结果
		//query.setParameter(0, 5l);
		query.setParameter("cust_id", 4l);
		Customer result = query.uniqueResult();
		//提交事务，关闭资源
		System.out.println(result);
		transaction.commit();
		session.close();
	}
	
	@Test
	//分页查询
	public void fun4() {
		//获得Session
		Session session = HibernateUtils.openSession();
		//打开事务
		Transaction transaction = session.beginTransaction();
		//执行操作
		//HQL语句
		String hql = "from cn.itheima.domain.Customer"; //查询所有的Customer对象
		//根据HQL语句创建查询对象
		Query<Customer> query = session.createQuery(hql,Customer.class);
		//设置分页信息  limit ?,?
		query.setFirstResult(0);
		query.setMaxResults(1);
		//根据查询对象获得查询结果
		List<Customer> list = query.list();
		System.out.println(list);
		//提交事务，关闭资源
		transaction.commit();
		session.close();
	}
}
