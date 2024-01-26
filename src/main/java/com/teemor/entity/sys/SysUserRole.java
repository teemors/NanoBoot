package com.teemor.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户和角色关联表(SysUserRole)表实体类
 *
 * @author easycode
 * @since 2024-01-16 11:08:16
 */
@Getter
@Setter
@TableName("sys_user_role")
public class SysUserRole {


    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Long roleId;

}

