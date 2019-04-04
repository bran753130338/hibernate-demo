package cn.itheima.web;

import cn.itheima.dao.CustomerDao;
import cn.itheima.dao.CustomerDaoImpl;
import cn.itheima.dao.LinkManDao;
import cn.itheima.dao.LinkManDaoImpl;
import cn.itheima.domain.Customer;
import cn.itheima.domain.LinkMan;
import cn.itheima.service.LinkManService;
import cn.itheima.utils.HibernateUtils;

public class LinkManServiceImpl implements LinkManService {
	
	private CustomerDao cd = new CustomerDaoImpl();
	private LinkManDao lmd = new LinkManDaoImpl();
	
	@Override
	public void save(LinkMan linkMan) {
		HibernateUtils.getCurrentSession().beginTransaction();
		
		try {
			//1 根据客户id获得客户对象
			Long cust_id = linkMan.getCust_id();
			Customer customer = cd.getById(cust_id);
			//2 将客户放进LinkMan中表达关系
			linkMan.setCustomer(customer);
			//3 保存LinkMan
			lmd.save(linkMan);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			HibernateUtils.getCurrentSession().getTransaction().rollback();
		}
		
		HibernateUtils.getCurrentSession().getTransaction().commit();
	}

}
