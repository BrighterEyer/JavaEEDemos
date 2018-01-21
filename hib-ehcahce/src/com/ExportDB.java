package com;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class ExportDB {

	/**
	 * 将hbm生成ddl.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		SchemaExport export = new SchemaExport(cfg);
		// 打印到控制台，输出到数据库
		export.create(true, true);
	}

}
