package cn.itheima.dao;

import java.util.List;

import cn.itheima.domain.Customer;

public interface CustomerDao {
	void save(Customer c);
	
	List<Customer> list();
	//根据id获得客户
	Customer getById(Long cust_id);
}
