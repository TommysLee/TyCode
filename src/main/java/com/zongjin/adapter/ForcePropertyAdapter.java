package com.zongjin.adapter;

import com.zongjin.entity.Mapping;

import java.util.HashMap;
import java.util.Map;

import static com.zongjin.constant.DBType.MYSQL;
import static com.zongjin.constant.DBType.ORACLE;

/**
 * 强制属性适配器
 *
 * <p>
 * 无论如何，遇到此种属性，则使用对应强制值
 * </p>
 *
 * @Author Tommy
 * @Date 2015-9-5
 */
public class ForcePropertyAdapter {

    /**
     * 强制属性规则定义
     */
    private static Map<String, String> RULE_MAP;

    static {
        RULE_MAP = new HashMap<String, String>() {

            private static final long serialVersionUID = -1084933064734793649L;

            {
                // MYSQL
                put("createTime" + MYSQL, "now()");
                put("updateTime" + MYSQL, "now()");

                // ORACLE
                put("createTime" + ORACLE, "sysdate");
                put("updateTime" + ORACLE, "sysdate");

                // 其它
                put("createTime", "#{createTime}");
                put("updateTime", "#{updateTime}");
            }
        };
    }

    /**
     * 如果满足规则，则填充强制值
     *
     * @param mapping
     */
    public static void fillForceValue(Mapping mapping) {

        final String javaName = mapping.getJavaName();
        if (RULE_MAP.containsKey(javaName + DBAdapter.getDBType())) {
            mapping.setForceValue(RULE_MAP.get(javaName + DBAdapter.getDBType()));
        } else if (RULE_MAP.containsKey(javaName)) {
            mapping.setForceValue(RULE_MAP.get(javaName));
        }
    }
}
