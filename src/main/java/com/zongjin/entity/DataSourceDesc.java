package com.zongjin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import com.zongjin.constant.DBType;
import lombok.Data;

import java.util.Map;

import static com.zongjin.constant.Ty.DRIVER;
import static com.zongjin.constant.Ty.PASSWORD;
import static com.zongjin.constant.Ty.URL;
import static com.zongjin.constant.Ty.USER_NAME;

/**
 * 数据源描述文件实体类
 *
 * @Author Tommy
 * @Date 2021/11/23
 */
@Data
public class DataSourceDesc {

    // 数据源ID
    private String id;

    // 数据源描述
    private String desc;

    // 数据库类型
    @JsonIgnore
    private DBType dbType;

    // 驱动程序
    @JsonIgnore
    private String driver;

    // 数据库连接
    @JsonIgnore
    private String url;

    // 账户
    @JsonIgnore
    private String username;

    // 密码
    @JsonIgnore
    private String password;

    /**
     * 将数据库必备连接参数以Map返回
     */
    public Map<String, String> toMap() {

        final Map<String, String> map = Maps.newHashMap();
        map.put(DRIVER, this.driver);
        map.put(URL, this.url);
        map.put(USER_NAME, this.username);
        map.put(PASSWORD, this.password);
        return map;
    }
}
