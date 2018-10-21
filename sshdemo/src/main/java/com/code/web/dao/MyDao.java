package com.code.web.dao;


import com.code.web.entity.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MyDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserInfo getByName(String name){
        Session session = this.sessionFactory.openSession();
        List list = session.createSQLQuery("SELECT * FROM logins WHERE NAME ='"+name+"'")
                .addEntity(UserInfo.class)
                .list();
        session.close();
        if(list != null && list.size() != 0){
            return (UserInfo) list.get(0);
        }else {
            return null;
        }
    }
}