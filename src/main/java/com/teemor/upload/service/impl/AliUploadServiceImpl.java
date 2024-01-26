package com.teemor.upload.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.ServiceException;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.teemor.upload.pojo.FileUploadResponse;
import com.teemor.upload.service.UploadService;
import com.teemor.upload.util.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 阿里云上传
 *
 * @author
 * @date 2019/5/5 14:15
 */
@Slf4j
public class AliUploadServiceImpl implements UploadService {

    @Value("${htwz.upload.aliyun.accessKeySecret}")
    private String accessKeySecret;


    @Value("${htwz.upload.aliyun.accessKeyId}")
    private String accessKeyId;


    @Value("${htwz.upload.aliyun.endPoint}")
    private String endPoint;


    @Value("${htwz.upload.aliyun.bucketName}")
    private String bucketName;


    @Value("${htwz.upload.aliyun.region}")
    public String region;


    @Value("${htwz.upload.aliyun.domain}")
    public String domain;


    @Value("${htwz.upload.aliyun.roleArn}")
    public String roleArn;


    @Value("${htwz.upload.aliyun.roleSessionName}")
    public String roleSessionName;


    /**
     * 全局控制器
     */
    private OSSClient ossClient;


    /**
     * Aliyun Acs Client, 用于发起 OpenAPI 请求
     */
    private IClientProfile profile;


    /**
     * 目前只有"cn-hangzhou"这个region可用, 不要使用填写其他region的值
     */
    public static final String REGION_CN_HANGZHOU = "cn-hangzhou";

    /**
     * 初始化控制器
     */
    @PostConstruct
    public void init() {
        ossClient = new OSSClient(endPoint, new DefaultCredentialProvider(accessKeyId, accessKeySecret), null);
        profile = DefaultProfile.getProfile(REGION_CN_HANGZHOU, accessKeyId, accessKeySecret);
    }


    @Override
    @Transactional
    public FileUploadResponse upload(MultipartFile file) {
        String key = "common/editor/" + IdUtil.fastSimpleUUID() + UploadUtil.getFileSuffix(file);

        try {
            ossClient.putObject(bucketName, key, file.getInputStream());
            return FileUploadResponse
                    .builder()
                    .key(key)
                    .url(getUrl(key))
                    .name(file.getOriginalFilename())
                    .size(file.getSize())
                    .build();
        } catch (Exception e) {
            // 上传失败 再次执行一次
            try {
                ossClient.putObject(bucketName, key, file.getInputStream());
                return FileUploadResponse
                        .builder()
                        .key(key)
                        .url(getUrl(key))
                        .name(file.getOriginalFilename())
                        .size(file.getSize())
                        .build();
            } catch (Exception e1) {
                throw new ServiceException("文件上传失败");
            }
        }
    }

    /**
     * 单文件上传
     *
     * @param file
     * @param fileName
     * @return
     */
    @Override
    public FileUploadResponse upload(File file, String fileName, Long size) {


        String key = IdUtil.fastSimpleUUID() + UploadUtil.getFileSuffix(fileName);
        try {
            ossClient.putObject(bucketName, key, file);
            return FileUploadResponse
                    .builder()
                    .key(key)
                    .url(getUrl(key))
                    .name(fileName)
                    .size(size)
                    .build();
        } catch (Exception e) {
            // 上传失败 再次执行一次
            try {
                ossClient.putObject(bucketName, key, file);
                return FileUploadResponse
                        .builder()
                        .key(key)
                        .url(getUrl(key))
                        .name(fileName)
                        .size(size)
                        .build();
            } catch (Exception e1) {
                throw new ServiceException("文件上传失败");
            }
        }
    }

