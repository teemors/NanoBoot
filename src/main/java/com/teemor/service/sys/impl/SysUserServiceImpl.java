package com.teemor.service.sys.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.math.Calculator;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.teemor.admin.request.AdminLoginReq;
import com.teemor.admin.resp.AdminLoginResp;
import com.teemor.admin.resp.CaptchaResp;
import com.teemor.admin.resp.SysUserInfoResp;
import com.teemor.admin.resp.SysUserResp;
import com.teemor.core.constant.UserType;
import com.teemor.core.exception.InvalidParameterException;
import com.teemor.core.exception.ResourceNotFoundException;
import com.teemor.core.security.JwtTokenUtil;
import com.teemor.entity.sys.SysUser;
import com.teemor.repository.sys.SysRoleMenuRepository;
import com.teemor.repository.sys.SysUserRepository;
import com.teemor.repository.sys.SysUserRoleRepository;
import com.teemor.service.CacheService;
import com.teemor.service.sys.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author easycode
 * @since 2024-01-15 16:56:45
 */
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {
    /**
     * Repository对象
     */
    @Resource
    private SysUserRepository sysUserRepository;


    @Resource
    private CacheService cacheService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private SysUserRoleRepository sysUserRoleRepository;

    @Resource
    private SysRoleMenuRepository sysRoleMenuRepository;


    /**
     * 获取验证码
     *
     * @param width  宽度
     * @param height 高度
     * @return {@link CaptchaResp}
     */
    @Override
    public CaptchaResp getCaptcha(Integer width, Integer height) {

        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(width, height, 2, 4);
        captcha.setGenerator(new MathGenerator(1));
        captcha.createCode();

        int calculateResult = (int) Calculator.conversion(captcha.getCode());


        String key = UUID.fastUUID().toString();
        cacheService.cacheCaptcha(key, String.valueOf(calculateResult));

        log.debug("key: {}, code: {}, calculateResult: {}", key, captcha.getCode(), calculateResult);

        return CaptchaResp.builder()
                .key(key)
                .imageBase64(captcha.getImageBase64Data())
                .expire(120)
                .build();
    }

    /**
     * 管理员登录
     *
     * @param req {@link AdminLoginReq}
     * @return {@link AdminLoginResp}
     */
    @Override
    public AdminLoginResp login(AdminLoginReq req) {
        SysUser one = sysUserRepository.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUserName, req.getUsername()), false);
        if (one == null) {
            throw new InvalidParameterException("用户名不存在");
        }
        if (!BCrypt.checkpw(req.getPassword(), one.getPassword())) {
            throw new InvalidParameterException("密码错误");
        }
        String captcha = cacheService.getCaptcha(req.getKey());
        if (captcha == null) {
            throw new InvalidParameterException("验证码已过期");
        }
        if (!req.getCode().equals(captcha)) {
            throw new InvalidParameterException("验证码错误");
        }


        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtTokenUtil.USER_ID, String.valueOf(one.getUserId()));
        claims.put(JwtTokenUtil.USER_TYPE, UserType.ADMIN);

        String token = jwtTokenUtil.generateToken(claims);

        Set<String> roles = sysUserRoleRepository.selectRoleListByUserId(one.getUserId());
        Set<String> permissions = sysRoleMenuRepository.selectMenuListByUserId(roles);

        AdminLoginResp resp = AdminLoginResp.builder()
                .id(one.getUserId())
                .avatar(one.getAvatar())
                .username(one.getUserName())
                .token(token)
                .introduction(one.getRemark())
                .lastLoginTime(one.getLoginDate())
                .roles(roles)
                .permissions(permissions)
                .build();

        cacheService.cacheUserAuthInfo(resp);
        cacheService.removeCaptcha(req.getKey());
        return resp;
    }

    /**
     * 获取用户信息
     *
     * @return {@link CaptchaResp}
     */
    @Override
    public SysUserInfoResp getInfo(String userId) {
        SysUser one = sysUserRepository.getById(userId);
        if (one == null) {
            throw new ResourceNotFoundException("用户不存在");
        }

        Set<String> roles = sysUserRoleRepository.selectRoleListByUserId(one.getUserId());
        Set<String> permissions = sysRoleMenuRepository.selectMenuListByUserId(roles);

        SysUserResp sysUserResp = SysUserResp.builder()
                .userId(one.getUserId())
                .userName(one.getUserName())
                .nickName(one.getNickName())
                .build();

        return SysUserInfoResp.builder()
                .sysUser(sysUserResp)
                .roles(roles)
                .permissions(permissions)
                .build();

    }
}

