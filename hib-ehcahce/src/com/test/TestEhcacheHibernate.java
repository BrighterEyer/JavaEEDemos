package com.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import net.sf.ehcache.CacheManager;

public class TestEhcacheHibernate {

	public static void main(String[] args) {
		testMultiConfigMethod();
	}

	/**
	 * 测试多种配置缓存方法
	 */

	public static void testMultiConfigMethod() {
		SessionFactory sessionFactory = null;
		try {
			System.out.println("ehcache - hibernate Test ...");
			Configuration config = new AnnotationConfiguration()
					.configure("hibernate.cfg.xml");

			System.out.println("hibernate config successful : " + config);
			sessionFactory = config.buildSessionFactory();

			Transaction ts = null;
			try {
				Session session = sessionFactory.getCurrentSession();
				ts = session.beginTransaction();
			} catch (Exception e) {
				e.printStackTrace();
				ts.rollback();
			}
			String[] cacheNames=CacheManager.getInstance().getCacheNames();
			System.out.println("缓存的key cacheNames length :="+cacheNames.length+"具体详细列表如下");
			for(String name:cacheNames) {
				System.out.println("name:"+name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("ehcache - hibernate Test end.");
	}

}
