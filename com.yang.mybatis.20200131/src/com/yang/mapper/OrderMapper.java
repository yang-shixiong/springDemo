package com.yang.mapper;

import com.yang.domain.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    // 插入一条order
    void insertOrder(Order order);

    // 插入关系表
    void insertRelation(@Param("userId") Integer userId, @Param("orderID") Integer orderId);

    // 获取所有订单
    List<Order> getAllOrder();

    // 获取单个订单
    Order getOrderById(Integer id);
}
