package com.zongjin;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 使用外部Tomcat容器时，需要此类
 * <p>
 *     由外部容器自动加载此类。
 * </p>
 *
 * @Author Tommy
 * @Date 2021/11/10
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BootApplication.class);
    }
}
