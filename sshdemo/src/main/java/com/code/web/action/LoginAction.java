package com.code.web.action;


import com.code.web.service.MyService;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

    private String name;
    private String pwd;
    private MyService service;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public MyService getService() {
        return service;
    }

    public void setService(MyService service) {
        this.service = service;
    }

    public String execute(){
        boolean flag = this.service.sure(name,pwd);
        if(flag) return SUCCESS;
        else return ERROR;
    }

}
