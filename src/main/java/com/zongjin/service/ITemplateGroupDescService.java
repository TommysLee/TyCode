package com.zongjin.service;

import com.zongjin.entity.TemplateGroupDesc;

import java.util.List;

/**
 * 模板组描述文件Service
 *
 * @Author Tommy
 * @Date 2021/12/7
 */
public interface ITemplateGroupDescService {

    /**
     * 获取模板组描述文件列表
     *
     * @return List<TemplateGroupDesc>
     */
    public List<TemplateGroupDesc> getList() throws Exception ;

    /**
     * 通过ID获取模板组详情
     *
     * @param id
     * @return TemplateGroupDesc
     */
    public TemplateGroupDesc getById(String id) throws Exception;

    /**
     * 清除缓存的模板组描述文件列表
     */
    public void clear();
}
