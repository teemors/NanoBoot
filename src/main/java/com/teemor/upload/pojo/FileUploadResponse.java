package com.teemor.upload.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * 文件上传基础响应
 *
 * @author lujing
 * @date 2019/5/5
 */

@Builder
@Data
public class FileUploadResponse {


    private String key;


    private String url;


    private String name;


    private long size;


}
