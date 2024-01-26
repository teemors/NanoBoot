package com.teemor.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 通知公告表(SysNotice)表实体类
 *
 * @author easycode
 * @since 2024-01-16 11:08:15
 */
@Getter
@Setter
@TableName("sys_notice")
public class SysNotice {


    /**
     * 公告ID
     */
    @TableId(value = "notice_id", type = IdType.AUTO)
    private Integer noticeId;

    /**
     * 公告标题
     */
    @TableField("notice_title")
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    @TableField("notice_type")
    private String noticeType;

    /**
     * 公告内容
     */
    @TableField("notice_content")
    private Object noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    @TableField("status")
    private String status;

    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

}

