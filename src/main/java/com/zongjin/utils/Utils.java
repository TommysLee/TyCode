package com.zongjin.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类：用于扩展Thymeleaf 和 读取自定义配置信息
 *
 * @Author Tommy
 * @Date 2021/12/10
 */
@Component
@ConfigurationProperties(prefix = "ty")
@Data
public class Utils {

    /** 数据库表名前缀 **/
    private String tabNamePrefix;

    /** 包名前缀 **/
    public String pkgPrefix;

    /**
     * 当前时间
     */
    public String now() {
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }
}
