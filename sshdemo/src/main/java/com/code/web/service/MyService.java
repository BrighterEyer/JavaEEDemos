package com.code.web.service;

import com.code.web.entity.UserInfo;
import com.code.web.dao.MyDao;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class MyService {

    private MyDao dao;

    public MyDao getDao() {
        return dao;
    }

    public void setDao(MyDao dao) {
        this.dao = dao;
    }

    public boolean sure(String name,String pwd){
        UserInfo userInfo = this.dao.getByName(name);
        if(userInfo == null){
            return false;
        }else {
            if(userInfo.getPwd().equals(pwd)) return true;
            else return false;
        }
    }
}
