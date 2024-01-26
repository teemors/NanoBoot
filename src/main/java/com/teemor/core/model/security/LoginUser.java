package com.teemor.core.model.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 当前用户
 *
 * @author lujing
 * @since 2024/1/16 9:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUser {

    private Long id;

}
