package com.teemor.service.sys.impl;

import com.teemor.repository.sys.SysUserPostRepository;
import com.teemor.service.sys.SysUserPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户与岗位关联表(SysUserPost)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:16
 */
@Service
public class SysUserPostServiceImpl implements SysUserPostService {
    /**
     * Repository对象
     */
    @Resource
    private SysUserPostRepository sysUserPostRepository;
}

