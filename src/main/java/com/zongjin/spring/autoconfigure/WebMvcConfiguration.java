package com.zongjin.spring.autoconfigure;

import com.zongjin.interceptor.TyInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 * Spring MVC配置类 等同于以前xml配置方式
 *
 * @Author Tommy
 * @Date 2021/11/10
 */
@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {

    // 默认语言区域
    @Value("${spring.web.locale}")
    private String defaultLocale;

    private final String lang = "lang"; // 语言的Cookie标识

    /**
     * 配置I18N国际化解析器
     */
    @Bean
    public LocaleResolver localeResolver() {
        log.info("基于Cookie::" + lang + "国际化解析器初始化完毕！");

        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieName(lang);
        resolver.setCookiePath("/");
        resolver.setDefaultLocale(StringUtils.parseLocale(defaultLocale));
        return resolver;
    }

    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器 拦截规则
        // 多个拦截器，依次添加，拦截顺序同添加顺序
        registry.addInterceptor(tyInterceptor()).addPathPatterns("/generate");
    }

    /**
     * TyInterceptor实例化拦截器
     */
    @Bean
    public TyInterceptor tyInterceptor() {
        return new TyInterceptor();
    }
}
