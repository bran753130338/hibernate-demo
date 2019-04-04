package cn.itheima.b_api;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.itheima.domain.Role;
import cn.itheima.domain.User;
import cn.itheima.utils.HibernateUtils;

public class ManyToMany {
	@Test
	public void fun1() {
		// 1 获得session
		Session session = HibernateUtils.openSession();
		// 2 开启事务
		Transaction tx = session.beginTransaction();
		// -------------------------------------------------
		//3 操作
		//1>创建两个User
		User u1 = new User();
		User u2 = new User();
		u1.setUser_name("卡卡西");
		u2.setUser_name("卡卡罗特");
		//2>创建两个Role
		Role r1 = new Role();
		Role r2 = new Role();
		r1.setRole_name("上忍");
		r2.setRole_name("火影");
		//3>用户表达关系
		u1.getRoles().add(r1);
		u1.getRoles().add(r2);

		u2.getRoles().add(r2);
		
		//4>角色表达关系
		r1.getUsers().add(u1);
		
		r2.getUsers().add(u1);
		r2.getUsers().add(u2);
		
		//5>调用save方法一次保存
		session.save(u1);
		session.save(u2);
		session.save(r1);
		session.save(r2);
		// -------------------------------------------------
		// 4提交事务
		tx.commit();
		// 5关闭资源
		session.close();
	}
	
	@Test
	//为某一个用户新加一个角色
	public void fun2() {
		Session session = HibernateUtils.openSession();
		
		Transaction tx = session.beginTransaction();
		
		//操作
		//1>获得用户
		User user = session.get(User.class, 2l);
		//2>创建新角色
		Role role = new Role();
		role.setRole_name("赛亚人");
		//3>将角色添加到用户中
		user.getRoles().add(role);
		//4>将角色转换成持久化
		//session.save(user);
		tx.commit();
		session.close();
	}
}
