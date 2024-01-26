package com.teemor.core.model;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 分页响应实体
 *
 * @author lujing
 * @since 0.0.28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RPage<T> implements Serializable {

    private static final Long DEFAULT_PER_PAGE = 10L;
    private static final Long DEFAULT_PAGE = 1L;
    /**
     * 总条数
     */
    private Long total = 0L;

    /**
     * 每页显示条数
     */
    private Long size = DEFAULT_PER_PAGE;

    /**
     * 当前页
     */
    private Long current = DEFAULT_PAGE;

    /**
     * 分页对象记录列表
     */
    private List<T> data = Collections.emptyList();

    public RPage(IPage<T> page) {
        if (page == null) {
            return;
        }
        this.total = page.getTotal();
        this.current = page.getCurrent();
        this.size = page.getSize();
        this.data = page.getRecords();
    }


    public RPage(IPage<?> page, List<T> datas) {
        if (page == null) {
            return;
        }
        this.total = page.getTotal();
        this.current = page.getCurrent();
        this.size = page.getSize();
        this.data = datas;
    }


}
