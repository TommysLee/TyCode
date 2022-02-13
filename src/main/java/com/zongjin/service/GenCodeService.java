package com.zongjin.service;

import com.zongjin.entity.Mapping;
import com.zongjin.entity.Requirement;
import com.zongjin.entity.Result;

import java.util.List;

/**
 * 代码生成器接口规范
 *
 * @Author Tommy
 * @Date 2015/12/9
 */
public interface GenCodeService {

    /**
     * 该方法用于获取表字段映射关系数据
     *
     * @param requirement
     * @return List<Mapping>
     * @throws Exception
     */
    List<Mapping> getMappingData(final Requirement requirement) throws Exception;

    /**
     * 该方法根据表字段映射关系数据生成各组件源代码
     *
     * @param requirement
     * @param mappings
     * @return List<Result> 源码结果集合
     * @throws Exception
     */
    List<Result> generateSources(final Requirement requirement, final List<Mapping> mappings) throws Exception;
}
