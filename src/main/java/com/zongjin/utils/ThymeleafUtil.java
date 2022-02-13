package com.zongjin.utils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.dialect.SpringStandardDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Thymeleaf工具类
 *
 * <p>
 * 用于本地化执行模板 <br/>
 * 参考：https://gitee.com/jonathanzyf/thymeleaf-example
 * </p>
 *
 * @Author Tommy
 * @Date 2021/11/15
 */
public final class ThymeleafUtil {

    /**
     * 模板引擎
     */
    private static final TemplateEngine ENGINE;

    static {
        // 初始化模板解析器并配置参数
        final ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();

        // 关闭thymeleaf缓存
        resolver.setCacheable(false);

        // Mode
        resolver.setTemplateMode(TemplateMode.TEXT);

        // 模板默认编码
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());

        // 模板默认后缀
        resolver.setSuffix(".th");

        // 指定模板从何处加载：相对于当前classloader的classpath
        resolver.setPrefix("/repository/templateGroups/");

        // 初始化模板引擎
        ENGINE = new TemplateEngine();
        ENGINE.setTemplateResolver(resolver);
        ENGINE.setDialect(new SpringStandardDialect()); // 指定方言，默认为OGNL，此处设置为SpringEL
    }

    /**
     * 解析模板
     *
     * @param templateName 模板名称
     * @param dataModel 模板数据
     * @return 返回解析后的内容
     */
    public static String resolveTemplate(String templateName, Map<String, Object> dataModel) {

        // 构造上下文
        Context ctx = new Context(null, dataModel);

        // 渲染模板：直接输出内容
        String result = ENGINE.process(templateName, ctx);
        return result;
    }
}
