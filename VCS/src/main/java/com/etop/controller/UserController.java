package com.etop.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.etop.basic.controller.BaseController;
import com.etop.dto.UserDto;
import com.etop.pojo.User;
import com.etop.service.UserService;
import com.etop.utils.MD5Utils;
import com.etop.utils.PageUtil;

/**
 * @类名: UserController
 * @描述: 处理用户操作的控制器
 * @作者 liuren-mail@163.com
 * @日期 2015年5月20日 下午3:10:19
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	Map<String, Object> map = new HashMap<String, Object>();
	private final static Logger log = Logger.getLogger(UserController.class);
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// add,edit,del页面并没有写具体逻辑，要验证是否成功，需要观察控制台输出。
	@RequestMapping(value = "/get_users.html")
	@ResponseBody
	public Object getUsers() {
		log.info("/get_users.html");
		PageUtil<UserDto> pageList = userService.findAllUser();
		return pageList;
	}

	@RequestMapping("/userList.html")
	public String userList() {
		log.info("/userList.html");
		return "/userList.jsp";
	}

	@RequestMapping("/add.html")
	@ResponseBody
	public Object addUser(String username, String password) {
		log.info("/add.html");
		MD5Utils MD5 = new MD5Utils();
		User user = new User();
		if (username == null || password == null || "".equals(username) || "".equals(password)) {
			map.put("success", false);
		}
		String md5_password = MD5.GetMD5Code(password);
		user.setUsername(username);
		user.setPassword(md5_password);
		userService.saveUser(user);
		map.put("success", true);
		return map;
	}

	@RequestMapping("/edit.html")
	@ResponseBody
	public Object updateUser(int id, String username, String password) {
		log.info("/edit.html");
		MD5Utils MD5 = new MD5Utils();
		User user = userService.findById(id);
		if (user == null || "".equals(user)) {
			map.put("success", false);
			return map;
		}
		String md5_password = MD5.GetMD5Code(password);
		user.setUsername(username);
		user.setPassword(md5_password);
		userService.updateUser(user);
		map.put("success", true);
		System.out.println("=========================================>要修改的id为:" + id);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/del.html")
	public Object deleteUser(int id) {
		log.info("/del.html");
		userService.deleteUser(id);
		map.put("success", true);
		System.out.println("=========================================>要删除的id为:" + id);
		return map;
	}
}
