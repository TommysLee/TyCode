package com.zongjin.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 *
 * @Author Tommy
 * @Date 2021/12/10
 */
public final class StringUtil {

    /**
     * 获取符合Java命名规范的文本
     *
     * @param str
     * @param isProp 是否为属性命名
     * @return String
     */
    public static String getNamingText(String str, boolean isProp) {

        String text = getText(str).toLowerCase();
        if (text.length() > 0 && !text.matches("_+")) {
            final String[] letterArray = text.split("_");
            final StringBuilder textBuilder = new StringBuilder();

            int index = 0;
            if (isProp) { // 若为属性命名，则第一个单词小写
                textBuilder.append(letterArray[0]);
                index = 1;
            }

            for (int i = index; i < letterArray.length; i++) {
                textBuilder.append(getFirstUpperText(letterArray[i]));
            }
            text = textBuilder.toString();
        }
        return text;
    }

    /**
     * 获取符合Java属性命名规范的文本
     *
     * @param str
     * @return String
     */
    public static String getNamingProperty(String str) {
        return getNamingText(str, true);
    }

    /**
     * 获取符合Java类命名规范的文本
     *
     * @param str
     * @param tabNamePrefix
     * @return String
     */
    public static String getNamingClass(String str, String tabNamePrefix) {

        return getNamingText(
                getText(str).toLowerCase().replaceFirst(
                        "(" + tabNamePrefix + ")+", StringUtils.EMPTY), false);
    }

    /**
     * 获取首字母大写的文本
     *
     * @param str
     * @return String
     */
    public static String getFirstUpperText(String str) {

        String text = getText(str);
        text = text.length() > 0 ? text.replaceFirst(".",
                String.valueOf(text.charAt(0)).toUpperCase()) : text;
        return text;
    }

    /**
     * 获取首字母小写的文本
     *
     * @param str
     * @return String
     */
    public static String getFirstLowerText(String str) {

        String text = getText(str);
        text = text.length() > 0 ? text.replaceFirst(".",
                String.valueOf(text.charAt(0)).toLowerCase()) : text;
        return text;
    }

    /**
     * 获取字符串内容
     *
     * @param str
     * @return String 无论如何，都会返回非空值
     */
    public static String getText(String str) {
        return StringUtils.defaultIfBlank(str, StringUtils.EMPTY).trim();
    }
}
