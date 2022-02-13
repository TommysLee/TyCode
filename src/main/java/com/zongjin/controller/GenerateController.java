package com.zongjin.controller;

import com.zongjin.entity.DataSourceDesc;
import com.zongjin.entity.Mapping;
import com.zongjin.entity.Requirement;
import com.zongjin.entity.Result;
import com.zongjin.entity.TemplateGroupDesc;
import com.zongjin.service.GenCodeService;
import com.zongjin.service.IDataSourceDescService;
import com.zongjin.service.ITemplateGroupDescService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 代码生成控制器
 *
 * @Author Tommy
 * @Date 2021/11/21
 */
@Controller
@Slf4j
public class GenerateController {

    @Autowired
    private IDataSourceDescService dataSourceDescService;

    @Autowired
    private ITemplateGroupDescService templateGroupDescService;

    @Autowired
    private GenCodeService genCodeService;

    /**
     * 代码生成
     */
    @PostMapping("/generate")
    public String generate(Requirement requirement, Model model) throws Exception {

        log.info("代码生成中... " + requirement);

        // 获取表字段定义元数据
        final List<Mapping> mappings = genCodeService.getMappingData(requirement);

        // 生成各组件源代码
        final List<Result> resultList = genCodeService.generateSources(requirement, mappings);
        model.addAttribute("resultList", resultList);
        model.addAttribute("clazzName", requirement.getClazzName());
        return "result";
    }

    /**
     * 获取配置的数据源
     */
    @GetMapping("/datasources")
    @ResponseBody
    public List<DataSourceDesc> datasources() {
        return dataSourceDescService.getList();
    }

    /**
     * 获取已装载的模板组
     */
    @GetMapping("/templategroups")
    @ResponseBody
    public List<TemplateGroupDesc> templateGroups() throws Exception {
        return templateGroupDescService.getList();
    }

    /**
     * 清除缓存
     */
    @GetMapping("/cls")
    @ResponseBody
    public String clear() {

        dataSourceDescService.clear();
        templateGroupDescService.clear();
        return "Clear Success";
    }
}
