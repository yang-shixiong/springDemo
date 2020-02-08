package com.yang.domain;


import java.util.ArrayList;
import java.util.List;

/**
 * 封装结果类
 */
public class PageListRes {

    private Long total;
    private List<?> rows = new ArrayList<>();

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
