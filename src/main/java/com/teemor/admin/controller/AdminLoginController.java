package com.teemor.admin.controller;

import com.teemor.admin.request.AdminLoginReq;
import com.teemor.admin.resp.AdminLoginResp;
import com.teemor.admin.resp.CaptchaResp;
import com.teemor.admin.resp.SysUserInfoResp;
import com.teemor.core.model.RestResponseBody;
import com.teemor.core.security.AuthContextHolder;
import com.teemor.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author lujing
 * @date 2023/12/18 15:02
 */
@RestController
@RequestMapping("/api/v1/admin")
@Api(tags = "管理员登录相关接口")
public class AdminLoginController {

    @Autowired
    private SysUserService sysUserService;


    @PostMapping("/login")
    @ApiOperation(value = "管理员登录")
    public RestResponseBody<AdminLoginResp> login(@Valid @RequestBody AdminLoginReq req) {

        AdminLoginResp resp = sysUserService.login(req);
        return RestResponseBody.success(resp);
    }


    @GetMapping("/captchaImage")
    @ApiOperation(value = "获取验证码")
    public RestResponseBody<CaptchaResp> getCaptcha(
            @ApiParam @RequestParam(required = false, defaultValue = "200") Integer width,
            @ApiParam @RequestParam(required = false, defaultValue = "45") Integer height

    ) {
        CaptchaResp resp = sysUserService.getCaptcha(width, height);
        return RestResponseBody.success(resp);
    }


    @GetMapping("/getInfo")
    @ApiOperation(value = "获取用户信息")
    public RestResponseBody<SysUserInfoResp> getInfo(
            @RequestAttribute(AuthContextHolder.USER_ID_ATTR) String userId
    ) {
        SysUserInfoResp resp = sysUserService.getInfo(userId);
        return RestResponseBody.success(resp);
    }

}
