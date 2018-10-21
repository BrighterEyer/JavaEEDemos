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
    public String login(HttpServletRequest request, HttpSession session) {
        User user = (User)session.getAttribute(Constants.USER_SESSION);
        if(user!=null){
            return "frame";
        }else{
            return "login";
        }
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
            if(user.getUserPassword() == null) {
                request.setAttribute("passwordStatus", "密码错误");
                return "login";
            }
            session.setAttribute(Constants.USER_SESSION, user);
            return "redirect:sys/main.html";
        }
        request.setAttribute("userCode",userCode);
        request.setAttribute("userPassword",userPassword);
        return "register";
    }

    @RequestMapping(value = "doRegister.html", method = RequestMethod.POST)
    public String doRegister(@RequestParam String userCode, @RequestParam String userPassword, HttpServletRequest request, HttpSession session) throws Exception {
        int effect = iUserService.register(userCode, userPassword);
        User user = iUserService.findByUserCode(userCode);
        if (null != user) {
            session.setAttribute(Constants.USER_SESSION, user);
            return "redirect:sys/main.html";
        }
        return "401";
    }

    @RequestMapping(value = "sys/main.html")
    public String main() {
        return "frame";
    }

}
