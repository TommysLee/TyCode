package com.zongjin.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 生成需求实体类
 *
 * @Author Tommy
 * @Date 2021/12/7
 */
@Data
public class Requirement {

    // 作者
    private String author;

    // 类所属包
    private String pkgName;

    // 类名
    private String clazzName;

    // 类实例名
    private String instanceName;

    // 类主键属性
    private String pkProperty;

    // DB表名
    private String tabName;

    // DB表注释
    private String tabComment;

    // DB表主键字段
    private String pkColumn;

    // 数据源
    private String datasource;

    // 模板组
    private String templateGroup;

    // 需要导入的包
    private Set<String> packageSet = new HashSet<>();

    /**
     * 将业务包名添加到集合
     *
     * @param pkg
     * @return String
     */
    public Set<String> addPackage(String pkg) {
        if (StringUtils.isNotBlank(pkg))
            packageSet.add(pkg);
        return packageSet;
    }

    /**
     * 以包名的最后一个单词作为模块名称
     *
     * @return String
     */
    public String getModuleName() {
        return this.pkgName.substring(this.pkgName.lastIndexOf(".") + 1);
    }
}
