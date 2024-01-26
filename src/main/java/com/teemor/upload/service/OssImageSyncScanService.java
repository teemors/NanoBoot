package com.teemor.upload.service;

import java.util.List;

/**
 * 阿里云图片安全检测
 *
 * @author lxs
 */
public interface OssImageSyncScanService {

    /**
     * oss图片安全检测
     *
     * @param graphUrlList oss图片地址
     * @return 通过安全检测的图片地址
     */
    List<String> ossCheckImage(List<String> graphUrlList);
}
