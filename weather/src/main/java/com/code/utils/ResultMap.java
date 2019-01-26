package com.code.utils;


import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResultMap extends HashMap {

	private static final String CODE = "code";
	private static final String MSG = "msg";
	private static final String DATA = "data";
	private static final String SUCCESS = "success";
	
	
	public static ResultMap ok() {
		return new ResultMap().setOk();
	}
	public static ResultMap okData(Object value) {
		return new ResultMap().setOkData(value);
	}
	
	public static ResultMap ok(Object value) {
		return new ResultMap().setOk(value);
	}
	
	public static ResultMap ok(Object key, Object value) {
		return ok().set(key, value);
	}
	
	public static ResultMap fail() {
		return new ResultMap().setFail();
	}
	
	public static ResultMap fail(int code, Object msg) {
		return new ResultMap().setFail(code,msg);
	}
	
	public static ResultMap ok(int code, Object msg) {
		return new ResultMap().setOk(code, msg);
	}
	
	public static ResultMap fail(Object msg) {
		return new ResultMap().setFail(msg);
	}
	
	public static ResultMap fail(Object key, Object value) {
		return fail().set(key, value);
	}
	
	public static ResultMap create() {
		return new ResultMap();
	}
	
	public static ResultMap create(Object key, Object value) {
		return new ResultMap().set(key, value);
	}
	
	public boolean success() {
		Boolean success = (Boolean)get(SUCCESS);
		return success != null && success;
	}
	
	public ResultMap setOk() {
		super.put(SUCCESS, true);
		super.put(CODE, 200);
		super.put(MSG, "操作成功");
		return this;
	}
	
	public ResultMap setOk(Object msg) {
		super.put(SUCCESS, true);
		super.put(CODE, 200);
		super.put(MSG, msg);
		return this;
	}
	
	
	public ResultMap setOk(int code, Object msg) {
		super.put(SUCCESS, true);
		super.put(CODE, code);
		super.put(MSG, "操作成功");
		return this;
	}
	
	public ResultMap setOkData(Object value) {
		super.put(DATA, value);
		super.put(SUCCESS, true);
		super.put(CODE, 200);
		super.put(MSG, "操作成功");
		return this;
	}
	
	public ResultMap setFail(Object msg) {
		super.put(SUCCESS, false);
		super.put(CODE, 501);
		super.put(MSG, msg);
		return this;
	}
	
	public ResultMap setFail(int code, Object msg) {
		super.put(SUCCESS, false);
		super.put(CODE, code);
		super.put(MSG, msg);
		return this;
	}
	
	public ResultMap setFail() {
		super.put(SUCCESS, false);
		super.put(CODE, 501);
		super.put(MSG, "操作失败");
		return this;
	}
	
	public ResultMap set(Object key, Object value) {
		super.put(key, value);
		return this;
	}
	
	public ResultMap setCode(int value) {
		super.put(CODE, value);
		return this;
	}
	
	public ResultMap setMsg(String value) {
		super.put(MSG, value);
		return this;
	}
	
	public ResultMap set(Map map) {
		super.putAll(map);
		return this;
	}
	
	public ResultMap set(ResultMap ret) {
		super.putAll(ret);
		return this;
	}
	
	public ResultMap delete(Object key) {
		super.remove(key);
		return this;
	}
	
	public <T> T getAs(Object key) {
		return (T)get(key);
	}
	
	public String getStr(Object key) {
		return (String)get(key);
	}

	public Integer getInt(Object key) {
		return (Integer)get(key);
	}

	public Long getLong(Object key) {
		return (Long)get(key);
	}

	public Boolean getBoolean(Object key) {
		return (Boolean)get(key);
	}
	
	/**
	 * key 存在，并且 value 不为 null
	 */
	public boolean notNull(Object key) {
		return get(key) != null;
	}
	
	/**
	 * key 不存在，或者 key 存在但 value 为null
	 */
	public boolean isNull(Object key) {
		return get(key) == null;
	}
	
	/**
	 * key 存在，并且 value 为 true，则返回 true
	 */
	public boolean isTrue(Object key) {
		Object value = get(key);
		return (value instanceof Boolean && ((Boolean)value == true));
	}
	
	/**
	 * key 存在，并且 value 为 false，则返回 true
	 */
	public boolean isFalse(Object key) {
		Object value = get(key);
		return (value instanceof Boolean && ((Boolean)value == false));
	}
	
	public String toJson() {
		return  JSONObject.fromObject(this).toString();
	}
	
	public boolean equals(Object ret) {
		return ret instanceof ResultMap && super.equals(ret);
	}
}


