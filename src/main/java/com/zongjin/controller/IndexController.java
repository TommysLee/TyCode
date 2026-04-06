package com.zongjin.controller;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 默认首页
 *
 * @Author Tommy
 * @Date 2021/11/10
 */
@Controller
@Slf4j
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("msg", "感谢选择 TyCode 助您Coding提速!!");
        model.addAttribute("data", dataMap);
        return "index";
    }

    @GetMapping("/printsys")
    @ResponseBody
    public String printsys() {
        String lineBreak = "<br/>";
        StringBuilder msgBuilder = new StringBuilder("Classpath下面的所有文件与目录");
        msgBuilder.append(lineBreak);

        // 1. 创建解析器
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();

        // 2. 定义搜索模式：classpath根目录下的所有资源
        String pattern = ResourceLoader.CLASSPATH_URL_PREFIX + "/**";

        try {
            // 3. 获取所有匹配的资源
            Resource[] resources = patternResolver.getResources(pattern);

            // 4. 处理资源
            for (Resource resource : resources) {
                msgBuilder.append("资源URL: ").append(resource.getURL()).append(lineBreak);
                msgBuilder.append("是否存在: ").append(resource.exists()).append(lineBreak);
                msgBuilder.append("文件名: ").append(resource.getFilename()).append(lineBreak);
                msgBuilder.append("描述: ").append(resource.getDescription()).append(lineBreak);
                if (resource.exists() && resource.isFile() && resource.isReadable()) {
                    msgBuilder.append("大小：").append(resource.getInputStream().available()).append(lineBreak);
                }
                msgBuilder.append("---------------------------------").append(lineBreak);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            msgBuilder.append("发生异常，请查看后台日志。").append(e.getMessage()).append(lineBreak);
        }
        return msgBuilder.toString();
    }
}
