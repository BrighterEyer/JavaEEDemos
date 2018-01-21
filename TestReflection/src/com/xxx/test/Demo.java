package com.xxx.test;

public class Demo {  
	  
    private Integer age;  
  
    private String  userName;  
  
    public Integer getAge() {  
        return age;  
    }  
  
    public void setAge(Integer age) {  
        this.age = age;  
    }  
  
    public String getUserName() {  
        return userName;  
    }  
  
    public void setUserName(String userName) {  
        this.userName = userName;  
    }  
  
    @Override  
    public String toString() {  
        return "Demo [age=" + age + ", userName=" + userName + "]";  
    } 
    
    private void hiddenSay() {
    	System.out.println("hello");
    }
  
}  