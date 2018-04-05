package com.code.util;

import java.io.File;

public class OutPathUtil {

	private static final String FILE_SEPARATOR = File.separator;
	public static final String SRC_DIR_JAVA = System.getProperty("user.dir") + FILE_SEPARATOR + "src" + FILE_SEPARATOR
			+ "main" + FILE_SEPARATOR + "java" + FILE_SEPARATOR;

	public static final String SRC_DIR_RES = System.getProperty("user.dir") + FILE_SEPARATOR + "src" + FILE_SEPARATOR
			+ "main" + FILE_SEPARATOR + "resources" + FILE_SEPARATOR;

	public static final String package2Path(String packageName) {
		return packageName.replace(".", FILE_SEPARATOR) + FILE_SEPARATOR;
	}

	public static final boolean out2SrcPath(String packageName) {
		return makeFile(SRC_DIR_JAVA + packageName.replace(".", FILE_SEPARATOR));
	}

	public static final boolean out2ResPath(String packageName) {
		return makeFile(SRC_DIR_RES + packageName.replace(".", FILE_SEPARATOR));
	}

	public static final boolean makeFile(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return dir.exists();
	}
}
