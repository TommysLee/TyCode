package com.zongjin.utils;

import com.zongjin.constant.DBType;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import static com.zongjin.constant.Ty.DRIVER;
import static com.zongjin.constant.Ty.URL;
import static com.zongjin.constant.Ty.USER_NAME;
import static com.zongjin.constant.Ty.PASSWORD;

/**
 * 获得与数据库的连接和资源释放
 *
 * @Author zongjin
 * <p>
 * 2009-10-9
 */
public class DBHelper {

    /**
     * 该方法用于获得与数据库的连接
     *
     * @param config
     * @return Connection
     */
    public static Connection getConnection(Map<String, String> config) {
        Connection conn = null;
        try {
            Class.forName(config.get(DRIVER));
            conn = DriverManager.getConnection(config.get(URL),
                    config.get(USER_NAME), config.get(PASSWORD));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 该方法用于释放与数据库的连接资源
     *
     * @param rs
     * @param ps
     * @param conn
     */
    public static void releaseConnection(ResultSet rs, PreparedStatement ps,
                                         Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseConnection(ps, conn);
        }
    }

    /**
     * 该方法用于释放与数据库的连接资源
     *
     * @param ps
     * @param conn
     */
    public static void releaseConnection(PreparedStatement ps, Connection conn) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseConnection(conn);
        }
    }

    /**
     * 该方法用于释放与数据库的连接资源
     *
     * @param conn
     */
    public static void releaseConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据数据库URL推测数据库类型
     *
     * @param url
     * @return DBType
     */
    public static DBType getDBType(String url) {
        if (StringUtils.isNotBlank(url)) {
            if (url.startsWith("jdbc:mysql:") || url.startsWith("jdbc:cobar:") || url.startsWith("jdbc:log4jdbc:mysql:")) {
                return DBType.MYSQL;
            } else if (url.startsWith("jdbc:mariadb:")) {
                return DBType.MARIADB;
            } else if (url.startsWith("jdbc:oracle:") || url.startsWith("jdbc:log4jdbc:oracle:")) {
                return DBType.ORACLE;
            } else if (url.startsWith("jdbc:sqlserver:") || url.startsWith("jdbc:microsoft:") || url.startsWith("jdbc:log4jdbc:microsoft:") || url.startsWith("jdbc:log4jdbc:sqlserver:")) {
                return DBType.SQLSERVER;
            } else if (url.startsWith("jdbc:postgresql:") || url.startsWith("jdbc:log4jdbc:postgresql:")) {
                return DBType.POSTGRESQL;
            } else if (url.startsWith("jdbc:db2:")) {
                return DBType.DB2;
            } else if (url.startsWith("jdbc:oceanbase:")) {
                return DBType.OCEANBASE;
            }
        }
        return null;
    }

    /**
     * 从URL中获取数据库名称
     *
     * @param url
     * @param dbType
     * @return String
     */
    public static String getDatabaseName(String url, DBType dbType) {

        String dbName = null;
        if (StringUtils.isNotBlank(url)) {
            switch (dbType) {
                case MYSQL:
                    int begin = url.indexOf("/", url.indexOf("//") + 2) + 1;
                    int end = url.indexOf("?");
                    end = end != -1? end : url.length();
                    dbName = url.substring(begin, end);
                    break;
            }
        }
        return dbName;
    }
}
