package com;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import com.xxx.test.Demo;
import com.xxx.test.Demo2;

public class JavaTest {

	@Test
	public void getPackageName() {
		// 第一种方法
		Class<?> demoClass = Demo.class; // 通过 类名.class 直接获取类的Class
		System.out.println("Demo.class的方式获取包名：" + demoClass.getName());

		// 第二种方法
		Demo demo = new Demo();
		Class<?> demoClass2 = demo.getClass(); // 通过对象的getClass获取类的Class
		System.out.println("demo.getClass()的方式获取包名：" + demoClass2.getName());

		// 第三种方法
		Class<?> demoClass3 = null;
		try {
			demoClass3 = Class.forName("com.xxx.test.Demo");
		} catch (Exception e) {
		}
		System.out.println("Class.forName(\"Demo\")的方式获取包名：" + demoClass3.getName());
	}

	@Test // 方法不能为静态的
	public void getClassInfo() {
		// 获取类提供的方法
		Class<?> demoClass = Demo.class; // 通过 类名.class 直接获取类的Class
		Demo demo = null;
		// 获取类的域信息
		Field[] fields = demoClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getName() + fields[i].getType());
		}
		// 获取方法信息
		Method methods[] = demoClass.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.println(methods[i]);
		}
	}

	@Test
	public void newAClass() {
		// 获取类提供的方法
		Class<?> demoClass = Demo.class; // 通过 类名.class 直接获取类的Class
		Demo demo = null;
		try {
			demo = (Demo) demoClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		demo.setAge(20);
		demo.setUserName("initphp");
		System.out.println(demo.toString());
	}

	// 通过反射设置私有属性值
	@Test
	public void setPrivateFieldValue()
			throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		// 获取类提供的方法
		Class<?> demoClass = Demo.class; // 通过 类名.class 直接获取类的Class
		Demo demoObj = (Demo) demoClass.newInstance();
		// 设置类的属性值
		Field field = demoClass.getDeclaredField("age");
		field.setAccessible(true); // 设置私有属性范围
		field.set(demoObj, 20);

		Field field2 = demoClass.getDeclaredField("userName");
		field2.setAccessible(true);
		field2.set(demoObj, "initphp");
		System.out.println("获取一个值：" + field2.get(demoObj));
		System.out.println(demoObj.toString());
	}

	@Test
	public void invokeByReflect() throws Exception {
		// 获取类提供的方法
		Class<?> demoClass = Demo.class; // 通过 类名.class 直接获取类的Class
		Demo demoObj = (Demo) demoClass.newInstance();

		// 调用类的方法
		Method method = demoClass.getMethod("setAge", Integer.class); // 获取类方法信息
		method.invoke(demoObj, 100); // 调用方法
		Method method2 = demoClass.getMethod("getAge");
		System.out.println("Age:" + method2.invoke(demoObj));

		Method method3 = demoClass.getMethod("setUserName", String.class);
		method3.invoke(demoObj, "initphp");
		Method method4 = demoClass.getMethod("getUserName");
		System.out.println("UserName:" + method4.invoke(demoObj));

		System.out.println(demoObj.toString());
	}

	@Test
	public void getParent() throws Exception {
		// 获取类提供的方法
		Class<?> demoClass = Demo2.class; // 通过 类名.class 直接获取类的Class
		// 获取构造函数信息
		Constructor<?> ctorlist[] = demoClass.getDeclaredConstructors();
		for (int i = 0; i < ctorlist.length; i++) {
			Constructor<?> ct = ctorlist[i];
			System.out.print("构造函数 = " + ct.getName());
			Class<?> pvec[] = ct.getParameterTypes(); // 获取参数类型。数组形式
			for (int j = 0; j < pvec.length; j++) {
				System.out.print(" 参数： " + pvec[j]);
			}
			System.out.println("");

			// 通过构造函数实例化对象
			if (pvec.length > 0) {
				Demo2 demo = (Demo2) ct.newInstance("zhul", 10);
				System.out.println("通过构造函数(Demo) ct.newInstance(\"zhul\", 10)实例化：" + demo.toString());
			}
		}

		// 获取父类信息
		Class<?> temp = demoClass.getSuperclass(); // Java只能单继承，所以只有一个父类
		System.out.println("父类名称：" + temp.getName());

		// 获取interface信息
		Class<?> interfaces[] = demoClass.getInterfaces(); // Java可以支持多个接口，所以是数组
		for (int i = 0; i < interfaces.length; i++) {
			System.out.println("实现的接口   " + interfaces[i].getName());
		}

	}
}
