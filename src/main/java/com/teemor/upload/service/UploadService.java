package com.teemor.upload.service;

import cn.hutool.core.collection.CollUtil;
import com.aliyun.oss.ServiceException;
import com.teemor.upload.pojo.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件上传服务
 *
 * @author : lujing
 * @since :  2019/11/13 12:42
 */
public interface UploadService {


    /**
     * 单文件上传
     *
     * @param file
     * @return
     */
    FileUploadResponse upload(MultipartFile file);


    /**
     * 单文件上传
     *
     * @param inputStream
     * @param fileName
     * @return
     */
    FileUploadResponse upload(File inputStream, String fileName, Long size);

    /**
     * 多文件上传 默认实现
     *
     * @param files 文件列表
     * @returns
     */
    default List<FileUploadResponse> uploads(List<MultipartFile> files) {
        // todo 更改为多线程执行 失败后全部文件回滚删除
        if (!CollUtil.isEmpty(files)) {
            List<FileUploadResponse> keys = new ArrayList<>();
            files.forEach(file -> keys.add(upload(file)));
            return keys;
        } else {
            throw new ServiceException("没有文件可以上传");
        }

    }

    /**
     * 文件直传
     *
     * @return
     */
    Map getConfig();


    /**
     * 获取sts上传token
     */
    Map getSTSConfig();

    /**
     * 复制文件
     *
     * @param sourceKey 原始key
     * @param destKey   目标key
     */
    boolean copy(String sourceKey, String destKey);


    /**
     * 删除文件
     *
     * @param key
     */
    boolean delete(String key);


    /**
     * 文件是否存在
     *
     * @param key
     */
    boolean exist(String key);


    /**
     * 获取文件公共访问路径路径
     *
     * @param key
     * @return
     */
    String getUrl(String key);


    /**
     * 获取签名访问路径
     *
     * @param key
     * @return
     */
    String getePresignedUrl(String key);


    /**
     * 小程序文件上传
     *
     * @param path
     * @param file
     * @return
     */
    FileUploadResponse appletUpload(String path, MultipartFile file);


}
