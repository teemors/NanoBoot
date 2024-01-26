package com.teemor.core.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @description: 基础 model
 * @author: Gary.Jin
 * @create: 2021-07-14 09:54
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseDO<T extends Model<?>> extends Model<T> {
    public static final String ID = "id";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String DELETED = "deleted";

    private static final long serialVersionUID = -449312899712423277L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableLogic
    @TableField("deleted")
    private Boolean deleted;

    @Override
    public Serializable pkVal() {
        return this.id;
    }
}