    /**
     * 小程序文件上传
     *
     * @param path
     * @param file
     * @return
     */
    @Override
    public FileUploadResponse appletUpload(String path, MultipartFile file) {
        String key = path + "/" + IdUtil.fastSimpleUUID() + UploadUtil.getFileSuffix(file);

        try {
            ossClient.putObject(bucketName, key, file.getInputStream());
            return FileUploadResponse
                    .builder()
                    .key(key)
                    .url(getePresignedUrl(key))
                    .name(file.getOriginalFilename())
                    .size(file.getSize())
                    .build();
        } catch (Exception e) {
            // 上传失败 再次执行一次
            try {
                ossClient.putObject(bucketName, key, file.getInputStream());
                return FileUploadResponse
                        .builder()
                        .key(key)
                        .url(getePresignedUrl(key))
                        .name(file.getOriginalFilename())
                        .size(file.getSize())
                        .build();
            } catch (Exception e1) {
                throw new ServiceException("文件上传失败");
            }
        }
    }

    /**
     * 文件直传
     *
     * @return
     */
    @Override
    public Map getConfig() {

        String dir = DateUtil.format(new Date(), DatePattern.NORM_DATETIME_FORMAT) + "/" + IdUtil.fastSimpleUUID();
        String host = "https://" + bucketName + "." + endPoint;

        try {
            long expireTime = 30 * 60;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            java.sql.Date expiration = new java.sql.Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            Map<String, String> respMap = new LinkedHashMap<>();
            respMap.put("OSSAccessKeyId", accessKeyId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("key", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            return respMap;

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取sts上传token
     */
    @Override
    public Map getSTSConfig() {
        return null;
    }
    //    /**
//     * 获取sts上传token
//     */
//    @Override
//    public Map getSTSConfig() {
//        Map<String, Object> map = new HashMap<>();
//
//        try {
//            DefaultAcsClient client = new DefaultAcsClient(profile);
//            // 创建一个 AssumeRoleRequest 并设置请求参数
//            final AssumeRoleRequest request = new AssumeRoleRequest();
//            request.setMethod(MethodType.POST);
//            request.setRoleArn(roleArn);
//            request.setRoleSessionName(roleSessionName);
//            request.setDurationSeconds(3600L);
//            // 发起请求，并得到response
//            final AssumeRoleResponse response = client.getAcsResponse(request);
//
//            map.put("StatusCode", 200);
//            map.put("AccessKeyId", (response.getCredentials().getAccessKeyId()));
//            map.put("AccessKeySecret", (response.getCredentials().getAccessKeySecret()));
//            map.put("SecurityToken", response.getCredentials().getSecurityToken());
//            map.put("Expiration", response.getCredentials().getExpiration());
//            map.put("Host", "https://" + bucketName + "." + endPoint);
//            map.put("Domain", domain);
//
//
//        } catch (ClientException e) {
//            log.error("获取token失败", e);
//            map.put("StatusCode", "500");
//            map.put("ErrorCode", e.getErrCode());
//            map.put("ErrorMessage", e.getErrMsg());
//
//        }
//        return map;
//    }

    @Override
    public boolean copy(String sourceKey, String destKey) {
        ossClient.copyObject(bucketName, sourceKey, bucketName, destKey);
        return true;
    }

    @Override
    public boolean delete(String key) {
        ossClient.deleteObject(bucketName, key);
        return true;
    }

    @Override
    public boolean exist(String key) {
        try {
            return ossClient.doesObjectExist(bucketName, key);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getUrl(String key) {
        String ignoreKeyPrefix = "http";
        if (StrUtil.isBlank(key)) {
            return "";
        } else if (key.startsWith(ignoreKeyPrefix)) {
            return key;
        } else {
            return domain + "/" + key;
        }
    }


    /**
     * 获取签名访问路径
     *
     * @param key
     * @return
     */
    @Override
    public String getePresignedUrl(String key) {
        String ignoreKeyPrefix = "http";
        if (StrUtil.isBlank(key)) {
            return "";
        } else if (key.startsWith(ignoreKeyPrefix)) {
            return key;
        } else {
            Date expiration = new Date(new Date().getTime() + 3600 * 1000);
            return ossClient.generatePresignedUrl(bucketName, key, expiration).toString().replace("http://" + bucketName + "." + endPoint, domain);
        }
    }

}
