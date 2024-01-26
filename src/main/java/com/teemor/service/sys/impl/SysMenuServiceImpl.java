package com.teemor.service.sys.impl;

import com.teemor.repository.sys.SysMenuRepository;
import com.teemor.service.sys.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 菜单权限表(SysMenu)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:15
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    /**
     * Repository对象
     */
    @Resource
    private SysMenuRepository sysMenuRepository;
}

