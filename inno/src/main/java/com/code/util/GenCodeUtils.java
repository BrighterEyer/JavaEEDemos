package com.code.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 描述：代码生成器
 */
public class GenCodeUtils {

	private final String AUTHOR = "bluedragon";
	private final String CURRENT_DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
	private final String diskPath = OutPathUtil.SRC_DIR_JAVA;
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/test";
	private final String tableName = "user";
	private final String tableAnnotation = "用户表";
	private final String USER = "root";
	private final String PASSWORD = "root";
	private final String webPackageName = "com.code.web";

	/**
	 * 生成文件basePackage为webPackageName,subPackage分开定义
	 */
	private final String ModelSubPackage = "model";
	private final String DaoSubPackage = "repository.mybatis";
	private final String DtoSubPackage = "dto";
	private final String MappeSubPackage = "repository.mybatis";
	private final String RepositorySubPackage = "repository";
	private final String ServiceSubPackage = "service";
	private final String ServiceImplSubPackage = "service.impl";
	private final String ControllerSubPackage = "controller";

	private final String changeTableName = replaceUnderLineAndUpperCase(tableName);
	private ResultSet resultSet;
	private List<FtlNCode> ftlNcodeList;

	public Connection getConnection() throws Exception {
		Class.forName(DRIVER);
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		return connection;
	}

	public GenCodeUtils begin() {
		try {
			Connection connection = getConnection();
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			resultSet = databaseMetaData.getColumns(null, "%", tableName, "%");
			ftlNcodeList = new ArrayList<FtlNCode>();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
		}
		return this;
	}

	public void build() {
		for (FtlNCode ftlNcode : ftlNcodeList) {
			// System.out.println(ftlNcode);
			Map<String, Object> dataMap;
			if ((dataMap = ftlNcode.getDataMap()) != null) {
				generateFileByTemplate(ftlNcode.getFtl(),
						diskPath + OutPathUtil.package2Path(webPackageName + "." + ftlNcode.getSubPackage())
								+ ftlNcode.getCode(),
						dataMap);
			} else
				generateFileByTemplate(ftlNcode.getFtl(),
						diskPath + OutPathUtil.package2Path(webPackageName + "." + ftlNcode.getSubPackage())
								+ ftlNcode.getCode());
		}
		System.out.println("success!");
	}

	public GenCodeUtils Model(String ftl, String code) {
		List<ColumnClass> columnClassList = new ArrayList<ColumnClass>();
		ColumnClass columnClass = null;
		try {
			while (resultSet.next()) {
				// id字段略过
				if (resultSet.getString("COLUMN_NAME").equals("id"))
					continue;
				columnClass = new ColumnClass();
				// 获取字段名称
				columnClass.setColumnName(resultSet.getString("COLUMN_NAME"));
				// 获取字段类型
				columnClass.setColumnType(resultSet.getString("TYPE_NAME"));
				// 转换字段名称，如 sys_name 变成 SysName
				columnClass.setChangeColumnName(replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
				// 字段在数据库的注释
				columnClass.setColumnComment(resultSet.getString("REMARKS"));
				columnClassList.add(columnClass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("model_column", columnClassList);
		ftlNcodeList.add(
				new FtlNCode(ftl, ModelSubPackage, changeTableName + (code.length() == 0 ? ".java" : code), dataMap));
		return this;
	}

	public GenCodeUtils Dto(String ftl, String code) {
		ftlNcodeList.add(new FtlNCode(ftl, DtoSubPackage, changeTableName + code));
		return this;
	}

	public GenCodeUtils Controller(String ftl, String code) {
		ftlNcodeList.add(new FtlNCode(ftl, ControllerSubPackage, changeTableName + code));
		return this;
	}

	public GenCodeUtils Service(String ftl, String prefix, String code) {
		ftlNcodeList.add(new FtlNCode(ftl, ServiceSubPackage, prefix + changeTableName + code));
		return this;
	}

	public GenCodeUtils Service(String ftl, String code) {
		ftlNcodeList.add(new FtlNCode(ftl, ServiceSubPackage, changeTableName + code));
		return this;
	}

	public GenCodeUtils ServiceImpl(String ftl, String code) {
		ftlNcodeList.add(new FtlNCode(ftl, ServiceImplSubPackage, changeTableName + code));
		return this;
	}

	public GenCodeUtils Repository(String ftl, String code) {
		ftlNcodeList.add(new FtlNCode(ftl, RepositorySubPackage, changeTableName + code));
		return this;
	}

	public GenCodeUtils Dao(String ftl, String code) {
		ftlNcodeList.add(new FtlNCode(ftl, DaoSubPackage, changeTableName + code));
		return this;
	}

	public GenCodeUtils Mapper(String ftl, String code) {
		ftlNcodeList.add(new FtlNCode(ftl, MappeSubPackage, changeTableName + code));
		return this;
	}

	private void generateFileByTemplate(final String templateName, String path) {
		this.generateFileByTemplate(templateName, path, new HashMap<String, Object>());
	}

	private void generateFileByTemplate(final String templateName, String path, Map<String, Object> dataMap) {
		Template template = null;
		FileOutputStream fos = null;
		OutPathUtil.makeFile(path.substring(0, path.lastIndexOf("/")));
		try {
			template = FreeMarkerTemplateUtils.getTemplate(templateName);
			fos = new FileOutputStream(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		dataMap.put("table_name_small", tableName);
		dataMap.put("table_name", changeTableName);
		dataMap.put("author", AUTHOR);
		dataMap.put("date", CURRENT_DATE);
		dataMap.put("package_name", webPackageName);
		dataMap.put("table_annotation", tableAnnotation);
		Writer out;
		try {
			out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
			template.process(dataMap, out);
		} catch (UnsupportedEncodingException e) {
		} catch (TemplateException e) {
		} catch (IOException e) {
		}
	}

	public String replaceUnderLineAndUpperCase(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append(str);
		int count = sb.indexOf("_");
		while (count != 0) {
			int num = sb.indexOf("_", count);
			count = num + 1;
			if (num != -1) {
				char ss = sb.charAt(count);
				char ia = (char) (ss - 32);
				sb.replace(count, count + 1, ia + "");
			}
		}
		String result = sb.toString().replaceAll("_", "");
		return StringUtils.capitalize(result);
	}
}
