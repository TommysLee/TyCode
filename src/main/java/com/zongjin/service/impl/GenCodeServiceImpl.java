package com.zongjin.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zongjin.adapter.DBAdapter;
import com.zongjin.adapter.DataTypeAdapter;
import com.zongjin.adapter.ForcePropertyAdapter;
import com.zongjin.dao.GenCodeDao;
import com.zongjin.entity.Mapping;
import com.zongjin.entity.Requirement;
import com.zongjin.entity.Result;
import com.zongjin.entity.TemplateFileDesc;
import com.zongjin.entity.TemplateGroupDesc;
import com.zongjin.service.GenCodeService;
import com.zongjin.service.IDataSourceDescService;
import com.zongjin.service.ITemplateGroupDescService;
import com.zongjin.utils.StringUtil;
import com.zongjin.utils.ThymeleafUtil;
import com.zongjin.utils.Utils;
import com.zongjin.utils.uusn.UUSNUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器业务逻辑实现
 *
 * @Author Tommy
 * @Date 2015/12/9
 */
@Service
public class GenCodeServiceImpl implements GenCodeService {

    @Autowired
    private IDataSourceDescService dataSourceDescService;

    @Autowired
    private ITemplateGroupDescService templateGroupDescService;

    @Autowired
    private Utils utils;

    /**
     * 该方法用于获取表字段映射关系数据
     *
     * @param requirement
     * @return List<Mapping>
     * @throws Exception
     */
    @Override
    public List<Mapping> getMappingData(Requirement requirement) throws Exception {

        final GenCodeDao genCodeDao = DBAdapter.getDao();
        List<Mapping> mappingList = new ArrayList<Mapping>();
        if (StringUtils.isNotBlank(requirement.getTabName())) {
            requirement.setClazzName(StringUtil.getNamingClass(requirement.getTabName(), utils.getTabNamePrefix()));
            requirement.setInstanceName(StringUtil.getFirstLowerText(requirement.getClazzName()));
            mappingList = genCodeDao.getTableDefinition(requirement, dataSourceDescService.getById(requirement.getDatasource()));
            for (Mapping m : mappingList) {
                m.setJavaName(StringUtil.getNamingProperty(m.getColumnName()));
                m.setMethodName(StringUtil.getFirstUpperText(m.getJavaName()));
                DataTypeAdapter.fillJavaType(m);
                ForcePropertyAdapter.fillForceValue(m);
                requirement.addPackage(m.getPackageName());

                // 查找主键
                if (m.getIsKey() && StringUtils.isBlank(requirement.getPkProperty())) {
                    requirement.setPkProperty(m.getJavaName());
                    requirement.setPkColumn(m.getColumnName());
                }
            }
        }
        return mappingList;
    }

    /**
     * 该方法根据表字段映射关系数据生成各组件源代码
     *
     * @param requirement
     * @param mappings
     * @return List<Result> 源码结果集合
     * @throws Exception
     */
    public List<Result> generateSources(final Requirement requirement, List<Mapping> mappings) throws Exception {

        final List<Result> resultList = Lists.newArrayList();

        // 模板数据
        final Map<String, Object> dataModel = Maps.newHashMap();
        dataModel.put("base", requirement);
        dataModel.put("mappings", mappings);
        dataModel.put("utils", utils);

        // 获取选定的各组件模板
        final TemplateGroupDesc templateGroupDesc = templateGroupDescService.getById(requirement.getTemplateGroup());
        if (null != templateGroupDesc && mappings.size() > 0) {
            final List<TemplateFileDesc> templateFileDescs = templateGroupDesc.getFileDescList();

            // 循环生成各组件源代码
            for (TemplateFileDesc t : templateFileDescs) {
                Result result = new Result();
                dataModel.put("uusn", UUSNUtil.generateLocalUUSN()); // serialVersionUID
                result.setSourceCode(ThymeleafUtil.resolveTemplate(t.getPath(), dataModel)); // 源码
                result.setShowName(t.getDesc()).setType(t.getType()); // 源码名称与文件类型
                resultList.add(result);
            }
        }
        return resultList;
    }
}
