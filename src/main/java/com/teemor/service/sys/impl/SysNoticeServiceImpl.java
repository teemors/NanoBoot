package com.teemor.service.sys.impl;

import com.teemor.repository.sys.SysNoticeRepository;
import com.teemor.service.sys.SysNoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 通知公告表(SysNotice)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:15
 */
@Service
public class SysNoticeServiceImpl implements SysNoticeService {
    /**
     * Repository对象
     */
    @Resource
    private SysNoticeRepository sysNoticeRepository;
}

