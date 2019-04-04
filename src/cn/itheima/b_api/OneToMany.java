package cn.itheima.b_api;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.itheima.domain.Customer;
import cn.itheima.domain.LinkMan;
import cn.itheima.utils.HibernateUtils;

public class OneToMany {
	@Test
	// 保存客户 以及客户 下的联系人
	public void fun1() {
		// 1 获得session
		Session session = HibernateUtils.openSession();
		// 2 开启事务
		Transaction tx = session.beginTransaction();
		// -------------------------------------------------
		// 3操作
		Customer c = new Customer();
		c.setCust_name("明明");

		LinkMan lm1 = new LinkMan();
		lm1.setLkm_name("雷神");

		LinkMan lm2 = new LinkMan();
		lm2.setLkm_name("阿龙");

		// 表达一对多,客户下有多个联系人.
		// 如果客户放弃维护与联系人的关系. 维护关系的代码可以省略
		c.getLinkMens().add(lm1);
		c.getLinkMens().add(lm2);

		// 表达对对对,联系人属于哪个客户
		lm1.setCustomer(c);
		lm2.setCustomer(c);

		session.save(c);
//		session.save(lm1);
//		session.save(lm2);

		// -------------------------------------------------
		// 4提交事务
		tx.commit();
		// 5关闭资源
		session.close();
	}

	@Test
	// 为客户增加联系人
	public void fun2() {
		// 1 获得session
		Session session = HibernateUtils.openSession();
		// 2 开启事务
		Transaction tx = session.beginTransaction();
		// -------------------------------------------------
		// 3操作
		// 1> 获得要操作的客户对象
		Customer c = session.get(Customer.class, 1l);
		// 2> 创建联系人
		LinkMan lm1 = new LinkMan();
		lm1.setLkm_name("郝强勇");
		// 3> 将联系人添加到客户,将客户设置到联系人中
		c.getLinkMens().add(lm1);
		lm1.setCustomer(c);
		// 4> 执行保存
		session.save(lm1);
		// -------------------------------------------------
		// 4提交事务
		tx.commit();
		// 5关闭资源
		session.close();
	}

	@Test
	// 为客户删除联系人
	public void fun3() {
		// 1 获得session
		Session session = HibernateUtils.openSession();
		// 2 开启事务
		Transaction tx = session.beginTransaction();
		// -------------------------------------------------
		// 3操作
		// 1> 获得要操作的客户对象
		Customer c = session.get(Customer.class, 1l);
		// 2> 获得要移除的联系人
		LinkMan lm = session.get(LinkMan.class, 1l);
		// 3> 将联系人从客户集合中移除
		c.getLinkMens().remove(lm);
		lm.setCustomer(null);
		// -------------------------------------------------
		// 4提交事务
		tx.commit();
		// 5关闭资源
		session.close();
	}

	@Test
	public void fun4() {
		// 1 获得session
		Session session = HibernateUtils.openSession();
		// 2 开启事务
		Transaction tx = session.beginTransaction();
		// -------------------------------------------------
		//级联删除客户
		Customer c = session.get(Customer.class, 1l);
		session.delete(c);
		// -------------------------------------------------
		// 4提交事务
		tx.commit();
		// 5关闭资源
		session.close();
	}
}
