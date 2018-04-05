package com.code.web.common.domain;

public class BaseModel {

	private String id;

	private String status;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BaseModel [id=" + id + ", status=" + status + "]";
	}

}
