package com.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import com.entity.Student;
import com.entity.User;
import com.utils.HibernateUtils;

import junit.framework.TestCase;

public class TestTotal extends TestCase {

	public void testAll() {
		Session session = null;
		try {
			// ----------------------------------------------------------
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

			// ----------------------------------------------------------
			session = HibernateUtils.getSession();
			// 开启事务.
			session.beginTransaction();
			// 手动构造的Detached对象.
			user = new User();
			user.setId("402881e4441b3d1c01441b3f5dfe0001");
			session.delete(user);
			// 提交事务.把内存的改变提交到数据库上.
			session.getTransaction().commit();
			// ----------------------------------------------------------
			session = HibernateUtils.getSession();
			session.beginTransaction();
			// 参数是一个字符串,是HQL的查询语句.注意此时的的UserU为大写,为对象的,而不是表的.
			Query query = session.createQuery("from User");
			// 使用List方法.
			List userList = query.list();
			// 迭代器去迭代.
			for (Iterator iter = userList.iterator(); iter.hasNext();) {
				user = (User) iter.next();
				System.out.println("id=" + user.getId() + "name=" + user.getName());
			}
			session.getTransaction().commit();
			// ----------------------------------------------------------
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtils.closeSession(session);
		}
	}

	/**
	 * 开启二级缓存
	 * 
	 * 在两个session中发load查询
	 */
	@Test
	public void testCache1() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			Student student = (Student) session.load(Student.class, 1);
			System.out.println("student.name=" + student.getName());
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtils.closeSession(session);
		}

		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			Student student = (Student) session.load(Student.class, 1);

			// 不会发出查询语句，因为配置二级缓存，session可以共享二级缓存中的数据
			// 二级缓存是进程级的缓存
			System.out.println("student.name=" + student.getName());
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtils.closeSession(session);
		}
	}

	public void testCache3() {
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			Student student = (Student) session.load(Student.class, 1);
			System.out.println("student.name=" + student.getName());
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtils.closeSession(session);
		}

		// 管理二级缓存 （evict清除的意思)
		// HibernateUtils.getSessionFactory().evict(Student.class);
		HibernateUtils.getSessionFactory().evict(Student.class, 1);
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			Student student = (Student) session.load(Student.class, 1);

			// 会发出查询语句，因为二级缓存中的数据被清除了
			System.out.println("student.name=" + student.getName());
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtils.closeSession(session);
		}
	}
}
