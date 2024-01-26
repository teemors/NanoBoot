package com.teemor.app.controller;

import com.teemor.app.request.AppLoginReq;
import com.teemor.core.model.RestResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lujing
 * @date 2023/12/18 15:02
 */
@RestController
@RequestMapping("/api/v1/app")
@Api(tags = "APP登录相关接口")
public class AppLoginController {


    @PostMapping("/login")
    @ApiOperation(value = "管理员登录")
    public RestResponseBody<String> login(@Valid @RequestBody AppLoginReq req) {


        return RestResponseBody.success("resp");
    }


}
