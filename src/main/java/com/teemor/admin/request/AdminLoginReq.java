package com.teemor.admin.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author lujing
 * @since 2023/12/18 15:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminLoginReq {


    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;


    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;


    /**
     * 验证码
     */
    @ApiModelProperty("验证码")
    @NotBlank(message = "验证码不能为空")
    private String code;


    /**
     * 唯一标识
     */
    @ApiModelProperty("验证码key")
    @NotBlank(message = "验证码key不能为空")
    private String key;


}
