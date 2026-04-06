package com.zongjin.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 文件工具类
 *
 * @Author Tommy
 * @Date 2021/12/7
 */
@Slf4j
public class FileUtil {

    /**
     * 获取指定路径下的所有文件夹
     *
     * @param path 路径
     * @return List<File>
     */
    public static List<File> getDirectories(String path) {

        List<File> dirList = Lists.newArrayList();
        if (StringUtils.isNotBlank(path)) {
            final File directory = new File(path);
            if (directory.exists() && directory.isDirectory()) {
                FileFilter dirFilter = DirectoryFileFilter.INSTANCE;
                dirList = Lists.newArrayList(directory.listFiles(dirFilter));
            }
        }
        return dirList;
    }

    /**
     * 获取指定目录下的特定文件（非递归）
     *
     * @param dirList  文件夹列表
     * @param fileName 要查找的文件名称
     * @return Map<String, File>
     */
    public static Map<String, File> getFileBySpecDirectories(List<File> dirList, String fileName) {

        Map<String, File> filesMap = Maps.newLinkedHashMap();
        for (File dir : dirList) {
            File file = new File(dir.getPath() + File.separator + fileName);
            if (file.exists()) {
                filesMap.put(dir.getName(), file);
            }
        }
        return filesMap;
    }

    /**
     * 获取匹配搜索模式的文件流
     *
     * @param pattern 搜索模式
     * @return Map<String, InputStream>
     */
    public static Map<String, InputStream> getInputstreamByPattern(String pattern) {
        Map<String, InputStream> streamMap = Maps.newHashMap();
        if (StringUtils.isNotBlank(pattern)) {
            try {
                // 1. 创建解析器
                PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();

                // 2. 获取所有匹配的资源
                Resource[] resources = patternResolver.getResources(pattern);
                log.info("搜索到资源数：" + resources.length);

                // 3. 处理资源
                for (Resource resource : resources) {
                    System.out.println(resource.getURL());
                    System.out.print("exists：" + resource.exists() +"\t");
                    System.out.print("isReadable：" + resource.isReadable() +"\t");
                    System.out.println("isFile：" + resource.isFile());

                    if (resource.exists() && resource.isReadable()) {
                        String dirName = getParentPathName(resource.getURL().getPath());
                        streamMap.put(dirName, resource.getInputStream());
                    }
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return streamMap;
    }

    /**
     * 获取文件流
     *
     * @param relativePath 相对于Root的路径
     * @return InputStream
     */
    public static InputStream getFileInputStream(String relativePath) {

        if (StringUtils.isNotBlank(relativePath)) {
            return Thread.currentThread().getContextClassLoader().getResourceAsStream(relativePath);
        }
        return null;
    }

    /**
     * 获取绝对路径
     *
     * @param relativePath 相对于Root的路径
     * @return String
     */
    public static String getAbsolutePath(String relativePath) {

        if (StringUtils.isNotBlank(relativePath)) {
            return getRoot() + (relativePath.startsWith("/") ? relativePath.substring(1) : relativePath);
        }
        return relativePath;
    }

    /**
     * 获取根路径
     *
     * @return String
     */
    public static String getRoot() {
        return Thread.currentThread().getContextClassLoader().getResource(StringUtils.EMPTY).getPath();
    }

    /**
     * 获取指定路径的父路径名称
     *
     * @param path 路径
     * @return String
     */
    public static String getParentPathName(String path) {
        String parentPath = FilenameUtils.getPathNoEndSeparator(path);
        if (Objects.nonNull(parentPath)) {
            return FilenameUtils.getName(parentPath);
        }
        return null;
    }
}
