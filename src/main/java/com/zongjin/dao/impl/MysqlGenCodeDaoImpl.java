package com.zongjin.dao.impl;

import com.zongjin.constant.DBType;
import com.zongjin.dao.GenCodeDao;
import com.zongjin.entity.DataSourceDesc;
import com.zongjin.entity.Mapping;
import com.zongjin.entity.Requirement;
import com.zongjin.utils.DBHelper;
import com.zongjin.utils.TypeResolveUtil;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.zongjin.constant.SQL.MYSQL_TABLE;
import static com.zongjin.constant.SQL.MYSQL_COLUMN;

/**
 * 获取 MySQL 表结构的实现
 *
 * @Author Tommy
 * @Date 2015-9-3
 */
public class MysqlGenCodeDaoImpl implements GenCodeDao {

    /**
     * 该方法用于获取表定义信息
     *
     * @param requirement
     * @param dataSourceDesc
     * @return List<Mapping>
     * @throws Exception
     */
    public List<Mapping> getTableDefinition(final Requirement requirement, final DataSourceDesc dataSourceDesc)
            throws Exception {

        final String dbName = DBHelper.getDatabaseName(dataSourceDesc.getUrl(), DBType.MYSQL); // 数据库实例名

        final List<Mapping> list = new ArrayList<>();

        final Connection conn = DBHelper.getConnection(dataSourceDesc.toMap());
        // 获取表基本属性
        PreparedStatement ps = conn.prepareStatement(MYSQL_TABLE.replace("{0}",
                dbName).replace("{1}", requirement.getTabName()));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            requirement.setTabComment(rs.getString(2));
        }

        // 获取表字段定义
        ps = conn.prepareStatement(MYSQL_COLUMN.replace("{0}", dbName).replace(
                "{1}", requirement.getTabName()));
        rs = ps.executeQuery();
        while (rs.next()) {
            int i = 2;
            Mapping m = new Mapping();
            m.setIsKey("PRI".equals(rs.getString(i++)));
            m.setColumnName(rs.getString(i++));
            m.setColumnComment(rs.getString(i++));
            m.setColumnType(rs.getString(i++).toLowerCase());
            m.setLength(rs.getInt(i++));
            m.setScale(rs.getInt(i++));
            m.setDefaultValue(rs.getString(i++));
            m.setHasDefault(StringUtils.isNotBlank(m.getDefaultValue()) ? true : false);

            // 校准类型长度
            int factLen = TypeResolveUtil.length(rs.getString(i++));
            if (factLen >= 0 && null != m.getLength()) {
                int len = factLen < m.getLength() ? factLen : m.getLength();
                len = 0 == m.getLength() ? factLen : len;
                m.setLength(len);
            }

            // 添加表字段
            list.add(m);
        }
        DBHelper.releaseConnection(rs, ps, conn);
        return list;
    }
}
