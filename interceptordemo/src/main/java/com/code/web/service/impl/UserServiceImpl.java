package com.code.web.service.impl;


import com.code.web.dao.UserMapper;
import com.code.web.pojo.User;
import com.code.web.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String userCode, String userPassword) throws Exception {
        User user = null;
        user = userMapper.getUserByUserCode(userCode);
        //匹配密码
        if (null != user) {
            if (!user.getUserPassword().equals(userPassword)) {
                user = new User();
                user.setUserCode(userCode);
            }
        }
        return user;
    }

    @Override
    public int register(String userCode, String userPassword) {
        User user = new User();
        user.setUserCode(userCode);
        user.setUserPassword(userPassword);
        return userMapper.add(user);
    }

    @Override
    public User findByUserCode(String userCode) throws Exception {
        return userMapper.getUserByUserCode(userCode);
    }

}
