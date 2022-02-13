package com.zongjin.utils;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * 数字工具类
 * 
 * @Author Tommy
 * 
 *         2015-8-24
 */
public final class NumberUtil {

	/**
	 * 获取随机UID
	 * 
	 * @return String
	 */
	public static String randomSerialUID() {

		return Long.valueOf(new Random().nextLong()).toString();
	}

	/**
	 * 获取随机数
	 * 
	 * @param length
	 *            ----> 随机数长度
	 * @return
	 */
	public static String random(int length) {

		final String numberChar = "1234567890";
		final StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
		}
		return sb.toString();
	}

	/**
	 * 数字位数不足自动补零
	 * 
	 * @param number
	 *            ----> 数字
	 * @param digit
	 *            ----> 位数
	 * @return
	 */
	public static String fillZero(Long number, int digit) {

		final StringBuilder patternBuilder = new StringBuilder("#");
		for (int i = 0; i < digit; i++) {
			patternBuilder.append("0");
		}

		final DecimalFormat df = new DecimalFormat(patternBuilder.toString());
		return df.format(number);
	}

	/**
	 * 数字位数不足自动补零
	 * 
	 * @param number
	 *            ----> 数字
	 * @param digit
	 *            ----> 位数
	 * @return
	 */
	public static String fillZero(Integer number, int digit) {

		return fillZero(new Long(number), digit);
	}

	/**
	 * 数字位数不足自动补零
	 * 
	 * @param numberStr
	 *            ----> 数字字符串
	 * @param digit
	 *            ----> 位数
	 * @return
	 */
	public static String fillZero(String numberStr, int digit) {

		return fillZero(new Long(numberStr), digit);
	}
}
