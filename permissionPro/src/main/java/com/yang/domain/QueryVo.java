package com.yang.domain;

/**
 * 这个就是我们接受参数的一个类
 */
public class QueryVo {
    // 页码
    private Integer page;
    // 每页条数
    private Integer rows;
    // 关键词
    private String keyword;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
