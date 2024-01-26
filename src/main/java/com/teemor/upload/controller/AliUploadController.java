package com.teemor.upload.controller;


import com.teemor.core.model.RestResponseBody;
import com.teemor.upload.pojo.FileUploadResponse;
import com.teemor.upload.service.UploadService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ author
 */
@RequestMapping("/ali/upload")
@ResponseBody
public class AliUploadController {

    @Autowired
    private UploadService uploadService;


    /**
     * 阿里云批量上传文件
     *
     * @param files
     * @return
     */
    @PostMapping(value = "/files", headers = "content-type=multipart/form-data")
    public RestResponseBody<List<FileUploadResponse>> uploads(@RequestParam(required = false, value = "file") List<MultipartFile> files) {

        return RestResponseBody.success(uploadService.uploads(files));
    }


    /**
     * 阿里云单上传文件
     *
     * @param file
     * @return
     */
    @ApiOperation(value = "阿里云单上传文件")
    @PostMapping(value = "/file", headers = "content-type=multipart/form-data")
    public RestResponseBody<FileUploadResponse> upload(@RequestParam(required = false, value = "file") MultipartFile file) {
        return RestResponseBody.success(uploadService.upload(file));
    }

    /**
     * 阿里云单上传文件(小程序)
     *
     * @param file
     * @return
     */
    @ApiOperation(value = "阿里云单上传文件(小程序)")
    @PostMapping(value = "/applet/file", headers = "content-type=multipart/form-data")
    public RestResponseBody<FileUploadResponse> appletUpload(@RequestParam @ApiParam("项目OSS路径") String path, @RequestParam(required = false, value = "file") MultipartFile file) {
        return RestResponseBody.success(uploadService.appletUpload(path, file));
    }

    /**
     * 阿里云直传配置
     *
     * @return
     */
    @ApiOperation(value = "阿里云直传配置")
    @GetMapping(value = "/getconfig")
    public RestResponseBody<Map> getConfig() {
        return RestResponseBody.success(uploadService.getConfig());
    }


    /**
     * 阿里云STS直传配置
     *
     * @return
     */
    @GetMapping(value = "/sts/getconfig")
    public RestResponseBody<Map> getSTSConfig() {
        return RestResponseBody.success(uploadService.getSTSConfig());
    }

    /**
     * 获取签名访问链接
     *
     * @return
     */
    @GetMapping(value = "/getUrl")
    public RestResponseBody<String> getUrl(@RequestParam String key) {
        return RestResponseBody.success(uploadService.getePresignedUrl(key));
    }


}
