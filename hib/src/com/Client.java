package com;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entity.User;

public class Client {

	// SessionFactory是线程安全的，可以被多个单线程调用以取得session对象，
	// 而构造SessionFactory要消耗资源，所以多数情况下一个应用中只初始化一个SessionFactory

	private static SessionFactory factory;
	static {
		// 默认读取的是hibernate.cfg.xml 文件
		Configuration cfg = new Configuration().configure();
		// 建立SessionFactory
		factory = cfg.buildSessionFactory();
	}

	public static void main(String[] args) {

		// 取得session

		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
			// 给对象赋值
			User user = new User();
			user.setName("张三");
			user.setPassword("123");
			user.setCreateTime(new Date());
			user.setExpireTime(new Date());

			// 保存一user对象到数据库
			// 一个库存类对象对应一个数据库中的表
			// session.getTransaction();
			session.save(user);

			// 先拿到前面事务的对象，再提交事务
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			session.getTransaction().rollback();
		} finally {
			if (session != null) {
				// 关闭session
				// hibernate中已经将connextion的关闭语句封装(ง •̀_•́)ง
				// 我们没有看见一条语句
				session.close();
			}
		}
	}

}
