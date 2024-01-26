package com.teemor.service.sys.impl;

import com.teemor.repository.sys.SysRoleDeptRepository;
import com.teemor.service.sys.SysRoleDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色和部门关联表(SysRoleDept)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:16
 */
@Service
public class SysRoleDeptServiceImpl implements SysRoleDeptService {
    /**
     * Repository对象
     */
    @Resource
    private SysRoleDeptRepository sysRoleDeptRepository;
}

