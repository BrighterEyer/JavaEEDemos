package com.code.web.interceptor;


import com.code.web.pojo.User;
import com.code.web.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SysInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        logger.debug("拦截路径 requestURL: "+request.getRequestURL());//请求全路径
        logger.debug("拦截路径 requestURI: "+request.getRequestURI());//请求路径
        logger.debug("拦截路径 contextPath: "+request.getContextPath());//项目名称
        logger.debug("拦截路径 pathInfo: "+request.getPathInfo());

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.USER_SESSION);
//        if (null == user) {
//            response.sendRedirect(request.getContextPath() + "/401.jsp");
//            return false;
//        }
        logger.debug("拦截用户 user: "+user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
