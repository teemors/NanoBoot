package com.teemor.repository.sys;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.teemor.dao.sys.SysRoleMenuDao;
import com.teemor.entity.sys.SysMenu;
import com.teemor.entity.sys.SysRoleMenu;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色和菜单关联表(SysRoleMenu)表服务实现类
 *
 * @author easycode
 * @since 2024-01-16 11:08:16
 */
@Repository
public class SysRoleMenuRepository extends ServiceImpl<SysRoleMenuDao, SysRoleMenu> {

    @Resource
    private SysMenuRepository sysMenuRepository;

    public Set<String> selectMenuListByUserId(Set<String> roles) {
        LambdaQueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<SysRoleMenu>().lambda().in(SysRoleMenu::getRoleId, roles);

        List<SysRoleMenu> roleMenus = this.list(queryWrapper);
        if (CollUtil.isEmpty(roleMenus)) {
            return Collections.emptySet();
        }
        Set<Long> menuIds = roleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toSet());

        List<SysMenu> sysMenus = sysMenuRepository.list(new QueryWrapper<SysMenu>().lambda()
                .in(SysMenu::getMenuId, menuIds)
                .isNotNull(SysMenu::getPerms));

        return sysMenus.stream().map(SysMenu::getPerms).collect(Collectors.toSet());
    }
}

