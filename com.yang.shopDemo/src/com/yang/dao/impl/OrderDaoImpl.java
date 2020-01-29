package com.yang.dao.impl;

import com.mysql.jdbc.Statement;
import com.yang.dao.IOrderDao;
import com.yang.domain.Car;
import com.yang.domain.Order;
import com.yang.domain.OrderDetail;
import com.yang.handler.BeanHandler;
import com.yang.handler.BeanListHandler;
import com.yang.util.CRUDTemplate;
import com.yang.util.JdbcUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class OrderDaoImpl implements IOrderDao {
    @Override
    public List<Order> getList(Integer userId) {
        // 建立sql语句
        String sql = "select * from orders where userId = ?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Order.class), userId);
    }

    // 获取订单详情
    @Override
    public List<OrderDetail> getDetailList(Integer orderId) {
        // 建立sql语句
        String sql = "select * from order_detail where orderId = ?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(OrderDetail.class), orderId);
    }

    // 单个查询
    @Override
    public Order getOrder(Integer userId, Integer id) {
        // 建立sql语句
        String sql = "select * from orders where userId = ? and id = ?";
        return CRUDTemplate.executeQuery(sql, new BeanHandler<>(Order.class), userId, id);
    }

    // 删除,需要删除订单以及订单详情，数据库一致性没需要开始事务
    @Override
    public boolean deleteOrder(Integer id) {
        String sql = "delete from orders where id = ?";
        String sql2 = "delete from order_detail where orderId = ?";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = null;
        boolean flag = true;
        try {
            assert conn != null;
            // 设置数据库不自动提交
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            // 预执行
            ps.executeUpdate();
            ps = conn.prepareStatement(sql2);
            ps.setInt(1, id);
            ps.executeUpdate();
            // 事务提交
            conn.commit();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
            try {
                // 删除失败，数据库回滚
                conn.rollback();
            } catch (Exception err) {
                err.printStackTrace();
            }
        } finally {
            JdbcUtil.close(conn, ps, null);
        }
        return flag;

    }

    // 增加新的订单
    @Override
    public Integer addOrder(Order order, List<Car> carList) {
        // 执行语句
        String sql = "insert into orders(userId, price) values(?,?)";
        String sql2 = "insert into order_detail(orderId, productId, price) values(?,?,?)";
        String sql3 = "delete from car where id = ?";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int orderId = 0;
        try {
            assert conn != null;
            // 设置不要自动提交
            conn.setAutoCommit(false);
            // 生成订单
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getUserId());
            ps.setFloat(2, order.getPrice());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                // 获取生成订单的id
                int id = rs.getInt(1);
                orderId = id;
                for(Car car : carList){

                    // 将产品插入订单详情
                    ps = conn.prepareStatement(sql2);
                    ps.setInt(1, id);
                    ps.setInt(2, car.getProductId());
                    ps.setFloat(3, car.getPrice());
                    ps.executeUpdate();
                    // 从购物车中删除
                    ps = conn.prepareStatement(sql3);
                    ps.setInt(1, car.getId());
                    ps.executeUpdate();

                }
            }
            // 提交事务
            conn.commit();

        } catch (Exception e) {
            orderId = 0;
            e.printStackTrace();
            try {
                // 事务回滚
                conn.rollback();
            } catch (Exception err) {
                err.printStackTrace();
            }
        } finally {
            // 关闭资源
            JdbcUtil.close(conn, ps, rs);
        }
        return orderId;
    }

    @Override
    public boolean updateOrder(Integer id, Order order) {
        // 只更新付款状态
        String sql = "update orders set status = ? where id = ?";
        int result = CRUDTemplate.executeUpdate(sql, order.getStatus(), id);
        return result == 1;
    }
}
