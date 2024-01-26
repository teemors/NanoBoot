package com.teemor.admin.resp;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author lujing
 * @since 2024/1/16 9:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("用户信息")
public class SysUserInfoResp {


    private SysUserResp sysUser;

    private Set<String> roles;

    private Set<String> permissions;
}
