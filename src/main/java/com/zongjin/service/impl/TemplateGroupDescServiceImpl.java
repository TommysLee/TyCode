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
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import static com.zongjin.constant.Ty.*;

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

        if (tgDescMap.size() < 1) {
            Yaml yaml = new Yaml();
            // 获取所有模板组
            final Map<String, File> templates = FileUtil.getFileBySpecDirectories(FileUtil.getDirectories(FileUtil.getAbsolutePath(TEMPLATE_GROUP_PATH)), TEMPLATE_GROUP_FILE);
            for (Map.Entry<String, File> entry : templates.entrySet()) { // 构建模板组对象
                TemplateGroupDesc tg = yaml.loadAs(new FileInputStream(entry.getValue()), TemplateGroupDesc.class);
                tg.setId(entry.getKey());
                tg.setPath(entry.getValue().getParent());

                for (TemplateFileDesc tf : tg.getFileDescList()) { // 设置模板文件相对路径
                    tf.setPath(StringUtils.joinWith(File.separator, tg.getId(), TEMPLATE_GROUP_MODULE, FilenameUtils.getBaseName(tf.getName())));
                }
                tgDescMap.put(entry.getKey(), tg);
            }
            log.info("将 模板组描述信息 加入缓存，条数：" + tgDescMap.size());
        } else {
            log.info("从缓存中加载 模板组描述信息，已加载条数：" + tgDescMap.size());
        }
        return Lists.newArrayList(tgDescMap.values());
    }

    /**
     * 通过ID获取模板组详情
     *
     * @param id
     * @return TemplateGroupDesc
     */
    @Override
    public TemplateGroupDesc getById(String id) throws Exception {

        if (tgDescMap.size() < 1) {
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
