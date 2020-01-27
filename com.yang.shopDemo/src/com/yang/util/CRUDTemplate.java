package com.yang.util;

import com.yang.handler.IResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 为了方便学习，自己封装一个JDBC的增删改查的工具类，等同于dbUtil
 */
public class CRUDTemplate {
    private static Connection conn = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;


    // 这个执行增删改操作，params必须与sql语句中的位置一一对应
    public static int executeUpdate(String sql, Object... params) {

        try {
            conn = JdbcUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql);
            // 使用prepareStatement的方法为指定位置添加传入参数
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 最终关闭资源
            JdbcUtil.close(conn, ps, rs);
        }
        // 如果有报错，则返回0
        return 0;
    }

    // 执行查询操作
    public static <T> T executeQuery(String sql, IResultSetHandler<T> rh, Object... params) {
        try {
            conn = JdbcUtil.getConn();
            assert conn != null;
            // 预执行sql语句
            ps = conn.prepareStatement(sql);
            // 将参数传入预执行语句
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            // 执行sql语句并获取结果集
            rs = ps.executeQuery();
            // 调用传入的结果集处理方法，进行处理
            return rh.handle(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(conn, ps, rs);
        }
        return null;
    }
}
