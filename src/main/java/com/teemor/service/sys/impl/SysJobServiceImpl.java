package com.teemor.service.sys.impl;

import com.teemor.repository.sys.SysJobRepository;
import com.teemor.service.sys.SysJobService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 定时任务调度表(SysJob)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:15
 */
@Service
public class SysJobServiceImpl implements SysJobService {
    /**
     * Repository对象
     */
    @Resource
    private SysJobRepository sysJobRepository;
}

