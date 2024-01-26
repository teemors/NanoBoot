package com.teemor.service.sys.impl;

import com.teemor.repository.sys.SysRoleMenuRepository;
import com.teemor.service.sys.SysRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:16
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    /**
     * Repository对象
     */
    @Resource
    private SysRoleMenuRepository sysRoleMenuRepository;
}

