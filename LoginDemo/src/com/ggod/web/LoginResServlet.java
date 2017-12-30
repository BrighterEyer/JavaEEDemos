package com.ggod.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginResServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	sqlDemo sqlD = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			sqlD = new sqlDemo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		PrintWriter out = resp.getWriter();
		// 注册
		if (action.equals("Register")) {
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			String passwordProblm = req.getParameter("passwordProblem");
			String problemResult = req.getParameter("problemResult");
			boolean isEmpty = (name != null && !name.equals("") && password != null && !password.equals("")
					&& passwordProblm != null && !passwordProblm.equals("") && problemResult != null
					&& !problemResult.equals(""));
			System.out.println(isEmpty);
			if (isEmpty) {
				try {
					boolean flag = sqlD.InsertData(name, password, passwordProblm, problemResult);
					if (flag) {
						out.println("<h1>恭喜你" + name + ":注册成功点击<a href='login.html'>登录</a></h1>");
					} else {
						out.println("<h1>Soory" + name + ":注册失败,该用户已经存在,点击<a href='register.html'>重新注册</a></h1>");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				out.println("<h1>所以选项均不可以为空！！！点击<a href='register.html'>重新注册</h1>");
			}

		} else { // 登录
			String name = req.getParameter("name");
			String password = req.getParameter("password");

			try {
				boolean flag = sqlD.LoginCorrect(name, password);
				if (flag) {
					out.println("<h1>欢迎你:" + name + "</h1>");
				} else {
					out.println("<h1>SOrry密码错误！点击重新<a href='login.html'>登录</a></h1>");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}

class sqlDemo {
	private Connection conn = null;
	private Statement statement = null;

	public sqlDemo() throws Exception {
		conn = getConnection();
		statement = conn.createStatement();
	}

	// 查看登录用户名和密码是否正确
	public boolean LoginCorrect(String name, String password) throws Exception {
		String sql = "select * from logindemo where name = '" + name + "'";
		ResultSet rs = statement.executeQuery(sql);
		// 查看是否合法
		while (rs.next()) {
			if (name.equals(rs.getString("name")) && password.equals(rs.getString("password")))
				return true;
		}
		rs.close();
		return false;
	}

	// 插入数据
	public boolean InsertData(String name, String password, String passwordProblm, String problemResult)
			throws Exception {
		String sql = "insert into logindemo values ('" + name + "','" + password + "','" + passwordProblm + "','"
				+ problemResult + "')";
		String isAgainSql = "select name from logindemo where name = '" + name + "'";
		ResultSet rs = statement.executeQuery(isAgainSql);
		// 查看是否用户名重复
		while (rs.next()) {
			if (rs.getString("name").equals(name)) {
				return false;
			}
		}

		statement.executeUpdate(sql);
		rs.close();
		return true;
	}

	private Connection getConnection() throws Exception {
		String driverClass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/hello?useUnicode=true&characterEncoding=utf8";
		String user = "root";
		String password = "hello";
		// 注册加载驱动
		Class.forName(driverClass);
		// 获取连接
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println(conn);
		return conn;
	}

}