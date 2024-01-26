package com.teemor.upload.config;


import com.teemor.upload.controller.AliUploadController;
import com.teemor.upload.service.UploadService;
import com.teemor.upload.service.impl.AliUploadServiceImpl;
import org.springframework.context.annotation.Bean;

/**
 * 文件上传自动配置类
 *
 * @author lujing
 * @date 2019-11-13 15:40
 */
public class UploadAutoConfig {

    /**
     * 阿里OSS 文件上传服务
     *
     * @return
     */
    @Bean("aliUploadService")
    public UploadService aliUploadService() {
        return new AliUploadServiceImpl();
    }

    /**
     * 阿里OSS 文件上传接口
     *
     * @return
     */
    @Bean
    public AliUploadController aliUploadController() {
        return new AliUploadController();
    }


}
