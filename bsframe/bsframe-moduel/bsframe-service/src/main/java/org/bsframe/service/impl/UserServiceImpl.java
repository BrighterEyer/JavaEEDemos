package org.bsframe.service.impl;

import org.bsframe.dao.UserMapper;
import org.bsframe.entity.User;
import org.bsframe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserMapper userMapper;

    public int addUser(User user) {
        
        return userMapper.addUser(user);
    }

    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    public User findByUsername(String name) {
        return userMapper.findByUsername(name);
    }

}