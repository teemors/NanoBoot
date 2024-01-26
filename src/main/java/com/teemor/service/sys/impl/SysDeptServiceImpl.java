package com.teemor.service.sys.impl;

import com.teemor.repository.sys.SysDeptRepository;
import com.teemor.service.sys.SysDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 部门表(SysDept)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:15
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {
    /**
     * Repository对象
     */
    @Resource
    private SysDeptRepository sysDeptRepository;
}

