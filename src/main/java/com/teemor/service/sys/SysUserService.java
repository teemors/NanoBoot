package com.teemor.service.sys;

import com.teemor.admin.request.AdminLoginReq;
import com.teemor.admin.resp.AdminLoginResp;
import com.teemor.admin.resp.CaptchaResp;
import com.teemor.admin.resp.SysUserInfoResp;

/**
 * 用户信息表(SysUser)表服务接口
 *
 * @author easycode
 * @since 2024-01-15 16:56:45
 */
public interface SysUserService {


    /**
     * 获取验证码
     *
     * @param width  宽度
     * @param height 高度
     * @return {@link CaptchaResp}
     */
    CaptchaResp getCaptcha(Integer width, Integer height);

    /**
     * 管理员登录
     *
     * @param req {@link AdminLoginReq}
     * @return {@link AdminLoginResp}
     */
    AdminLoginResp login(AdminLoginReq req);

    /**
     * 获取用户信息
     *
     * @return {@link CaptchaResp}
     */
    SysUserInfoResp getInfo(String userId);

}

