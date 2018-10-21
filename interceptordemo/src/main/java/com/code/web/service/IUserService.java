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

    int register(String userCode, String userPassword);

    public User findByUserCode(String userCode) throws Exception;
}
