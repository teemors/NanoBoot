package com.teemor.repository.sys;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teemor.dao.sys.SysJobDao;
import com.teemor.entity.sys.SysJob;
import org.springframework.stereotype.Repository;

/**
 * 定时任务调度表(SysJob)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:15
 */
@Repository
public class SysJobRepository extends ServiceImpl<SysJobDao, SysJob> {

}

