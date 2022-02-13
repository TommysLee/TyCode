package com.zongjin.entity;

import lombok.Data;

/**
 * 模板文件描述文件实体类
 *
 * @Author Tommy
 * @Date 2021/11/15
 */
@Data
public class TemplateFileDesc {

    // 模板文件名称
    private String name;

    // 模板文件描述
    private String desc;

    // 模板文件类型
    private String type;

    // 模板文件路径(相对)
    private String path;
}
