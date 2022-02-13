package com.zongjin.controller;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 默认首页
 *
 * @Author Tommy
 * @Date 2021/11/10
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index(Model model) {

        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("msg", "感谢选择 Thymeleaf 作为视图渲染器!!");
        model.addAttribute("data", dataMap);

        return "index";
    }
}
