package com.yang.service.impl;

import com.yang.dao.IOrderDao;
import com.yang.domain.Car;
import com.yang.domain.Order;
import com.yang.domain.OrderDetail;
import com.yang.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    IOrderDao iOrderDao;

    @Override
    public List<Order> getList(Integer userId) {
        return iOrderDao.getList(userId);
    }

    @Override
    public Order getOrder(Integer userId, Integer id) {
        return iOrderDao.getOrder(userId, id);
    }

    @Override
    public List<OrderDetail> getDetail(Integer id) {
        return iOrderDao.getDetailList(id);
    }

    @Override
    public boolean deleteOrder(Integer id) {
        return iOrderDao.deleteOrder(id);
    }

    @Override
    public Integer addOrder(Order order, List<Car> carList) {
        return iOrderDao.addOrder(order, carList);
    }

    @Override
    public boolean updateOrder(Integer id, Order order) {
        return iOrderDao.updateOrder(id, order);
    }
}
