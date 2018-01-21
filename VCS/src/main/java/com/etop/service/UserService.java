package com.etop.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.dao.UserDAO;
import com.etop.dto.UserDto;
import com.etop.pojo.User;
import com.etop.utils.PageUtil;

/**
 * @类名: UserService
 * @描述: 用户服务，与dao进行对接
 * @作者 liuren-mail@163.com
 * @日期 2015年5月20日 下午3:03:20
 */
@SuppressWarnings("serial")
@Service("UserService")
public class UserService implements Serializable {

    @Autowired
    private UserDAO userDAO;

    /**
     * 通过用户名查找用户信息
     * 
     * @param username
     * @return
     */
    public User findByName(String username) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", username);
        return userDAO.findUniqueResult("from User u where u.username = :name",
                params);
    }

    public List<User> getAllUser() {
        return userDAO.find("from User u");
    }

    public PageUtil<UserDto> findAllUser() {
        return userDAO.findBySql("select * from t_user", UserDto.class, false);
    }

    public void saveUser(User user) {
        userDAO.save(user);
    }

    public User findById(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return userDAO.findUniqueResult("from User u where u.id = :id", params);
    }

    public void updateUser(User user) {
        userDAO.saveOrUpdate(user);
    }

    public void deleteUser(int id) {
        userDAO.deleteById(id);
    }
}
