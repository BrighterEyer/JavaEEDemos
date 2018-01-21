package com.entity;

import java.util.Date;

//user实体。  
public class User {
	// 用户编号。
	private String id;

	// 名字。
	private String name;

	// 密码。
	private String password;

	// 创建日期。
	private Date createTime;

	// 失效时间。
	private Date expireTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

}
