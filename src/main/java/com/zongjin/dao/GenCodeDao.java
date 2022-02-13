package com.zongjin.dao;

import com.zongjin.entity.DataSourceDesc;
import com.zongjin.entity.Mapping;
import com.zongjin.entity.Requirement;

import java.util.List;

/**
 * 获取数据库表结构的接口规范
 *
 * @Author Tommy
 * @Date 2015/12/9
 */
public interface GenCodeDao {

    /**
     * 该方法用于获取表定义信息
     *
     * @param requirement
     * @param dataSourceDesc
     * @return List<Mapping>
     * @throws Exception
     */
    List<Mapping> getTableDefinition(final Requirement requirement, final DataSourceDesc dataSourceDesc) throws Exception;
}
