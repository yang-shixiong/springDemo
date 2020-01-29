package com.yang.service;

import com.yang.domain.Car;
import com.yang.domain.Order;
import com.yang.domain.OrderDetail;

import java.util.List;

public interface IOrderService {

    // 获取订单信息
    List<Order> getList(Integer userId);

    // 获取单个订单信息
    Order getOrder(Integer userId, Integer id);

    // 获取订单详情
    List<OrderDetail> getDetail(Integer id);

    // 删除订单
    boolean deleteOrder(Integer id);

    // 添加订单
    Integer addOrder(Order order, List<Car> carList);

    // 更新订单
    boolean updateOrder(Integer id, Order order);
}
