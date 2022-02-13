package com.zongjin.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 源码生成结果的实体类
 *
 * @Author Tommy
 * @Date 2022/1/29
 */
@Data
@Accessors(chain = true)
public class Result {

    // 显示名称
    private String showName;

    // 文件类型
    private String type;

    // 源码
    private String sourceCode;
}
