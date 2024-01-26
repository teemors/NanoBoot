package com.teemor.repository.sys;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teemor.dao.sys.SysUserDao;
import com.teemor.entity.sys.SysUser;
import org.springframework.stereotype.Repository;

/**
 * 用户信息表(SysUser)表服务实现类
 *
 * @author easycode
 * @since 2024-01-15 16:56:45
 */
@Repository
public class SysUserRepository extends ServiceImpl<SysUserDao, SysUser> {

}

