package com.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.User;
import com.utils.HibernateUtils;

import junit.framework.TestCase;

public class SessionTest extends TestCase {
	// public void testSave() {
	// Session session = null;
	// Transaction tx = null;
	//
	// try {
	// session = HibernateUtils.getSession();
	// tx = session.beginTransaction();
	//
	// // 传入值，变为transaction状态
	// User user = new User();
	// user.setName("张三");
	// user.setPassword("123");
	// user.setCreateTime(new Date());
	// user.setExpireTime(new Date());
	//
	// // 进行保存，执行save则对session进行管理，处于持久状态，
	// // persistent状态对象，当对象属性发生改变的时候，hibernate在清理
	// // 缓存的时候(脏数据检查)的时候，会和数据库同步
	// session.save(user);
	// user.setName("李四");
	//
	// // 再次提交
	// tx.commit();
	// } catch (Exception e) {
	// e.printStackTrace();
	// if (null != tx) {
	// tx.rollback();
	// }
	// } finally {
	// HibernateUtils.closeSession(session);
	// }
	// }

	public void testSave3() {
		Session session = null;
		Transaction tx = null;
		User user = null;
		try {
			// 取得session.
			session = HibernateUtils.getSession();
			// 自己开启事务. fanhui transient的一个实例.
			tx = session.beginTransaction();

			// 传入值.变为Transient状态.
			user = new User();
			user.setName("张三");
			user.setPassword("123");
			user.setCreateTime(new Date());
			user.setExpireTime(new Date());

			// 进行保存.执行save则对session进行管理了. 处于持久状态.
			// persistent状态的对象.当对象的属性发生改变的时候,hibernate在清理
			// 缓存的时候(脏数据检查)的时候,会和数据库同步.
			session.save(user);

			user.setName("李四");
			// 可以显示的调用update方法,因为此时为持久状态,调用update没有什么意义.
			// 再次提交.
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				// 事务回滚.
				tx.rollback();
			}
		} finally {
			// 关闭session.当关闭session时处于Detached状态.
			HibernateUtils.closeSession(session);
		}

		// 已经不能用以前的session了.
		user.setName("王五");
		try {
			// 得到新的session.
			session = HibernateUtils.getSession();
			// 开启事务.
			session.beginTransaction();
			// 将detached状态的对象重新纳入session管理.
			// 此时将变为persistent状态的对象.
			// persistent状态的对象,在清理缓存时,会根数据库同步.
			session.update(user);
			// 提交事务.把内存的改变提交到数据库上.
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtils.closeSession(session);
		}
	}
}
