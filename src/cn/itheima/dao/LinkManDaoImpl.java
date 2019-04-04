package cn.itheima.dao;

import org.hibernate.Session;

import cn.itheima.domain.LinkMan;
import cn.itheima.utils.HibernateUtils;

public class LinkManDaoImpl implements LinkManDao {

	@Override
	public void save(LinkMan linkMan) {
		Session session = HibernateUtils.getCurrentSession();
		session.save(linkMan);
	}

}
