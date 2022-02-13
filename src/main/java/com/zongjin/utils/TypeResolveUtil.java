package com.zongjin.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DB类型解析工具类
 * 
 * @Author Tommy
 * 
 *         2015-11-11
 */
public class TypeResolveUtil {

	/**
	 * 列类型长度正则表达式
	 */
	private final static Pattern COLUMN_TYPE_LEN_PATTERN = Pattern.compile("(\\w+)\\((\\d+)\\)");

	/**
	 * 获取列类型的长度
	 * 
	 * @param columnType
	 *            ----> 列类型，形如：int(8)
	 * @return int
	 */
	public static int length(String columnType) {

		int len = -1;
		if (null != columnType) {
			final Matcher matcher = COLUMN_TYPE_LEN_PATTERN.matcher(columnType);
			if (matcher.matches()) {
				len = Integer.parseInt(matcher.group(2));
			}
		}
		return len;
	}
}
