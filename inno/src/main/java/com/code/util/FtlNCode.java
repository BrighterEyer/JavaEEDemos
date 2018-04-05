package com.code.util;

import java.util.Map;

public class FtlNCode {

	private String ftl, subPackage, code;
	private Map<String, Object> dataMap;

	public FtlNCode() {

	}

	public FtlNCode(String ftl, String subPackage, String code) {
		super();
		this.ftl = ftl;
		this.subPackage = subPackage;
		this.code = code;
	}

	public FtlNCode(String ftl, String subPackage, String code, Map<String, Object> dataMap) {
		this(ftl, subPackage, code);
		this.dataMap = dataMap;
	}

	public String getFtl() {
		return ftl;
	}

	public void setFtl(String ftl) {
		this.ftl = ftl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getSubPackage() {
		return subPackage;
	}

	public void setSubPackage(String subPackage) {
		this.subPackage = subPackage;
	}

	@Override
	public String toString() {
		return "FtlNCode [ftl=" + ftl + ", subPackage=" + subPackage + ", code=" + code + "]";
	}

}
