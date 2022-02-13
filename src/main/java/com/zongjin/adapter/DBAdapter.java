package com.zongjin.adapter;

import com.zongjin.constant.DBType;
import com.zongjin.dao.GenCodeDao;
import com.zongjin.dao.impl.MysqlGenCodeDaoImpl;
import com.zongjin.dao.impl.OracleGenCodeDaoImpl;
import com.zongjin.dao.impl.SQLServerGenCodeDaoImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static com.zongjin.constant.DBType.MYSQL;
import static com.zongjin.constant.DBType.ORACLE;
import static com.zongjin.constant.DBType.SQLSERVER;

/**
 * DB适配器
 *
 * @Author Tommy
 * @Date 2015-9-4
 */
@Slf4j
public class DBAdapter {

    // DAO Map对象
    private static final Map<DBType, GenCodeDao> DAO_MAP;

    // 数据库类型
    private static final ThreadLocal<DBType> DB_TYPE = new ThreadLocal<>();

    static {
        DAO_MAP = new HashMap<DBType, GenCodeDao>() {

            private static final long serialVersionUID = 4383032221041532573L;

            {
                put(MYSQL, new MysqlGenCodeDaoImpl());
                put(ORACLE, new OracleGenCodeDaoImpl());
                put(SQLSERVER, new SQLServerGenCodeDaoImpl());
            }
        };

        setDBType(MYSQL); // 默认DB类型
    }

    /**
     * 根据DB类型，获取合适的DAO
     *
     * @return GenCodeDao
     */
    public static GenCodeDao getDao() {
        log.info("自动适配合适的DAO：" + getDBType());
        return DAO_MAP.get(getDBType());
    }

    /**
     * 设置当前的数据库类型
     *
     * @param dbType
     */
    public static void setDBType(DBType dbType) {
        DB_TYPE.set(dbType);
    }

    /**
     * 获取当前的数据库类型
     *
     * @return DBType
     */
    public static DBType getDBType() {
        return DB_TYPE.get();
    }
}
