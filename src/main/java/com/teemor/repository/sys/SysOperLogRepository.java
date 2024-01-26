package com.teemor.repository.sys;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teemor.dao.sys.SysOperLogDao;
import com.teemor.entity.sys.SysOperLog;
import org.springframework.stereotype.Repository;

/**
 * 操作日志记录(SysOperLog)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:16
 */
@Repository
public class SysOperLogRepository extends ServiceImpl<SysOperLogDao, SysOperLog> {

}

