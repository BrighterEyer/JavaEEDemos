package com;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Util {

	private static String JDBC_URL = "jdbc:h2:";
	private static final String USER = "zhangsan";
	private static final String PASSWORD = "lisi";
	private static final String DRIVER_CLASS = "org.h2.Driver";

	private static H2Util instance;

	private H2Util() {
	}

	private static H2Util getInstance() {
		if (null == instance) {
			instance = new H2Util();
		}
		return instance;
	}

	static File file;
	static Connection conn;
	static Statement stmt;

	static {
		file = new File(System.getProperty("user.dir"));
		JDBC_URL += file.getAbsolutePath() + "/H2Base/log";
		// 加载H2数据库驱动
		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
			stmt = conn.createStatement();

			stmt.execute("DROP TABLE IF EXISTS USERS");
			stmt.execute("DROP TABLE IF EXISTS HTTP_LOG");
			// 创建USERS表
			stmt.execute("CREATE TABLE USERS(name VARCHAR(100),password VARCHAR(100))");
			addUser("zhangsan", "123");
			// 创建HTTP_LOG表
			stmt.execute("CREATE TABLE HTTP_LOG(url VARCHAR(100),method varchar(100))");
		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		}
	}

	private static void addUser(String name, String passwd) {
		try {
			stmt.executeUpdate("INSERT INTO USERS VALUES('" + name + "','" + passwd + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addLog(String url, String method) {
		try {
			stmt.executeUpdate("INSERT INTO HTTP_LOG VALUES('" + url + "','" + method + "')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void login(String name, String passwd) {
		// 查询
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM USERS where name='" + name + "' and password = '" + passwd + "'");
			while (rs.next()) {
				System.out.println(rs.getString("name") + "," + rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void queryAllLogs() {
		// 查询
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT * FROM HTTP_LOG");
			while (rs.next()) {
				System.out.println(rs.getString("url") + "," + rs.getString("method"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			// 释放资源
			stmt.close();
			// 关闭连接
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		H2Util util = H2Util.getInstance();
		util.addLog("www.baidu.com", "GET");
		util.login("zhangsan", "123");
		util.queryAllLogs();
		util.close();
	}

}
