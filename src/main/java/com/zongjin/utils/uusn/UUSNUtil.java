package com.zongjin.utils.uusn;

/**
 * UUSN工具类
 * 
 * @Author Tommy
 * 
 *         2015-8-26
 */
public class UUSNUtil {

	// 本地UUSN对象
	private static UUSN localUUSN = null;

	static {
		localUUSN = new LocalUUSN();
	}

	private UUSNUtil() {
	}

	/**
	 * 生成本地UUSN编码
	 * 
	 * @return
	 */
	public static String generateLocalUUSN() {

		return localUUSN.generateUUSN();
	}
}
