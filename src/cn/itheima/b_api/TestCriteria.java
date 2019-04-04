package cn.itheima.b_api;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;
import cn.itheima.domain.Customer;
import cn.itheima.utils.HibernateUtils;

public class TestCriteria {

	@Test
	public void fun1() {
		//获得Session
		Session session = HibernateUtils.openSession();
		//打开事务
		Transaction transaction = session.beginTransaction();
		//执行操作
		//1.创建CriteriaBuilder对象
		CriteriaBuilder builder = session.getCriteriaBuilder();
		//2.获取CriteriaQuery对象
		CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
		//3.指定根条件
		Root<Customer> root = criteria.from(Customer.class);
		criteria.select(root);
		criteria.where(builder.equal(root.get("cust_id"), 5l));		
		//criteria.where(builder.or(builder.equal(root.get("cust_id"), 5l),builder.equal(root.get("cust_id"), 4l)));	
		Query<Customer> query = session.createQuery(criteria);
		List<Customer> resultList = query.getResultList();
		System.out.println(resultList);
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
		//1.创建CriteriaBuilder对象
		CriteriaBuilder builder = session.getCriteriaBuilder();
		//2.获取CriteriaQuery对象
		CriteriaQuery criteria = builder.createQuery();
		//3.指定根条件
		Root<Customer> root = criteria.from(Customer.class);
		criteria.select(builder.count(root));	
		
		Query query = session.createQuery(criteria);
		Object result = query.uniqueResult();
		System.out.println(result);
		//提交事务，关闭资源
		transaction.commit();
		session.close();
	}
}
