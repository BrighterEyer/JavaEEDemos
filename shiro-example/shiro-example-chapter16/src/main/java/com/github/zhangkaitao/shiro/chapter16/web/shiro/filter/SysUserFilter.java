package com.github.zhangkaitao.shiro.chapter16.web.shiro.filter;

import com.github.zhangkaitao.shiro.chapter16.Constants;
import com.github.zhangkaitao.shiro.chapter16.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        System.out.println("=====start=====");
        System.out.println("用户名:"+username);
        System.out.println(userService.findByUsername(username));
        System.out.println("=====end=====");
        request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
        return true;
    }
}
