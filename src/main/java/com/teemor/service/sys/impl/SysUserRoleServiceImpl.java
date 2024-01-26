package com.teemor.service.sys.impl;

import com.teemor.repository.sys.SysUserRoleRepository;
import com.teemor.service.sys.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户和角色关联表(SysUserRole)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:16
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {
    /**
     * Repository对象
     */
    @Resource
    private SysUserRoleRepository sysUserRoleRepository;
}

