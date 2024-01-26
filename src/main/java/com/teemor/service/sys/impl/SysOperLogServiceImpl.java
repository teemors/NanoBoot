package com.teemor.service.sys.impl;

import com.teemor.repository.sys.SysOperLogRepository;
import com.teemor.service.sys.SysOperLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 操作日志记录(SysOperLog)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:16
 */
@Service
public class SysOperLogServiceImpl implements SysOperLogService {
    /**
     * Repository对象
     */
    @Resource
    private SysOperLogRepository sysOperLogRepository;
}

