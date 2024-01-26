package com.teemor.upload.util;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 上传工具类
 *
 * @author
 * @date 2019/5/5 14:15
 */
public final class UploadUtil {

    /**
     * 获取文件后缀
     *
     * @param file
     * @return
     */
    public static String getFileSuffix(File file) {
        return getFileSuffix(file.getName());
    }

    /**
     * 获取文件后缀
     *
     * @param file
     * @return
     */
    public static String getFileSuffix(MultipartFile file) {
        return getFileSuffix(file.getOriginalFilename());
    }

    /**
     * 获取文件后缀
     *
     * @param filename 文件名称
     * @return
     */
    public static String getFileSuffix(String filename) {
        if (StrUtil.isBlank(filename)) {
            return filename;
        } else {
            int indexOf = filename.lastIndexOf(".");
            if (indexOf >= 0) {
                return filename.substring(indexOf);
            } else {
                return filename;
            }
        }
    }
}
