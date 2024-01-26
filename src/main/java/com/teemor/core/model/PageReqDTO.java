package com.teemor.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 分页请求公共参数
 * @author: Gary.Jin
 * @create: 2021-07-14 16:14
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageReqDTO implements Serializable {

    private static final long serialVersionUID = -4086358648737665089L;
    /**
     * 页码 大于等于1
     */
//    @Min(PaginationHelper.DEF_PAGE_NUM)
    private Integer page;
    /**
     * 每页大小
     */
//    @Max(PaginationHelper.PER_PAGE_MAX)
//    @Min(PaginationHelper.PER_PAGE_MIN)
    private Integer perPage;
    /**
     * 排序字段，需存在于 orderByList 之中
     */
    private String sortBy;
    /**
     * 排序，asc or desc
     */
    @Pattern(regexp = "(ASC|DESC|asc|desc)")
    private String direction;

    public static final transient String DESC = "DESC";

    public List<String> getSortByList() {
        return new ArrayList<>();
    }
}
