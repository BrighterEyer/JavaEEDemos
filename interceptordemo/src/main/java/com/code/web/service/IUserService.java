package com.code.web.service;


import com.code.web.pojo.User;

public interface IUserService {

    /**
     * 登录
     * @param userCode
     * @param password
     * @return
     * @throws Exception
     */
    public User login(String userCode,String password) throws Exception;

    User register(String userCode, String userPassword);
}
