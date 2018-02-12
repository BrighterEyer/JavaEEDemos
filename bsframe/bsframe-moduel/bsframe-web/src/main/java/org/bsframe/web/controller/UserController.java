package org.bsframe.web.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.bsframe.entity.Sysres;
import org.bsframe.entity.User;
import org.bsframe.service.impl.UserServiceImpl;
import org.bsframe.web.util.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping(value = "/findByUsername", method = RequestMethod.GET)
	public @ResponseBody User findByUsername(@RequestParam("name") String name) {
		return this.userServiceImpl.findByUsername(name);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String isLogin(HttpServletRequest request) {

		String loginName = request.getParameter("username");
		String loginPassword = request.getParameter("password");

		HttpSession session = request.getSession(true);
		String errorMessage = "";

		Subject user = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPassword);
		token.setRememberMe(true);

		try {
			user.login(token);
			String userID = (String) user.getPrincipal();
			session.setAttribute("USERNAME", userID);
			return "admin";
		} catch (UnknownAccountException uae) {
			errorMessage = "用户认证失败：" + "username wasn't in the system.";
		} catch (IncorrectCredentialsException ice) {
			errorMessage = "用户认证失败：" + "password didn't match.";
		} catch (LockedAccountException lae) {
			errorMessage = "用户认证失败：" + "account for that username is locked - can't login.";
		} catch (AuthenticationException e) {
			errorMessage = "登录失败错误信息：" + e;
			e.printStackTrace();
			token.clear();
		}
		session.setAttribute("ErrorMessage", errorMessage);
		return "error";
	}

	/**
	 * 获取当前用户的用户名
	 * 
	 * @return 跳转到展示当前用户姓名的页面
	 */
	@RequestMapping(value = "/getCurrent", method = RequestMethod.GET)
	public ModelAndView getCurrent(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView();
		try {
			// 获取当前用户的姓名
			String userName = getLoginUserName();
			// 放入到request中
			mv.addObject("userName", userName);
			// 设置跳转页面
			mv.setViewName("/top");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("message", "获取用户出错!");
			mv.setViewName("/exception");
		}
		return mv;
	}

	@RequestMapping(value = "getMenu", method = RequestMethod.GET)
	public ModelAndView getMenu(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			List<Sysres> slist = new ArrayList<Sysres>();
			Sysres s = new Sysres();
			s.setId(1l);
			s.setName("通用类别管理");
			s.setUrl("/category/toCategory");
			s.setResdesc("toCategory");
			s.setParentid(2l);
			slist.add(s);

			mav.setViewName("/left");
			mav.addObject("menuList", slist);
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("message", "获取菜单出错!");
			mav.setViewName("/exception");
			return mav;
		}
	}

	/**
	 * 用户登出
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
		String logout = InternalResourceViewResolver.FORWARD_URL_PREFIX + "/login.jsp";
		return logout;
	}

	/**
	 * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
	 */
	@RequestMapping("/getVerifyCodeImage")
	public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
		// 将验证码放到HttpSession里面
		request.getSession().setAttribute("verifyCode", verifyCode);
		System.out.println("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
		// 设置输出的内容的类型为JPEG图像
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE,
				Color.BLACK, null);
		// 写给浏览器
		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
	}
}
