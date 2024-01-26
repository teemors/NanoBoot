package com.teemor.service.sys.impl;

import com.teemor.repository.sys.SysPostRepository;
import com.teemor.service.sys.SysPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 岗位信息表(SysPost)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:16
 */
@Service
public class SysPostServiceImpl implements SysPostService {
    /**
     * Repository对象
     */
    @Resource
    private SysPostRepository sysPostRepository;
}

