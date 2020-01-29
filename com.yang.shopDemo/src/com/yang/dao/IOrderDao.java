package com.yang.dao;

import com.yang.domain.Car;
import com.yang.domain.Order;
import com.yang.domain.OrderDetail;

import java.util.List;

public interface IOrderDao {

    // 获取订单信息
    List<Order> getList(Integer userId);

    // 获取订单详情
    List<OrderDetail> getDetailList(Integer orderId);

    // 获取单个订单信息
    Order getOrder(Integer userId, Integer id);

    // 删除订单
    boolean deleteOrder(Integer id);

    // 添加订单
    Integer addOrder(Order order, List<Car> carList);

    // 更新订单
    boolean updateOrder(Integer id, Order order);

}
