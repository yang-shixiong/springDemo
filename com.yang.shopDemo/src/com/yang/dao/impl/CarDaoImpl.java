package com.yang.dao.impl;

import com.yang.dao.ICarDao;
import com.yang.domain.Car;
import com.yang.handler.BeanHandler;
import com.yang.handler.BeanListHandler;
import com.yang.util.CRUDTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDaoImpl implements ICarDao {
    // 查询购物车列表
    @Override
    public List<Car> getList(Integer userId) {
        String sql = "select * from car where userId = ?";
        return CRUDTemplate.executeQuery(sql, new BeanListHandler<>(Car.class), userId);
    }

    // 添加至购物车
    @Override
    public boolean addCar(Car car) {
        String sql = "insert into car(userId, productId,  price) values(?, ?, ?)";
        int result = CRUDTemplate.executeUpdate(sql, car.getUserId(), car.getProductId(), car.getPrice());
        return result == 1;
    }

    // 从购物车中删除
    @Override
    public boolean deleteCar(Integer id) {
        String sql = "delete from car where id = ?";
        int result = CRUDTemplate.executeUpdate(sql, id);
        return result == 1;
    }

    // 根据userId以及productId判断购物车中是否存在
    @Override
    public boolean checkCar(Integer userId, Integer productId) {
        String sql = "select * from car where userId = ? and productId = ?";
        Car car = CRUDTemplate.executeQuery(sql, new BeanHandler<>(Car.class), userId, productId);
        return car != null;
    }


}
