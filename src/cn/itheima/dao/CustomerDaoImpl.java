package cn.itheima.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import cn.itheima.domain.Customer;
import cn.itheima.utils.HibernateUtils;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public void save(Customer c) {
		// 获得Session
		Session session = HibernateUtils.openSession();
		Transaction beginTransaction = session.beginTransaction();
		try {
			session.save(c);
		} catch (Exception e) {
			e.printStackTrace();
			beginTransaction.rollback();
		}
		beginTransaction.commit();
		session.close();
		

	}

	@Override
	public List<Customer> list() {
		// 获得Session
		Session session = HibernateUtils.openSession();
		// 1.创建CriteriaBuilder对象
		CriteriaBuilder builder = session.getCriteriaBuilder();
		// 2.获取CriteriaQuery对象
		CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
		// 3.指定根条件
		Root<Customer> root = criteria.from(Customer.class);
		criteria.select(root);
		//criteria.where(builder.equal(root.get("cust_id"), 5l));
		// criteria.where(builder.or(builder.equal(root.get("cust_id"),
		// 5l),builder.equal(root.get("cust_id"), 4l)));
		Query<Customer> query = session.createQuery(criteria);
		List<Customer> resultList = query.getResultList();	
		return resultList;
	}

	@Override
	public Customer getById(Long cust_id) {
		Session session = HibernateUtils.getCurrentSession();
		
		return session.get(Customer.class, cust_id);
	}

}
