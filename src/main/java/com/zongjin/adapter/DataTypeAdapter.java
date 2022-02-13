package com.zongjin.adapter;

import com.zongjin.entity.Mapping;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据类型适配器<br/>
 *
 * <p>
 * 用于将<b>数据库</b>的数据类型对应转换为<b>Java数据类型</b>
 * </p>
 *
 * @Author Tommy
 * @Date 2015-9-5
 */
public class DataTypeAdapter {

    /**
     * DB2J-数据类型映射Map
     */
    private static final Map<String, String> DATA_TYPE_MAPPING = new HashMap<String, String>() {

        private static final long serialVersionUID = 1027426247262818091L;

        {
            // java.lang.String
            put("char", "String");
            put("nchar", "String");
            put("character", "String");
            put("varchar", "String");
            put("varchar2", "String");
            put("nvarchar", "String");
            put("nvarchar2", "String");
            put("text", "String");
            put("longtext", "String");
            put("clob", "String");
            put("nclob", "String");

            // java.util.Date
            put("date", "Date");
            put("datetime", "Date");
            put("timestamp", "Date");
            put("time", "Date");

            // 浮点数
            put("float", "Float");
            put("double", "Double");

            // 整数
            put("long", "Long");
            put("bigint", "Long");
        }
    };

    /**
     * DB2J-不明确数据类型映射Map
     */
    private static final Map<String, Integer> UNSURE_DATA_TYPE_MAPPING = new HashMap<String, Integer>() {

        private static final long serialVersionUID = 6590817177881497300L;

        {
            // 整数
            put("integer", 1); // 也可能映射为Long
            put("int", 2); // 也可能映射为Long

            // 不确定数字类型(Integer, Long, Double, BigDecimal)
            put("numeric", 3);
            put("number", 4);
            put("decimal", 5);
        }
    };

    /**
     * Java Package Map
     */
    private static final Map<String, String> PACKAGE_MAP = new HashMap<String, String>() {

        private static final long serialVersionUID = 6590817177881497300L;

        {
            put("Date", "java.util.Date");
            put("BigDecimal", "java.math.BigDecimal");
        }
    };

    /**
     * 填充Java数据类型
     *
     * @param mapping
     */
    public static void fillJavaType(Mapping mapping) {

        String columnType = mapping.getColumnType();
        int index;
        if (StringUtils.isNotBlank(columnType)
                && (index = columnType.indexOf("(")) != -1) {
            columnType = columnType.substring(0, index);
        }

        mapping.setJavaType(DATA_TYPE_MAPPING.containsKey(columnType) ? DATA_TYPE_MAPPING
                .get(columnType) : UNSURE_DATA_TYPE_MAPPING
                .containsKey(columnType) ? resolveType(mapping) : "Object");
        mapping.setPackageName(PACKAGE_MAP.get(mapping.getJavaType()));

    }

    /**
     * 进一步分析DB数据类型
     *
     * @param mapping
     * @return String
     */
    private static String resolveType(Mapping mapping) {

        String jType = "Object";
        final Integer typeId = UNSURE_DATA_TYPE_MAPPING.get(mapping
                .getColumnType());
        switch (typeId) {
            case 1:
            case 2:
                jType = mapping.getLength() > 10 ? "Long" : "Integer";
                break;
            case 3:
            case 4:
            case 5:
                if (mapping.getScale() > 0) {
                    jType = mapping.getScale() > 4 ? "BigDecimal" : "Double";
                } else {
                    jType = mapping.getLength() > 9 ? "Long" : "Integer";
                }
                break;
        }
        return jType;
    }
}
