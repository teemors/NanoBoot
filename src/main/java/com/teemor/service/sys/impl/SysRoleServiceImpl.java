package com.teemor.service.sys.impl;

import com.teemor.repository.sys.SysRoleRepository;
import com.teemor.service.sys.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色信息表(SysRole)表服务实现类
 *
 * @author easycode
 * @since 2024-01-15 15:37:10
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    /**
     * Repository对象
     */
    @Resource
    private SysRoleRepository sysRoleRepository;
}

