package com.zongjin.service;

import com.zongjin.entity.DataSourceDesc;

import java.util.List;

/**
 * 数据源描述信息Service
 *
 * @Author Tommy
 * @Date 2021/12/7
 */
public interface IDataSourceDescService {

    /**
     * 获取数据源描述列表
     *
     * @return Map<String, DataSourceDesc>
     */
    List<DataSourceDesc> getList();

    /**
     * 根据ID获取数据源描述详情
     *
     * @param id
     * @return DataSourceDesc
     */
    DataSourceDesc getById(String id);

    /**
     * 清除缓存的数据源描述列表
     */
    void clear();
}
