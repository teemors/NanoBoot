package com.teemor.admin.resp;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统用户响应体
 *
 * @author lujing
 * @since 2024/1/16 9:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("系统用户信息")
public class SysUserResp {

    /**
     * 用户ID
     */
    private Long userId;


    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;
}
