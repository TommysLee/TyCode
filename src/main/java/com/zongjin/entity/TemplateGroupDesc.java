package com.zongjin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 模板组描述文件实体类
 *
 * @Author Tommy
 * @Date 2021/11/15
 */
@Data
public class TemplateGroupDesc {

    // 模板组ID
    private String id;

    // 模板组名称
    private String name;

    // 模板组描述
    private String desc;

    // 模板组路径
    @JsonIgnore
    private String path;

    // 模板文件列表描述
    @JsonIgnore
    private List<TemplateFileDesc> fileDescList;
}
