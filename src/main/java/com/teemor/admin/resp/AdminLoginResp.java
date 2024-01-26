package com.teemor.admin.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author lujing
 * @date 2023/12/18 15:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminLoginResp {

    private Long id;

    private String username;

    private String token;

    private String avatar;

    private String introduction;


    private Set<String> roles;

    private Set<String> permissions;


    private LocalDateTime lastLoginTime;

}
