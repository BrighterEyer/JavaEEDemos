package com.code.util;

public class GanCodeMain {
	public static void main(String[] args) throws Exception {
		GenCodeUtils util = new GenCodeUtils();
		util.begin()//
				.Model("Model.ftl", "")//
				.Dao("DAO.ftl", "DAO.java")//
				.Dto("DTO.ftl", "DTO.java")//
				.Mapper("Mapper.ftl", "Mapper.xml")//
				.Repository("Repository.ftl", "Repository.java")//
				.Service("Service.ftl", "I", "Service.java")//
				.ServiceImpl("ServiceImpl.ftl", "ServiceImpl.java")//
				.Controller("Controller.ftl", "Controller.java")//
				.build();
	}
}
