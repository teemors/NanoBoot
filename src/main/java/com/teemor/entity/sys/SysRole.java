package com.teemor.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 角色信息表(SysRole)表实体类
 *
 * @author easycode
 * @since 2024-01-15 15:37:08
 */
@Getter
@Setter
@TableName("sys_role")
public class SysRole {


    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 角色权限字符串
     */
    @TableField("role_key")
    private String roleKey;

    /**
     * 显示顺序
     */
    @TableField("role_sort")
    private Integer roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    @TableField("data_scope")
    private String dataScope;

    /**
     * 菜单树选择项是否关联显示
     */
    @TableField("menu_check_strictly")
    private Integer menuCheckStrictly;

    /**
     * 部门树选择项是否关联显示
     */
    @TableField("dept_check_strictly")
    private Integer deptCheckStrictly;

    /**
     * 角色状态（0正常 1停用）
     */
    @TableField("status")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableField("del_flag")
    private String delFlag;

    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

}

