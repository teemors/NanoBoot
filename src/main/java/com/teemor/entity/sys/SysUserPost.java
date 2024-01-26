package com.teemor.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户与岗位关联表(SysUserPost)表实体类
 *
 * @author easycode
 * @since 2024-01-16 11:08:16
 */
@Getter
@Setter
@TableName("sys_user_post")
public class SysUserPost {


    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 岗位ID
     */
    @TableField(value = "post_id")
    private Long postId;

}

