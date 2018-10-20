package com.code.web.bean;


public class UserBean {
    private String name;
    private String password;
    private String errMessage;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public String verify() {
        if (!name.equals("justin") ||
                !password.equals("123456")) {
            errMessage = "名称或密码错误";
            return "failure";
        } else {
            return "success";
        }
    }
}

