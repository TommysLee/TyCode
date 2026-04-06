package com.zongjin.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zongjin.entity.TemplateFileDesc;
import com.zongjin.entity.TemplateGroupDesc;
import com.zongjin.service.ITemplateGroupDescService;
import com.zongjin.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.zongjin.constant.Ty.TEMPLATE_GROUP_MODULE;
import static com.zongjin.constant.Ty.TEMPLATE_GROUP_PATH;

/**
 * 模板组描述文件业务逻辑层
 *
 * @Author Tommy
 * @Date 2021/12/7
 */
@Service
@Slf4j
public class TemplateGroupDescServiceImpl implements ITemplateGroupDescService {

    private final Map<String, TemplateGroupDesc> tgDescMap = Maps.newLinkedHashMap();

    /**
     * 获取模板组描述文件列表
     *
     * @return List<TemplateGroupDesc>
     */
    @Override
    public List<TemplateGroupDesc> getList() throws Exception {
        if (tgDescMap.isEmpty()) {
            Yaml yaml = new Yaml();

            // 定义模板组描述文件的Pattern
            final String pattern = ResourceLoader.CLASSPATH_URL_PREFIX + TEMPLATE_GROUP_PATH + "/*/readme.yml";

            // 获取所有模板组
            final Map<String, InputStream> templates = FileUtil.getInputstreamByPattern(pattern);
            for (Map.Entry<String, InputStream> entry : templates.entrySet()) { // 构建模板组对象
                TemplateGroupDesc tg = yaml.loadAs(entry.getValue(), TemplateGroupDesc.class);
                tg.setId(entry.getKey());

                for (TemplateFileDesc tf : tg.getFileDescList()) { // 设置模板文件ClassPath路径
                    tf.setPath(StringUtils.joinWith("/", tg.getId(), TEMPLATE_GROUP_MODULE, FilenameUtils.getBaseName(tf.getName())));
                }
                System.out.println(tg);
                System.out.println("-----------------------------");
                tgDescMap.put(tg.getId(), tg);
            }
            log.info("将 模板组描述信息 加入缓存，条数：" + tgDescMap.size());
        } else {
            log.info("从缓存中加载 模板组描述信息，已加载条数：" + tgDescMap.size());
        }
        List<TemplateGroupDesc> tgList = Lists.newArrayList(tgDescMap.values());
        tgList.sort(Comparator.comparingInt(TemplateGroupDesc::getOrder));
        return tgList;
    }

    /**
     * 通过ID获取模板组详情
     *
     * @param id
     * @return TemplateGroupDesc
     */
    @Override
    public TemplateGroupDesc getById(String id) throws Exception {
        if (tgDescMap.isEmpty()) {
            getList();
        }
        return tgDescMap.get(id);
    }

    /**
     * 清除缓存的模板组描述文件列表
     */
    @Override
    public void clear() {
        tgDescMap.clear();
        log.info("已清除缓存的全部模板组描述信息.");
    }
}
