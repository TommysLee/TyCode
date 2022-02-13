package com.zongjin.entity;

import lombok.Data;

/**
 * Java属性与数据库字段映射关系实体类
 *
 * @Author Tommy
 * @Date 2015/12/7
 */
@Data
public class Mapping {

    // Java名称
    private String javaName;

    // Java数据类型
    private String javaType;

    // Java数据类型所属包
    private String packageName;

    // Java Get/Set方法名称
    private String methodName;

    // 表字段名
    private String columnName;

    // 表字段类型
    private String columnType;

    // 表字段注释
    private String columnComment;

    // 字段长度
    private Integer length;

    // 字段保留数(小数点后位数)
    private Integer scale;

    // 是否为主键
    private Boolean isKey;

    // 是否有默认值
    private Boolean hasDefault;

    // 默认值
    private String defaultValue;

    // 强制值
    private String forceValue;
}
