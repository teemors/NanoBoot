package com.teemor.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色和菜单关联表(SysRoleMenu)表实体类
 *
 * @author easycode
 * @since 2024-01-16 11:08:16
 */
@Getter
@Setter
@TableName("sys_role_menu")
public class SysRoleMenu {


    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 菜单ID
     */
    @TableField(value = "menu_id")
    private Long menuId;

}

