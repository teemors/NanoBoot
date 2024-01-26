package com.teemor.repository.sys;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teemor.dao.sys.SysUserPostDao;
import com.teemor.entity.sys.SysUserPost;
import org.springframework.stereotype.Repository;

/**
 * 用户与岗位关联表(SysUserPost)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:16
 */
@Repository
public class SysUserPostRepository extends ServiceImpl<SysUserPostDao, SysUserPost> {

}

