package com.zongjin.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 文件工具类
 *
 * @Author Tommy
 * @Date 2021/12/7
 */
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
}
