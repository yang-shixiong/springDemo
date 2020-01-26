package com.yang.handler;

import java.sql.ResultSet;

/**
 * 定义结果集的处理函数
 *
 * @param <T> 范型，指定处理类型
 */
public interface IResultSetHandler<T> {
    T handle(ResultSet rs) throws Exception;
}
