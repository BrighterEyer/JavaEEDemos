package com.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.entity.User;
import com.utils.HibernateUtils;

import junit.framework.TestCase;

public class TestDel extends TestCase {

	public void testDel1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			// 开启事务.
			session.beginTransaction();
			// 采用load查询不存在的数据,hibernate会抛出object not found exception
			User user = (User) session.load(User.class, "402881e5441c035e01441c0360510003");
			// 删除表中的记录.
			// 删除,建议用此种方式删除,先加载再删除.
			session.delete(user);
			// 提交事务.把内存的改变提交到数据库上.
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtils.closeSession(session);
		}
	}

	public void testDel2() {
		Session session = null;

		try {
			session = HibernateUtils.getSession();
			// 开启事务.
			session.beginTransaction();

			// 手动构造的Detached对象.
			User user = new User();
			user.setId("402881e4441b3d1c01441b3f5dfe0001");
			session.delete(user);

			// 提交事务.把内存的改变提交到数据库上.
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtils.closeSession(session);
		}
	}

	public void testQuery1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			// 参数是一个字符串,是HQL的查询语句.注意此时的的UserU为大写,为对象的,而不是表的.
			Query query = session.createQuery("from User");
			// 使用List方法.
			List userList = query.list();
			// 迭代器去迭代.
			for (Iterator iter = userList.iterator(); iter.hasNext();) {
				User user = (User) iter.next();
				System.out.println("id=" + user.getId() + "name=" + user.getName());
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtils.closeSession(session);
		}
	}

}
