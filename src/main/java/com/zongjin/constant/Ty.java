package com.zongjin.constant;

/**
 * TyCode常量类
 *
 * @Author Tommy
 * @Date 2021/12/2
 */
public interface Ty {

    /**
     * JDBC数据源配置文件
     */
    String JDBC_FILE_PATH = "repository/jdbc.yml";

    /**
     * 模板组根目录
     */
    String TEMPLATE_GROUP_PATH = "repository/templateGroups";

    /**
     * 模板组模块目录名
     */
    String TEMPLATE_GROUP_MODULE = "modules";

    /**
     * 模板组描述文件
     */
    String TEMPLATE_GROUP_FILE = "readme.yml";

    /**
     * DB驱动
     */
    String DRIVER = "driver";

    /**
     * DB URL
     */
    String URL = "url";

    /**
     * DB用户名
     */
    String USER_NAME = "username";

    /**
     * DB密码
     */
    String PASSWORD = "password";

    /**
     * DB类型
     */
    String DB_TYPE = "dbType";

    /**
     * DB名称
     */
    String DB_NAME = "dbName";
}
