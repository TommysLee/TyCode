package com.zongjin.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zongjin.entity.DataSourceDesc;
import com.zongjin.service.IDataSourceDescService;
import com.zongjin.utils.DBHelper;
import com.zongjin.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;

import static com.zongjin.constant.Ty.JDBC_FILE_PATH;

/**
 * 数据源描述信息业务逻辑层
 *
 * @Author Tommy
 * @Date 2021/12/7
 */
@Service
@Slf4j
public class DataSourceDescServiceImpl implements IDataSourceDescService {

    private final Map<String, DataSourceDesc> dsDescMap = Maps.newLinkedHashMap();

    /**
     * 获取数据源描述列表
     *
     * @return Map<String, DataSourceDesc>
     */
    @Override
    public List<DataSourceDesc> getList() {

        if (dsDescMap.size() < 1) {
            Yaml yaml = new Yaml();
            Map<String, Object> dsMap = yaml.load(FileUtil.getFileInputStream(JDBC_FILE_PATH)); // 读取YML文件
            for (Map.Entry<String, Object> entry : dsMap.entrySet()) { // 将YML文本转换为实体类对象
                String ymlText = yaml.dump(entry.getValue());
                DataSourceDesc desc = yaml.loadAs(ymlText, DataSourceDesc.class);
                desc.setId(entry.getKey());
                desc.setDbType(DBHelper.getDBType(desc.getUrl()));
                dsDescMap.put(entry.getKey(), desc);
            }
            log.info("将 数据源描述信息 加入缓存，条数：" + dsDescMap.size());
        } else {
            log.info("从缓存中加载 数据源描述信息，已加载条数：" + dsDescMap.size());
        }
        return Lists.newArrayList(dsDescMap.values());
    }

    /**
     * 根据ID获取数据源描述详情
     *
     * @param id
     * @return DataSourceDesc
     */
    @Override
    public DataSourceDesc getById(String id) {

        if (dsDescMap.size() < 1) {
            getList();
        }
        return dsDescMap.get(id);
    }

    /**
     * 清除缓存的数据源描述列表
     */
    @Override
    public void clear() {
        dsDescMap.clear();
        log.info("已清除缓存的全部数据源描述信息.");
    }
}
