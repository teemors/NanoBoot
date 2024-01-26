package com.teemor.repository.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teemor.dao.sys.SysUserRoleDao;
import com.teemor.entity.sys.SysUserRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户和角色关联表(SysUserRole)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:16
 */
@Repository
public class SysUserRoleRepository extends ServiceImpl<SysUserRoleDao, SysUserRole> {

    public Set<String> selectRoleListByUserId(Long userId) {
        LambdaQueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<SysUserRole>().lambda().eq(SysUserRole::getUserId, userId);

        List<SysUserRole> userRoles = this.list(queryWrapper);

        return userRoles.stream().map(SysUserRole::getRoleId).map(String::valueOf).collect(Collectors.toSet());
    }
}

