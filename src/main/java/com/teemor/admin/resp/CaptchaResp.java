package com.teemor.admin.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lujing
 * @since 2024/1/15 16:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaptchaResp {

    @ApiModelProperty(value = "验证码key")
    private String key;

    @ApiModelProperty(value = "验证码图片")
    private String imageBase64;


    @ApiModelProperty(value = "验证码过期时间")
    private Integer expire;
}
