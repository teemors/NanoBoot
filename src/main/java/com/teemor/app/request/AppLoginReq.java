package com.teemor.app.request;

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
public class AppLoginReq {


    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
    private String account;


    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;


}
