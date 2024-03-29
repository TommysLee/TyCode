package com.zongjin.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring容器工具类，方便在非spring管理环境中获取bean
 *
 * 此种实现方式更为通用化，兼顾Spring普通项目与Spring Boot项目
 *
 * @Author Tommy
 * @Date 2022/1/26
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static DefaultListableBeanFactory defaultListableBeanFactory;

    private static LocaleResolver localeResolver;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
        SpringContextHolder.defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext
                .getAutowireCapableBeanFactory();
        SpringContextHolder.localeResolver = applicationContext.getBean(LocaleResolver.class);
    }

    /**
     * 获取Spring上下文
     *
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    /**
     * 获取Message信息
     *
     * @param code
     * @return String
     */
    public static String getMessage(String code) {
        return applicationContext.getMessage(code, null, localeResolver.resolveLocale(getRequest()));
    }

    /**
     * 获取Spring管理的Bean对象
     *
     * @param name
     * @return <T> T
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 获取Spring管理的Bean对象
     *
     * @param clazz
     * @return <T> T
     */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }

    /**
     * 将Bean注册到Spring上下文
     *
     * @param beanName
     * @param bean
     */
    public static <T> void setBean(String beanName, T bean) {
        checkBeanFactory();
        defaultListableBeanFactory.registerSingleton(beanName, bean);
    }

    /**
     * 从Spring上下文移除Bean
     *
     * @param beanName
     */
    public static void removeBean(String beanName) {
        checkBeanFactory();
        defaultListableBeanFactory.removeBeanDefinition(beanName);
    }

    /**
     * 清除Spring上下文
     */
    public static void cleanApplicationContext() {
        applicationContext = null;
    }

    /**
     * 获取Http Request对象
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    /**
     * 检查ApplicationContext
     */
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }

    /**
     * 检查DefaultListableBeanFactory
     */
    private static void checkBeanFactory() {
        if (null == defaultListableBeanFactory) {
            throw new IllegalStateException("DefaultListableBeanFactory未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }
}
