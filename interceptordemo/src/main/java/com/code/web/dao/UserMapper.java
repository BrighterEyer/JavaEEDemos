package com.code.web.dao;


import com.code.web.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     *
     * 通过userCode获取用户
     * @param userCode
     * @return
     * @throws Exception
     */
    public User getUserByUserCode(@Param("userCode") String userCode) throws Exception;

    public int add(@Param("user") User user);
}
