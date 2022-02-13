package com.zongjin.dao.impl;

import com.zongjin.dao.GenCodeDao;
import com.zongjin.entity.DataSourceDesc;
import com.zongjin.entity.Mapping;
import com.zongjin.entity.Requirement;
import com.zongjin.utils.DBHelper;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.zongjin.constant.SQL.SQLSERVER_TABLE;
import static com.zongjin.constant.SQL.SQLSERVER_COLUMN;

/**
 * 获取 SQLServer 表结构的实现
 *
 * @Author Tommy
 * <p>
 * 2016-4-12
 */
public class SQLServerGenCodeDaoImpl implements GenCodeDao {

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

        final List<Mapping> list = new ArrayList<>();

        final Connection conn = DBHelper.getConnection(dataSourceDesc.toMap());
        // 获取表基本属性
        PreparedStatement ps = conn.prepareStatement(SQLSERVER_TABLE.replace(
                "{1}", requirement.getTabName()));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            requirement.setTabComment(rs.getString(2));
        }

        // 获取表字段定义
        ps = conn.prepareStatement(SQLSERVER_COLUMN.replace("{1}",
                requirement.getTabName()));
        rs = ps.executeQuery();
        while (rs.next()) {
            int i = 2;
            Mapping m = new Mapping();
            m.setColumnName(rs.getString(i++));
            m.setIsKey("PRI".equals(rs.getString(i++)));
            m.setColumnComment(rs.getString(i++));
            m.setColumnType(rs.getString(i++).toLowerCase());

            int dataLen = rs.getInt(i++);
            int dataPrecision = rs.getInt(i++);
            m.setLength(dataPrecision > dataLen ? dataPrecision : dataLen);
            m.setScale(rs.getInt(i++));

            m.setDefaultValue(rs.getString(i++));
            m.setHasDefault(StringUtils.isNotBlank(m.getDefaultValue()) ? true : false);

            // 添加表字段
            list.add(m);
        }
        DBHelper.releaseConnection(rs, ps, conn);
        return list;
    }
}
