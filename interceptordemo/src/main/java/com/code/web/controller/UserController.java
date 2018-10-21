package com.code.web.controller;


import com.code.web.pojo.User;
import com.code.web.service.IUserService;
import com.code.web.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService iUserService;

    @RequestMapping(value = "login.html")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "logout.html")
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.USER_SESSION);
        return "login";
    }

    @RequestMapping(value = "dologin.html", method = RequestMethod.POST)
    public String doLogin(@RequestParam String userCode, @RequestParam String userPassword, HttpServletRequest request, HttpSession session) throws Exception {
        User user = iUserService.login(userCode, userPassword);
        if (null != user) {
            session.setAttribute(Constants.USER_SESSION, user);
            return "redirect:sys/main.html";
        }
        request.setAttribute("userCode",userCode);
        request.setAttribute("userPassword",userPassword);
        return "register";
    }

    @RequestMapping(value = "doRegister.html", method = RequestMethod.POST)
    public String doRegister(@RequestParam String userCode, @RequestParam String userPassword, HttpServletRequest request, HttpSession session) throws Exception {
        User user = iUserService.register(userCode, userPassword);
        if (null != user) {
            session.setAttribute(Constants.USER_SESSION, user);
            return "redirect:sys/main.html";
        }
        return "forward:401";
    }

    @RequestMapping(value = "sys/main.html")
    public String main() {
        return "frame";
    }

}
