package cn.itheima.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.itheima.dao.CustomerDao;
import cn.itheima.dao.CustomerDaoImpl;
import cn.itheima.domain.Customer;
import cn.itheima.utils.HibernateUtils;


public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao = new CustomerDaoImpl();
	@Override
	public void save(Customer c) {	
	  
		customerDao.save(c);

	}
	@Override
	public List<Customer> getAll() {
		Session session = HibernateUtils.getCurrentSession();
		
		Transaction transaction = session.beginTransaction();
		List<Customer> list = null;
		try {
			list = customerDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}	
		transaction.commit();
		return list;
	}
	

}
