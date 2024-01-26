package com.teemor.repository.sys;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teemor.dao.sys.SysRoleDeptDao;
import com.teemor.entity.sys.SysRoleDept;
import org.springframework.stereotype.Repository;

/**
 * 角色和部门关联表(SysRoleDept)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:16
 */
@Repository
public class SysRoleDeptRepository extends ServiceImpl<SysRoleDeptDao, SysRoleDept> {

}

