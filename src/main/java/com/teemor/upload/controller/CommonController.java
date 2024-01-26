package com.teemor.upload.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lujing
 * @since 2024/1/16 16:03
 */
@RestController
@RequestMapping("api/v1/common")
@Api(tags = "公共接口")
public class CommonController {

    @GetMapping("/test")
    @ApiOperation(value = "测试")
    public void test() {
        System.out.println("test");
    }
}
